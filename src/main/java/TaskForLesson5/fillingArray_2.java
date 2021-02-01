package TaskForLesson5;

public class fillingArray_2 {


    public fillingArray_2(int SIZE, int HALF_SIZE) {
        float[] arr = new float[SIZE];
        float[] arr1 = new float[HALF_SIZE];
        float[] arr2 = new float[HALF_SIZE];
        new mainCalculated(arr);

        long start = System.currentTimeMillis();
        System.arraycopy(arr, 0, arr1, 0, HALF_SIZE);
        System.arraycopy(arr, HALF_SIZE, arr2, 0, HALF_SIZE);
        long split = System.currentTimeMillis();
        System.out.println("Время разбивки массива на два: " + (split - start));

        new Thread(() -> {
            long start1 = System.currentTimeMillis();
            new calculated(arr1);
            long end1 = System.currentTimeMillis();
            long time1 = end1 - start1;
            System.out.println("Время выполения первого потока: " + time1);
        }).start();

        new Thread(() -> {
            long start2 = System.currentTimeMillis();
            new calculated(arr2);
            long end2 = System.currentTimeMillis();
            long time2 = end2 - start2;
            System.out.println("Время выполения второго птока: " + time2);
        }).start();

        long start3 = System.currentTimeMillis();
        System.arraycopy(arr1, 0, arr, 0, HALF_SIZE);
        System.arraycopy(arr2, 0, arr, HALF_SIZE, HALF_SIZE);
        long end3 = System.currentTimeMillis();
        long time3 = end3 - start3;
        System.out.println("Время сшивкм массивов: " + time3);
    }
}
