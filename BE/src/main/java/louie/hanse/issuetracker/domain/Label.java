package louie.hanse.issuetracker.domain;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.*;

@Entity
public class Label {

    @Id @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn
    private Issue issue;

    @Column(name = "label_name")
    private String name;

    @Column(name = "label_description")
    private String description;
    private String backgroundColor;
    private String textColor;

}
