package com.team09.issue_tracker.label;

import com.team09.issue_tracker.label.dto.LabelSelectResponseDto;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class Label {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "label_id")
	private Long id;

	private String title;

	private String description;

	private boolean isDarkMode;

	private String backgroundColor;



	public LabelSelectResponseDto toResponseDto() {
		return LabelSelectResponseDto.builder()
			.id(id)
			.title(title)
			.description(description)
			.backgroundColor(backgroundColor)
			.isDarkMode(isDarkMode)
			.build();
	}
}
