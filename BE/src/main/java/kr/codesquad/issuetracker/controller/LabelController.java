package kr.codesquad.issuetracker.controller;

import kr.codesquad.issuetracker.dto.LabelRequest;
import kr.codesquad.issuetracker.dto.LabelResponse;
import kr.codesquad.issuetracker.service.LabelService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/labels")
public class LabelController {

    private final LabelService labelService;

    @GetMapping
    public List<LabelResponse> getLabelList() {
        return labelService.getLabelList();
    }

    @PostMapping
    public ResponseEntity createLabel(@RequestBody LabelRequest labelRequest){
        labelService.createLabel(labelRequest);
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteLabel(@PathVariable Long id) {
        try {
            labelService.deleteLabel(id);
        } catch (EmptyResultDataAccessException e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(HttpStatus.OK);
    }
}
