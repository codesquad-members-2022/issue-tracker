package com.sh.issuetracker.issuelabel;

import com.sh.issuetracker.issue.Issue;
import com.sh.issuetracker.label.Label;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Table(name = "label_has_issue")
@Entity
public class IssueLabel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "issue_label")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "issue_id")
	private Issue issue;

	@ManyToOne
	@JoinColumn(name = "label_id")
	private Label label;

	public Label getLabel() {
		return this.label;
	}
}
