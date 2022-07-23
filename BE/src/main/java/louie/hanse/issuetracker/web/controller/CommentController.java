package louie.hanse.issuetracker.web.controller;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import louie.hanse.issuetracker.service.CommentService;
import louie.hanse.issuetracker.web.dto.comment.CommentSaveResponse;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/issues/{issueId}/comments")
@RestController
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    public CommentSaveResponse writeComment(@PathVariable Long issueId,
        @RequestBody Map<String, String> map) {
        String contents = map.get("contents");
        return commentService.write(issueId, contents);
    }

    @PutMapping("/{id}")
    public Map<String, LocalDateTime> editComment(@PathVariable Long id,
        @RequestBody Map<String, String> map) {
        String contents = map.get("contents");
        LocalDateTime updateDateTime = commentService.edit(id, contents);
        return Collections.singletonMap("updateDateTime", updateDateTime);
    }
}
