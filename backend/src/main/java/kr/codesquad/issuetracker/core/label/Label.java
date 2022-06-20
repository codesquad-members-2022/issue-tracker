package kr.codesquad.issuetracker.core.label;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import kr.codesquad.issuetracker.core.issue.Issue;
import org.springframework.lang.Nullable;

@Entity
public class Label {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Issue issue;
    private String labelColor;
    private String textColor;
    private String title;
    @Nullable
    private String description;
}
