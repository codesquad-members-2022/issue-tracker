package louie.hanse.issuetracker.web.dto;

import louie.hanse.issuetracker.domain.Member;

public class ManagerResponse {
    private String name;
    private String imageUrl;

    public ManagerResponse(Member manager) {
        this.name = manager.getSocialId();
        this.imageUrl = manager.getAvatarImageUrl();
    }
}
