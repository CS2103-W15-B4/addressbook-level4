package seedu.address.logic.commands;

import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import seedu.address.logic.commands.exceptions.CommandException;

/**
 * Lists all persons in the address book to the user.
 */
public class TaskByEndCommand extends Command {

    public static final String COMMAND_WORD = "taskbyend";
    public static final String COMMAND_ALIAS = "byend";

    public static final String MESSAGE_SUCCESS = "Task sorted by end date now.";


    @Override
    public CommandResult execute() {
        model.taskByEnd();
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
