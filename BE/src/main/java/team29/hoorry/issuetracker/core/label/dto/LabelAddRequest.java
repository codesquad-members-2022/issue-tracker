package team29.hoorry.issuetracker.core.label.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import team29.hoorry.issuetracker.core.label.domain.Label;

@Getter
@NoArgsConstructor
@Schema(description = "라벨 등록 요청")
public class LabelAddRequest {

	@Schema(required = true)
	@NotBlank(message = "title은 필수 입력 값(공백 불가)입니다.")
	private String title;

	@Schema(required = true)
	@NotBlank(message = "titleColor는 필수 입력 값(공백 불가)입니다.")
	private String titleColor;

	@Schema(required = true)
	@NotBlank(message = "backgroundColor는 필수 입력 값(공백 불가)입니다.")
	private String backgroundColor;

	private String description;

	public Label toEntity() {
		return Label.of(title, titleColor, backgroundColor, description);
	}
}
