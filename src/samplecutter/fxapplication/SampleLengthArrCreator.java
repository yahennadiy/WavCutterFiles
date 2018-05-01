package samplecutter.fxapplication;

public class SampleLengthArrCreator {
    public static String[] get(int length) {
        String[] samplesLengthArr;
        if (length == 24) {
            samplesLengthArr = create(24);
        } else {
            samplesLengthArr = create(16); // anyway bitsRemainArr16 !!!
        }

        return samplesLengthArr;
    }

    private static String[] create(int length) {
        String[] arr = new String[length];
        for (int i = 0; i < length; i++) {
            if (i < 10) {
                arr[i] = "0".concat(Integer.toString(i)).concat(" bits");
            } else {
                arr[i] = Integer.toString(i).concat(" bits");
            }
        }

        return arr;
    }
}
