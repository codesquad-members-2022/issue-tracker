package com.sh.issuetracker.label;

import com.sh.issuetracker.project.Project;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(of = "id")
@Where(clause = "is_deleted = false")
@Table(name = "issue_tracker_label")
@Entity
public class Label {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "label_id")
	private Long id;

	@Column(name = "label_name")
	private String name;
	@Column(name = "label_description")
	private String description;
	private String backgroundColor;
	@Enumerated(EnumType.STRING)
	private TextColor fontColor;
	@ColumnDefault("0")
	private boolean isDeleted = false;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "project_id")
	private Project project;

	public Label(
		String name,
		String description,
		String backgroundColor,
		TextColor fontColor,
		Project project) {
		this.name = name;
		this.description = description;
		this.backgroundColor = backgroundColor;
		this.fontColor = fontColor;
		this.isDeleted = false;
		this.project = project;
	}

	public void delete() {
		this.isDeleted = true;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public String getBackgroundColor() {
		return backgroundColor;
	}

	public String getFontColorText() {
		return fontColor.toString();
	}
}
