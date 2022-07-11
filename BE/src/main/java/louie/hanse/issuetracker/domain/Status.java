package louie.hanse.issuetracker.domain;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum Status {
    OPEN, CLOSE;

    @JsonCreator
    public static Status valueOfWithCaseInsensitive(String name) {
        name = name.toUpperCase();
        return valueOf(name);
    }

    public Status reverse() {
        return this == OPEN ? CLOSE : OPEN;
    }

    public boolean isClosed() {
        return this.equals(CLOSE);
    }

    public boolean isOpened() {
        return this.equals(OPEN);
    }
}
