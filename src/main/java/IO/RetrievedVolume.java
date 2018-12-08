package IO;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.stream.Collectors;

public class RetrievedVolume {

    private String title;
    private String author;

    public static RetrievedVolume build(String isbn) throws MalformedURLException, IOException {
        URL url = createQuery(isbn);
        String book = getBook(url);
        JsonElement info = getVolumeInfo(book);
        RetrievedVolume volume = new RetrievedVolume();
        volume.title = getTitle(info);
        volume.author = getAuthor(info);
        return volume;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }
    
    

    private RetrievedVolume() {
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
    
    private static URL createQuery(String isbn) throws MalformedURLException {
        String url = "https://www.googleapis.com/books/v1/volumes?q=isbn:" + isbn;
        return new URL(url);
    }

    private static String getBook(URL query) throws IOException {
        BufferedReader in = new BufferedReader(
                new InputStreamReader(query.openStream()));
        String output = in.lines().collect(Collectors.joining());
        in.close();
        return output;
    }

    private static JsonElement getVolumeInfo(String book) {
        JsonParser parser = new JsonParser();
        JsonObject parsedData = parser.parse(book).getAsJsonObject();
        JsonElement info = parsedData
                .getAsJsonArray("items")
                .get(0)
                .getAsJsonObject()
                .get("volumeInfo");
        return info;
    }
    
    private static String getTitle(JsonElement info) {
        return info.getAsJsonObject()
                .get("title")
                .getAsString();
    }
    
    private static String getAuthor(JsonElement info) {
        return info.getAsJsonObject()
                .get("authors")
                .getAsJsonArray()
                .get(0)
                .getAsString();
    }

}
