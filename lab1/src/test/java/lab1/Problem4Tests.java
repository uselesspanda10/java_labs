package lab1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Problem4Tests {

    @Test
    void shouldReturnTrue_ifGeometricProgression() {
        // given
        String number = "1,2,4,8,16";

        // when
        boolean actual = Problem4.isGeometricProgression(number);

        // then
        assertTrue(actual);
    }

    @Test
    void shouldReturnTrue_ifGeometricProgression_test2() {
        // given
        String number = "16,2,8,1,4";

        // when
        boolean actual = Problem4.isGeometricProgression(number);

        // then
        assertTrue(actual);
    }

    @Test
    void shouldReturnFalse_ifGeometricProgression() {
        // given
        String number = "2,3,5";

        // when
        boolean actual = Problem4.isGeometricProgression(number);

        // then
        assertFalse(actual);
    }

}
