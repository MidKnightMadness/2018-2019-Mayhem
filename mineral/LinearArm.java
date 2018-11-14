package org.firstinspires.ftc.teamcode.mineral;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.MotorControlAlgorithm;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.common.Config;
import org.firstinspires.ftc.teamcode.common.Distance;

@TeleOp
public class LinearArm extends MineralArm {
    private DcMotor extendMotor;
    private DcMotor rotateMotor;
    private final int TARGET_POSITION_TO_EXTEND = 7550;

    @Override
    public void init() {
        extendMotor = hardwareMap.dcMotor.get(Config.MineralArm.MINERAL_EXTEND_MOTOR);
        //DcMotor test = hardwareMap.dcMotor.get(Config.MineralArm.MINERAL_ROTATE_MOTOR);
        rotateMotor = hardwareMap.dcMotor.get(Config.MineralArm.MINERAL_ROTATE_MOTOR);
        //telemetry.addData("DcMotorEx", DcMotorEx.class.isInstance(test));
        //telemetry.update();
        //rotateMotor = (DcMotorEx) test;
        extendMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        extendMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rotateMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rotateMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        //rotateMotor.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER, new PIDFCoefficients(15, 0, 1, 0, MotorControlAlgorithm.PIDF));
        //rotateMotor.setPIDFCoefficients(DcMotor.RunMode.RUN_TO_POSITION,   new PIDFCoefficients(15, 0, 0,0, MotorControlAlgorithm.PIDF));
        rotateMotor.setTargetPosition(0);
        rotateMotor.setPower(1);
        extendMotor.setTargetPosition(0);
        extendMotor.setPower(1);
    }

    private int currentAngle = 0;
    private int extension = 0;

    @Override
    public void loop() {
        if (gamepad1.left_bumper) {
            currentAngle -= 1;
        } else if (gamepad1.right_bumper){
            currentAngle += 1;
        }

        if (gamepad1.left_trigger > 0) {
            extension += gamepad1.left_trigger * 20;
        } else if (gamepad1.right_trigger > 0) {
            extension -= gamepad1.right_trigger * 20;
        }

        extension = Math.min(Math.max(extension, 0), TARGET_POSITION_TO_EXTEND);

        extendMotor.setTargetPosition(extension);

        telemetry.addData("Current Angle", currentAngle);
        telemetry.addData("Extension", extension);
        telemetry.update();
        rotateMotor.setTargetPosition(currentAngle);
    }

    @Override
    public void extend() throws InterruptedException {
        extendMotor.setTargetPosition(TARGET_POSITION_TO_EXTEND);
        extendMotor.setPower(1);
        while (extendMotor.isBusy() && !Thread.currentThread().isInterrupted()){}

    }

    @Override
    public void raiseTo(int targetPosition) throws InterruptedException {
        extendMotor.setTargetPosition(targetPosition);
        extendMotor.setPower(1);
        while (extendMotor.isBusy() && !Thread.currentThread().isInterrupted()){}

    }
}
