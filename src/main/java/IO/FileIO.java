package IO;

import bookmark.BookmarkContainer;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileIO {
    
    public static boolean saveContainerToFile(BookmarkContainer container, String file) {
        String json = BookmarkContainer.serializeBookmarkContainer(container);
        Path path = Paths.get(file);

        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            writer.write(json);
            return true;
        } catch (IOException e) {
            System.err.format("IOException: %s%n", e);
        }
        return false;
    }
    
    public static BookmarkContainer loadContainerFromFile(String file) {
        Path path = Paths.get(file);
        String json = "";

        try (BufferedReader reader = Files.newBufferedReader(path)) {
            json = reader.readLine();
        } catch (IOException e) {
            return null;
        }
        return BookmarkContainer.deserializeBookmarkContainer(json);
    }
}
