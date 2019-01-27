package org.firstinspires.ftc.teamcode.hand;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.common.Config;

@TeleOp
public class IntakeMotorVEX extends Hand {

    private DcMotor motor;
    private boolean on = false;
    private boolean dpad = false;

    public void init() {
        motor = hardwareMap.dcMotor.get(Config.Hand.HAND_MOTOR);
        motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    public void start() {}

    public void loop() {
        if (gamepad2.dpad_left && !dpad) {
            dpad = true;
            on = !on;
        } else {
            dpad = false;
        }


        if (on) {
            motor.setPower(1);
        } else {
            motor.setPower(0);
        }

        /*if (gamepad2.dpad_left) {
            motor.setPower(-1);
            telemetry.addLine("trying to reverse");
            telemetry.update();
        } else if (gamepad2.dpad_right) {
            motor.setPower(1);
            telemetry.addLine("trying to rotate");
            telemetry.update();
        } else {
            motor.setPower(0);
            telemetry.addLine("trying to brake");
            telemetry.update();
        }*/
        //telemetry.update();
    }

    public void stop() {}

    @Override
    public void tiltHand(int direction) throws InterruptedException {

    }
}
