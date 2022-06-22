package dev.kukim.learning.lombok.builder.object;


import dev.kukim.learning.lombok.builder.object.TestUser2.TestUser2Builder;
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
public class TestUser3 {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Long thirdPartyId; // 필수

	private String name; // 필수

	private String email;

	@Builder.Default
	private String avatarUrl = "test.com";

	public static TestUser3Builder builder(@NonNull Long thirdPartyId, @NonNull String name) {
		return hiddenBuilder().thirdPartyId(thirdPartyId).name(name);
	}
}
