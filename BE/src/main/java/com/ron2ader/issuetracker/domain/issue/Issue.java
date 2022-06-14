package com.ron2ader.issuetracker.domain.issue;

import com.ron2ader.issuetracker.controller.memberdto.MemberDto;
import com.ron2ader.issuetracker.domain.common.BaseEntity;
import com.ron2ader.issuetracker.domain.member.Member;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class Issue extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member issuer;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer issueNumber;

    private String title;

    private String contents;

    private String attachmentUrl;

    private Boolean isOpen;

    public static Issue of(MemberDto member, String title, String contents, String attachmentUrl) {
        return new Issue(null, member, null,
                title, contents, attachmentUrl, true);
    }

}
