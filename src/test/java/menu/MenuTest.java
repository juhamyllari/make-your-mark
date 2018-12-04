package menu;

import IO.IO;
import IO.StubIO;
import bookmark.Bookmark;
import bookmark.BookmarkContainer;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class MenuTest {
    
    private Menu menu;
    private BookmarkContainer container;
    private StubIO io;
    
    public MenuTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        container = new BookmarkContainer();
        io = new StubIO(new ArrayList<>());
        menu = new Menu(container, io);
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testPrintMenu() {
    }

    @Test
    public void testIsExiting() {
    }

    @Test
    public void testSetExiting() {
    }

    @Test
    public void testSetFieldByUserInput() {
    }

    @Test
    public void testContainerHasChanged() {
    }

    @Test
    public void testCreateSamples() {
    }

    @Test
    public void testGetCommandAndExecute() {
    }

    @Test
    public void testEdit() {
        String oldTitle = "Excession";
        String newTitle = "Look to Windward";
        Bookmark bm = new Bookmark();
        bm.setSingleField("Title", oldTitle);
        io.addLine("foo");
        io.addLine("title");
        io.addLine("Look to Windward");
        Menu.edit(bm, io);
        assertEquals(newTitle, bm.getSingleField("Title"));
    }

    @Test
    public void testEditAll() {
    }

    @Test
    public void testAskConfirmation() {
        io.addLine("foo");
        io.addLine("no");
        String action = "perform this action";
        assertFalse(Menu.askConfirmation(io, action));
    }
    
}
