package dev.kukim.issues.user.user.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter @Setter
@Entity
@Table(indexes = @Index(name = "i_thirdpartyid", columnList = "thirdPartyId"))
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Long thirdPartyId;

	@Column(nullable = false)
	private String name;

	@Column(length = 255)
	private String email;

	@Column(length = 1000)
	private String avatarUrl;

	@Builder
	public User(Long thirdPartyId, String name, String email, String avatarUrl) {
		this.thirdPartyId = thirdPartyId;
		this.name = name;
		this.email = email;
		this.avatarUrl = avatarUrl;
	}
}
