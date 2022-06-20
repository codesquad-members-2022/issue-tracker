package codesquad.issuetracker.jwt;

public interface TokenProvider<T> {

    T createToken(String memberId);

    T convertToObject(String token);
}
