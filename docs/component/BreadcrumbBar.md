## Components - breadcrumb bars

<img src="https://raw.githubusercontent.com/kirill-grouchnikov/aurora/icicle/docs/images/component/walkthrough/breadcrumb-bar.png" width="646" border=0/>

Breadcrumb bars facilitate quick navigation of multi-level hierarchies, such as file systems, XML documents or abstract syntax trees. This functionality is commonly found in many web sites and is the main navigation tool in the Windows Explorer application.

### Supplying content to breadcrumb bar

Unlike [command](Command.md) and other related content models (command strips, command panels, command popup menus), breadcrumb bar does not force ahead-of-time, exhaustive construction of the entire content at initialization time.

The `BreadcrumbBarContentProvider` is used by the breadcrumb component at runtime to ask the application code for the structure of the hierarchy being navigated. The two main methods are:

- `fun getPathChoices(item: T?): List<T>`
- `fun getLeaves(item: T): List<T>`

What are these two used for? Let's take another look at the screenshot of a sample app that is using the breadcrumb bar for navigating the local file system:

<img src="https://raw.githubusercontent.com/kirill-grouchnikov/aurora/icicle/docs/images/component/walkthrough/breadcrumb-bar.png" width="646" border=0/>

The `getPathChoices()` should return the list of paths that can be "explored" from the specified path. In the screenshot above these are the subfolders of the currently activated "JavaVirtualMachines" path.

The `getLeaves()` should return the list of leaf elements that correspond to the specified path. In the screenshot above these are the individual files (not subfolders) of the currently selected "bin" path.

In this particular case, here is a basic implementation of the content provider that exposes the structure of the local file system:

```kotlin
val fileSystemView = FileSystemView.getFileSystemView()
val breadcrumbBarContentProvider =
    object: BreadcrumbBarContentProvider<File>() {
        override fun getDisplayText(item: File?): String {
            if (item == null) {
                return ""
            }
            return fileSystemView.getSystemDisplayName(item)
                .let { name -> name.ifEmpty { item.absolutePath } }
        }

        override fun getIcon(item: File?): Painter? {
            return if (item?.isDirectory == true) folder_open_black_24dp() else null
        }

        override suspend fun getPathChoices(item: File?): List<File> {
            // If our item is null, get the file system roots. Otherwise, get all files under
            // this file item.
            val candidates =
                (if (item == null) fileSystemView.roots else item.listFiles())
                    ?: return emptyList()

            // Now filter out hidden ones and non-directories, map the rest to
            // what the content provider needs to return, and sort them by display name
            return candidates.filterNot { !it.isDirectory || fileSystemView.isHiddenFile(it) }
                .map { it }
                .sortedBy { getDisplayText(it).lowercase() }
        }

        override suspend fun getLeaves(item: File): List<File> {
            // Get all files under the file item, filter out hidden ones and
            // directory ones, map the rest to what the content provider needs to
            // return, and sort them by display name
            val candidates = item.listFiles() ?: return emptyList()
            return candidates
                .filterNot { it.isDirectory || fileSystemView.isHiddenFile(it) }
                .map { it }
                .sortedBy { getDisplayText(it).lowercase() }
        }
    }
```

Note that this particular implementation does not track dynamic changes to the underlying file system like adding, removing or renaming folders and files.

### Working with breadcrumb bar

The `AuroraBreadcrumbBar` is a composable that implements the breadcrumb bar. It is initialized with:

* a `List<Command>` obtained from `BreadcrumbBarContentModel` call with a passed `BreadcrumbBarContentProvider` that is used to dynamically populate the breadcrumb bar content as the user interacts with it
* a `BreadcrumbBarPresentationModel` that is used to configure the visual appearance of the content

For additional, dynamic reconfiguration of the breadcrumb bar content, manipulate the content of the command list to trigger the recomposition of the breadcrumb bar.

### Observing breadcrumb events

Let's go back to our app screenshot again:

<img src="https://raw.githubusercontent.com/kirill-grouchnikov/aurora/icicle/docs/images/component/walkthrough/breadcrumb-bar.png" width="646" border=0/>

The top part of the app is an `AuroraBreadcrumbBar` composable. The main content is the panel that shows the list of files in the currently selected path, implemented as a [command button panel](CommandPanel.md).

But how does a selection change in the breadcrumb bar (clicking any element of the path or selecting a new path) get reflected in this panel?

It is achieved by passing an `onItemSelected: (T) -> Unit` callback to the `BreadcrumbBarContentModel` function that "converts" a `BreadcrumbBarContentProvider` into a list of commands:

```kotlin
val onBreadcrumbItemSelected: (File) -> Unit = {
    scope.launch(Dispatchers.Default) {
        commandPanelContentModel.value = getCommandPanelContent(breadcrumbBarContentProvider, it)
        delay(150)
        breadcrumbBarHorizontalScrollState.animateScrollTo(
            breadcrumbBarHorizontalScrollState.maxValue)
    }
}

val breadcrumbBarContentModel = BreadcrumbBarContentModel(
    contentProvider = breadcrumbBarContentProvider,
    onItemSelected = onBreadcrumbItemSelected
)

private suspend fun getCommandPanelContent(
    contentProvider: BreadcrumbBarContentProvider<File>,
    selected: File
): CommandPanelContentModel {
    val leaves = contentProvider.getLeaves(selected)
    return CommandPanelContentModel(
        commandGroups = listOf(
            CommandGroup(
                title = null,
                leaves.map { leaf ->
                    Command(
                        text = contentProvider.getDisplayText(leaf),
                        action = {})
                }
            )
        )
    )
}
```

Let's take a look at what we're doing here:

- Create a `(File) -> Unit` callback and pass the currently selected folder to `getCommandPanelContent` along with our content provider.
- Pass this callback to the `BreadcrumbBarContentModel` function so that it gets wired to any user-initiated changes as the user interacts with the breadcrumb bar content.
- Call `BreadcrumbBarContentProvider.getLeaves()` off the UI thread (using `scope.launch(Dispatchers.Default)` in the callback) so that the UI remains responsive during this potentially blocking I/O operation.
- When the list of files is loaded, create a `CommandPanelContentModel` that maps each file in the currently selected folder to a command.


### Next

Continue to [box with highlights](BoxWithHighlights.md).
