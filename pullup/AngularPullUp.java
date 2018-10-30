package org.firstinspires.ftc.teamcode.pullup;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.common.Config;

public class AngularPullUp extends PullUp {
    private DcMotor pullUpMotor;
    private Servo pullUpServo;
    private double POWER_TO_OPEN_PULLUP = 1;
    private final int TARGET_POSITION_TO_OPEN = 11000;



    @Override
    public void init() {
        pullUpMotor = hardwareMap.dcMotor.get(Config.PullUp.PULLUP_MOTOR);
        pullUpServo = hardwareMap.servo.get(Config.PullUp.PULLUP_SERVO);
        pullUpMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        pullUpMotor.setPower(-POWER_TO_OPEN_PULLUP);
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            telemetry.addLine("I DIED!");
            telemetry.update();
        }
        pullUpMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        pullUpMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        pullUpMotor.setTargetPosition(0);


    }

    public void lower() throws InterruptedException{
        pullUpMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        pullUpMotor.setPower(-1);
        pullUpServo.setPosition(1);
        Thread.sleep(1000);
        pullUpMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        pullUpMotor.setTargetPosition(TARGET_POSITION_TO_OPEN);
        pullUpMotor.setPower(1);
        while (pullUpMotor.isBusy() && !Thread.currentThread().isInterrupted()){}
    }

    public void raise() throws InterruptedException {
        pullUpMotor.setTargetPosition(0);
        pullUpMotor.setPower(1);
        while (pullUpMotor.isBusy() && !Thread.currentThread().isInterrupted()) { }
        pullUpMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        pullUpMotor.setPower(-1);
        Thread.sleep(500);
        pullUpServo.setPosition(0);
        Thread.sleep(1000);
        pullUpMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        pullUpMotor.setTargetPosition(0);
    }

    @Override
    public void loop() {

    }
}
