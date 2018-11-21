
import bookmark.Book;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class BookTest {

    Book book;
    String title, author, isbn, comment;
    List<String> tags, prerequisiteCourses, relatedCourses;

    public BookTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        title = "Snow";
        author = "Orhan Pamuk";
        isbn = "978-951-31-7583-2";
        book = new Book(title, author, isbn);
        comment = "Comment.";

        book.setComment(comment);

        tags = new ArrayList<>();
        tags.add("Turkey");
        tags.add("Nobel");
        book.setTags(tags);

        prerequisiteCourses = new ArrayList<>();
        prerequisiteCourses.add("White castle");
        prerequisiteCourses.add("Black book");
        book.setPrerequisiteCourses(prerequisiteCourses);

        relatedCourses = new ArrayList<>();
        relatedCourses.add("Yay");
        book.setRelatedCourses(relatedCourses);

    }

    @After
    public void tearDown() {
    }

    @Test
    public void constructorSetsVariablesProperly() {
        assertEquals(title, book.getTitle());
        assertEquals(author, book.getAuthor());
        assertEquals(isbn, book.getIsbn());
        assertEquals("Book", book.getType());

    }

    @Test
    public void settingACommentworks() {
        assertEquals(comment, book.getComment());
    }

    @Test
    public void settingTagsWorks() {
        assertEquals(tags, book.getTags());
    }

    @Test
    public void settingPrerequisiteCoursesWorks() {
        assertEquals(prerequisiteCourses, book.getPrerequisiteCourses());
    }

    @Test
    public void settingRelatedCoursesWorks() {
        assertEquals(relatedCourses, book.getRelatedCourses());
    }

    @Test
    public void readingABookWorks() {
        assertEquals(false, book.isRead());
        book.read();
        assertEquals(true, book.isRead());
    }

    @Test
    public void listWorks() {
        String first = "first";
        String second = "second";
        List<String> list = new ArrayList<>();
        list.add(first);
        list.add(second);
        String commaSeperated = "first, second";
        assertEquals(commaSeperated, list(list));
    }

    @Test
    public void attributeToStringWorks() {
        String resultWithtTitle = Book.attributeToString("Title", book.getTitle());
        assertEquals("Title: " + title + "\n", resultWithtTitle);

        String resultWithoutTitle = Book.attributeToString("Title", "");
        assertEquals("", resultWithoutTitle);
    }

    @Test
    public void toStringWorks() {
        String authorToString = Book.attributeToString("Author", author);
        String isbnToString = Book.attributeToString("ISBN", isbn);
        String typeToString = Book.attributeToString("Type", book.getType());
        String titleToString = Book.attributeToString("Title", title);
        String tagsToString = Book.attributeToString("Tags", list(tags));
        String prerequisiteCoursesToString = Book.attributeToString("Prerequisite courses", list(prerequisiteCourses));
        String relatedCoursesToString = Book.attributeToString("Related courses", list(relatedCourses));
        String commentToString = Book.attributeToString("Comment", comment);

        String comparision = authorToString + isbnToString + typeToString + titleToString + tagsToString + prerequisiteCoursesToString
                + relatedCoursesToString + commentToString;
        assertEquals(comparision, book.toString());

    }

    // list-metodi kopsattu tänne testejä varten bugikorjattuna..
    private String list(List<String> list) {
        String res = "";
        for (int i = 0; i < list.size(); i++) {
            if (i != list.size() - 1) {
                res += list.get(i) + ", ";
            } else {
                res += list.get(i);
            }
        }
        return res;
    }

}
