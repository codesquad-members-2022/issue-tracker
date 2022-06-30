package team29.hoorry.issuetracker.core.issue.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import team29.hoorry.issuetracker.core.member.domain.Member;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Entity
public class IssueAssignee {

	@Id
	@Column(name = "issue_assignee_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "issue_id", nullable = false)
	private Issue issue;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "assignee_id", nullable = false)
	private Member assignee;

	public static IssueAssignee of(Issue issue, Member assignee) {
		return new IssueAssignee(null, issue, assignee);
	}
}
