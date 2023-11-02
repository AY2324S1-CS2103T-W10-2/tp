package seedu.address.logic.commands.partnercourse;

import static java.util.Objects.requireNonNull;

import java.util.Comparator;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.SeplendidModel;
import seedu.address.model.partnercourse.PartnerCourse;
import seedu.address.seplendidui.UiUtil;

/**
 * Sorts partner course list.
 */
public class PartnerCourseSortCommand extends PartnerCourseCommand {
    public static final String PARTNER_COURSE_SORT_MESSAGE_USAGE = COMMAND_WORD
            + " sort: Sorts all partner courses by attributes - partnercode & partnername";
    public static final String ACTION_WORD = "sort";

    public static final String MESSAGE_SUCCESS = "Sorted all partner courses";

    private final Comparator<PartnerCourse> comparator;

    /**
     * Creates a PartnerCourseSortCommand to sort the partner course list.
     * @param comparator Comparator used for sorting
     */
    public PartnerCourseSortCommand(Comparator<PartnerCourse> comparator) {
        this.comparator = comparator;
    }

    /**
     * TBD: This stub is to be removed after morphing is complete.
     *
     * @param model {@code Model} which the command should operate on.
     * @return Nothing.
     * @throws CommandException Always.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        throw new CommandException("TBD: this is a stub and should be removed after morph.");
    }

    @Override
    public CommandResult execute(SeplendidModel seplendidModel) throws CommandException {
        requireNonNull(seplendidModel);
        seplendidModel.updateSortedPartnerList(comparator);
        return new CommandResult(MESSAGE_SUCCESS,
                UiUtil.ListViewModel.PARTNER_COURSE_SORT);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof PartnerCourseSortCommand)) {
            return false;
        }

        PartnerCourseSortCommand otherPartnerCourseSortCommand = (PartnerCourseSortCommand) other;
        return comparator.equals(otherPartnerCourseSortCommand.comparator);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("partnerCourseAttributeToSort", comparator)
                .toString();
    }
}
