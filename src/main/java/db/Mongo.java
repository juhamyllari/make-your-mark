package db;

import IO.IO;
import bookmark.Bookmark;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import bookmark.BookmarkContainer;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCursor;
import java.util.ArrayList;
import org.bson.Document;
import java.util.Arrays;

public class Mongo {
    
    MongoClientURI uri = new MongoClientURI("");
    MongoClient client = new MongoClient(uri);
    MongoDatabase db = client.getDatabase("mym");
    MongoCollection<Document> collection = db.getCollection("bookmarks");
    
    public String getString() {
        int length = (int) collection.countDocuments();
        int index = 1;
        String json = "{\"bookmarks\":[";
        MongoCursor<Document> cursor = collection.find().sort(new Document("_id", -1)).iterator();
        try {
            while (cursor.hasNext()) {
                if (index == length) {
                   json += cursor.next().toJson(); 
                } else {
                    json += cursor.next().toJson()+",";
                }
                index++;
            }
        } finally {
            cursor.close();
        }
        json += "], \"index\":0}";
        return json;
    }
    
    public BookmarkContainer createNew(BookmarkContainer container, IO io) {
        io.print("Provide the information, please (do not enter any text if you wish to leave the field blank)");
        Bookmark newB = Bookmark.createBookmark();
        String title = io.nextLine("Title:");
        String author = io.nextLine("Author:");
        String url = io.nextLine("URL:");
        String description = io.nextLine("Description:");
        String isbn = io.nextLine("ISBN:");
        ArrayList<String> tags = addList(io, "Give tags one by one for as long as you want; input an empty line to stop.");
        String comment = io.nextLine("Type a comment if you want to leave one.");
        ArrayList<String> pre = addList(io, "Give as many prerequisite courses as you want.");
        ArrayList<String> related = addList(io, "Give as many related courses as you want.");
        
        newB.setField("prerequisite courses", pre);
        newB.setField("comment", comment);
        newB.setField("author", author);
        newB.setField("isbn", isbn);
        newB.setField("title", title);
        newB.setField("related courses", related);
        newB.setField("tags", tags);
        newB.setField("url", url);
        newB.setField("description", description);
        
        container.add(newB);
        io.print("Bookmark created.");
        
        Document newDoc = new Document("fields",
                Arrays.asList(
                        new Document("name", "title")
                        .append("data", Arrays.asList(title))
                        .append("isSingleField", true),
                        new Document("name", "author")
                        .append("data", Arrays.asList(author))
                        .append("isSingleField", true),
                        new Document("name", "tags")
                        .append("data", tags)
                        .append("isSingleField", true),
                        new Document("name", "description")
                        .append("data", Arrays.asList(description))
                        .append("isSingleField", true),
                        new Document("name", "prerequisite courses")
                        .append("data", pre)
                        .append("isSingleField", true),
                        new Document("name", "related courses")
                        .append("data", related)
                        .append("isSingleField", true),
                        new Document("name", "comment")
                        .append("data", Arrays.asList(comment))
                        .append("isSingleField", true),
                        new Document("name", "isbn")
                        .append("data", Arrays.asList(isbn))
                        .append("isSingleField", true)
                )
        ).append("read", false);
        collection.insertOne(newDoc);
        return container;
    }
    
    private ArrayList<String> addList(IO io, String printLine) {
        ArrayList<String> list = new ArrayList<>();
        while (true) {
            String newO = io.nextLine(printLine);
            if (newO.trim().equals("")) {
                break;
            }
            list.add(newO);
        }    
        return list;
    }
 
}