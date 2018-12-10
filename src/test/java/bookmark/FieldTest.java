package bookmark;

import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class FieldTest {

    Field field;
    Field field2;

    public FieldTest() {

    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        field = new Field("ISBN", "0547249640");

        ArrayList<String> tags = new ArrayList();
        tags.add("kids");
        tags.add("story");

        field2 = new Field("Tags", tags);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testSetName() {
        assertEquals("ISBN", field.getName());
        field.setName("renamed");
        assertEquals("renamed", field.getName());
    }

    @Test
    public void testSetData() {
        assertEquals("story", field2.getList().get(1));
        ArrayList<String> tags = new ArrayList();
        tags.add("test1");
        tags.add("test2");
        field2.setData(tags);
        assertEquals("test2", field2.getList().get(1));
    }

    @Test
    public void testIsEmpty() {
        assertEquals(false, field.isEmpty());
        assertEquals(false, field2.isEmpty());
        assertEquals(true, new Field("test", "").isEmpty());
        assertEquals(true, new Field("test", new ArrayList()).isEmpty());
    }

    @Test
    public void testToString() {
        assertEquals("ISBN: 0547249640", field.toString());
        assertEquals("Tags: kids, story", field2.toString());
    }

    @Test
    public void testContains() {
        assertEquals(true, field2.contains("kids"));
        assertEquals(true, field2.contains("story"));
        assertEquals(false, field2.contains("id"));
    }

}
