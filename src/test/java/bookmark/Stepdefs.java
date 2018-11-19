
package bookmark;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import static org.junit.Assert.*;

public class Stepdefs {
    Kirja kirja;
    
    @Given("^Kirja is initialized with title \\\"([^\\\"]*)\\\" and writer \\\"([^\\\"]*)\\\" and isbn \\\"([^\\\"]*)\\\"$")
    public void kirja_is_initialized(String otsikko, String kirjoittaja, String isbn) throws Throwable {
        kirja = new Kirja(otsikko, kirjoittaja, isbn);
    }
    
    @When("^a comment \\\"([^\\\"]*)\\\" is set$")
    public void comment_is_set(String comment) throws Throwable {
        kirja.setKommentti(comment);
    }
    
    @Then("^the value of comment should be \"([^\"]*)\"$")
    public void the_value_of_comment_should_be(String comment) throws Throwable {
        assertEquals(comment, kirja.getKommentti());
    }

    
 }
