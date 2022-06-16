package com.team33.backend.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Issue extends CommonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 60)
    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    private Member author;

    @Enumerated(EnumType.STRING)
    private IssueStatus issueStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "milestone_id")
    private Milestone milestone;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "issue_group_id")
    private IssueGroup issueGroup;

    @OneToMany(mappedBy = "issue")
    private List<Assignee> assignees = new ArrayList<>();

    @OneToMany(mappedBy = "issue", cascade = CascadeType.REMOVE)
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "issue")
    private List<Label> labels = new ArrayList<>();
}
