package codesquad.issuetracker.service;

import codesquad.issuetracker.dto.milestone.MileStoneDtoList;
import codesquad.issuetracker.repository.MileStoneRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MileStoneService {

    private final MileStoneRepository mileStoneRepository;

    public MileStoneDtoList getMileStones() {
        return new MileStoneDtoList(mileStoneRepository.findAll());
    }
}
