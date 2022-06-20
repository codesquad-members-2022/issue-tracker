package codesquad.issuetracker.jwt;

import io.jsonwebtoken.SignatureAlgorithm;
import java.security.Key;
import java.util.Date;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

public class AccessTokenProvider implements TokenProvider<AccessToken> {

    private final Key secretKey;
    private final SignatureAlgorithm signatureAlgorithm;
    private final Date expiration;

    public AccessTokenProvider(String secret, SignatureAlgorithm signatureAlgorithm, long duration) {
        this.secretKey = getSecretKey(secret, signatureAlgorithm);
        this.signatureAlgorithm = signatureAlgorithm;
        this.expiration = new Date(System.currentTimeMillis() + duration);
    }

    private Key getSecretKey(String secret, SignatureAlgorithm signatureAlgorithm) {
        byte[] secretKeyBytes = DatatypeConverter.parseBase64Binary(secret);
        return new SecretKeySpec(secretKeyBytes, signatureAlgorithm.getJcaName());
    }

    public AccessToken createToken(String memberId) {
        return new AccessToken(memberId, secretKey, signatureAlgorithm, expiration);
    }

    public AccessToken convertToObject(String token) {
        return new AccessToken(token, secretKey);
    }
}
