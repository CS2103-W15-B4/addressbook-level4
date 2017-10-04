package seedu.address.commons.events.ui;

import seedu.address.commons.events.BaseEvent;

/**
 * Indicates that a new result is available.
 */
public class NewResultAvailableEvent extends BaseEvent {

    public final String message;
    private final boolean isError;

    public boolean checkError(){
        return isError;
    }

    public NewResultAvailableEvent(String message, boolean error) {
        this.message = message;
        this.isError = error;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }

}
