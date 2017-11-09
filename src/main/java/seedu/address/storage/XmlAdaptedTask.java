//@@author ShaocongDong
package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.xml.bind.annotation.XmlElement;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.*;

/**
 * JAXB-friendly version of the Task.
 */
public class XmlAdaptedTask {

    @XmlElement(required = true)
    private TaskName taskName;
    @XmlElement(required = true)
    private Description taskDescription;
    @XmlElement(required = true)
    private StartDateTime startDateTime;
    @XmlElement(required = true)
    private EndDateTime endDateTime;
    @XmlElement
    private Integer id;
    @XmlElement
    private List<XmlAdaptedTag> tagged = new ArrayList<>();
    @XmlElement(required = true)
    private Boolean complete;
    @XmlElement
    private List<Integer> peopleIndices = new ArrayList<>();
    @XmlElement(required = true)
    private Integer priority;


    /**
     * Constructs an XmlAdaptedPerson.
     * This is the no-arg constructor that is required by JAXB.
     */
    public XmlAdaptedTask() {}


    /**
     * Converts a given Task into this class for JAXB use.
     *
     * @param source future changes to this will not affect the created XmlAdaptedPerson
     */
    public XmlAdaptedTask(ReadOnlyTask source) {
        taskName = source.getTaskName();
        taskDescription = source.getDescription();
        startDateTime = source.getStartDateTime();
        endDateTime = source.getEndDateTime();
        tagged = new ArrayList<>();
        id = source.getId();
        for (Tag tag : source.getTags()) {
            tagged.add(new XmlAdaptedTag(tag));
        }
        complete = source.getComplete();
        peopleIndices = source.getPeopleIds();
        priority = source.getPriority();
    }

    /**
     * Converts this jaxb-friendly adapted task object into the model's Task object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person
     */
    public Task toModelType() throws IllegalValueException {
        final List<Tag> personTags = new ArrayList<>();
        for (XmlAdaptedTag tag : tagged) {
            personTags.add(tag.toModelType());
        }
        final TaskName taskName = this.taskName;
        final Description taskDescription = this.taskDescription;
        final StartDateTime startDateTime = this.startDateTime;
        final EndDateTime endDateTime = this.endDateTime;
        final Set<Tag> tags = new HashSet<>(personTags);
        final Boolean complete = this.complete;
        final ArrayList<Integer> peopleIndices = new ArrayList<>(this.peopleIndices);
        final Integer priority = this.priority;
        final Integer id = this.id;
        return new Task(taskName, taskDescription, startDateTime, endDateTime, tags, complete, priority, id,
                peopleIndices);
    }

}
