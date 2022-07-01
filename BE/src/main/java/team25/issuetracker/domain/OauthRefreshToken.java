package team25.issuetracker.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Entity
@Getter
@Table(name = "T_REFRESH_TOKEN")
@NoArgsConstructor
public class OauthRefreshToken {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "REFRESH_TOKEN_ID", nullable = false)
	private Long refreshTokenId;

	@Column(name = "OAUTH_REFRESH_TOKEN", nullable = false)
	private String refreshToken;

	@Column(name = "KEY_EMAIL", nullable = true)
	private String keyEmail;

	@Builder
	public OauthRefreshToken(Long refreshTokenId, String refreshToken, String keyEmail) {
		this.refreshTokenId = refreshTokenId;
		this.refreshToken = refreshToken;
		this.keyEmail = keyEmail;
	}
}
