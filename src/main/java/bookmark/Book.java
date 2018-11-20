package bookmark;


import bookmark.Bookmark;

public class Book extends Bookmark{
    private String author;
    private String isbn;
    
    public Book(String title, String author, String isbn){
        super.setTitle(title);
        super.setType("Book");
        this.author=author;
        this.isbn=isbn;
    }

    public String getAuthor() {
        return author;
    }

    public String getIsbn() {
        return isbn;
    }
    
    @Override
    public String toString(){
        return Bookmark.attributeToString("Author", author)+
                Bookmark.attributeToString("ISBN", isbn)+
                super.toString();
    }
}
