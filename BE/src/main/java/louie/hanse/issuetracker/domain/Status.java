package louie.hanse.issuetracker.domain;

public enum Status {
    OPEN, CLOSE;

    public Status reverse() {
        return this == OPEN ? CLOSE : OPEN;
    }
}
