package training;

import java.util.Arrays;

public class StrategyApp {

    public static void main(String[] args) {
        StrategyClient c = new StrategyClient();

        c.setStrategy(new SelectionSort());
        c.executeStrategy(new int[]{1, 3, 2, 1});

        System.out.println("###");

        c.setStrategy(new InsertingSort());
        c.executeStrategy(new int[]{11, 4, 2, 7, 8, 54});

        System.out.println("###");

        c.setStrategy(new BubbleSort());
        c.executeStrategy(new int[]{3, -8, 2, 0, 33, 1, 3, 2});
    }
}

class StrategyClient {
    private Sorting strategy;

    void setStrategy(Sorting strategy) {
        this.strategy = strategy;
    }

    void executeStrategy(int[] arr) {
        strategy.sort(arr);
    }
}

interface Sorting {
    void sort(int[] arr);
}

class BubbleSort implements Sorting {

    @Override
    public void sort(int[] arr) {
        System.out.println("Сортировка пузырьком");
        System.out.println("до:\t" + Arrays.toString(arr));
        for (int barier = arr.length - 1; barier >= 0; barier--) {
            for (int i = 0; i < barier; i++) {
                if (arr[i] > arr[i + 1]) {
                    int tmp = arr[i];
                    arr[i] = arr[i + 1];
                    arr[i + 1] = tmp;
                }
            }
        }
        System.out.println("после:\t" + Arrays.toString(arr));
    }
}

class SelectionSort implements Sorting {

    @Override
    public void sort(int[] arr) {
        System.out.println("сортировка выборками");
        System.out.println("до:\t" + Arrays.toString(arr));
        for (int barier = 0; barier < arr.length - 1; barier++) {
            for (int i = barier + 1; i < arr.length; i++) {
                if (arr[i] < arr[barier]) {
                    int tmp = arr[i];
                    arr[i] = arr[barier];
                    arr[barier] = tmp;
                }
            }
        }
        System.out.println("после:\t" + Arrays.toString(arr));
    }
}

class InsertingSort implements Sorting {

    @Override
    public void sort(int[] arr) {
        System.out.println("сортировка вставками");
        System.out.println("до:\t" + Arrays.toString(arr));
        for (int barier = 1; barier < arr.length; barier++) {
            int index = barier;
            while (index - 1 >= 0 && arr[index] < arr[index - 1]) {
                int tmp = arr[index];
                arr[index] = arr[index - 1];
                arr[index - 1] = tmp;
                index--;
            }
        }
        System.out.println("после:\t" + Arrays.toString(arr));
    }
}