package kr.codesquad.issuetracker.auth.annotation;

import static kr.codesquad.issuetracker.auth.utils.Utils.AUTHORIZATION;
import static kr.codesquad.issuetracker.auth.utils.Utils.BEARER;

import com.auth0.jwt.interfaces.DecodedJWT;
import javax.servlet.http.HttpServletRequest;
import kr.codesquad.issuetracker.auth.service.JwtService;
import kr.codesquad.issuetracker.domain.member.Member;
import kr.codesquad.issuetracker.domain.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@RequiredArgsConstructor
@Component
public class LoginVerifyResolver implements HandlerMethodArgumentResolver {

	private final JwtService jwtService;
	private final MemberRepository memberRepository;


	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		return parameter.hasMethodAnnotation(LoginVerify.class);
	}

	//TODO: 에러처리 필요
	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
		NativeWebRequest webRequest, WebDataBinderFactory binderFactory) {

		HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();
		String header = request.getHeader(AUTHORIZATION);
		String jwt = header.substring(BEARER.length()).trim();
		DecodedJWT decodedJWT = jwtService.verifyToken(jwt);
		Long memberId = jwtService.getMemberId(decodedJWT);
		Member member = memberRepository.findById(memberId).orElseThrow(RuntimeException::new);
		return member.getName();
	}
}
