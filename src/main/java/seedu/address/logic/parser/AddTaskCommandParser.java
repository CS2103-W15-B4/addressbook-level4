//@@author ShaocongDong
package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_END_DATE_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASKNAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRIORITY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START_DATE_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.AddTaskCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.*;

/**
 * A parser class for addTask Command
 */
public class AddTaskCommandParser implements Parser<AddTaskCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddTask Command
     * and returns an AddTask Command object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddTaskCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_TASKNAME, PREFIX_DESCRIPTION,
                        PREFIX_START_DATE_TIME, PREFIX_END_DATE_TIME, PREFIX_PRIORITY, PREFIX_TAG);

        if (!arePrefixesPresent(argMultimap, PREFIX_TASKNAME, PREFIX_DESCRIPTION,
                PREFIX_START_DATE_TIME, PREFIX_END_DATE_TIME)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddTaskCommand.MESSAGE_USAGE));
        }

        try {
            TaskName taskName = ParserUtil.parseTaskName(argMultimap.getValue(PREFIX_TASKNAME)).get();
            Description description = ParserUtil.parseDescription(argMultimap.getValue(PREFIX_DESCRIPTION)).get();
            StartDateTime startDateTime = ParserUtil.parseStart(argMultimap.getValue(PREFIX_START_DATE_TIME)).get();
            EndDateTime endDateTime = ParserUtil.parseEnd(argMultimap.getValue(PREFIX_END_DATE_TIME)).get();

            Optional<Integer> priority = ParserUtil.parseInteger(argMultimap.getValue(PREFIX_PRIORITY));

            Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));
            Boolean complete = false;
            ReadOnlyTask task;
            // Renew the task object with the priority parameter specially set if given
            if (priority.isPresent()) {
                Integer priorityValue = priority.get();
                task = new Task(taskName, description, startDateTime, endDateTime, tagList, complete, priorityValue);
            } else {
                task = new Task(taskName, description, startDateTime, endDateTime, tagList, complete);
            }

            return new AddTaskCommand(task);
        } catch (IllegalValueException ive) {
            throw new ParseException(ive.getMessage(), ive);
        }
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
