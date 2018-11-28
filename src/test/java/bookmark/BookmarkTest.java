package bookmark;

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
        bmTagged.setField("tags", lst);
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testSetField_String_List() {
        bmDefault.setField("tags", lst);
        assertEquals(lst, bmDefault.getListField("tags"));
    }

    @Test
    public void testSetField_String_String() {
        bmDefault.setField("title", "The Book");
        assertEquals("The Book", bmDefault.getStringField("title"));
    }

    @Test
    public void testCreateBookmark() {
        Bookmark bm = Bookmark.createBookmark();
        assertEquals("", bm.getStringField("title"));
        assertEquals("", bm.getStringField("url"));
        assertEquals("", bm.getStringField("description"));
        assertEquals("", bm.getStringField("author"));
        assertEquals("", bm.getStringField("comment"));
        assertEquals("", bm.getStringField("isbn"));
        assertEquals(0, bm.getListField("tags").size());
        assertEquals(0, bm.getListField("prerequisite courses").size());
        assertEquals(0, bm.getListField("related courses").size());
        
        bm.setField("tags", lst);
        assertEquals(2, bm.getListField("tags").size());
    }

    @Test
    public void testGetListField() {
    }

    @Test
    public void testToString() {
    }

    @Test
    public void testGetStringField() {
    }

    @Test
    public void testGetFieldNames() {
    }

    @Test
    public void testFieldIsString() {
    }
    
}
