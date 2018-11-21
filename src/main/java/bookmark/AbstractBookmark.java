package bookmark;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractBookmark implements Bookmark {
    private String type="";
    private String title="";
    private List<String> tags=new ArrayList<>();
    private List<String> prerequisiteCourses=new ArrayList<>();
    private List<String> relatedCourses=new ArrayList<>();
    private String comment="";
    private boolean read=false;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public List<String> getPrerequisiteCourses() {
        return prerequisiteCourses;
    }

    public void setPrerequisiteCourses(List<String> prequisiteCourses) {
        this.prerequisiteCourses = prequisiteCourses;
    }

    public List<String> getRelatedCourses() {
        return relatedCourses;
    }

    public void setRelatedCourses(List<String> relatedCourses) {
        this.relatedCourses = relatedCourses;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public boolean isRead() {
        return read;
    }

    public void setRead(boolean read) {
        this.read = read;
    }
    
    public void read(){
        this.read = true;
    }
    
    // Returns the row for toString() if s is not an empty string
    public static String attributeToString(String attribute, String s){
        return (s.isEmpty()?"":attribute+": "+s+"\n");
    }
    
    // Seperates values with a comma
    public static String list(List<String> list){
        String res="";
        for(int i=0;i<list.size();i++){
            if (i!=list.size()-1) {
                res+=list.get(i)+", ";
            } else {
                res+=list.get(i);
            }
        }
        return res;
    }
    
    @Override
    public String toString(){
        return attributeToString("Type", type)+
                attributeToString("Title", title)+
                attributeToString("Tags", list(tags))+
                attributeToString("Prerequisite courses", list(prerequisiteCourses))+
                attributeToString("Related courses", list(relatedCourses))+
                attributeToString("Comment", comment);
    }
}
