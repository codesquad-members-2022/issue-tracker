package kr.codesquad.issuetracker.controller;

import kr.codesquad.issuetracker.dto.LabelResponse;
import kr.codesquad.issuetracker.service.LabelService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class LabelController {

    private final LabelService labelService;

    @GetMapping("/labels")
    public List<LabelResponse> getLabelList() {
        return labelService.getLabelList();
    }

    @DeleteMapping("/labels/{id}")
    public ResponseEntity deleteLabel(@PathVariable Long id) {
        try {
            labelService.deleteLabel(id);
        } catch (EmptyResultDataAccessException e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(HttpStatus.OK);
    }
}
