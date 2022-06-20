package kr.codesquad.issuetracker.domain.label;

import javax.persistence.Embeddable;

@Embeddable
public class Color {

	private String backgroundColor;

	private String textColor;
}
