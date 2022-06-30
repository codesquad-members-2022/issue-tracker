package com.ron2ader.issuetracker.controller;

import com.ron2ader.issuetracker.controller.labeldto.LabelRequest;
import com.ron2ader.issuetracker.controller.labeldto.LabelResponse;
import com.ron2ader.issuetracker.controller.labeldto.LabelsResponse;
import com.ron2ader.issuetracker.service.LabelService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LabelController {

    private final LabelService labelService;

    @PostMapping("/labels")
    public LabelResponse register(@RequestBody LabelRequest labelRequest) {
        return labelService.save(labelRequest.getTitle(), labelRequest.getColor(), labelRequest.getDescription());
    }

    @GetMapping("/labels")
    public List<LabelsResponse> getLabels() {
        return labelService.findAll();
    }

    @PostMapping("/labels/{id}")
    public LabelResponse update(@PathVariable Long id, @RequestBody LabelRequest labelRequest) {
        LabelResponse updateLabel = labelService.update(
            id,
            labelRequest.getTitle(),
            labelRequest.getColor(),
            labelRequest.getDescription()
        );

        return updateLabel;
    }

    @DeleteMapping("/labels/{id}")
    public void delete(@PathVariable Long id) {
        labelService.deleteById(id);
    }
}
