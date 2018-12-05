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
        rotateMotor.resetDeviceConfigurationForOpMode();

        rotateMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rotateMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        //rotateMotor.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER, new PIDFCoefficients(15, 0, 1, 0, MotorControlAlgorithm.PIDF));
        //rotateMotor.setPIDFCoefficients(DcMotor.RunMode.RUN_TO_POSITION,   new PIDFCoefficients(15, 0, 0,0, MotorControlAlgorithm.PIDF));
        //rotateMotor.setTargetPosition(0);
        //rotateMotor.setPower(1);
        extendMotor.setTargetPosition(0);
        extendMotor.setPower(1);
        rotateMotor.setTargetPosition(0);
        rotateMotor.setPower(1);
    }

    private int currentAngle = 0;
    private int extension = 0;
    private int currentAngleOffset = 0;
    private int currentExtensionOffset = 0;

    @Override
    public void loop() {
        if (gamepad1.right_trigger > 0) {
            currentAngle -= gamepad1.right_trigger * 50;
            if (gamepad1.b) {
                currentAngleOffset -= gamepad1.right_trigger * 50;
            }
        } else if (gamepad1.left_trigger > 0){
            currentAngle += gamepad1.left_trigger * 50;
            if (gamepad1.b) {
                currentAngleOffset += gamepad1.left_trigger * 50;
            }
        }

        if (gamepad1.right_bumper) {
            extension += 150;
            if (gamepad1.b) {
                currentExtensionOffset += 150;
            }
        } else if (gamepad1.left_bumper) {
            extension -= 150;
            if (gamepad1.b) {
                currentExtensionOffset -= 150;
            }
        }

        extension = Math.min(Math.max(extension, 0 + currentExtensionOffset), 10600 + currentExtensionOffset);
        currentAngle = Math.min(Math.max(currentAngle, -2000 + currentAngleOffset), 600 + currentAngleOffset);
        extendMotor.setTargetPosition(extension);
        rotateMotor.setTargetPosition(currentAngle);
        //updateRotate();
        telemetry.addData("Current Angle", currentAngle);
        telemetry.addData("Extension", extension);
        telemetry.addData("Current Angle2", rotateMotor.getCurrentPosition());
        telemetry.update();

        //rotateMotor.setTargetPosition(currentAngle);
    }

    public void updateRotate() {
        double power = currentAngle - rotateMotor.getCurrentPosition();
        if (Math.abs(power) < 20) {
            power = 0;
        } else if (Math.abs(power) < 100) {
            power = Math.signum(power) * 0.02;
        } else {
            power = Math.min(Math.max(Math.pow(power / 20.0, 7),-0.5), 0.5);
        }

        telemetry.addData("Power", power);

        rotateMotor.setPower(power);
    }

    @Override
    public void rotate() throws InterruptedException {
        rotateMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rotateMotor.setTargetPosition(2000);
        rotateMotor.setPower(1);
        while (rotateMotor.isBusy() && !Thread.currentThread().isInterrupted());
    }
}
