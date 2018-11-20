
import IO.*;
import bookmark.BookmarkContainer;
import bookmark.Kirja;
import java.awt.Desktop;
import java.util.ArrayList;

/*
 * This Java source file was generated by the Gradle 'init' task.
 */
public class App {

    public static void main(String[] args) {       
        IO io = new ConsoleIO();
        run(io);
    }
    
    public static void run(IO io) {
        BookmarkContainer container = new BookmarkContainer();
        while(true){
            String command = io.nextLine("Type \"new\" to create a bookmark or \"browse\" to browse the bookmarks");
            if(command.equals("new")){
                createNew(container, io);
            }else if(command.equals("browse")){
                if(container.size()==0){
                    io.print("No bookmarks");
                }else{
                    browse(container, io);
                }
            }else if(command.equals("exit")){
                break;
            }else{
                io.print("No bookmarks");
            }
        } 
    }
    
    private static void browse(BookmarkContainer container, IO io){
        while(true){
            io.print(container.getCurrent().getOtsikko()+(container.getIndex()+1)+"/"+container.size());
            String command = io.nextLine("Type \"next\" to see next bookmark, \"show\" to show more information on the current one or \"exit\" to stop browsing bookmarks.");
            if(command.equals("next")){
                container.getNext();
            }else if(command.equals("show")){
                io.print(container.getCurrent().toString());
            }else if(command.equals("exit")){
                break;
            }else{
                io.print("Invalid command.");
            }
        }
    }
    
    private static void createNew(BookmarkContainer container, IO io){
        io.print("Provide the information, please.");
        String type = io.nextLine("What kind of a bookmark would you like to save? (book)");
        ArrayList<String> types = new ArrayList<>();
        types.add("book");
        while(!types.contains(type)){
            type = io.nextLine("Invalid type.");
        }
        String title = io.nextLine("Title:");
        ArrayList<String> tags = new ArrayList<>();
        while(true){
            String newTag = io.nextLine("Give tags one by one for as long as you want; type nothing to stop.");
            if(newTag.trim().equals("")) break;
            tags.add(newTag);
        }
        String comment = io.nextLine("Type a comment if you want to leave one.");
        ArrayList<String> preC = new ArrayList<>();
        while(true){
            String newC = io.nextLine("Give as many prerequisite courses as you want.");
            if(newC.trim().equals("")) break;
            preC.add(newC);
        }
        ArrayList<String> relC = new ArrayList<>();
        while(true){
            String newC = io.nextLine("Give as many related courses as you want.");
            if(newC.trim().equals("")) break;
            relC.add(newC);
        }
        
        if(type.equals("book")){
            String author = io.nextLine("Author:");
            String isbn = io.nextLine("ISBN:");
            Kirja newB = new Kirja(title, author, isbn);
            newB.setEsitietokurssit(preC);
            newB.setKommentti(comment);
            newB.setLiittyvatKurssit(relC);
            newB.setTitle(title);
            newB.setTagit(tags);
            container.add(newB);
            io.print("Bookmark created");
        }
    }
}
