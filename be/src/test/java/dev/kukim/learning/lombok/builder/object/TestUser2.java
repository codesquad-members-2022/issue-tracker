package dev.kukim.learning.lombok.builder.object;


import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder(builderMethodName = "hiddenBuilder")
public class TestUser2 {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Long thirdPartyId; // 필수

	private String name; // 필수

	private String email;

	private String avatarUrl;

	public static TestUser2Builder builder(@NonNull Long thirdPartyId, @NonNull String name) {
		return hiddenBuilder().thirdPartyId(thirdPartyId).name(name);
	}
}
