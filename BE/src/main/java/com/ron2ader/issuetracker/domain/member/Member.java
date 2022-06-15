package com.ron2ader.issuetracker.domain.member;

import com.ron2ader.issuetracker.domain.common.BaseEntity;
import lombok.*;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table(name = "member")
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String memberId;

    private String avatarUrl;

    public static Member of(String memberId, String avatarUrl) {
        return new Member(null, memberId, avatarUrl);
    }
}
