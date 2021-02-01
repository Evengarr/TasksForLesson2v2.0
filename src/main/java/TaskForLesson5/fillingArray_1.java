package TaskForLesson5;

public class fillingArray_1 {
    public fillingArray_1(int SIZE) {
        float[] arr = new float[SIZE];
        new mainCalculated(arr);        //заполнение массива 1
        long start = System.currentTimeMillis();
        new calculated(arr);            //заполнение массива по формуле
        long end = System.currentTimeMillis();
        long time = end - start;
        System.out.println("Время работы метода по формуле : " + time);
        System.out.println();
    }
}
