package louie.hanse.issuetracker.domain;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import static javax.persistence.GenerationType.IDENTITY;

@Getter
@Entity
public class Label {

    @Id @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(name = "label_name")
    private String name;

    @Column(name = "label_description")
    private String description;
    private String backgroundColor;
    private String textColor;

}
