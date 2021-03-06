# User Manual

## Downloading and starting the application

Download the jar-file [sprint 4 release](https://github.com/juhamyllari/make-your-mark/releases/tag/sprintti4)

The application is started with the command

```
java -jar makeyourmark.jar
```

## Using the program

### Overview

The application provides tools for managing reading tips. You can use the application to add and modify tips, browse them and search for them. You may choose to save your tips when exiting the program, in that case a file "saved_bookmarks.txt" is created and you can access your bookmarks later.

### Adding bookmarks

Type "new" in the main menu to add a reading tip. Then the interface prompts you to add properties for the reading tip. You may choose to skip fields and some fields can have multiple parametres.

You can also use the command "isbn" to add a bookmark by giving only the ISBN of the book. The ISBN will be searched from the Google Books database. If a book with the entered ISBN is found then a bookmark is created and its fields are filled automatically.

### Viewing and commenting bookmarks

By typing "browse" in the main menu you can browse the added bookmarks if there are any. In the browse menu you can scroll through the bookmarks, show more information of them, mark them as read, remove them, edit them and search for them. 

You can also add comments to the selected bookmark by typing "comment". Comments and the date when they were created are displayed when a commented bookmark is selected.

### Searching bookmarks

There are two options for searching bookmarks: searching by tags and searching from title, author and description.

The command "tagsearch" allows you to enter one or multiple tags. Bookmarks that match a tag will be shown afterwards if there are any.

The command "search" prompts you to enter a single keyword. Bookmarks that contain the keyword in their title, author or description fields will be shown.

### Marking bookmarks as read

By giving the command "mark" you can set a bookmark as read. Read bookmarks aren't shown by default when browsing, this can be changed with commands "show" and "hide". If read bookmarks are shown the date when they were marked as read is displayed.

### Deleting and editing bookmarks

The command "remove" removes the selected bookmark. After giving the command you are prompted to confirm the removal.

The application has two options for editing a bookmark: editing its single field or all fields that have only one parameter.

The command "edit" allows you to modify a single field. It prompts you to pick a field and to provide new content for it. 

The command "editall" goes through all one-parameter fields in order and allows you to edit the ones you desire. After going through all the fields the changes are displayed and confirmation is asked.
