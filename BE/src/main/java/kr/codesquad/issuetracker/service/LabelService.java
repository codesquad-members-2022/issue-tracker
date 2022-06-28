package kr.codesquad.issuetracker.service;

import kr.codesquad.issuetracker.domain.Label;
import kr.codesquad.issuetracker.dto.LabelRequest;
import kr.codesquad.issuetracker.dto.LabelResponse;
import kr.codesquad.issuetracker.repository.LabelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class LabelService {

    private final LabelRepository labelRepository;

    public List<LabelResponse> getLabelList(){
        return labelRepository.findAll().stream()
                .map(LabelResponse::new).collect(Collectors.toList());
    }

    public void createLabel(LabelRequest labelRequest){
        Label label = new Label(labelRequest);
        labelRepository.save(label);
    }

    public ResponseEntity deleteLabel(Long id){
        try {
            labelRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(HttpStatus.OK);
    }
}
