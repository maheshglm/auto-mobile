package glue.joplin;

import com.gummarajum.automation.automobile.Bootstrap;
import com.gummarajum.automation.automobile.steps.joplin.JoplinSteps;
import cucumber.api.java8.En;

public class JoplinStepsDef implements En {

    private JoplinSteps steps = (JoplinSteps) Bootstrap.getBean(JoplinSteps.class);

    public JoplinStepsDef() {

        Given("I have Joplin application running in device", () -> {
            steps.launchJoplinApplication();
        });

        Given("I delete notebook {string}", (String notebookName) -> {
            steps.deleteNoteBook(notebookName);
        });

        When("I create a new notebook with name {string}", (String notebookName) -> {
            steps.createNewNoteBook(notebookName);
        });

        Then("I rename the notebook {string} to {string}", (String notebookName, String newName) -> {
            steps.renameNoteBook(notebookName, newName);
        });

        Then("I expect {string} notebook (created|renamed)", (String notebookName) -> {
            steps.verifyNoteBookAvailable(notebookName);
        });

        Then("I expect notebook {string} is (deleted|not available)", (String notebookName) -> {
            steps.verifyNoteBookNotAvailable(notebookName);
        });


//
//        When("I add a new note with below params", (io.cucumber.datatable.DataTable dataTable) -> {
//            // Write code here that turns the phrase above into concrete actions
//            // For automatic transformation, change DataTable to one of
//            // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
//            // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
//            // Double, Byte, Short, Long, BigInteger or BigDecimal.
//            //
//            // For other transformations you can register a DataTableType.
//            throw new cucumber.api.PendingException();
//        });
//
//        Then("I expect a new note with below params is added successfully", (io.cucumber.datatable.DataTable dataTable) -> {
//            // Write code here that turns the phrase above into concrete actions
//            // For automatic transformation, change DataTable to one of
//            // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
//            // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
//            // Double, Byte, Short, Long, BigInteger or BigDecimal.
//            //
//            // For other transformations you can register a DataTableType.
//            throw new cucumber.api.PendingException();
//        });
//
//        Given("I have a notebook {string}", (String string) -> {
//            // Write code here that turns the phrase above into concrete actions
//            throw new cucumber.api.PendingException();
//        });
//
//        Then("I modify a new note with below params", (io.cucumber.datatable.DataTable dataTable) -> {
//            // Write code here that turns the phrase above into concrete actions
//            // For automatic transformation, change DataTable to one of
//            // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
//            // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
//            // Double, Byte, Short, Long, BigInteger or BigDecimal.
//            //
//            // For other transformations you can register a DataTableType.
//            throw new cucumber.api.PendingException();
//        });
//
//        Given("I add a new note with below params", (io.cucumber.datatable.DataTable dataTable) -> {
//            // Write code here that turns the phrase above into concrete actions
//            // For automatic transformation, change DataTable to one of
//            // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
//            // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
//            // Double, Byte, Short, Long, BigInteger or BigDecimal.
//            //
//            // For other transformations you can register a DataTableType.
//            throw new cucumber.api.PendingException();
//        });
//
//        When("I delete a note with title {string}", (String string) -> {
//            // Write code here that turns the phrase above into concrete actions
//            throw new cucumber.api.PendingException();
//        });
//
//        Then("I expect note with title {string} should be deleted", (String string) -> {
//            // Write code here that turns the phrase above into concrete actions
//            throw new cucumber.api.PendingException();
//        });
//
//        When("I move below note from {string} to {string}", (String string, String string2, io.cucumber.datatable.DataTable dataTable) -> {
//            // Write code here that turns the phrase above into concrete actions
//            // For automatic transformation, change DataTable to one of
//            // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
//            // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
//            // Double, Byte, Short, Long, BigInteger or BigDecimal.
//            //
//            // For other transformations you can register a DataTableType.
//            throw new cucumber.api.PendingException();
//        });
//
//        Then("I expect below note is moved to {string}", (String string, io.cucumber.datatable.DataTable dataTable) -> {
//            // Write code here that turns the phrase above into concrete actions
//            // For automatic transformation, change DataTable to one of
//            // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
//            // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
//            // Double, Byte, Short, Long, BigInteger or BigDecimal.
//            //
//            // For other transformations you can register a DataTableType.
//            throw new cucumber.api.PendingException();
//        });
//
//        Then("I expect below note does not exist under {string}", (String string, io.cucumber.datatable.DataTable dataTable) -> {
//            // Write code here that turns the phrase above into concrete actions
//            // For automatic transformation, change DataTable to one of
//            // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
//            // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
//            // Double, Byte, Short, Long, BigInteger or BigDecimal.
//            //
//            // For other transformations you can register a DataTableType.
//            throw new cucumber.api.PendingException();
//        });


    }
}
