package bookmark;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class BookmarkContainerTest {

    Bookmark funnyBook;
    Bookmark sillyBook;
    LinkedList<Bookmark> lst = new LinkedList<>();
    BookmarkContainer bc;

    public BookmarkContainerTest() {
    }

    @BeforeClass
    public static void setUpClass() {

    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        funnyBook = Bookmark.createBookmark();
        funnyBook.setSingleField("Title", "Funny Book");
        funnyBook.setSingleField("Author", "Punny Guy");
        funnyBook.setSingleField("ISBN", "1234");
        sillyBook = Bookmark.createBook("Silly Book", "S. Illy Pherson", "9999");
        lst.add(funnyBook);
        bc = new BookmarkContainer(lst);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testAdd() {
        assertEquals(1, bc.size());
        bc.add(sillyBook);
        assertEquals(2, bc.size());
    }

    @Test
    public void testRemove() {
        assertEquals(1, bc.size());
        bc.add(sillyBook);
        assertEquals(2, bc.size());

        assertEquals(0, bc.getIndex());
        bc.getNext();
        assertEquals(1, bc.getIndex());
        bc.remove(funnyBook);
        assertEquals(0, bc.getIndex());
        assertEquals(sillyBook, bc.getCurrent());
        assertEquals(1, bc.size());

        bc.remove(sillyBook);
        assertEquals(0, bc.size());
        bc.remove(sillyBook);
        assertEquals(0, bc.size());
    }

    @Test
    public void testGetCurrent() {
        bc.add(sillyBook);
        assertEquals(sillyBook, bc.getCurrent());
    }

    @Test
    public void testGetNext() {
        bc.add(sillyBook);
        assertEquals(funnyBook, bc.getNext());
    }

    @Test
    public void testGetPrevious() {
        bc.add(sillyBook);
        assertEquals(funnyBook, bc.getPrevious());
        assertEquals(sillyBook, bc.getPrevious());
    }

    @Test
    public void testGetIndex() {
        assertEquals(0, bc.getIndex());
        bc.add(sillyBook);
        assertEquals(0, bc.getIndex());
        bc.getPrevious();
        assertEquals(1, bc.getIndex());
        bc.getNext();
        assertEquals(0, bc.getIndex());
    }

    @Test
    public void testSize() {
        assertEquals(1, bc.size());
        bc.remove(funnyBook);
        assertEquals(0, bc.size());
        bc.add(funnyBook);
        bc.add(sillyBook);
        assertEquals(2, bc.size());
        bc.add(sillyBook);
        assertEquals(2, bc.size());
    }

    @Test
    public void testGetBookmarks() {
        assertEquals(lst, bc.getBookmarks());
    }

    @Test
    public void testOrSearchAndFind() {
        funnyBook.setSingleField("tags", "fun");
        bc.add(funnyBook);
        bc.add(sillyBook);
        List<String> tags = new ArrayList();
        tags.add("epic");
        tags.add("fun");
        assertEquals(1, bc.searchByTagsOR(tags).size());
    }

    @Test
    public void testOrSearchAndNotFind() {
        bc.add(funnyBook);
        bc.add(sillyBook);
        List<String> tags = new ArrayList();
        tags.add("nope");
        assertEquals(0, bc.searchByTagsOR(tags).size());
    }
}
