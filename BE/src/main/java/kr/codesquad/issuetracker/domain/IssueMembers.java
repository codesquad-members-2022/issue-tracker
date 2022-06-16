package kr.codesquad.issuetracker.domain;

import static javax.persistence.FetchType.LAZY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class IssueMembers {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "issues_members_id")
	private Long id;

	@ManyToOne(fetch = LAZY)
	private Issue issue;

	@ManyToOne(fetch = LAZY)
	private Member member;
}
