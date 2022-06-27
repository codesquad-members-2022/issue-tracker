package kr.codesquad.issuetracker.domain;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
public class IssueMembers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn
    private Issue issue;

    @ManyToOne(fetch = LAZY)
    @JoinColumn
    private Member member;
}
