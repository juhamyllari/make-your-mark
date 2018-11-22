package bookmark;

import java.util.ArrayList;
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
    List<Bookmark> lst = new ArrayList<>();
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
        funnyBook = FieldListBookmark.createBook("Funny Book", "Punny Guy", "1234");
        sillyBook = FieldListBookmark.createBook("Silly Book", "S. Illy Pherson", "9999");
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
        assertEquals(funnyBook, bc.getCurrent());
    }

    @Test
    public void testGetNext() {
        bc.add(sillyBook);
        assertEquals(sillyBook, bc.getNext());
    }

    @Test
    public void testGetPrevious() {
        bc.add(sillyBook);
        bc.getNext();
        assertEquals(funnyBook, bc.getPrevious());
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

}
