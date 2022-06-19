package louie.hanse.issuetracker;

import louie.hanse.issuetracker.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class IssueTrackerApplicationTests {

	@Autowired
	MemberRepository memberRepository;

	@Test
	void contextLoads() {
		memberRepository.existsBySocialId("dfae");
	}

}
