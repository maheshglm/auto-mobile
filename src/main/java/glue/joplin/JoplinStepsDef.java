package glue.joplin;

import com.gummarajum.automation.automobile.Bootstrap;
import com.gummarajum.automation.automobile.steps.joplin.JoplinSteps;
import com.gummarajum.automation.automobile.svc.DataTableSvc;
import cucumber.api.java8.En;
import io.cucumber.datatable.DataTable;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class JoplinStepsDef implements En {

    private JoplinSteps steps = (JoplinSteps) Bootstrap.getBean(JoplinSteps.class);
    private DataTableSvc dataTableSvc = (DataTableSvc) Bootstrap.getBean(DataTableSvc.class);

    public JoplinStepsDef() {

        Given("I have Joplin application running in device", () -> {
            steps.launchJoplinApplication();
        });

        When("I create a new notebook with name {string}", (String notebookName) -> {
            steps.createNewNoteBook(notebookName);
        });

        Given("I delete notebook {string}", (String notebookName) -> {
            steps.deleteNoteBook(notebookName);
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

        When("I add a new note with below params under notebook {string}", (String notebookName, DataTable dataTable) -> {
            Map<String, String> map = dataTableSvc.getTwoColumnsAsMap(dataTable);
            steps.addNewNote(notebookName, Arrays.asList(map.get("Title"), map.get("Body")));
        });

        Then("I expect a new note available under notebook {string}", (String notebookName, DataTable dataTable) -> {
            Map<String, String> map = dataTableSvc.getTwoColumnsAsMap(dataTable);
            steps.verifyNoteAvailable(notebookName, Arrays.asList(map.get("Title"), map.get("Body")));
        });

        Then("I move note with title {string} from {string} to {string}", (String noteTitle, String fromNoteBook, String toNoteBook) -> {
            steps.moveNote(noteTitle, fromNoteBook, toNoteBook);
        });

        Then("I expect note with title {string} should not be available under notebook {string}", (String noteTitle, String noteBookName) -> {
            steps.verifyNoteNotAvailable(noteBookName, noteTitle);
        });


        Then("I delete a note with title {string} under notebook {string}", (String noteTitle, String noteBookName) -> {
            steps.deleteNote(noteBookName, noteTitle);
        });


    }
}
