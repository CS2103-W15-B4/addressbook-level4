# KongjiaQi
###### /resources/view/TaskListCard.fxml
``` fxml
        <ImageView fx:id="mark" />
      </HBox>
      <FlowPane fx:id="tags" />
      <Label fx:id="description" styleClass="cell_small_label" text="\$description" />
      <Label fx:id="startDateTime" styleClass="cell_small_label" text="\$start" />
      <Label fx:id="endDateTime" styleClass="cell_small_label" text="\$end" />
    </VBox>
      <rowConstraints>
         <RowConstraints />
      </rowConstraints>
  </GridPane>
</HBox>
```
###### /java/seedu/address/ui/TaskCard.java
``` java
    /**
     * Initialize mark for task card
     * @param task
     */
    private void initMark(ReadOnlyTask task) {
        Image markIcon = new Image(ICON);
        if (task.getComplete()) {
            mark.setImage(markIcon);
        }

    }
    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof TaskCard)) {
            return false;
        }

        // state check
        TaskCard card = (TaskCard) other;
        return id.getText().equals(card.id.getText())
                && task.equals(card.task);
    }
}
```
###### /java/seedu/address/ui/BrowserPanel.java
``` java
    public static final String GOOGLE_DIR_URL_PREFIX = "https://www.google.com.sg/maps/dir//";

    private static final String FXML = "BrowserPanel.fxml";

    private final Logger logger = LogsCenter.getLogger(this.getClass());

    @FXML
    private WebView browser;

    public BrowserPanel() {
        super(FXML);

        // To prevent triggering events for typing inside the loaded Web page.
        getRoot().setOnKeyPressed(Event::consume);

        loadDefaultPage();
        registerAsAnEventHandler(this);
    }

    /**
     * Loads a person page with his location.
     */
    private void loadPersonPage(ReadOnlyPerson person) {
        loadPage(GOOGLE_DIR_URL_PREFIX  + person.getAddress().toString().replaceAll(" ", "+")
                .replaceAll(",", "+")
                .replaceAll("#", "+")
                .replaceAll("-", "+"));
    }

    public void loadPage(String url) {
        Platform.runLater(() -> browser.getEngine().load(url));
    }

    /**
     * Loads a default HTML file with a background that matches the general theme.
     */
    private void loadDefaultPage() {
        URL defaultPage = MainApp.class.getResource(FXML_FILE_FOLDER + DEFAULT_PAGE);
        loadPage(defaultPage.toExternalForm());
    }

    /**
     * Frees resources allocated to the browser.
     */
    public void freeResources() {
        browser = null;
    }

    @Subscribe
    private void handlePersonPanelSelectionChangedEvent(PersonPanelSelectionChangedEvent event) {
        logger.info(LogsCenter.getEventHandlingLogMessage(event));
        loadPersonPage(event.getNewSelection().person);
    }
}
```
###### /java/seedu/address/ui/TaskListPanel.java
``` java
    /**
     * Some comment
     */
    class TaskListViewCell extends ListCell<TaskCard> {

        @Override
        protected void updateItem(TaskCard task, boolean empty) {
            super.updateItem(task, empty);

            if (showAllTask) {
                if (empty || task == null) {
                    setGraphic(null);
                    setText(null);
                } else {
                    setGraphic(task.getRoot());
                }
            } else {
                if (empty || task == null || task.getTask().getComplete()) {
                    setGraphic(null);
                    setText(null);
                } else {
                    setGraphic(task.getRoot());
                }
            }
        }
    }

}
```
###### /java/seedu/address/logic/parser/AddressBookParser.java
``` java
        case EditTaskCommand.COMMAND_WORD:
        case EditTaskCommand.COMMAND_ALIAS:
            return new EditTaskCommandParser().parse(arguments);

        case SelectCommand.COMMAND_WORD:
        case SelectCommand.COMMAND_ALIAS:
            return new SelectCommandParser().parse(arguments);

        case SelectTaskCommand.COMMAND_WORD:
        case SelectTaskCommand.COMMAND_ALIAS:
            return new SelectTaskCommandParser().parse(arguments);

        case DeleteCommand.COMMAND_WORD:
        case DeleteCommand.COMMAND_ALIAS:
            return new DeleteCommandParser().parse(arguments);

        case DeleteTaskCommand.COMMAND_WORD:
        case DeleteTaskCommand.COMMAND_ALIAS:
            return new DeleteTaskCommandParser().parse(arguments);

        case LinkCommand.COMMAND_WORD:
        case LinkCommand.COMMAND_ALIAS:
            return new LinkCommandParser().parse(arguments);
        case LinkedTasksCommand.COMMAND_WORD:
        case LinkedTasksCommand.COMMAND_ALIAS:
            return new LinkedTasksCommandParser().parse(arguments);
        case LinkedPersonsCommand.COMMAND_WORD:
        case LinkedPersonsCommand.COMMAND_ALIAS:
            return new LinkedPersonsCommandParser().parse(arguments);

```
###### /java/seedu/address/logic/parser/AddressBookParser.java
``` java
        case MarkTaskCommand.COMMAND_WORD:
        case MarkTaskCommand.COMMAND_ALIAS:
            return new MarkTaskCommandParser().parse(arguments);

        case ClearCommand.COMMAND_WORD:
        case ClearCommand.COMMAND_ALIAS:
            return new ClearCommand();

        case ClearTaskCommand.COMMAND_WORD:
        case ClearTaskCommand.COMMAND_ALIAS:
            return new ClearTaskCommand();

        case FindCommand.COMMAND_WORD:
        case FindCommand.COMMAND_ALIAS:
            return new FindCommandParser().parse(arguments);

        case RemarkCommand.COMMAND_WORD:
            return new RemarkCommandParser().parse(arguments);

        case SetPriorityCommand.COMMAND_WORD:
        case SetPriorityCommand.COMMAND_ALIAS:
            return new SetPriorityCommandParser().parse(arguments);

        case ListCommand.COMMAND_WORD:
        case ListCommand.COMMAND_ALIAS:
            return new ListCommand();

```
###### /java/seedu/address/logic/parser/AddressBookParser.java
``` java
        case ListTaskCommand.COMMAND_WORD:
        case ListTaskCommand.COMMAND_ALIAS:
            return new ListTaskCommand();

        case ExportCommand.COMMAND_WORD:
        case ExportCommand.COMMAND_ALIAS:
            return new ExportCommandParser().parse(arguments);

        case TaskByEndCommand.COMMAND_WORD:
        case TaskByEndCommand.COMMAND_ALIAS:
            return new TaskByEndCommand();

        case TaskByPriorityCommand.COMMAND_WORD:
        case TaskByPriorityCommand.COMMAND_ALIAS:
            return new TaskByPriorityCommand();

        case ExportTaskCommand.COMMAND_WORD:
        case ExportTaskCommand.COMMAND_ALIAS:
            return new ExportTaskCommandParser().parse(arguments);

        case HistoryCommand.COMMAND_WORD:
        case HistoryCommand.COMMAND_ALIAS:
            return new HistoryCommand();

        case ExitCommand.COMMAND_WORD:
        case ExitCommand.COMMAND_ALIAS:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
        case HelpCommand.COMMAND_ALIAS:
            return new HelpCommand();

        case UndoCommand.COMMAND_WORD:
        case UndoCommand.COMMAND_ALIAS:
            return new UndoCommand();

        case RedoCommand.COMMAND_WORD:
        case RedoCommand.COMMAND_ALIAS:
            return new RedoCommand();


        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
```
###### /java/seedu/address/logic/parser/ParserUtil.java
``` java
    /**
     * Parses a {@code Optional<String> stringOptional} into an optional of the same type
     * @param stringOptional , the optional passed in
     * @return optional of String type
     * @throws IllegalValueException
     */
    public static Optional<String> parseString(Optional<String> stringOptional) throws IllegalValueException {
        requireNonNull(stringOptional);
        return stringOptional.isPresent() ? Optional.of(stringOptional.get()) : Optional.empty();
    }

    /**
     * Parser a {@code Optional<Boolean> complete} into an optional of the same type
     */
    public static Optional<Boolean> parseBoolean(Optional<Boolean> complete) throws IllegalValueException {
        requireNonNull(complete);
        return Optional.of(complete.get());
    }

    /**
     * Parses a {@code Optional<String> phone} into an {@code Optional<Phone>} if {@code phone} is present.
     * See header comment of this class regarding the use of {@code Optional} parameters.
     */
    public static Optional<Phone> parsePhone(Optional<String> phone) throws IllegalValueException {
        requireNonNull(phone);
        return phone.isPresent() ? Optional.of(new Phone(phone.get())) : Optional.empty();
    }

    /**
     * Parses a {@code Optional<String> address} into an {@code Optional<Address>} if {@code address} is present.
     * See header comment of this class regarding the use of {@code Optional} parameters.
     */
    public static Optional<Address> parseAddress(Optional<String> address) throws IllegalValueException {
        requireNonNull(address);
        return address.isPresent() ? Optional.of(new Address(address.get())) : Optional.empty();
    }

    /**
     * Parses a {@code Optional<String> email} into an {@code Optional<Email>} if {@code email} is present.
     * See header comment of this class regarding the use of {@code Optional} parameters.
     */
    public static Optional<Email> parseEmail(Optional<String> email) throws IllegalValueException {
        requireNonNull(email);
        return email.isPresent() ? Optional.of(new Email(email.get())) : Optional.empty();
    }

    /**
     * Parses a {@code Optional<String> integerString} into an {@code Optional<Integer>} if it is present.
     * See header comment for usage of {@code Optional} parameters.
     * @param integerString , an optional of String
     * @return an optional if integer if the optional of String input is present
     * @throws IllegalValueException
     */
    public static Optional<Integer> parseInteger(Optional<String> integerString) throws IllegalValueException {
        if (!integerString.isPresent()) {
            return Optional.empty();
        }
        String string = integerString.get();
        string = string.trim();
        if (!StringUtil.isNonZeroUnsignedInteger(string)) {
            throw new IllegalValueException(MESSAGE_INVALID_INTEGER);
        }
        return integerString.isPresent() ? Optional.of(Integer.parseInt(
                integerString.get().trim())) : Optional.empty();
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>}.
     */
    public static Set<Tag> parseTags(Collection<String> tags) throws IllegalValueException {
        requireNonNull(tags);
        final Set<Tag> tagSet = new HashSet<>();
        for (String tagName : tags) {
            tagSet.add(new Tag(tagName));
        }
        return tagSet;
    }
}
```
###### /java/seedu/address/logic/parser/MarkTaskCommandParser.java
``` java
package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.MarkTaskCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new MarkCommand object
 */
public class MarkTaskCommandParser implements Parser<MarkTaskCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the MarkTaskCommand
     * and returns an MarkTaskCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public MarkTaskCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new MarkTaskCommand(index);
        } catch (IllegalValueException ive) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, MarkTaskCommand.MESSAGE_USAGE));
        }
    }

}
```
###### /java/seedu/address/logic/parser/EditTaskCommandParser.java
``` java

package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_END_DATE_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START_DATE_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.EditTaskCommand;
import seedu.address.logic.commands.EditTaskCommand.EditTaskDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.DateTime;
import seedu.address.model.task.Description;
import seedu.address.model.task.Name;

/**
 * Parses input arguments and creates a new EditTaskCommand object
 */
public class EditTaskCommandParser implements Parser<EditTaskCommand> {

```
###### /java/seedu/address/logic/commands/ListTaskCommand.java
``` java
package seedu.address.logic.commands;

import static seedu.address.model.Model.PREDICATE_SHOW_ALL_TASKS;

/**
 * Lists all tasks in the task book to the user.
 */
public class ListTaskCommand extends Command {

    public static final String COMMAND_WORD = "listTask";
    public static final String COMMAND_ALIAS = "lt";

    public static final String MESSAGE_SUCCESS = "Listed all tasks";


    @Override
    public CommandResult execute() {

        model.updateFilteredTaskList(PREDICATE_SHOW_ALL_TASKS);

        return new CommandResult(MESSAGE_SUCCESS);
    }
}
```
###### /java/seedu/address/logic/commands/EditTaskCommand.java
``` java
package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_END_DATE_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START_DATE_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_TASKS;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.DateTime;
import seedu.address.model.task.Description;
import seedu.address.model.task.Name;
import seedu.address.model.task.ReadOnlyTask;
import seedu.address.model.task.Task;
import seedu.address.model.task.exceptions.DuplicateTaskException;
import seedu.address.model.task.exceptions.TaskNotFoundException;

/**
 * Edits the details of an existing task in the address book.
 */

public class EditTaskCommand extends UndoableCommand {

    public static final String COMMAND_WORD = "editTask";
    public static final String COMMAND_ALIAS = "edt";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the task identified "
            + "by the index number used in the last task listing. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_DESCRIPTION + "DESCRIPTION] "
            + "[" + PREFIX_START_DATE_TIME + "START_DATE_TIME] "
            + "[" + PREFIX_END_DATE_TIME + "END_DATE_TIME] "
            //+ "[" + PREFIX_PRIORITY + "INTEGER[1~5] "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_NAME + "picnic "
            + PREFIX_DESCRIPTION + "have fun at Botanic Garden "
            + PREFIX_START_DATE_TIME + "1/1/2017 12:00pm "
            + PREFIX_END_DATE_TIME + "1/1/2017 15:00pm "
            + PREFIX_TAG + "friends";

    public static final String MESSAGE_EDIT_TASK_SUCCESS = "Edited Task: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_TASK = "This task already exists in the task book.";
    public static final String MESSAGE_DATE_TIME_TASK = "This task edit has date time error: start <= end.";

    private final Index index;
    private final EditTaskDescriptor editTaskDescriptor;

    /**
     * @param index of the task in the filtered task list to edit
     * @param editTaskDescriptor details to edit the task with
     */
    public EditTaskCommand(Index index, EditTaskDescriptor editTaskDescriptor) {
        requireNonNull(index);
        requireNonNull(editTaskDescriptor);

        this.index = index;
        this.editTaskDescriptor = new EditTaskDescriptor(editTaskDescriptor);
    }
```
###### /java/seedu/address/logic/commands/MarkTaskCommand.java
``` java
package seedu.address.logic.commands;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.task.ReadOnlyTask;
import seedu.address.model.task.exceptions.TaskNotFoundException;

/**
 * Marks a task identified as completed using it's last displayed index from the address book.
 * When list tasks again the marked tasks will not be shown, but can be reviewed when the history is listed.
 */
public class MarkTaskCommand extends UndoableCommand {

    public static final String COMMAND_WORD = "markTask";
    public static final String COMMAND_ALIAS = "mt";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Marks the task identified to be completed by the index number used in the last task listing.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_MARK_TASK_SUCCESS = "Marked Task: %1$s";

    private final Index targetIndex;

    public MarkTaskCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }


    @Override
    public CommandResult executeUndoableCommand() throws CommandException {

        List<ReadOnlyTask> lastShownList = model.getSortedTaskList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        }

        ReadOnlyTask taskToComplete = lastShownList.get(targetIndex.getZeroBased());

        try {
            model.markTask(taskToComplete);
        } catch (TaskNotFoundException tnfe) {
            assert false : "The target task cannot be missing";
        }

        return new CommandResult(String.format(MESSAGE_MARK_TASK_SUCCESS, taskToComplete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof MarkTaskCommand // instanceof handles nulls
                && this.targetIndex.equals(((MarkTaskCommand) other).targetIndex)); // state check
    }
}
```
###### /java/seedu/address/model/task/Description.java
``` java
package seedu.address.model.task;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.exceptions.IllegalValueException;

/**
 * Represents a Task's description in the task book.
 * Guarantees: immutable; is valid as declared in {@link #isValidDescription(String)}
 */
public class Description {

    public static final String MESSAGE_DESCRIPTION_CONSTRAINTS =
            "Task description can take any values, and it should not be blank";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String DESCRIPTION_VALIDATION_REGEX = "[^\\s].*";

    public final String value;

    /**
     * Validates given description.
     *
     * @throws IllegalValueException if given description string is invalid.
     */
    public Description(String description) throws IllegalValueException {
        requireNonNull(description);
        if (!isValidDescription(description)) {
            throw new IllegalValueException(MESSAGE_DESCRIPTION_CONSTRAINTS);
        }
        this.value = description;
    }

    /**
     * Returns true if a given string is a valid task description.
     */
    public static boolean isValidDescription(String test) {
        return test.matches(DESCRIPTION_VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Description // instanceof handles nulls
                && this.value.equals(((Description) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
```
###### /java/seedu/address/model/task/UniqueTaskList.java
``` java
    /**
     * Replaces the task {@code target} in the list with {@code editedTask}.
     *
     * @throws DuplicateTaskException if the replacement is equivalent to another existing task in the list.
     * @throws TaskNotFoundException if {@code target} could not be found in the list.
     */
    public void setTask(ReadOnlyTask target, ReadOnlyTask editedTask)
            throws DuplicateTaskException, TaskNotFoundException {
        requireNonNull(editedTask);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new TaskNotFoundException();
        }

        if (!target.equals(editedTask) && internalList.contains(editedTask)) {
            throw new DuplicateTaskException();
        }

        internalList.set(index, new Task(editedTask));
    }

    /**
     * Marks a task in the list to be completed.
     *
     * @throws TaskNotFoundException if {@code target} could not be found in the list.
     */
    public void setComplete(ReadOnlyTask task) throws TaskNotFoundException {
        requireNonNull(task);
        int index = internalList.indexOf(task);
        if (index == -1) {
            throw new TaskNotFoundException();
        }
        Task completedTask = new Task(task);
        completedTask.setComplete();
        internalList.set(index, completedTask);
    }

```
###### /java/seedu/address/model/task/Name.java
``` java
package seedu.address.model.task;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.exceptions.IllegalValueException;

/**
 * Represents a Task's name in the task book.
 * Guarantees: immutable; is valid as declared in {@link #isValidName(String)}
 */
public class Name {

    public static final String MESSAGE_NAME_CONSTRAINTS =
            "Task names should only contain alphanumeric characters and spaces, and it should not be blank";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String NAME_VALIDATION_REGEX = "[^\\s].*";

    public final String name;

    /**
     * Validates given name.
     *
     * @throws IllegalValueException if given name string is invalid.
     */
    public Name(String name) throws IllegalValueException {
        requireNonNull(name);
        if (!isValidName(name)) {
            throw new IllegalValueException(MESSAGE_NAME_CONSTRAINTS);
        }
        this.name = name;
    }

    /**
     * Returns true if a given string is a valid task name.
     */
    public static boolean isValidName(String test) {
        return test.matches(NAME_VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Name // instanceof handles nulls
                && this.name.equals(((Name) other).name)); // state check
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

}

```
###### /java/seedu/address/model/TaskBook.java
``` java
    /**
     * Marks an existing task to be completed.
     * @throws TaskNotFoundException if the task could not be found in the list..
     */
    public void markTask(ReadOnlyTask p) throws TaskNotFoundException {
        tasks.setComplete(p);
    }

```