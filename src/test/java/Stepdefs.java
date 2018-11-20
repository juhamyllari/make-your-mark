
import IO.StubIO;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;

public class Stepdefs {
    
    List<String> inputLines = new ArrayList<>();
    StubIO io;
    App app;
    
    @Given("^command new is given$")
    public void command_new_is_given() throws Throwable {
        inputLines.add("new");
    }

    @Given("^command book is given$")
    public void command_book_is_given() throws Throwable {
        inputLines.add("book");
    }

    @When("^a valid title \"([^\"]*)\" is entered$")
    public void a_valid_title_is_entered(String title) throws Throwable {
        inputLines.add(title);
    }

    @When("^four empty answers are entered$")
    public void four_empty_answers_are_entered() throws Throwable {
        inputLines.add("");
        inputLines.add("");
        inputLines.add("");
        inputLines.add("");
    }

    @When("^a valid author \"([^\"]*)\" is entered$")
    public void a_valid_author_is_entered(String author) throws Throwable {
        inputLines.add(author);
    }

    @When("^a valid ISBN \"([^\"]*)\" is entered$")
    public void a_valid_ISBN_is_entered(String isbn) throws Throwable {
        inputLines.add(isbn);
    }

    @Then("^system will respond with \"([^\"]*)\"$")
    public void system_will_respond_with(String expected) throws Throwable {
        inputLines.add("exit");
        io = new StubIO(inputLines);
        App.run(io);
        assertTrue(io.getPrints().contains(expected));
    }

 }
