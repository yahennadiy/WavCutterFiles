package samplecutter.fileoperations;

import samplecutter.fxapplication.mainscene.CheckFileButton;
import samplecutter.fxapplication.mainscene.SampleBitsRemainListView;

class DataOutArrCreator {
    static byte[] exec(byte[] data) {
        byte[] dataArr = data.clone();
        int sampleLength = CheckFileButton.getSampleLength();
        int bytesNum = sampleLength / 8;
        int len = dataArr.length;
        for (int i = 0; i < len; i += bytesNum) {
            int sample = 0;
            for (int j = (bytesNum - 1); j >= 0; j--) {
                sample |= (0xff & dataArr[i + j]);
                if (j > 0) {
                    sample <<= 8;
                }
            }

            sample >>>= (sampleLength - Integer.parseInt(
                    SampleBitsRemainListView.getDepth()));
            for (int j = 0; j < bytesNum; j++) {
                if (j != 0) {
                    sample >>>= 8;
                }

                dataArr[i + j] = (byte) (sample & 0xff);
            }
        }

        return dataArr;
    }
}
