/*
 * Copyright 2020-2026 Aurora, Kirill Grouchnikov
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.pushingpixels.aurora.demo.util

// Implementation based on https://github.com/jaketodaro/discrete-interval-tree/tree/master/src
// made available under MIT license, with bug fixes and extensions

class Interval(var start: Int, var end: Int) {
    fun contains(value: Int) = (value >= this.start) && (value <= this.end)
    fun intersectsWith(otherStart: Int, otherEnd: Int) =
        !((otherEnd < this.start) || (otherStart > this.end))
}

class DiscreteIntervalNode(start: Int, end: Int, var parent: DiscreteIntervalNode?) {
    var interval = Interval(start, end)
    var left: DiscreteIntervalNode? = null
    var right: DiscreteIntervalNode? = null

    fun contains(start: Int, end: Int): Boolean {
        if (this.interval.contains(start) && this.interval.contains(end)) {
            return true
        }

        if ((this.left != null) && (end < this.interval.start)) {
            return this.left!!.contains(start, end)
        }

        if ((this.right != null) && (start < this.interval.end)) {
            return this.right!!.contains(start, end)
        }

        return false
    }

    fun intersectsWith(start: Int, end: Int): Boolean {
        if (this.interval.intersectsWith(start, end)) {
            return true
        }

        if ((this.left != null) && (end < this.interval.start)) {
            return this.left!!.intersectsWith(start, end)
        }

        if ((this.right != null) && (start < this.interval.end)) {
            return this.right!!.intersectsWith(start, end)
        }

        return false
    }

    val isLeaf: Boolean
        get() = (this.left == null) && (this.right == null)
    val hasOneChild: Boolean
        get() = ((this.left != null) && (this.right == null)) || ((this.left == null) && (this.right != null))
    val leftMostLeaf: DiscreteIntervalNode
        get() = this.left?.leftMostLeaf ?: this
    val rightMostLeaf: DiscreteIntervalNode
        get() = this.right?.rightMostLeaf ?: this

    fun absorbLeft(node: DiscreteIntervalNode): DiscreteIntervalNode {
        this.interval.start = node.interval.start
        this.left = node.left

        this.left?.parent = this
        return this
    }

    fun absorbRight(node: DiscreteIntervalNode): DiscreteIntervalNode {
        this.interval.end = node.interval.end
        this.right = node.right

        this.right?.parent = this
        return this
    }
}

class DiscreteIntervalTree {
    var root: DiscreteIntervalNode? = null

    fun contains(start: Int, end: Int): Boolean {
        return this.root?.contains(start, end) ?: false
    }

    fun intersectsWith(start: Int, end: Int): Boolean {
        return this.root?.intersectsWith(start, end) ?: false
    }

    fun getIntervals(): List<DiscreteIntervalNode> {
        val result = mutableListOf<DiscreteIntervalNode>()

        fun DiscreteIntervalNode.visit(list: MutableList<DiscreteIntervalNode>) {
            this.left?.visit(list)
            list.add(this)
            this.right?.visit(list)
        }

        this.root?.visit(result)

        return result
    }

    fun add(start: Int, end: Int): DiscreteIntervalNode {
        if (this.root == null) {
            this.root = DiscreteIntervalNode(start = start, end = end, parent = null)
            return this.root!!
        }
        return this.addValue(start).absorbRight(this.addValue(end))
    }

    fun addValue(value: Int): DiscreteIntervalNode {
        var curr: DiscreteIntervalNode = this.root!!
        var valueNode: DiscreteIntervalNode? = null

        while (valueNode == null) {
            if (value < curr.interval.start - 1) {
                // value is somewhere to the left

                if (curr.left != null) {
                    curr = curr.left!!
                } else {
                    curr.left = DiscreteIntervalNode(value, value, curr)
                    valueNode = curr.left!!
                }
            } else if (value == curr.interval.start - 1) {
                // value borders left

                if ((curr.left != null) && (value == curr.left!!.interval.end + 1)) {
                    // absorb left child
                    valueNode = curr.absorbLeft(curr.left!!)
                } else {
                    // just extend 1 to the left
                    curr.interval.start = value
                    valueNode = curr;
                }
            } else if (curr.interval.contains(value)) {
                // value is contained in existing interval
                valueNode = curr
            } else if (value == curr.interval.end + 1) {
                // value borders right

                if ((curr.right != null) && (value == curr.right!!.interval.start - 1)) {
                    // absorb right child
                    valueNode = curr.absorbRight(curr.right!!)
                } else {
                    // just extend 1 to the right
                    curr.interval.end = value
                    valueNode = curr
                }
            } else if (value > curr.interval.end + 1) {
                // value is somewhere to the right
                if (curr.right != null) {
                    curr = curr.right!!
                } else {
                    curr.right = DiscreteIntervalNode(value, value, curr)
                    valueNode = curr.right!!
                }
            }
        }

        return valueNode
    }

    fun removeNode(node: DiscreteIntervalNode): DiscreteIntervalNode? {
        if (node.isLeaf) {
            if (node == this.root) {
                this.root = null
            } else {
                if (node == node.parent!!.left) {
                    node.parent!!.left = null
                } else if (node == node.parent!!.right) {
                    node.parent!!.right = null
                }
            }

            return null
        } else if (node.hasOneChild) {
            val child = node.left ?: node.right!!
            node.interval = child.interval
            node.left = child.left
            node.right = child.right

            if (child.left != null) {
                child.left!!.parent = node
            }
            if (child.right != null) {
                child.right!!.parent = node
            }

            return node
        } else {
            val replacement = node.leftMostLeaf
            node.interval = replacement.interval
            this.removeNode(replacement)
            return node
        }
    }

    fun remove(start: Int, end: Int): Boolean {
        var curr = this.root

        while (curr != null) {
            if (start > curr.interval.start && end < curr.interval.end) {
                // split node
                val prevEnd = curr.interval.end
                curr.interval.end = start - 1
                this.add(end + 1, prevEnd)
                return true;
            } else if (end < curr.interval.start) {
                // look left
                curr = curr.left
            } else if (start > curr.interval.end) {
                // look right
                curr = curr.right
            } else if (start <= curr.interval.start && end >= curr.interval.end) {
                // remove entire node
                curr = this.removeNode(curr)
                //curr = curr.right;
            } else if (start <= curr.interval.start && end < curr.interval.end) {
                // prune left
                curr.interval.start = end + 1
                curr = curr.left
            } else if (start > curr.interval.start && end >= curr.interval.end) {
                // prune right
                curr.interval.end = start - 1
                curr = curr.right
            }
        }

        return false
    }
}

fun main() {
    val tree = DiscreteIntervalTree()

    tree.add(1, 5)
    tree.add(7, 10)
    tree.dump()

    println(tree.intersectsWith(0, 20))
    println(tree.intersectsWith(1, 1))
    println(tree.intersectsWith(0, 0))
    println(tree.intersectsWith(2, 4))
    println(tree.intersectsWith(2, 8))
    println(tree.intersectsWith(6, 6))

    tree.add(6, 6)
    tree.dump()

    tree.remove(1, 100)
    tree.dump()
}

fun DiscreteIntervalTree.dump() {
    val intervals = this.getIntervals()
    val content = intervals.joinToString { interval -> "[${interval.interval.start}, ${interval.interval.end}]" }
    println("Dump: $content")
}