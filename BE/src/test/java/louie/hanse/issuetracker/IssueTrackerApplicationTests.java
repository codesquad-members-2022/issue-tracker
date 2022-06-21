package louie.hanse.issuetracker;

import org.junit.jupiter.api.Test;

//@SpringBootTest
class IssueTrackerApplicationTests {

    @Test
    void contextLoads() throws Exception {
        throwCheckedException();
    }

    void throwCheckedException() {
        try {
            throw new Exception();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void throwUnCheckedException() {
        throw new RuntimeException();
    }

    static class CheckedException extends Exception {

    }

    static class UnCheckedException extends RuntimeException {

    }
}
