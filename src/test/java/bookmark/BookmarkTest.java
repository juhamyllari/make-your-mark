package bookmark;

import Menu.Menu;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class BookmarkTest {
    
    Bookmark bmDefault;
    Bookmark bmTagged;
    List<String> lst;
    
    public BookmarkTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        bmDefault = new Bookmark();
        bmTagged = Bookmark.createBookmark();
        lst = new ArrayList<>();
        lst.add("tag1");
        lst.add("tag2");
        bmTagged.setListField("tags", lst);
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testSetField_String_List() {
        bmDefault.setListField("tags", lst);
        assertEquals(lst, bmDefault.getListField("tags"));
    }

    @Test
    public void testSetField_String_String() {
        bmDefault.setSingleField("title", "The Book");
        assertEquals("The Book", bmDefault.getSingleField("title"));
    }

    @Test
    public void testCreateBookmark() {
        Bookmark bm = Bookmark.createBookmark();
        assertEquals("", bm.getSingleField("title"));
        assertEquals("", bm.getSingleField("url"));
        assertEquals("", bm.getSingleField("description"));
        assertEquals("", bm.getSingleField("author"));
        assertEquals("", bm.getSingleField("comment"));
        assertEquals("", bm.getSingleField("isbn"));
        assertEquals(0, bm.getListField("tags").size());
        assertEquals(0, bm.getListField("prerequisite courses").size());
        assertEquals(0, bm.getListField("related courses").size());
        
        bm.setListField("tags", lst);
        assertEquals(2, bm.getListField("tags").size());
    }
    
    @Test
    public void testGetListField() {
    }

    @Test
    public void testToString() {
    }

    @Test
    public void testGetSingleField() {
    }

    @Test
    public void testGetFieldNames() {
    }

    @Test
    public void testFieldIsString() {
    }
    
}
