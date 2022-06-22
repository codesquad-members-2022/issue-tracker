package dev.kukim.issues.issue.domain;

import static javax.persistence.FetchType.LAZY;

import dev.kukim.issues.milestone.domain.Milestone;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Getter;

@Getter
@Entity
public class Issue {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	// TODO : 그외 이슈 속성 추가해야한다.

	@JoinColumn
	@ManyToOne(fetch = LAZY)
	private Milestone milestone;

}
