package louie.hanse.issuetracker.domain;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.NoArgsConstructor;

import static javax.persistence.GenerationType.IDENTITY;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Label {

    @Id @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "label", cascade = CascadeType.REMOVE)
    private List<IssueLabel> issueLabels = new ArrayList<>();

    @Column(name = "label_name")
    private String name;

    @Column(name = "label_description")
    private String description;
    private String backgroundColor;

    @Enumerated(EnumType.STRING)
    private TextColor textColor;

    public Label(String name, String description, String backgroundColor,
        TextColor textColor) {
        this.name = name;
        this.description = description;
        this.backgroundColor = backgroundColor;
        this.textColor = textColor;
    }

    public void changeName(String name) {
        this.name = name;
    }

    public void changeDescription(String description) {
        this.description = description;
    }

    public void changeBackgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public void changeTextColor(TextColor textColor) {
        this.textColor = textColor;
    }
}
