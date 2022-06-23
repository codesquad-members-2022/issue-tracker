package kr.codesquad.issuetracker.domain.label;

import javax.persistence.Embeddable;
import lombok.Getter;

@Getter
@Embeddable
public class Color {

	private String backgroundColor;

	private String textColor;
}
