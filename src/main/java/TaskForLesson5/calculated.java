package TaskForLesson5;

public class calculated {
    public calculated(float[] arr) {    //заполение массива по формуле
        for (int i = 0; i < arr.length; i++) {
            arr[i] = arr[i] = (float) (arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
    }
}

