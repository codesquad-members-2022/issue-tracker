package com.ron2ader.issuetracker.domain.issue;

import com.ron2ader.issuetracker.config.JpaConfig;
import com.ron2ader.issuetracker.service.IssueService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.*;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.*;

@DataJpaTest
@Import(JpaConfig.class)
@AutoConfigureTestDatabase(replace = Replace.NONE)
@ActiveProfiles("local")
@Sql(scripts = "classpath:sql/data.sql")
class IssueAssigneeRepositoryTest {

    @Autowired
    private IssueAssigneeRepository issueAssigneeRepository;

    @Test
    void findAllByIssueIdTest() {
        List<IssueAssignee> allByIssueId = issueAssigneeRepository.findAllById(List.of(1L, 2L));

        List<String> memberIds = allByIssueId.stream()
                .map(issueAssignee -> issueAssignee.getAssignee().getMemberId())
                .collect(Collectors.toList());

        assertThat(memberIds).containsAll(List.of("ron2", "ader"));
    }
}
