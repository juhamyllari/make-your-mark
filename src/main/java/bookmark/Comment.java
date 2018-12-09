package bookmark;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Comment {
    
    private String content;
    private String addedOn;
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");

    public void setAddedOn() {
        LocalDateTime now = LocalDateTime.now();
        this.addedOn = formatter.format(now);
    }
    
    public void setContent(String content) {
        this.content = content;
    }

    public String getAddedOn() {
        return addedOn;
    }

    public String getContent() {
        return content;
    }
    
}
