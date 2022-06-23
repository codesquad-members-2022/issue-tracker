package codesquad.issuetracker.dto.issue;

import codesquad.issuetracker.domain.IssueStatus;
import java.util.HashSet;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IssueSearchCondition {

    private IssueStatus status = IssueStatus.OPEN;
    private String writer;
    private String assignee;
    private String replier;
    private String labels;
    private String milestone;
    private String exclusions;

    public Set<String> parseLabelConditions() {
        return parse(labels);
    }


    public Set<String> parseExclusionConditions() {
        return parse(exclusions);
    }

    private Set<String> parse(String conditions) {
        Set<String> exclusionConditions = new HashSet<>();
        if (conditions == null) {
            return exclusionConditions;
        }

        String[] params = labels.split(",");
        for (String param : params) {
            exclusionConditions.add(param.replaceAll(" ", ""));
        }

        return exclusionConditions;
    }
}
