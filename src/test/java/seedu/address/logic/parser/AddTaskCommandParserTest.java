//@@author Qi Kongjia
package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.DESC_DESC_DEMO;
import static seedu.address.logic.commands.CommandTestUtil.DESC_DESC_HOTPOT;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.END_DESC_DEMO;
import static seedu.address.logic.commands.CommandTestUtil.END_DESC_HOTPOT;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_ADDRESS_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_DEMO;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_HOTPOT;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.START_DESC_DEMO;
import static seedu.address.logic.commands.CommandTestUtil.START_DESC_HOTPOT;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_DEMO;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_HOTPOT;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_DEMO;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_HOTPOT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_END_DEMO;
import static seedu.address.logic.commands.CommandTestUtil.VALID_END_HOTPOT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_DEMO;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_HOTPOT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_START_DEMO;
import static seedu.address.logic.commands.CommandTestUtil.VALID_START_HOTPOT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_DEMO;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HOTPOT;

import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.Test;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.AddTaskCommand;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.Description;
import seedu.address.model.task.EndDateTime;
import seedu.address.model.task.StartDateTime;
import seedu.address.model.task.TaskName;
import seedu.address.model.task.Task;

import seedu.address.testutil.PersonBuilder;
import seedu.address.testutil.TaskBuilder;

public class AddTaskCommandParserTest {
    private AddTaskCommandParser parser = new AddTaskCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Task expectedTask = new TaskBuilder().withName(VALID_NAME_HOTPOT).withDescription(VALID_DESCRIPTION_HOTPOT)
                .withStart(VALID_START_HOTPOT).withEnd(VALID_END_HOTPOT).withTags(VALID_TAG_HOTPOT).build();

        // multiple names - last name accepted
        assertParseSuccess(parser, AddTaskCommand.COMMAND_WORD + NAME_DESC_DEMO
                + NAME_DESC_HOTPOT+ DESC_DESC_HOTPOT
                + START_DESC_HOTPOT + END_DESC_HOTPOT + TAG_DESC_HOTPOT, new AddTaskCommand(expectedTask));

        // multiple descriptions - last description accepted
        assertParseSuccess(parser, AddTaskCommand.COMMAND_WORD + NAME_DESC_HOTPOT
                + DESC_DESC_DEMO + DESC_DESC_HOTPOT
                + START_DESC_HOTPOT + END_DESC_HOTPOT + TAG_DESC_HOTPOT, new AddTaskCommand(expectedTask));

        // multiple start time - last start time accepted
        assertParseSuccess(parser, AddTaskCommand.COMMAND_WORD + NAME_DESC_HOTPOT
                + DESC_DESC_HOTPOT + START_DESC_DEMO
                + START_DESC_HOTPOT + END_DESC_HOTPOT + TAG_DESC_HOTPOT, new AddTaskCommand(expectedTask));

        // multiple end time - last end time accepted
        assertParseSuccess(parser, AddTaskCommand.COMMAND_WORD + NAME_DESC_HOTPOT
                + DESC_DESC_HOTPOT + START_DESC_HOTPOT
                + END_DESC_DEMO + END_DESC_HOTPOT + TAG_DESC_HOTPOT, new AddTaskCommand(expectedTask));

        // multiple tags - all accepted
        Task expectedTaskMultipleTags = new TaskBuilder().withName(VALID_NAME_HOTPOT)
                .withDescription(VALID_DESCRIPTION_HOTPOT)
                .withStart(VALID_START_HOTPOT).withEnd(VALID_END_HOTPOT)
                .withTags(VALID_TAG_HOTPOT).build();
        assertParseSuccess(parser, AddTaskCommand.COMMAND_WORD + NAME_DESC_HOTPOT + DESC_DESC_HOTPOT
                        + START_DESC_HOTPOT + END_DESC_HOTPOT + TAG_DESC_HOTPOT,
                new AddTaskCommand(expectedTaskMultipleTags));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        Task expectedTask = new TaskBuilder().withName(VALID_NAME_DEMO).withDescription(VALID_DESCRIPTION_DEMO)
                .withStart(VALID_START_DEMO).withEnd(VALID_END_DEMO).withTags().build();
        assertParseSuccess(parser, AddTaskCommand.COMMAND_WORD + NAME_DESC_DEMO + DESC_DESC_DEMO
                + START_DESC_DEMO + END_DESC_DEMO, new AddTaskCommand(expectedTask));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddTaskCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, AddTaskCommand.COMMAND_WORD + VALID_NAME_HOTPOT + DESC_DESC_HOTPOT
                + START_DESC_HOTPOT + END_DESC_HOTPOT, expectedMessage);

        // missing description prefix
        assertParseFailure(parser, AddTaskCommand.COMMAND_WORD + NAME_DESC_HOTPOT + VALID_DESCRIPTION_HOTPOT
                + START_DESC_HOTPOT + END_DESC_HOTPOT, expectedMessage);

        // missing start time prefix
        assertParseFailure(parser, AddTaskCommand.COMMAND_WORD + NAME_DESC_HOTPOT + DESC_DESC_HOTPOT
                + VALID_START_HOTPOT + END_DESC_HOTPOT, expectedMessage);

        // missing end time prefix
        assertParseFailure(parser, AddTaskCommand.COMMAND_WORD + NAME_DESC_HOTPOT + DESC_DESC_HOTPOT
                + START_DESC_HOTPOT + VALID_END_HOTPOT, expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, AddTaskCommand.COMMAND_WORD + VALID_NAME_HOTPOT + VALID_DESCRIPTION_HOTPOT
                + VALID_START_HOTPOT + VALID_END_HOTPOT, expectedMessage);
    }

    /*@Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, AddTaskCommand.COMMAND_WORD
                + INVALID_NAME_DESC + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Name.MESSAGE_NAME_CONSTRAINTS);

        // invalid phone
        assertParseFailure(parser, AddCommand.COMMAND_WORD + NAME_DESC_BOB + INVALID_DESCRIPTION + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Phone.MESSAGE_PHONE_CONSTRAINTS);

        // invalid email
        assertParseFailure(parser, AddCommand.COMMAND_WORD + NAME_DESC_BOB + PHONE_DESC_BOB + INVALID_EMAIL_DESC
                + ADDRESS_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Email.MESSAGE_EMAIL_CONSTRAINTS);

        // invalid address
        assertParseFailure(parser, AddCommand.COMMAND_WORD + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                        + INVALID_ADDRESS_DESC + TAG_DESC_HUSBAND + TAG_DESC_FRIEND,
                Address.MESSAGE_ADDRESS_CONSTRAINTS);

        // invalid tag
        assertParseFailure(parser, AddCommand.COMMAND_WORD + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + INVALID_TAG_DESC + VALID_TAG_FRIEND, Tag.MESSAGE_TAG_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, AddCommand.COMMAND_WORD + INVALID_NAME_DESC + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + INVALID_ADDRESS_DESC, Name.MESSAGE_NAME_CONSTRAINTS);
    }*/
}
