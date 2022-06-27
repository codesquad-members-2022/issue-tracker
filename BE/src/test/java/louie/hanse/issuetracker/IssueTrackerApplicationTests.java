package louie.hanse.issuetracker;

import com.querydsl.jpa.impl.JPAQueryFactory;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

//@SpringBootTest
class IssueTrackerApplicationTests {

    @Autowired
    EntityManager entityManager;

    @Test
    void contextLoads() {
        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(entityManager);
    }
}
