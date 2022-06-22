package dev.kukim.learning.lombok.builder;


import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import dev.kukim.learning.lombok.builder.object.TestUser;
import dev.kukim.learning.lombok.builder.object.TestUser2;
import org.junit.jupiter.api.Test;

// 롬북 생성자 @NonNull : https://lemontia.tistory.com/913
// 롬북+필수값 사용 토론 : https://stackoverflow.com/questions/29885428/required-arguments-with-a-lombok-builder
// 롬북에서 기본값사용(@Builder.Defalut) : https://velog.io/@kshired/Spring-Lombok-Builder-%EA%B8%B0%EB%B3%B8%EA%B0%92%EC%97%90-%EA%B4%80%ED%95%98%EC%97%AC
class BuilderTest {

	@Test
	void 생성자에_Builder_어노테이션사용_필수파라미터를_nonull_어노테이션으로사용하기_런타임에_에러잡는다() {
		TestUser user = TestUser.builder()
			.thirdPartyId(1234L)
			.name("test")
			.id(10L)
			.build();

		TestUser user2 = TestUser.builder()
			.thirdPartyId(1234L)
			.name("test")
			.build();

		assertThat(user.toString()).contains("id=10").contains("thirdPartyId=1234");
		assertThat(user2.toString()).contains("id=null").contains("thirdPartyId=1234");
		assertThatThrownBy(() -> TestUser.builder().name("test").build()).isInstanceOf(
			NullPointerException.class);
	}

	@Test
	void 클래스레벨에_Builder_붙이고_builderMethodName_사용하여_필수파라미터를_builder_메서드생성에_넣어사용하기_컴파일단에_에러잡는다() {
		TestUser2 user = TestUser2.builder(1234L, "haha")
			.id(1L)
			.build();


		TestUser2 user2 = TestUser2.builder(1234L, "haha")
			.build();

		TestUser2 user3 = TestUser2.builder(1234L, "haha").build();

		assertThat(user.toString()).contains("id=1").contains("thirdPartyId=1234").contains("name=haha");
		assertThat(user2.toString()).contains("thirdPartyId=1234").contains("name=haha");
	}
}
