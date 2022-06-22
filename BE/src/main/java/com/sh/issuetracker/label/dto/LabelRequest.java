package com.sh.issuetracker.label.dto;

import com.sh.issuetracker.label.Label;
import com.sh.issuetracker.label.TextColor;
import com.sh.issuetracker.project.Project;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class LabelRequest {
	@Getter
	@Setter
	@NoArgsConstructor
	public static class Creation {
		private String name;
		private String description;
		private String backgroundColor;
		private String fontColor;

		public Label toLabel(Project projectInfo) {
			return new Label(
				this.name,
				this.description,
				this.backgroundColor,
				TextColor.from(this.fontColor),
				projectInfo);
		}
	}
}
