package bookmark;

import java.util.List;

public interface Bookmark {

    public String getStringEntry(String title);
    public List<String> getListEntry(String title);
    public void setStringEntry(String name, String data);
    public void setListEntry(String name, List<String> data);
    
}
