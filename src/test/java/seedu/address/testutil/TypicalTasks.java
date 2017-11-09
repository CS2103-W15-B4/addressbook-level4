//@@author Qi Kongjia
package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_HOTPOT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_DEMO;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_HOTPOT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_DEMO;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_START_HOTPOT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_START_DEMO;
import static seedu.address.logic.commands.CommandTestUtil.VALID_END_HOTPOT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_END_DEMO;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HOTPOT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_DEMO;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.TaskBook;
import seedu.address.model.person.ReadOnlyPerson;
import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.model.task.ReadOnlyTask;
import seedu.address.model.task.Task;
import seedu.address.model.task.exceptions.DuplicateTaskException;

/**
 * A utility class containing a list of {@code Person} objects to be used in tests.
 */
public class TypicalTasks {

    public static final ReadOnlyTask PICNIC = new TaskBuilder().withName("picnic")
            .withDescription("Have a good time with my best friends").withStart("20/05/2018-12:00pm")
            .withEnd("20/05/2018-13:00pm")
            .withTags("Friends", "Fun").build();

    public static final ReadOnlyTask MEETING = new TaskBuilder().withName("meeting")
            .withDescription("Have a CS2101 group meeting for oral presentation 2").withStart(
            "20/05/2017-12:00pm").withEnd(
            "20/05/2017-13:00pm").withTags("Study").build();

    public static final ReadOnlyTask EXAM = new TaskBuilder().withName("CS2103 exam").withDescription(
            "Have a final exam for CS2103T").withStart(
            "20/05/2017-15:00pm").withEnd(
            "20/05/2017-16:00pm").withTags("Study").build();

    // Manually added - Task's details found in {@code CommandTestUtil}
    public static final ReadOnlyTask DEMO = new TaskBuilder().withName(VALID_NAME_DEMO)
            .withDescription(VALID_DESCRIPTION_DEMO)
            .withStart(VALID_START_DEMO).withEnd(VALID_END_DEMO).withTags(VALID_TAG_DEMO).build();
    public static final ReadOnlyTask HOTPOT = new TaskBuilder().withName(VALID_NAME_HOTPOT)
            .withDescription(VALID_DESCRIPTION_HOTPOT)
            .withStart(VALID_START_HOTPOT).withEnd(VALID_END_HOTPOT).withTags(VALID_TAG_HOTPOT)
            .build();




    private TypicalTasks() {} // prevents instantiation

    /**
     * get a typical task book, keep it empty for now
     * @return
     */
    public static TaskBook getTypicalTaskBook() {
        TaskBook tb = new TaskBook();
        for (ReadOnlyTask rot : getTypicalTasks()) {
            try {
                tb.addTask(rot);
            } catch (DuplicateTaskException e) {
                assert false : "There are duplicate tasks in this TaskBook";
            }
        }
        return tb;
    }

    /**
     * return the typical tasks inside this application - examples for users
     * @return the typical tasks as an arrayList
     */
    public static List<ReadOnlyTask> getTypicalTasks() {
        return new ArrayList<>(Arrays.asList(PICNIC, MEETING, EXAM));
    }


}
