package louie.hanse.issuetracker.domain;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum TextColor {
    DARK, BRIGHT;

    @JsonCreator
    public static TextColor valueOfWithCaseInsensitive(String name) {
        name = name.toUpperCase();
        return valueOf(name);
    }
}
