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
public class TestUser {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Long thirdPartyId; // 필수

	private String name; // 필수

	private String email;

	private String avatarUrl;

	@Builder
	public TestUser(Long id, @NonNull Long thirdPartyId, @NonNull String name, String email, String avatarUrl) {
		this.id = id;
		this.thirdPartyId = thirdPartyId;
		this.name = name;
		this.email = email;
		this.avatarUrl = avatarUrl;
	}
}
