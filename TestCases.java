import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class TestCases {

    @Test
    public void testAddition() {
        int result = 2 + 2;
        assertEquals(4, result, "2 + 2 should equal 4");
    }

    @Test
    public void testSubtraction() {
        int result = 3 - 1;
        assertEquals(2, result, "3 - 1 should equal 2");
    }

    @Test
    public void testMultiplication() {
        int result = 100 * 10;
        assertEquals(1000, result, "100 * 10 should equal 1000");
    }
}