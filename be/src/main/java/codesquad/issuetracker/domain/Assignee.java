package codesquad.issuetracker.domain;

import javax.validation.constraints.NotNull;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "issue_assignee")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Assignee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "assignee_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "issue_id")
    @NotNull
    private Issue issue;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "writer_id")
    @NotNull
    private Writer writer;
}
