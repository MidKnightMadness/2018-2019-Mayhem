package org.firstinspires.ftc.teamcode.common;

/**
 * Config: Configuration file to hold Motor Names.
 *
 * Create a new static final class for every assembly.
 *
 * Created by Gregory on 9/10/18.
 */

public final class HubConfig {
    public static final class Drive {     // Drive Assembly Configuration
        public static final String BACK_LEFT = "m"; // 3
    }

    public static final class Measurements {
        public static final double ROBOT_DEGREES_PER_TICK = 0.04939d;
        public static final double ENCODER_TICKS_PER_INCH = 5000d/68d;
        public static final int ENCODER_TICKS_PER_SHAFT_DEGREE = 1100;
    }
}

