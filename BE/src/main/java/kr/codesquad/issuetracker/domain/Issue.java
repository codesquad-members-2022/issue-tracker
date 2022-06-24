package kr.codesquad.issuetracker.domain;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Entity
public class Issue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(name = "issues_status")
    private boolean status;

    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private LocalDateTime createdTime;

    private String content;

    @OneToMany(mappedBy = "issue")
    private final List<IssueMembers> issueMembersList = new ArrayList<>();

    @ManyToOne(fetch = LAZY)
    private Member member;

    @OneToMany(mappedBy = "issue")
    private List<Comment> commentList = new ArrayList<>();

    @ManyToOne(fetch = LAZY)
    private Milestone milestone;

    @OneToMany(mappedBy = "issue")
    private List<IssueLabels> issueLabelsList = new ArrayList<>();
}

