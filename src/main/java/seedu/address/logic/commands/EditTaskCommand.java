package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_END_DATE_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START_DATE_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASKNAME;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_TASKS;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.*;
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
            + "[" + PREFIX_TASKNAME + "TASK_NAME] "
            + "[" + PREFIX_DESCRIPTION + "DESCRIPTION] "
            + "[" + PREFIX_START_DATE_TIME + "START_DATE_TIME] "
            + "[" + PREFIX_END_DATE_TIME + "END_DATE_TIME] "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_TASKNAME + "picnic "
            + PREFIX_DESCRIPTION + "have fun at Botanic Garden "
            + PREFIX_START_DATE_TIME + "1/1/2017 12:00pm "
            + PREFIX_END_DATE_TIME + "1/1/2017 15:00pm "
            + PREFIX_TAG + "friends";

    public static final String MESSAGE_EDIT_TASK_SUCCESS = "Edited Task: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_TASK = "This task already exists in the task book.";

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

    @Override
    public CommandResult executeUndoableCommand() throws CommandException {
        List<ReadOnlyTask> lastShownList = model.getSortedTaskList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        }

        ReadOnlyTask taskToEdit = lastShownList.get(index.getZeroBased());
        Task editedTask = createEditedTask(taskToEdit, editTaskDescriptor);

        try {
            model.updateTask(taskToEdit, editedTask);
        } catch (DuplicateTaskException dpe) {
            throw new CommandException(MESSAGE_DUPLICATE_TASK);
        } catch (TaskNotFoundException tnfe) {
            throw new AssertionError("The target task cannot be missing");
        }
        model.updateFilteredTaskList(PREDICATE_SHOW_ALL_TASKS);
        return new CommandResult(String.format(MESSAGE_EDIT_TASK_SUCCESS, editedTask));
    }

    /**
     * Creates and returns a {@code Task} with the details of {@code taskToEdit}
     * edited with {@code editTaskDescriptor}.
     */
    private static Task createEditedTask(ReadOnlyTask taskToEdit,
                                             EditTaskDescriptor editTaskDescriptor) {
        assert taskToEdit != null;

        TaskName updatedTaskName = editTaskDescriptor.getTaskName().orElse(taskToEdit.getTaskName());
        Description updatedDescription = editTaskDescriptor.getDescription().orElse(taskToEdit.getDescription());
        StartDateTime updatedStartDateTime = editTaskDescriptor.getStartDateTime().orElse(taskToEdit.getStartDateTime());
        EndDateTime updatedEndDateTime = editTaskDescriptor.getEndDateTime().orElse(taskToEdit.getEndDateTime());
        Set<Tag> updatedTags = editTaskDescriptor.getTags().orElse(taskToEdit.getTags());
        Boolean updateComplete = editTaskDescriptor.getComplete().orElse(taskToEdit.getComplete());
        //Remark updatedRemark = taskToEdit.getRemark(); // edit command does not allow editing remarks
        Integer originalPriority = taskToEdit.getPriority(); // edit command is not used to update priority
        Integer id = taskToEdit.getId();
        ArrayList<Integer> peopleIds = taskToEdit.getPeopleIds();
        return new Task(updatedTaskName, updatedDescription, updatedStartDateTime, updatedEndDateTime,
                updatedTags, updateComplete, originalPriority, id, peopleIds);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditTaskCommand)) {
            return false;
        }

        // state check
        EditTaskCommand e = (EditTaskCommand) other;
        return index.equals(e.index)
                && editTaskDescriptor.equals(e.editTaskDescriptor);
    }

    /**
     * Stores the details to edit the task with. Each non-empty field value will replace the
     * corresponding field value of the task.
     */
    public static class EditTaskDescriptor {
        private TaskName taskName;
        private Description description;
        private StartDateTime start;
        private EndDateTime end;
        private Set<Tag> tags;
        private Boolean complete;

        public EditTaskDescriptor() {}

        public EditTaskDescriptor(EditTaskDescriptor toCopy) {
            this.taskName = toCopy.taskName;
            this.description = toCopy.description;
            this.start = toCopy.start;
            this.end = toCopy.end;
            this.tags = toCopy.tags;
            this.complete = toCopy.complete;
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(this.taskName, this.description, this.start, this.end,
                    this.tags, this.complete);
        }

        public void setTaskName(TaskName taskName) {
            this.taskName = taskName;
        }

        public Optional<TaskName> getTaskName() {
            return Optional.ofNullable(taskName);
        }

        public void setDescription(Description description) {
            this.description = description;
        }

        public Optional<Description> getDescription() {
            return Optional.ofNullable(description);
        }

        public void setStart(StartDateTime start) {
            this.start = start;
        }

        public Optional<StartDateTime> getStartDateTime() {
            return Optional.ofNullable(start);
        }

        public void setEnd(EndDateTime end) {
            this.end = end;
        }

        public Optional<EndDateTime> getEndDateTime() {
            return Optional.ofNullable(end);
        }

        public void setTags(Set<Tag> tags) {
            this.tags = tags;
        }

        public Optional<Set<Tag>> getTags() {
            return Optional.ofNullable(tags);
        }

        public void setComplete(Boolean complete) {
            this.complete = complete;
        }

        public Optional<Boolean> getComplete() {
            return Optional.ofNullable(complete);
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditTaskDescriptor)) {
                return false;
            }

            // state check
            EditTaskDescriptor e = (EditTaskDescriptor) other;

            return getTaskName().equals(e.getTaskName())
                    && getDescription().equals(e.getDescription())
                    && getStartDateTime().equals(e.getStartDateTime())
                    && getEndDateTime().equals(e.getEndDateTime())
                    && getTags().equals(e.getTags())
                    && getComplete().equals(e.getComplete());
        }
    }
}

