package com.team33.backend.issue.domain;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import java.util.Objects;

@Entity
@Getter
public class Label {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50)
    @NotBlank
    private String name;

    @Column(length = 100)
    private String description;

    @Column(length = 6, columnDefinition = "char(6)")
    @NotBlank
    private String color;

    public void editLabel(String name, String description, String color) {
        this.name = name;
        this.description = description;
        validateColor(color);
        this.color = color;
    }

    private void validateColor(String color) {
        if (color != null && color.length() == 6) {
            this.color = color;
        }
        throw new IllegalArgumentException("Label 색상을 선택해주세요.");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Label)) return false;
        Label entity = (Label) o;
        return Objects.equals(id, entity.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
