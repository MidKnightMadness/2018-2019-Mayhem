package org.firstinspires.ftc.teamcode.pullup;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.common.Config;

public class AngularPullUp extends PullUp {
    private DcMotor pullUpMotor;
    private Servo pullUpServo;
    private double POWER_TO_OPEN_PULLUP = 0.1;
    private final int TARGET_POSITION_TO_OPEN = 600;



    @Override
    public void init() {
        pullUpMotor = hardwareMap.dcMotor.get(Config.PullUp.PULLUP_MOTOR);
        pullUpServo = hardwareMap.servo.get(Config.PullUp.PULLUP_SERVO);
        pullUpMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        pullUpMotor.setPower(POWER_TO_OPEN_PULLUP);
        try {
            Thread.sleep(400);
        } catch (InterruptedException e) {}
        pullUpMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        pullUpMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        pullUpMotor.setPower(0);
        pullUpServo.setPosition(0.7);

    }

    public void lower() throws InterruptedException{
        pullUpServo.setPosition(1);
        Thread.sleep(1000);
        pullUpMotor.setTargetPosition(TARGET_POSITION_TO_OPEN);
        pullUpMotor.setPower(1);
        while (pullUpMotor.isBusy() && !Thread.currentThread().isInterrupted()){}
    }

    public void raise() {
        pullUpMotor.setTargetPosition(TARGET_POSITION_TO_OPEN);
        pullUpMotor.setPower(1);
        while (pullUpMotor.isBusy() && !Thread.currentThread().isInterrupted()){}
        pullUpServo.setPosition(0.6);
    }

    @Override
    public void loop() {

    }
}
