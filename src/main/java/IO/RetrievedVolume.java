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
    private String url;
    private String description;

    public static RetrievedVolume build(String isbn) throws MalformedURLException, IOException {
        URL url = createQuery(isbn);
        String book = getBook(url);
        JsonElement info = getItemInfo(book);
        RetrievedVolume volume = new RetrievedVolume();
        volume.title = parseTitle(info);
        volume.author = parseAuthor(info);
        volume.url = parseUrl(info);
        volume.description = parseDescription(info);
        return volume;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getURL() {
        return url;
    }

    public String getDescription() {
        return description;
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

    private static JsonElement getItemInfo(String book) {
        JsonParser parser = new JsonParser();
        JsonObject parsedData = parser.parse(book).getAsJsonObject();
        JsonElement info = null;
        try {
            info = parsedData
                    .getAsJsonArray("items")
                    .get(0);
        } catch (Exception e) {
            throw new IllegalArgumentException("Could not extract volume (no match?)");
        }
        return info;
    }

    private static String parseTitle(JsonElement info) {
        return info.getAsJsonObject()
                .get("volumeInfo")
                .getAsJsonObject()
                .get("title")
                .getAsString();
    }

    private static String parseAuthor(JsonElement info) {
        return info.getAsJsonObject()
                .get("volumeInfo")
                .getAsJsonObject()
                .get("authors")
                .getAsJsonArray()
                .get(0)
                .getAsString();
    }

    private static String parseUrl(JsonElement info) {
        return info.getAsJsonObject()
                .get("selfLink")
                .getAsString();
    }

    private static String parseDescription(JsonElement info) {
        return info.getAsJsonObject()
                .get("volumeInfo")
                .getAsJsonObject()
                .get("description")
                .getAsString();
    }

}
