package kr.codesquad.issuetraker.domain.label;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
public class Label {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "label_id")
    private Long id;
    private String name;
    private String description;
    private String backgroundColor;
    private String letterColor;
}
