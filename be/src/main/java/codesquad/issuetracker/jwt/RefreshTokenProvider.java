package codesquad.issuetracker.jwt;

import io.jsonwebtoken.SignatureAlgorithm;
import java.security.Key;
import java.util.Date;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

public class RefreshTokenProvider implements TokenProvider<RefreshToken> {

    private final Key secretKey;
    private final SignatureAlgorithm signatureAlgorithm;
    private final Date expiration;

    public RefreshTokenProvider(String secret, SignatureAlgorithm signatureAlgorithm, long duration) {
        this.secretKey = getSecretKey(secret, signatureAlgorithm);
        this.signatureAlgorithm = signatureAlgorithm;
        this.expiration = new Date(System.currentTimeMillis() + duration);
    }

    private Key getSecretKey(String secret, SignatureAlgorithm signatureAlgorithm) {
        byte[] secretKeyBytes = DatatypeConverter.parseBase64Binary(secret);
        return new SecretKeySpec(secretKeyBytes, signatureAlgorithm.getJcaName());
    }

    public RefreshToken createToken(String memberId) {
        return new RefreshToken(memberId, secretKey, signatureAlgorithm, expiration);
    }

    public RefreshToken convertToObject(String token) {
        return new RefreshToken(token, secretKey);
    }
}
