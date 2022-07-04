package kr.codesquad.issuetracker.domain;

import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private LocalDateTime createdTime;

    @ManyToOne(fetch = LAZY)
    @JoinColumn
    private Member member;

    @ManyToOne(fetch = LAZY)
    @JoinColumn
    private Issue issue;
}
