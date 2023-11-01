package seedu.address.seplendidui;

import java.util.logging.Logger;

import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.Logic;
import seedu.address.logic.SeplendidLogic;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.SeplendidDataType;
import seedu.address.model.localcourse.LocalCourse;

/**
 * The Main Window. Provides the basic application layout containing
 * a menu bar and space where other JavaFX elements can be placed.
 */
public class MainWindow extends UiPart<Stage> {

    private static final String FXML = "MainWindow.fxml";

    private final Logger logger = LogsCenter.getLogger(getClass());

    private Stage primaryStage;
    private SeplendidLogic seplendidLogic;

    // Independent Ui parts residing in this Ui container
    private ItemListPanel<SeplendidDataType> itemListPanel;
    private ItemDetailPanel<SeplendidDataType> itemDetailPanel;
    private HelpWindow helpWindow;

    private ResultBox resultBox;

    @FXML
    private StackPane commandBoxPlaceholder;

    @FXML
    private StackPane resultBoxPlaceholder;

    @FXML
    private StackPane itemListPanelPlaceholder;

    @FXML
    private StackPane itemDetailPanelPlaceholder;

    /**
     * Creates a {@code MainWindow} with the given {@code Stage} and {@code Logic}.
     */
    public MainWindow(Stage primaryStage, SeplendidLogic seplendidLogic) {
        super(FXML, primaryStage);

        // Set dependencies
        this.primaryStage = primaryStage;
        this.seplendidLogic = seplendidLogic;

        // Configure the UI
        UiManager.setDefaultFont(primaryStage.getScene().getRoot(), UiManager.getDefaultFont());
        setWindowDefaultSize(seplendidLogic.getGuiSettings());

        this.helpWindow = new HelpWindow();
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    /**
     * Fills up all the placeholders of this window.
     */
    void fillInnerParts() {
        itemDetailPanel = new ItemDetailPanel<>(seplendidLogic);
        itemDetailPanelPlaceholder.getChildren().add(itemDetailPanel.getRoot());

        ObservableList<LocalCourse> localList = seplendidLogic.getFilteredLocalCourseCatalogue();
        ObservableList<SeplendidDataType> itemList = FXCollections.observableArrayList(localList);
        Bindings.bindContent(itemList, localList);

        itemListPanel = new ItemListPanel<>(itemList, itemDetailPanel);
        itemListPanelPlaceholder.getChildren().add(itemListPanel.getRoot());

        resultBox = new ResultBox();
        resultBoxPlaceholder.getChildren().add(resultBox.getRoot());

        CommandBox commandBox = new CommandBox(this::executeCommand);
        commandBoxPlaceholder.getChildren().add(commandBox.getRoot());
    }

    /**
     * Sets the default size based on {@code guiSettings}.
     */
    private void setWindowDefaultSize(GuiSettings guiSettings) {
        primaryStage.setHeight(guiSettings.getWindowHeight());
        primaryStage.setWidth(guiSettings.getWindowWidth());
        if (guiSettings.getWindowCoordinates() != null) {
            primaryStage.setX(guiSettings.getWindowCoordinates().getX());
            primaryStage.setY(guiSettings.getWindowCoordinates().getY());
        }
    }

    void show() {
        primaryStage.show();
    }

    /**
     * Closes the application.
     */
    @FXML
    private void handleExit() {
        GuiSettings guiSettings = new GuiSettings(primaryStage.getWidth(), primaryStage.getHeight(),
                (int) primaryStage.getX(), (int) primaryStage.getY());
        seplendidLogic.setGuiSettings(guiSettings);
        primaryStage.hide();
    }

    /**
     * Opens the help window or focuses on it if it's already opened.
     */
    @FXML
    public void handleHelp() {
        if (!helpWindow.isShowing()) {
            helpWindow.show();
        } else {
            helpWindow.focus();
        }
    }


    public ItemListPanel<? extends SeplendidDataType> getItemListPanel() {
        return itemListPanel;
    }

    /**
     * Executes the command and returns the result.
     *
     * @see Logic#execute(String)
     */
    private CommandResult executeCommand(String commandText) throws CommandException, ParseException {
        try {
            CommandResult commandResult = seplendidLogic.execute(commandText);
            logger.info("Result: " + commandResult.getFeedbackToUser());
            resultBox.setFeedbackToUser(commandResult.getFeedbackToUser());

            switch (commandResult.getUiView()) {
            case LOCAL_COURSE_LIST:
                itemListPanel.setDisplayList(seplendidLogic.getFilteredLocalCourseCatalogue());
                break;
            case PARTNER_COURSE_LIST:
                itemListPanel.setDisplayList(seplendidLogic.getFilteredPartnerCourseCatalogue());
                break;
            case UNIVERSITY_LIST:
                itemListPanel.setDisplayList(seplendidLogic.getFilteredUniversityCatalogue());
                break;
            case NOTE_LIST:
                itemListPanel.setDisplayList(seplendidLogic.getFilteredNoteCatalogue());
                break;
            case MAPPING_LIST:
                itemListPanel.setDisplayList(seplendidLogic.getFilteredMappingCatalogue());
                break;
            case LOCAL_COURSE_SORT:
                itemListPanel.setDisplayList(seplendidLogic.getSortedLocalCourseCatalogue());
                break;
            case PARTNER_COURSE_SORT:
                itemListPanel.setDisplayList(seplendidLogic.getSortedPartnerCourseCatalogue());
                break;
            case UNIVERSITY_SORT:
                itemListPanel.setDisplayList(seplendidLogic.getSortedUniversityCatalogue());
                break;
            default:
                // do nothing
            }

            if (commandResult.isShowHelp()) {
                handleHelp();
            }

            if (commandResult.isExit()) {
                handleExit();
            }

            return commandResult;
        } catch (CommandException | ParseException e) {
            logger.info("An error occurred while executing command: " + commandText);
            resultBox.setFeedbackToUser(e.getMessage());
            throw e;
        }
    }
}
