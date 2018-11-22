package bookmark;

import java.util.List;

/**
 * For the current primary implementation see class FieldListBookmark.
 */
public interface Bookmark {

    public List<String> getListField(String title);
    public String getStringField(String title);
    public void setField(String name, List<String> data);
    public void setField(String name, String data);
    public List<String> getFieldNames();
    public boolean fieldIsString(String fieldName);
    
}
