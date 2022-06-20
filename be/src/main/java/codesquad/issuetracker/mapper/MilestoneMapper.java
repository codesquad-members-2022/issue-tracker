package codesquad.issuetracker.mapper;

import codesquad.issuetracker.domain.Milestone;
import codesquad.issuetracker.dto.milestone.MilestoneDto;

public class MilestoneMapper {

    public static MilestoneDto convertToDto(Milestone milestone) {
        if (milestone != null) {
            return new MilestoneDto(milestone.getId(), milestone.getSubject(),
                milestone.getDescription());
        }

        return null;
    }
}
