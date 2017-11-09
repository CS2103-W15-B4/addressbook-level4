package seedu.address.model.task;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.exceptions.IllegalValueException;

/**
 * Represents a Task's name in the task book.
 * Guarantees: immutable; is valid as declared in {@link #isValidTaskName(String)}
 */
public class TaskName {

    public static final String MESSAGE_NAME_CONSTRAINTS =
            "Task names should only contain alphanumeric characters and spaces, and it should not be blank";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String NAME_VALIDATION_REGEX = "[^\\s].*";

    public final String taskName;

    /**
     * Validates given name.
     *
     * @throws IllegalValueException if given name string is invalid.
     */
    public TaskName(String taskName) throws IllegalValueException {
        requireNonNull(taskName);
        if (!isValidTaskName(taskName)) {
            throw new IllegalValueException(MESSAGE_NAME_CONSTRAINTS);
        }
        this.taskName = taskName;
    }

    /**
     * Returns true if a given string is a valid task name.
     */
    public static boolean isValidTaskName(String test) {
        return test.matches(NAME_VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return taskName;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TaskName // instanceof handles nulls
                && this.taskName.equals(((TaskName) other).taskName)); // state check
    }

    @Override
    public int hashCode() {
        return taskName.hashCode();
    }

}

