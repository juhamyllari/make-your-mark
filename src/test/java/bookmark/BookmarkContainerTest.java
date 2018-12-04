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

    Bookmark book;
    Bookmark video;
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
        book = Bookmark.createBookmark();
        book.setSingleField("Title", "Transition");
        book.setSingleField("Author", "Iain Banks");
        book.setSingleField("ISBN", "978-0-349-11927-4");
        video = Bookmark.createBookmark();
        video.setSingleField("Title", "Virtual Memory, Video 1: Overview");
        video.setSingleField("URL", "https://www.youtube.com/watch?v=IUJvnIamMo8");
        lst.add(book);
        bc = new BookmarkContainer(lst);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testAdd() {
        assertEquals(1, bc.size());
        bc.add(video);
        assertEquals(2, bc.size());
    }

    @Test
    public void testRemove() {
        assertEquals(1, bc.size());
        bc.add(video);
        assertEquals(2, bc.size());

        assertEquals(0, bc.getIndex());
        bc.getNext();
        assertEquals(1, bc.getIndex());
        bc.remove(book);
        assertEquals(0, bc.getIndex());
        assertEquals(video, bc.getCurrent());
        assertEquals(1, bc.size());

        bc.remove(video);
        assertEquals(0, bc.size());
        bc.remove(video);
        assertEquals(0, bc.size());
    }

    @Test
    public void testGetCurrent() {
        bc.add(video);
        assertEquals(video, bc.getCurrent());
    }

    @Test
    public void testGetNext() {
        bc.add(video);
        assertEquals(book, bc.getNext());
    }

    @Test
    public void testGetPrevious() {
        bc.add(video);
        assertEquals(book, bc.getPrevious());
        assertEquals(video, bc.getPrevious());
    }

    @Test
    public void testGetIndex() {
        assertEquals(0, bc.getIndex());
        bc.add(video);
        assertEquals(0, bc.getIndex());
        bc.getPrevious();
        assertEquals(1, bc.getIndex());
        bc.getNext();
        assertEquals(0, bc.getIndex());
    }

    @Test
    public void testSize() {
        assertEquals(1, bc.size());
        bc.remove(book);
        assertEquals(0, bc.size());
        bc.add(book);
        bc.add(video);
        assertEquals(2, bc.size());
        bc.add(video);
        assertEquals(2, bc.size());
    }

    @Test
    public void testGetBookmarks() {
        assertEquals(lst, bc.getBookmarks());
    }

    @Test
    public void testOrSearchAndFind() {
        book.addToField("tags", "fun");
        bc.add(book);
        bc.add(video);
        List<String> tags = new ArrayList();
        tags.add("epic");
        tags.add("fun");
        assertEquals(1, bc.searchByTagsOR(tags).size());
    }

    @Test
    public void testOrSearchAndNotFind() {
        bc.add(book);
        bc.add(video);
        List<String> tags = new ArrayList();
        tags.add("nope");
        assertEquals(0, bc.searchByTagsOR(tags).size());
    }

    @Test
    public void testMarkCurrentAsRead() {
        assertTrue(!book.isRead());
        bc.markCurrentAsRead();
        assertTrue(book.isRead());
    }

    @Test
    public void testUnfilteredSize() {
        assertEquals(1, bc.size());
        bc.add(video);
        assertEquals(2, bc.size());
    }

    @Test
    public void testSearchByTagsOR() {
    }

    @Test
    public void testSetFilter() {
        bc.add(video);
        book.addToField("tags", "funny");
        List<String> searchTags = new ArrayList<>();
        searchTags.add("serious");
        searchTags.add("funny");
        bc.setFilter("tags", searchTags);
        assertEquals(1, bc.size());
    }

    @Test
    public void testDropFilter() {
        bc.add(video);
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
        assertTrue(str.contains("Transition"));
        assertTrue(str.contains("Iain Banks"));
    }

    @Test
    public void testSerialize() {
        String str = bc.serialize();
        assertTrue(str.length() > 15); // sanity check: longer than a few characters
        assertTrue(str.contains("Transition"));
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
        bc.add(video);
        bc.getNext();
        assertEquals(book, bc.getCurrent());
        bc.resetIndex();
        assertEquals(video, bc.getCurrent());
    }

    @Test
    public void testIsShowingRead() {
        assertEquals(false, bc.getCurrent().isRead());
        bc.getCurrent().markAsRead();
        assertEquals(true,  bc.getCurrent().isRead());
    }

    @Test
    public void testSetShowingRead() {

    }

    @Test
    public void dateIsSetOnRead() {
        assertEquals(null, bc.getCurrent().getReadOn());
        bc.getCurrent().markAsRead();
        assertNotEquals(null, bc.getCurrent().getReadOn());
    }
}
