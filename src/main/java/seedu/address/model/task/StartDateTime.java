package seedu.address.model.task;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.exceptions.IllegalValueException;

/**
 * Represents a Task's start time in the task book.
 * Guarantees: immutable; is valid as declared in {@link #isValidStart(String)}
 */
public class StartDateTime {

    public static final String MESSAGE_START_CONSTRAINTS =
            "Start time can only contain numbers and '/', and should be valid date";
    public static final String START_VALIDATION_REGEX =
            "(0?[1-9]|[12][0-9]|3[01])/(0?[1-9]|1[012])/((19|20)\\\\d\\\\d)}";
    public final String value;

    /**
     * Validates given start date time.
     *
     * @throws IllegalValueException if given start date time string is invalid.
     */
    public StartDateTime(String start) throws IllegalValueException {
        requireNonNull(start);
        String trimmedStart = start.trim();
        if (!isValidStart(trimmedStart)) {
            throw new IllegalValueException(MESSAGE_START_CONSTRAINTS);
        }
        this.value = trimmedStart;
    }

    /**
     * Returns true if a given string is a valid start date time.
     */
    public static boolean isValidStart(String test) {
        return test.matches(START_VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof StartDateTime // instanceof handles nulls
                && this.value.equals(((StartDateTime) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
