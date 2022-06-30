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
import team29.hoorry.issuetracker.core.label.domain.Label;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Entity
public class IssueLabel {

	@Id
	@Column(name = "issue_label_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "issue_id", nullable = false)
	private Issue issue;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "label_id", nullable = false)
	private Label label;

	public static IssueLabel of(Issue issue, Label label) {
		return new IssueLabel(null, issue, label);
	}
}
