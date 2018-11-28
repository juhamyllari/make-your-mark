
import IO.FileIO;
import mainApp.App;
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

    @Given("^command browse is given$")
    public void command_browse_is_given() throws Throwable {
        inputLines.add("browse");
    }

    @Given("^command samples is given$")
    public void command_samples_is_given() throws Throwable {
        inputLines.add("samples");
    }

    @Given("^command next is given$")
    public void command_next_is_given() throws Throwable {
        inputLines.add("next");
    }

    @Given("^command edit is given$")
    public void command_edit_is_given() throws Throwable {
        inputLines.add("edit");
    }
    
     @Given("^command exit is given$")
    public void command_exit_is_given() throws Throwable {
        inputLines.add("exit");
    }

    @Given("^command yes is given$")
    public void command_yes_is_given() throws Throwable {
        inputLines.add("yes");
    }

    @Given("^command no is given$")
    public void command_no_is_given() throws Throwable {
        inputLines.add("no");
    }

    @Given("^command search is given$")
    public void command_search_is_given() throws Throwable {
        inputLines.add("search");
    }

    @When("^a valid tag \"([^\"]*)\" is entered$")
    public void a_valid_tag_hobbies_is_entered(String tag) throws Throwable {
        inputLines.add(tag);
    }

    @When("^an invalid tag \"([^\"]*)\" is entered$")
    public void an_invalid_tag_none_is_entered(String tag) throws Throwable {
        inputLines.add(tag);
    }

    @When("^a valid field \"([^\"]*)\" is entered$")
    public void a_valid_field_is_entered(String field) throws Throwable {
        inputLines.add(field);
    }

    @When("^a valid title \"([^\"]*)\" is entered$")
    public void a_valid_title_is_entered(String title) throws Throwable {
        inputLines.add(title);
    }

    @When("^an invalid title \"([^\"]*)\" is entered$")
    public void an_invalid_title_is_entered(String title) throws Throwable {
        inputLines.add(title);
    }

    @When("^four empty answers are entered$")
    public void four_empty_answers_are_entered() throws Throwable {
        inputLines.add("");
        inputLines.add("");
        inputLines.add("");
        inputLines.add("");
    }

    @When("^two empty answers are entered$")
    public void two_empty_answers_are_entered() throws Throwable {
        inputLines.add("");
        inputLines.add("");
    }

    @When("^one empty answer is entered$")
    public void one_empty_answer_is_entered() throws Throwable {
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
        inputLines.add("exit");
        inputLines.add("exit");
        io = new StubIO(inputLines);
        App.run(io, false);
        assertTrue(io.getPrints().contains(expected));
    }

    @Then("^system with save file will respond with \"([^\"]*)\"$")
    public void system_with_save_file_will_respond_with(String expected) throws Throwable {
        App.createSampleSaveFile(new FileIO());
        inputLines.add("exit");
        inputLines.add("exit");
        inputLines.add("exit");
        io = new StubIO(inputLines);
        App.run(io, true);
        assertTrue(io.getPrints().contains(expected));
    }
 }