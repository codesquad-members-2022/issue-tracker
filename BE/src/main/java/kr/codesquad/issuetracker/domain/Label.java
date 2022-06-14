package kr.codesquad.issuetracker.domain;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;


@Entity
public class Label {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "label_id")
	private Long id;

	private String title;

	private String description;

	private String backgroundColor;

	private String textColor;

	@OneToMany(mappedBy = "label")
	private List<IssueLabels> issueLabelsList = new ArrayList<>();
}
