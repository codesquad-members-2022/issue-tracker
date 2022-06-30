package team29.hoorry.issuetracker.core.member;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team29.hoorry.issuetracker.core.exception.dto.ErrorResponse;
import team29.hoorry.issuetracker.core.jwt.dto.JwtResponse;
import team29.hoorry.issuetracker.core.member.dto.MemberAndJwtResponse;
import team29.hoorry.issuetracker.core.member.dto.MemberLoginRequest;
import team29.hoorry.issuetracker.core.member.dto.MemberRequest;
import team29.hoorry.issuetracker.core.member.dto.MembersResponse;

@Tag(name = "members", description = "회원 API")
@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
public class MemberController {

	private final MemberService memberService;

	@Operation(
		summary = "회원 리스트 조회",
		description = "모든 회원을 조회합니다.",
		responses = {
			@ApiResponse(
				responseCode = "200",
				description = "회원 리스트 조회 성공",
				content = {
					@Content(
						mediaType = "application/json",
						array = @ArraySchema(schema = @Schema(implementation = MembersResponse.class))
					)
				})
		}
	)
	@GetMapping
	public ResponseEntity<MembersResponse> members() {
		MembersResponse membersResponse = memberService.findAll();
		return ResponseEntity.ok(membersResponse);
	}

	@Operation(
		summary = "회원 등록",
		description = "회원을 등록합니다.",
		responses = {
			@ApiResponse(
				responseCode = "200",
				description = "회원 등록 성공",
				content = {
					@Content(
						mediaType = "application/json",
						schema = @Schema(implementation = MemberAndJwtResponse.class)
					)
				}
			),
			@ApiResponse(
				responseCode = "400",
				description = "회원 등록 실패",
				content = {
				@Content(
					mediaType = "application/json",
					schema = @Schema(implementation = ErrorResponse.class)
					)
				}
			)
		}
	)
	@PostMapping
	public ResponseEntity<MemberAndJwtResponse> join(@RequestBody MemberRequest memberRequest) {
		MemberAndJwtResponse memberAndJwtResponse = memberService.join(memberRequest);
		return ResponseEntity.ok(memberAndJwtResponse);
	}

	@Operation(
		summary = "회원 로그인",
		description = "회원 로그인을 합니다.",
		responses = {
			@ApiResponse(
				responseCode = "200",
				description = "회원 로그인 성공",
				content = {
					@Content(
						mediaType = "application/json",
						schema = @Schema(implementation = MemberAndJwtResponse.class)
					)
				}
			),
			@ApiResponse(
				responseCode = "400",
				description = "회원 로그인 실패",
				content = {
					@Content(
						mediaType = "application/json",
						schema = @Schema(implementation = ErrorResponse.class)
					)
				}
			)
		}
	)
	@PostMapping("/login")
	public ResponseEntity<MemberAndJwtResponse> login(@RequestBody MemberLoginRequest memberLoginRequest) {
		MemberAndJwtResponse memberAndJwtResponse = memberService.login(memberLoginRequest);
		return ResponseEntity.ok(memberAndJwtResponse);
	}

	@Operation(
		summary = "access_token 재발급",
		description = "access_token을 재발급합니다.",
		responses = {
			@ApiResponse(
				responseCode = "200",
				description = "access_token 재발급 성공",
				content = {
					@Content(
						mediaType = "application/json",
						schema = @Schema(implementation = JwtResponse.class)
					)
				}
			),
			@ApiResponse(
				responseCode = "400",
				description = "access_token 재발급 실패",
				content = {
					@Content(
						mediaType = "application/json",
						schema = @Schema(implementation = ErrorResponse.class)
					)
				}
			)
		}
	)
	@GetMapping("/re-issue-token")
	public ResponseEntity<JwtResponse> reIssue(HttpServletRequest request) {
		JwtResponse jwtResponse = memberService.reIssue(request);

		return ResponseEntity.ok(jwtResponse);
	}

}
