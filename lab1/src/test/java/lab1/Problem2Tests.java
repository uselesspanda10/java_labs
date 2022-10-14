package lab1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class Problem2Tests {

    @Test
    void shouldSegregateEvenAndOddNumbers_test1() {
        // given
        int[] array = {2, 1, 5, 6, 8};

        // when
        int[] actual = Problem2.segregateEvenAndOddNumbers(array);

        // then
        assertArrayEquals(new int[]{2, 6, 8, 1, 5}, actual);
    }

    @Test
    void shouldSegregateEvenAndOddNumbers_test2() {
        // given
        int[] array = {1, 8, 10, 5, 6, 45, 8, 22};

        // when
        int[] actual = Problem2.segregateEvenAndOddNumbers(array);

        // then
        assertArrayEquals(new int[]{8, 10, 6, 8, 22, 1, 5, 45}, actual);
    }

    @Test
    void shouldSegregateEvenAndOddNumbers_test3() {
        // given
        int[] array = {1, 2, 3, 4, 5, 6, 7, 8};

        // when
        int[] actual = Problem2.segregateEvenAndOddNumbers(array);

        // then
        assertArrayEquals(new int[]{2, 4, 6, 8, 1, 3, 5, 7}, actual);
    }

}
