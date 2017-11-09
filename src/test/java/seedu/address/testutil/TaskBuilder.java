package seedu.address.testutil;

import java.util.Set;

import seedu.address.commons.exceptions.IllegalValueException;

import seedu.address.model.tag.Tag;
import seedu.address.model.task.Description;
import seedu.address.model.task.EndDateTime;
import seedu.address.model.task.StartDateTime;
import seedu.address.model.task.ReadOnlyTask;
import seedu.address.model.task.Task;
import seedu.address.model.task.TaskName;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Task objects.
 */
public class TaskBuilder {


    public static final String DEFAULT_NAME = "picnic";
    public static final String DEFAULT_DESCRIPTION = "have fun at Botanic Garden";
    public static final String DEFAULT_START_DATE_TIME = "26/11/2017 12:00pm";
    public static final String DEFAULT_END_DATE_TIME = "26/11/2017 15:00pm";
    public static final int DEFAULT_PRIORITY = 3;
    public static final String DEFAULT_TAGS = "friends";

    private Task task;

    public TaskBuilder() {
        try {
            TaskName defaultName = new TaskName(DEFAULT_NAME);
            Description defaultDescription = new Description(DEFAULT_DESCRIPTION);
            StartDateTime defaultStart = new StartDateTime(DEFAULT_START_DATE_TIME);
            EndDateTime defaultEnd = new EndDateTime(DEFAULT_END_DATE_TIME);
            Integer defaultPriority = new Integer(DEFAULT_PRIORITY);
            Set<Tag> defaultTags = SampleDataUtil.getTagSet(DEFAULT_TAGS);
            this.task = new Task(defaultName, defaultDescription, defaultStart, defaultEnd,
                    defaultTags, false, defaultPriority);
        } catch (IllegalValueException ive) {
            throw new AssertionError("Default task's values are invalid.");
        }
    }

    /**
     * Initializes the TaskBuilder with the data of {@code taskToCopy}.
     */
    public TaskBuilder(ReadOnlyTask taskToCopy) {
        this.task = new Task(taskToCopy);
    }

    /**
     * Sets the {@code Name} of the {@code Task} that we are building.
     */
    public TaskBuilder withName(String taskName) {
        try {
            this.task.setName(new TaskName(taskName));
        } catch (IllegalValueException ive) {
            throw new IllegalArgumentException("name is expected to be unique.");
        }
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Task} that we are building.
     */
    public TaskBuilder withTags(String ... tags) {
        try {
            this.task.setTags(SampleDataUtil.getTagSet(tags));
        } catch (IllegalValueException ive) {
            throw new IllegalArgumentException("tags are expected to be unique.");
        }
        return this;
    }

    /**
     * Sets the {@code Description} of the {@code Task} that we are building.
     */
    public TaskBuilder withDescription(String description) {
        try {
            this.task.setDescription(new Description(description));
        } catch (IllegalValueException ive) {
            throw new IllegalArgumentException("description is expected to be unique.");
        }
        return this;
    }

    /**
     * Sets the {@code Start} of the {@code Task} that we are building.
     */
    public TaskBuilder withStart(String start) {
        try {
            this.task.setStartDateTime(new StartDateTime(start));
        } catch (IllegalValueException ive) {
            throw new IllegalArgumentException("start date time is expected to be unique.");
        }
        return this;
    }

    /**
     * Sets the {@code End} of the {@code Task} that we are building.
     */
    public TaskBuilder withEnd(String end) {
        try {
            this.task.setEndDateTime(new EndDateTime(end));
        } catch (IllegalValueException ive) {
            throw new IllegalArgumentException("end date time is expected to be unique.");
        }
        return this;
    }

    /**
     * Sets the {@code Remark} of the {@code Person} that we are building.
     */

    public Task build() {
        return this.task;
    }

}
