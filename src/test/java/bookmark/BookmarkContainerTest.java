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

    @Test
    public void testMarkCurrentAsRead() {
        assertTrue(!funnyBook.isRead());
        bc.markCurrentAsRead();
        assertTrue(funnyBook.isRead());
    }

    @Test
    public void testUnfilteredSize() {
        assertEquals(1, bc.size());
        bc.add(sillyBook);
        assertEquals(2, bc.size());
    }

    @Test
    public void testSearchByTagsOR() {
    }

    @Test
    public void testSetFilter() {
        bc.add(sillyBook);
        funnyBook.addToField("tags", "funny");
        List<String> searchTags = new ArrayList<>();
        searchTags.add("serious");
        searchTags.add("funny");
        bc.setFilter("tags", searchTags);
        assertEquals(1, bc.size());
    }

    @Test
    public void testDropFilter() {
        bc.add(sillyBook);
        List<String> searchTags = new ArrayList<>();
        searchTags.add("serious");
        searchTags.add("funny");
        bc.setFilter("tags", searchTags);
        bc.dropFilter();
        assertEquals(2, bc.size());
        assertTrue(!bc.hasFilter());
    }

    @Test
    public void testHasFilter() {
        assertTrue(!bc.hasFilter());
        List<String> searchTags = new ArrayList<>();
        searchTags.add("serious");
        searchTags.add("funny");
        bc.setFilter("tags", searchTags);
        assertTrue(bc.hasFilter());
        bc.dropFilter();
        assertTrue(!bc.hasFilter());
    }

    @Test
    public void testToString() {
        String str = bc.toString();
        assertTrue(str.contains("Funny Book"));
        assertTrue(str.contains("Punny Guy"));
        assertTrue(str.contains("1234"));
    }

    @Test
    public void testSerialize() {
        String str = bc.serialize();
        assertTrue(str.length() > 15); // sanity check: longer than a few characters
        assertTrue(str.contains("Funny Book"));
        assertTrue(str.contains("Punny Guy"));
        assertTrue(str.contains("1234"));
    }

    @Test
    public void testDeserializeBookmarkContainer() {
        BookmarkContainer deser = BookmarkContainer.deserializeBookmarkContainer(bc.serialize());
        bc.resetIndex();
        deser.resetIndex();
        assertEquals(bc.getCurrent().getSingleField("title"), deser.getCurrent().getSingleField("title"));
    }

    @Test
    public void testResetIndex() {
        bc.add(sillyBook);
        bc.getNext();
        assertEquals(funnyBook, bc.getCurrent());
        bc.resetIndex();
        assertEquals(sillyBook, bc.getCurrent());
    }

    @Test
    public void testIsShowingRead() {
    }

    @Test
    public void testSetShowingRead() {
    }
}
