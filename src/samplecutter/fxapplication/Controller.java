package samplecutter.fxapplication;

import javafx.geometry.Insets;
import samplecutter.fxapplication.mainscene.MainSceneCreator;

public class Controller {
    private static final int H_SIZE = 670;
    private static final int V_SIZE = 640;
    private static final int GAP = 10;
    private static final Insets INSETS = new Insets(10);
    private static final int SAMPLE_BITS_REMAIN_LV_WIDTH = 80;

    public static void init() {
        MainSceneCreator.create();
    }

    public static int getHSize() {
        return H_SIZE;
    }

    public static int getVSIZE() {
        return V_SIZE;
    }

    public static Insets getInsets() {
        return INSETS;
    }

    public static int getGap() {
        return GAP;
    }

    public static int getSampleBitsRemainLvWidth() {
        return SAMPLE_BITS_REMAIN_LV_WIDTH;
    }
}
