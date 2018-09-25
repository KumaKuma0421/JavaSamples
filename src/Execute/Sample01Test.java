package Execute;

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
    void testProcessSample1() {
        this.processSample(new String[] { "java", "-version" });
    }

    @Test
    void testProcessSample2() {
        this.processSample(new String[] { "vmstat", "1", "10" });
    }
}
