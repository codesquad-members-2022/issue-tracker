package codesquad.issuetracker.dto.milestone;

import java.util.List;
import lombok.Getter;

@Getter
public class MileStoneDtoList {

    private final List<MileStoneDto> mileStones;

    public MileStoneDtoList(List<MileStoneDto> mileStones) {
        this.mileStones = mileStones;
    }
}
