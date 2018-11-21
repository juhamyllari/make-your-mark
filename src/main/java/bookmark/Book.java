//package bookmark;
//
//
//import bookmark.AbstractBookmark;
//
//public class Book extends AbstractBookmark{
//    private String author;
//    private String isbn;
//    
//    public Book(String title, String author, String isbn){
//        super.setTitle(title);
//        super.setType("Book");
//        this.author=author;
//        this.isbn=isbn;
//    }
//
//    public void setAuthor(String author) {
//        this.author = author;
//    }
//
//    public void setIsbn(String isbn) {
//        this.isbn = isbn;
//    }
//
//    public String getAuthor() {
//        return author;
//    }
//
//    public String getIsbn() {
//        return isbn;
//    }
//    
//    @Override
//    public String toString(){
//        return AbstractBookmark.attributeToString("Author", author)+
//                AbstractBookmark.attributeToString("ISBN", isbn)+
//                super.toString();
//    }
//}
