package seedu.address.model.task;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code ReadOnlyTask}'s {@code Name} matches any of the keywords given.
 */
public class ContainsKeywordsPredicate implements Predicate<ReadOnlyTask> {
    private final List<String> keywords;

    public ContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(ReadOnlyTask task) {
        boolean hasName = keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(task.getTaskName().taskName, keyword));
        boolean hasDescription = keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(task.getDescription().toString(), keyword));
        boolean hasStart = keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(task.getStartDateTime().toString(), keyword));
        boolean hasEnd = keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(task.getEndDateTime().toString(), keyword));
        return hasName || hasDescription || hasStart || hasEnd;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ContainsKeywordsPredicate // instanceof handles nulls
                && this.keywords.equals(((ContainsKeywordsPredicate) other).keywords)); // state check
    }

}
