package org.firstinspires.ftc.teamcode.visual;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.common.AssemblyManager.Implementation;

/**
 * Created by Gregory on 9/14/18.
 */

@Implementation(VisualImpl.class)
public abstract class Visual {
    public enum MineralPosition {
        LEFT,
        CENTER,
        RIGHT,
        UNKNOWN;

        @Override
        public String toString() {
            return this == LEFT ? "Left" : this == CENTER ? "Center" : this == RIGHT ? "Right" : "Unknown";
        }
    }
    static boolean DEBUG = true;

    public static double minYellow[] = {20, 0.5, 0.5};
    public static double maxYellow[] = {45, 1, 1};
    public static double minWhite[] = {0, 0, 0.8};
    public static double maxWhite[] = {255, 0.2, 1};



    public Telemetry telemetry;
    public abstract void init();
    public abstract MineralPosition findGoldMineral(boolean save) throws InterruptedException;
    public abstract void stop();
}
