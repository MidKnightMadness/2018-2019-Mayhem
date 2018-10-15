package org.firstinspires.ftc.teamcode.common;

/**
 * Config: Configuration file to hold Motor Names.
 *
 * Create a new static final class for every assembly.
 *
 * Created by Gregory on 9/10/18.
 */

public final class Config {
    public static final class Drive {     // Drive Assembly Configuration
        public static final String FRONT_LEFT = "front left";
        public static final String BACK_RIGHT = "back right";
        public static final String BACK_LEFT = "back left";
        public static final String FRONT_RIGHT = "front right";
    }

    public static final class Measurements {
        public static final double ROBOT_DEGREES_PER_TICK = 0.5d;
        public static final int ENCODER_TICKS_PER_INCH = 1000;
        public static final int ENCODER_TICKS_PER_SHAFT_DEGREE = 1100;
    }
}

