package IO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.stream.Collectors;

public class GoogleBooks {

    public static URL createQuery(String isbn) throws MalformedURLException {
        String url = "https://www.googleapis.com/books/v1/volumes?q=isbn:" + isbn;
        return new URL(url);
    }

    public static String getBook(URL query) throws IOException {
        BufferedReader in = new BufferedReader(
                new InputStreamReader(query.openStream()));
        String output = in.lines().collect(Collectors.joining());
        in.close();
        return output;
    }
}
