package org.firstinspires.ftc.teamcode.hand;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.common.Config;
@TeleOp
public class Shovel extends Hand {

    private Servo tiltServo;
    public int TILT_LEFT = 1;
    public int TILT_RIGHT = -1;
    public int TILT_CENTER = 0;

    @Override
    public void init() {
        tiltServo = hardwareMap.servo.get(Config.Hand.HAND_SERVO);
        tiltHand(TILT_CENTER);
    }

    public void tiltHand(int direction){
        tiltServo.setPosition(0.38 + (0.25 * direction));
    }

    //private double pos = 0.5;
    @Override

    public void loop() {

        if(gamepad1.dpad_left){
            //pos -= 0.005;
            tiltHand(TILT_LEFT);
        } else if(gamepad1.dpad_right){
            //pos += 0.005;
            tiltHand(TILT_RIGHT);
        } else if(gamepad1.dpad_down) {
            tiltHand(TILT_CENTER);
        }
        //tiltServo.setPosition(pos);
        //telemetry.addData("Position", pos);
        //telemetry.update();


    }
}
