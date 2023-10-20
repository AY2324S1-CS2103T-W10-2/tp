package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertSeplendidCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalObjects.getTypicalLocalCourseCatalogue;
import static seedu.address.testutil.TypicalObjects.getTypicalPartnerCourseCatalogue;
import static seedu.address.testutil.TypicalObjects.getTypicalUniversityCatalogue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.model.SeplendidModel;
import seedu.address.model.SeplendidModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.partnercourse.PartnerCourse;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code PartnerCourseDeleteCommandTest}.
 */
public class PartnerCourseDeleteCommandTest {
    private SeplendidModel model;
    private SeplendidModel expectedModel;

    @BeforeEach
    public void setUp() {
        model = new SeplendidModelManager(getTypicalLocalCourseCatalogue(), new UserPrefs(),
                getTypicalPartnerCourseCatalogue(), getTypicalUniversityCatalogue());
        expectedModel = new SeplendidModelManager(getTypicalLocalCourseCatalogue(), new UserPrefs(),
                model.getPartnerCourseCatalogue(), getTypicalUniversityCatalogue());
    }

    @Test
    public void execute_partnerCourseDelete_success() {
        PartnerCourse partnerCourseToDelete = model.getFilteredPartnerCourseList().get(
                INDEX_FIRST_PERSON.getZeroBased());
        PartnerCourseDeleteCommand partnerCourseDeleteCommand =
                new PartnerCourseDeleteCommand(partnerCourseToDelete.getPartnerCode());

        assertSeplendidCommandSuccess(partnerCourseDeleteCommand, model, String.format(
                PartnerCourseDeleteCommand.MESSAGE_SUCCESS, Messages.format(partnerCourseToDelete)), expectedModel);

    }

    //more to be added.

}