package bookmark;

import java.util.List;

/**
 * Each FieldListBookmark contains a list of Field objects. The Field objects
 * represent the various data items contained in the bookmark, such as "title"
 * and "url".
 */
public interface Field {
    
    public String getName();
    public String getFirst();
    public List<String> getList();
    public boolean isStringField();
    
}
