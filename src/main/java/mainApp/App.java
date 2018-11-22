package mainApp;

import IO.*;
import bookmark.BookmarkContainer;
import bookmark.Bookmark;
import bookmark.FieldListBookmark;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

/*
 * This Java source file was generated by the Gradle 'init' task.
 */
public class App {

    public static void main(String[] args) {
        IO io = new ConsoleIO();
        run(io);
    }

    public static void run(IO io) {

        // samples option for cucumber testing
        BookmarkContainer container = new BookmarkContainer();
        while (true) {
            String command = io.nextLine("Type \"new\" to create a bookmark, \"browse\" to browse the bookmarks or \"exit\" to quit the application.");

            if (command.equals("new")) {
                createNew(container, io);
            } else if (command.equals("browse")) {
                if (container.size() == 0) {
                    io.print("No bookmarks");
                } else {
                    browse(container, io);
                }
            } else if (command.equals("exit")) {
                break;
            } else if (command.equals("samples")) {
                container.add(FieldListBookmark.createBook("Kalaopas", "Kimmo Kala", "8493-33"));
                container.add(FieldListBookmark.createBook("Reitittimet 1992-1996", "Koodi Kalevi", "43289-23432"));
            } else {
                io.print("Invalid command");
            }
        }
    }

    private static void browse(BookmarkContainer container, IO io) {
        while (true) {
            io.print(container.getCurrent().getStringField("title") + " " + (container.getIndex() + 1) + "/" + container.size());
            String command = io.nextLine("Type \"next\" to see the next bookmark, \"show\" to show more information on the current one, \"edit\" to edit the current one or \"exit\" to stop browsing bookmarks.");
            if (command.equals("next")) {
                container.getNext();
            } else if (command.equals("show")) {
                io.print(container.getCurrent().toString());
            } else if (command.equals("edit")) {
                edit(container.getCurrent(), io);
            } else if (command.equals("exit")) {
                break;
            } else {
                io.print("Invalid command.");
            }
        }
    }

    private static void edit(Bookmark bm, IO io) {
        List<String> allFields = bm.getFieldNames();
        String field = io.nextLine("Type which field to edit ("
                + allFields.stream().collect(Collectors.joining(", "))
                + ") or \"exit\" to stop editing.");

        if (field.equals("exit")) {
            return;
        }

        if (!allFields.contains(field)) {
            io.print("Invalid field.");
            edit(bm, io);
        }

        if (bm.fieldIsString(field)) {
            editStringField(bm, field, io);
        } else {
            editListField(bm, field, io);
        }

    }

    private static void editStringField(Bookmark bm, String field, IO io) {
        String newEntry = io.nextLine("Current "
                + field + ": "
                + bm.getStringField(field)
                + ". Set a new " + field
                + " or type the current title to remove it.");
        
        if (newEntry.equals(bm.getStringField(field))) {
            bm.setField(field, "");
            io.print(field + " removed.");
        } else if (newEntry.trim().equals("")) {
            io.print("No change made.");
        } else {
            bm.setField(field, newEntry);
        }
        
    }

    private static void editListField(Bookmark bm, String field, IO io) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

//    private static void edit(AbstractBookmark bm, IO io) {
//        String fields = setFields(bm);
//
//        while (true) {
//            String field = io.nextLine("Type which field to edit (" + fields + ") or \"exit\" to stop editing.");
//                
//            if (field.equals("title")) {
//                String title = io.nextLine("Current title: " + bm.getTitle() + ". Set a new title or type the current title to remove it.");
//                if (title.equals(bm.getTitle())) {
//                    bm.setTitle("");
//                    io.print("Title removed.");
//                } else if (!title.trim().equals("")) {
//                    bm.setTitle(title);
//                    io.print("New title set.");
//                } else {
//                    io.print("No change made.");
//                }
//            } else if (field.equals("comment")) {
//                String comment = io.nextLine("Current comment: " + bm.getComment() + ". Set a new comment or type the current comment to remove it.");
//                if (comment.equals(bm.getComment())) {
//                    bm.setComment("");
//                    io.print("Comment removed.");
//                } else if (!comment.trim().equals("")) {
//                    bm.setComment(comment);
//                    io.print("New comment set.");
//                } else {
//                    io.print("No change made.");
//                }
//            } else if (field.equals("tags")) {
//                List<String> tags = bm.getTags();
//                io.print(AbstractBookmark.attributeToString("Tags: ", AbstractBookmark.list(tags)));
//                String tag = io.nextLine("Type an existing tag to remove it or a new one to add it.");
//                addOrRemove(tags, tag, "Tag", io);
//            } else if (field.equals("prerequisites")) {
//                List<String> preC = bm.getPrerequisiteCourses();
//                io.print(AbstractBookmark.attributeToString("Prerequisite courses: ", AbstractBookmark.list(preC)));
//                String course = io.nextLine("Type an existing course to remove it or a new one to add it.");
//                addOrRemove(preC, course, "Course", io);               
//            } else if (field.equals("related")) {
//                List<String> relC = bm.getRelatedCourses();
//                io.print(AbstractBookmark.attributeToString("Related courses: ", AbstractBookmark.list(relC)));
//                String course = io.nextLine("Type an existing course to remove it or a new one to add it.");
//                addOrRemove(relC, course, "Course", io);
//            } else if(field.equals("exit")) {
//                break;
//            } else if (bm.getType().equals("Book")) {
////                editBook(field, io, bm);
//            } else {
//                io.print("Invalid field.");
//            }
//        }
//    }
//
//    private static String setFields(AbstractBookmark bm) {
//        String fields = "title, tags, prerequisites, related, comment";
//        if (bm.getType().equals("Book")) {
//            fields = "author, isbn, " + fields;
//        }
//        return fields;
//    }
//    private static void editBook(String field, IO io, AbstractBookmark bm) {
//        if (field.equals("author")) {
//            String author = io.nextLine("Current author: " + ((Book) bm).getAuthor() + ". Set a new author or type the current author to remove it.");
//            if (author.equals(((Book)bm).getAuthor())) {
//                ((Book)bm).setAuthor("");
//                io.print("Author removed.");
//            } else if (!author.trim().equals("")) {
//                ((Book)bm).setAuthor(author);
//                io.print("New author set.");
//            } else {
//                io.print("No change made.");
//            }
//        } else if (field.equals("isbn")) {
//            String isbn = io.nextLine("Current author: " + ((Book) bm).getIsbn() + ". Set a new ISBN or type the current ISBN to remove it.");
//            if (isbn.equals(((Book)bm).getIsbn())) {
//                ((Book)bm).setIsbn("");
//                io.print("ISBN removed.");
//            } else if (!isbn.trim().equals("")) {
//                ((Book)bm).setIsbn(isbn);
//                io.print("New ISBN set.");
//            } else {
//                io.print("No change made.");
//            }
//        }
//    }
//    private static void addOrRemove(List<String> list, String item, String field, IO io) {
//        if (list.contains(item)) {
//            for (Iterator<String> iter = list.listIterator(); iter.hasNext();) {
//                String a = iter.next();
//                if (a.equals(item)) {
//                    iter.remove();
//                    io.print(field + " removed.");
//                }
//            }
//        } else if (!item.trim().equals("")) {
//            list.add(item);
//            io.print("New " + field.toLowerCase() + " added.");
//        } else {
//            io.print("No change made.");
//        }
//    }

    private static void createNew(BookmarkContainer container, IO io) {
        io.print("Provide the information, please.");
        String type = io.nextLine("What kind of a bookmark would you like to save? (book) Type \"exit\" to return.");
        ArrayList<String> types = new ArrayList<>();
        types.add("book");
        types.add("exit");
        while (!types.contains(type)) {
            type = io.nextLine("Invalid type.");
        }
        if (type.equals("exit")) {
            return;
        }
        String title = io.nextLine("Title:");
        ArrayList<String> tags = new ArrayList<>();
        while (true) {
            String newTag = io.nextLine("Give tags one by one for as long as you want; input an empty line to stop.");
            if (newTag.trim().equals("")) {
                break;
            }
            tags.add(newTag);
        }
        String comment = io.nextLine("Type a comment if you want to leave one.");
        ArrayList<String> preC = new ArrayList<>();
        while (true) {
            String newC = io.nextLine("Give as many prerequisite courses as you want.");
            if (newC.trim().equals("")) {
                break;
            }
            preC.add(newC);
        }
        ArrayList<String> relC = new ArrayList<>();
        while (true) {
            String newC = io.nextLine("Give as many related courses as you want.");
            if (newC.trim().equals("")) {
                break;
            }
            relC.add(newC);
        }

        if (type.equals("book")) {
            String author = io.nextLine("Author:");
            String isbn = io.nextLine("ISBN:");
            Bookmark newB = FieldListBookmark.createBook(title, author, isbn);
            newB.setField("prerequisite courses", preC);
            newB.setField("comment", comment);
            newB.setField("related courses", relC);
            newB.setField("tags", tags);
            container.add(newB);
            io.print("Bookmark created.");
        }
    }

}
