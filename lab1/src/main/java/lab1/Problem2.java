package lab1;

public class Problem2 {

    /**
     * Метод segregateEvenAndOddNumbers разделяет четные и нечетные числа в массиве array, т.у. возвращает массив,
     * в котором сперва записаны все четные числа массива array в порядке их следования, а затем все нечетные числа
     * массива array в порядке их следования.
     *
     * @param array массив положительных чисел
     * @return массив с разделенными четными и нечетными числами
     *
     * ПРИМЕР:
     * Вход: [2, 6, 8, 1, 5]
     * Выход: [2, 6, 8, 1, 5]
     */

    public static int[] segregateEvenAndOddNumbers(int[] array) {
        int[] newArray = new int[array.length];
        int cnt = 0;
        for (int j = 0; j < array.length; j++) {
            if (array[j] % 2 == 0) {
                newArray[cnt] = array[j];
                cnt++;
            }
        }
        for (int j = 0; j < array.length; j++) {
            if (array[j] % 2 == 1){
                newArray[cnt] = array[j];
                cnt++;
            }
        }
        return newArray;
    }

}