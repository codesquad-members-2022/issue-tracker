package codesquad.issuetracker.controller;

import codesquad.issuetracker.dto.ResponseMessage;
import codesquad.issuetracker.dto.image.ImageResponse;
import codesquad.issuetracker.dto.reply.ReplyDto;
import codesquad.issuetracker.dto.reply.ReplyForm;
import codesquad.issuetracker.dto.reply.ReplyUpdateForm;
import codesquad.issuetracker.service.ImageService;
import codesquad.issuetracker.service.ReplyService;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
public class ReplyController {

    private final ReplyService replyService;
    private final ImageService imageService;

    @PostMapping("/api/images")
    public ImageResponse upload(@RequestPart List<MultipartFile> images) {
        return new ImageResponse(imageService.uploadFiles(images));
    }

    @PostMapping("/api/issues/{id}/replies")
    public ReplyDto reply(@PathVariable Long id, @Valid @RequestBody ReplyForm form) {
        return replyService.createReply(id, form);
    }

    @PatchMapping("/api/issues/replies/{id}/update")
    public ResponseMessage update(@PathVariable("id") Long replyId, @Valid @RequestBody ReplyUpdateForm form) {
        replyService.updateReply(replyId, form);
        return new ResponseMessage(HttpStatus.OK, "댓글 편집이 정상적으로 처리되었습니다.");
    }
}
