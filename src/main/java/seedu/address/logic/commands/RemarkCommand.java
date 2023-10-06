package seedu.address.logic.commands;

import seedu.address.model.Model;

/**
 * Adds a remark to an existing person in the address book.
 */
public class RemarkCommand extends Command {

    public static final String COMMAND_WORD = "remark";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a remark to the person identified";

    public static final String MESSAGE_SUCCESS = "Remark added for Person: %1$s";
    public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists in the address book.";

    // private final Index index;

    public RemarkCommand() {

    }

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult("Hello from remark");
    }
}
