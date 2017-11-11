package seedu.address.model.task;

import seedu.address.commons.exceptions.IllegalValueException;

/**
 * Generic dateTime Class specially catered for our task (start time and end time)
 * we apply abstraction design pattern here and we check differently from how current public package does
 * Current format is very rigid, changing it to be more flexible will be a future enhancement
 */
public class DateTime {
    public static final String MESSAGE_DATE_TIME_FORMAT_CONSTRAINTS =
            "The date time input should follow this format: dd-mm-YYYY "
                    + "hh:mm[am/pm] day-month-year hour(12):minute am/pm";

    public static final String MESSAGE_DATE_TIME_VALUE_CONSTRAINTS =
            "The format is correct but the values are not: dd-mm-YYYY "
                    + "hh:mm[am/pm] day-month-year hour(12):minute am/pm";

    /**
     * The data time input in our app currently have following rigit format
     * dd-mm-YYYY hh:mm[am/pm] day-month-year hour:minute am/pm
     */
    public static final String DATE_TIME_VALIDATION_REGEX =
            "\\d{2}-\\d{2}-\\d{4}\\s{1}\\d{2}:\\d{2}pm|\\d{2}-\\d{2}-\\d{4}\\s{1}\\d{2}:\\d{2}am";

    private int day;
    private int month;
    private int year;
    private int hour;
    private int minute;
    private String state;

    /**
     * Constructor and checker for the date time object
     * @param dateTime
     * @throws IllegalValueException
     */
    public DateTime (String dateTime) throws IllegalValueException {
        //check the format:
        if (!isValidDateTime(dateTime)) {
            throw new IllegalValueException(MESSAGE_DATE_TIME_FORMAT_CONSTRAINTS);
        }

        //Now we can safely proceed to decompose the inputs
        day = Integer.parseInt(dateTime.substring(0, 2));
        month = Integer.parseInt(dateTime.substring(3, 5));
        year = Integer.parseInt(dateTime.substring(6, 10));
        hour = Integer.parseInt(dateTime.substring(11, 13));
        minute = Integer.parseInt(dateTime.substring(14, 16));
        state = dateTime.substring(16);

        // value checking helper
        boolean isLeapYear = ((year % 4 == 0) && (year % 100 != 0) || (year % 400 == 0));

        //check the values:
        if (month < 0 || month > 12) {
            throw new IllegalValueException(MESSAGE_DATE_TIME_VALUE_CONSTRAINTS);
        } else if (day < 0) {
            throw new IllegalValueException(MESSAGE_DATE_TIME_VALUE_CONSTRAINTS);
        } else {
            if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
                if (day > 31) {
                    throw new IllegalValueException(MESSAGE_DATE_TIME_VALUE_CONSTRAINTS);
                }
            } else if (month == 2) {
                if ((isLeapYear && day > 29) || (!isLeapYear && day > 28)) {
                    throw new IllegalValueException(MESSAGE_DATE_TIME_VALUE_CONSTRAINTS);
                }
            } else {
                if (day > 30) {
                    throw new IllegalValueException(MESSAGE_DATE_TIME_VALUE_CONSTRAINTS);
                }
            }
        }

        if (hour < 0 || hour > 12) {
            throw new IllegalValueException(MESSAGE_DATE_TIME_VALUE_CONSTRAINTS);
        }

        if (minute < 0 || minute > 60) {
            throw new IllegalValueException(MESSAGE_DATE_TIME_VALUE_CONSTRAINTS);
        }

        if (!(state.equals("am") || state.equals("pm"))) {
            throw new IllegalValueException(MESSAGE_DATE_TIME_VALUE_CONSTRAINTS);
        }
        // At this point, the values and formats are both correct.
    }

    public static boolean isValidDateTime(String test) {
        return test.matches(DATE_TIME_VALIDATION_REGEX);
    }

    /**
     * Convert our date time object to String
     * @return
     */
    public String toString() {
        String dayString = Integer.toString(day);
        String monthString = Integer.toString(month);
        String yearString = Integer.toString(year);
        String hourString = Integer.toString(hour);
        String minuteString = Integer.toString(minute);
        return dayString + "-" + monthString + "-" + yearString + " " + hourString + ":" + minuteString + state;
    }

    /**
     * Hashcode getter for our date time object
     * @return the string representation's hash code
     */
    public int hashCode() {
        return this.toString().hashCode();
    }
}
