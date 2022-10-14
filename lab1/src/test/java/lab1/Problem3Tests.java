package lab1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class Problem3Tests {

    @Test
    void shouldFlattenMatrix_test1() {
        // given
        int[][] matrix = {{1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}};

        // when
        int[] actual = Problem3.flattenMatrix(matrix);

        // then
        assertArrayEquals(new int[] {1, 4, 7, 2, 5, 8, 3, 6, 9}, actual);
    }

    @Test
    void shouldFlattenMatrix_test2() {
        // given
        int[][] matrix = {{9, 8, 7, 6},
                {5, 4, 3, 2},
                {1, 0, -1, -2}};

        // when
        int[] actual = Problem3.flattenMatrix(matrix);

        // then
        assertArrayEquals(new int[] {9, 5, 1, 8, 4, 0, 7, 3, -1, 6, 2, -2}, actual);
    }

    @Test
    void shouldFlattenMatrix_test3() {
        // given
        int[][] matrix = {{9, 8}, {5, 4}, {1, 0}};

        // when
        int[] actual = Problem3.flattenMatrix(matrix);

        // then
        assertArrayEquals(new int[] {9, 5, 1, 8, 4, 0}, actual);
    }

}
