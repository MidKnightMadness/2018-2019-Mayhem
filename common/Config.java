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
        public static final String FRONT_LEFT = "front left"; // 0
        public static final String BACK_RIGHT = "back right"; // 2
        public static final String BACK_LEFT = "back left"; // 3
        public static final String FRONT_RIGHT = "front right"; // 1
    }

    public static final class Hand {     // Hand Assembly Configuration
        public static final String HAND_SERVO = "hand servo";

    }

    public static final class PullUp {     // Pull-up Assembly Configuration
        public static final String PULLUP_MOTOR = "pull up motor"; //
        public static final String PULLUP_SERVO = "pull up servo";

    }

    public static final class MineralArm {      // Mineral Arm Assembly Configuration
        public static final String MINERAL_EXTEND_MOTOR = "mineral extending motor";
        public static final String MINERAL_ROTATE_MOTOR = "mineral rotating motor";


    }

    public static final class Measurements {
        public static final double ROBOT_DEGREES_PER_TICK = 0.04939d;
        public static final double ENCODER_TICKS_PER_INCH = 5000d/68d;
        public static final int ENCODER_TICKS_PER_SHAFT_DEGREE = 1100;
    }
}

