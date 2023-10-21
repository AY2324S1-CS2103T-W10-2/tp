package seedu.address.logic;

import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.nio.file.Path;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.SeplendidLogsCenter;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.SeplendidParser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.ReadOnlyLocalCourseCatalogue;
import seedu.address.model.ReadOnlyPartnerCourseCatalogue;
import seedu.address.model.ReadOnlyUniversityCatalogue;
import seedu.address.model.SeplendidModel;
import seedu.address.model.localcourse.LocalCourse;
import seedu.address.model.notes.Note;
import seedu.address.model.partnercourse.PartnerCourse;
import seedu.address.model.university.University;
import seedu.address.seplendidui.Ui;
import seedu.address.storage.Storage;

/**
 * The main LogicManager of SEPlendid.
 */
public class SeplendidLogicManager implements SeplendidLogic {
    public static final String FILE_OPS_ERROR_FORMAT = "Could not save data due to the following error: %s";

    public static final String FILE_OPS_PERMISSION_ERROR_FORMAT =
            "Could not save data to file %s due to insufficient permissions to write to the file or the folder.";

    private final Logger logger = SeplendidLogsCenter.getLogger(SeplendidLogicManager.class);

    // Note, during morphing Storage handles both ab3 and SEPlendid.
    // On the other hand, SEPlendid has its only LogicManager.
    // TBD: remove this developer's note.
    private final SeplendidModel model;
    private final Storage storage;
    private final SeplendidParser seplendidParser;
    private Ui ui;

    /**
     * Constructs a {@code LogicManager} with the given {@code Model} and {@code Storage}.
     */
    public SeplendidLogicManager(SeplendidModel model, Storage storage) {
        this.model = model;
        this.storage = storage;
        seplendidParser = new SeplendidParser();
    }

    @Override
    public void setUi(Ui ui) {
        this.ui = ui;
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        CommandResult commandResult;
        Command command = seplendidParser.parseCommand(commandText);
        commandResult = command.execute(model);

        try {
            storage.saveLocalCourseCatalogue(model.getLocalCourseCatalogue());
            storage.savePartnerCourseCatalogue(model.getPartnerCourseCatalogue());
            storage.saveUniversityCatalogue(model.getUniversityCatalogue());
        } catch (AccessDeniedException e) {
            throw new CommandException(String.format(FILE_OPS_PERMISSION_ERROR_FORMAT, e.getMessage()), e);
        } catch (IOException ioe) {
            throw new CommandException(String.format(FILE_OPS_ERROR_FORMAT, ioe.getMessage()), ioe);
        }

        return commandResult;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return model.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        model.setGuiSettings(guiSettings);
    }

    //=========== LocalCourseCatalogue ================================================================================
    @Override
    public ReadOnlyLocalCourseCatalogue getLocalCourseCatalogue() {
        return model.getLocalCourseCatalogue();
    }

    @Override
    public ObservableList<LocalCourse> getFilteredLocalCourseCatalogue() {
        return model.getFilteredLocalCourseList();
    }

    @Override
    public Path getLocalCourseCatalogueFilePath() {
        return model.getLocalCourseCatalogueFilePath();
    }

    //=========== PartnerCourseCatalouge ============================================================================
    @Override
    public ReadOnlyPartnerCourseCatalogue getPartnerCourseCatalogue() {
        return model.getPartnerCourseCatalogue();
    }

    @Override
    public ObservableList<PartnerCourse> getFilteredPartnerCourseCatalogue() {
        return model.getFilteredPartnerCourseList();
    }

    @Override
    public Path getPartnerCourseCatalogueFilePath() {
        return model.getPartnerCourseCatalogueFilePath();
    }

    //=========== UniversityCatalouge ================================================================================

    public ReadOnlyUniversityCatalogue getUniversityCatalogue() {
        return model.getUniversityCatalogue();
    }

    public ObservableList<University> getFilteredUniversityCatalogue() {
        return model.getFilteredUniversityList();
    }

    public Path getUniversityeCatalogueFilePath() {
        return model.getUniversityCatalogueFilePath();
    }

    public ObservableList<Note> getFilteredNoteCatalogue() {
        return model.getFilteredNoteList();
    }

}