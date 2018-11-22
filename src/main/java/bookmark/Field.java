package bookmark;

/**
 * Each FieldListBookmark contains a list of Field objects. The Field objects
 * represent the various data items contained in the bookmark, such as "title"
 * and "url".
 */
public interface Field {
    
    public String getName();
    public String getFirst();
    public boolean isStringField();
    
}
