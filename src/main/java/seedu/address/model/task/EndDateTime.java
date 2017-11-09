package seedu.address.model.task;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.exceptions.IllegalValueException;

/**
 * Represents a Task's end time in the task book.
 * Guarantees: immutable; is valid as declared in {@link #isValidEnd(String)}
 */
public class EndDateTime {

    public static final String MESSAGE_END_CONSTRAINTS =
            "End time can only contain numbers and '/', and should be valid date, and should behind start time";
    public static final String END_VALIDATION_REGEX =
            "(0?[1-9]|[12][0-9]|3[01])/(0?[1-9]|1[012])/((19|20)\\\\d\\\\d)}";
    public final String value;

    /**
     * Validates given end date time.
     *
     * @throws IllegalValueException if given end date time string is invalid.
     */
    public EndDateTime(String end) throws IllegalValueException {
        requireNonNull(end);
        String trimmedEnd = end.trim();
        if (!isValidEnd(trimmedEnd)) {
            throw new IllegalValueException(MESSAGE_END_CONSTRAINTS);
        }
        this.value = trimmedEnd;
    }

    /**
     * Returns true if a given string is a valid end date time.
     */
    public static boolean isValidEnd(String test) {
        return test.matches(END_VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof EndDateTime // instanceof handles nulls
                && this.value.equals(((EndDateTime) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
