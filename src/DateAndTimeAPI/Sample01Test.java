package DateAndTimeAPI;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class Sample01Test extends Sample01 {

    @BeforeAll
    static void setUpBeforeClass() throws Exception {
    }

    @AfterAll
    static void tearDownAfterClass() throws Exception {
    }

    @BeforeEach
    void setUp() throws Exception {
    }

    @AfterEach
    void tearDown() throws Exception {
    }

    @Test
    void testLocalDateTime() {
        this.getLocalDateTime();
    }

    @Test
    void testOffsetDateTime() {
        this.getOffsetDateTime();
    }

    @Test
    void testZonedDateTime() {
        this.getZonedDateTime();
    }

}
