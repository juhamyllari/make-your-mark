
import IO.FileIO;
import mainApp.App;
import IO.StubIO;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Stepdefs {

    List<String> inputLines = new ArrayList<>();
    StubIO io;
    App app;

    @Given("^application is started$")
    public void application_is_started() throws Throwable {
    }

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

    @Given("^command quit is given$")
    public void command_quit_is_given() throws Throwable {
        inputLines.add("quit");
    }

    @Given("^command yes is given$")
    public void command_yes_is_given() throws Throwable {
        inputLines.add("yes");
    }

    @Given("^command no is given$")
    public void command_no_is_given() throws Throwable {
        inputLines.add("no");
    }

    @Given("^command tagsearch is given$")
    public void command_tagsearch_is_given() throws Throwable {
        inputLines.add("tagsearch");
    }

    @Given("^command drop is given$")
    public void command_drop_is_given() throws Throwable {
        inputLines.add("drop");
    }

    @Given("^command mark is given$")
    public void command_mark_is_given() throws Throwable {
        inputLines.add("mark");
    }

    @Given("^command show is given$")
    public void command_show_is_given() throws Throwable {
        inputLines.add("show");
    }

    @Given("^command hide is given$")
    public void command_hide_is_given() throws Throwable {
        inputLines.add("hide");
    }

    @Given("^command remove is given$")
    public void command_remove_is_given() throws Throwable {
        inputLines.add("remove");
    }

    @Given("^command change is given$")
    public void command_change_is_given() throws Throwable {
        inputLines.add("change");
    }

    @Given("^command delete is given$")
    public void command_delete_is_given() throws Throwable {
        inputLines.add("delete");
    }

    @Given("^command editall is given$")
    public void command_editall_is_given() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        inputLines.add("editall");
    }

    @Given("^command comment is given$")
    public void command_comment_is_given() throws Throwable {
        inputLines.add("comment");
    }
    
    @Given("^command isbn is given$")
    public void command_isbn_is_given() throws Throwable {
        inputLines.add("isbn");
    }

    @When("^a valid comment \"([^\"]*)\" is entered$")
    public void a_valid_comment(String comment) throws Throwable {
        inputLines.add(comment);
    }
    
    @Given("^command search is given$")
    public void command_fieldsearch_is_given() throws Throwable {
        inputLines.add("search");
    }

    @When("^a valid tag \"([^\"]*)\" is entered$")
    public void a_valid_tag_is_entered(String tag) throws Throwable {
        inputLines.add(tag);
    }

    @When("^an invalid tag \"([^\"]*)\" is entered$")
    public void an_invalid_tag_is_entered(String tag) throws Throwable {
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
    
    @When("^an invalid ISBN \"([^\"]*)\" is entered$")
    public void an_invalid_ISBN_is_entered(String isbn) throws Throwable {
        inputLines.add(isbn);
        inputLines.add("c");
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

    @When("^a valid URL \"([^\"]*)\" is entered$")
    public void a_valid_URL_is_entered(String url) throws Throwable {
        inputLines.add(url);
    }

    @When("^a valid description \"([^\"]*)\" is entered$")
    public void a_valid_description_is_entered(String description) throws Throwable {
        inputLines.add(description);
    }

    @Then("^system will respond with \"([^\"]*)\"$")
    public void system_will_respond_with(String expected) throws Throwable {
        if (expected.contains("Value")) {
            inputLines.add("exit");
        }
        inputLines.add("quit");
        inputLines.add("no");
        io = new StubIO(inputLines);
        App.run(io, false);
        assert io.getPrints().stream().anyMatch(line -> line.contains(expected)) :
                ("expected: " + expected + ", got: " + io.getPrints().stream().collect(Collectors.joining("; ")));
    }
    
    @Then("^system will respond with \"([^\"]*)\" and \"([^\"]*)\"$")
    public void system_will_respond_with(String expected1, String expected2) throws Throwable {
        inputLines.add("quit");
        inputLines.add("no");
        io = new StubIO(inputLines);
        App.run(io, false);
        assert io.getPrints().stream().anyMatch(line -> line.contains(expected1)) && 
                io.getPrints().stream().anyMatch(line -> line.contains(expected1)) :
                ("expected: " + expected1 + " and: " + expected2 + ", got: " + io.getPrints().stream().collect(Collectors.joining("; ")));
    }

    @Then("^system with save file will respond with \"([^\"]*)\"$")
    public void system_with_save_file_will_respond_with(String expected) throws Throwable {
        App.createSampleSaveFile(new FileIO());
        inputLines.add("quit");
        inputLines.add("quit");
        inputLines.add("quit");
        io = new StubIO(inputLines);
        App.run(io, true);
        assert io.getPrints().stream().anyMatch(line -> line.contains(expected)) :
                ("expected: " + expected + ", got: " + io.getPrints().stream().collect(Collectors.joining("; ")));
    }
}
