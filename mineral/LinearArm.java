package org.firstinspires.ftc.teamcode.mineral;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.common.Config;
import org.firstinspires.ftc.teamcode.common.Distance;
@TeleOp
public class LinearArm extends MineralArm {
    private DcMotor extendMotor;
    private DcMotor rotateMotor;
    private final int TARGET_POSITION_TO_EXTEND = -7500;

    @Override
    public void init() {
        extendMotor = hardwareMap.dcMotor.get(Config.MineralArm.MINERAL_EXTEND_MOTOR);
        rotateMotor = hardwareMap.dcMotor.get(Config.MineralArm.MINERAL_ROTATE_MOTOR);
        extendMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        extendMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        extendMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rotateMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rotateMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rotateMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        extendMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rotateMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);


    }

    @Override
    public void loop() {
        telemetry.addData("ext pos", extendMotor.getCurrentPosition());
        telemetry.addData("pos", rotateMotor.getCurrentPosition());
        telemetry.update();
        while (gamepad1.right_bumper){
            try {
                retract();
                telemetry.addData("ext pos", extendMotor.getCurrentPosition());
                telemetry.addLine("retracting");
                telemetry.update();
            } catch (InterruptedException e) {}
        } while (gamepad1.left_bumper) {
            try {
                extend();
                telemetry.addData("ext pos", extendMotor.getCurrentPosition());
                telemetry.addLine("Extending");
                telemetry.update();
            } catch (InterruptedException e) {}
        } while (gamepad1.left_trigger > 0) {
            if(rotateMotor.getCurrentPosition() <0) {
                telemetry.addData("pos", rotateMotor.getCurrentPosition());
                telemetry.addLine("Wants to lower");
                telemetry.update();

                try {
                    raiseTo(1);
                    telemetry.addData("pos", rotateMotor.getCurrentPosition());
                    telemetry.addLine("lowering");
                    telemetry.update();
                } catch (InterruptedException e) {
                }
            }

        } while (gamepad1.right_trigger > 0) {
            if(rotateMotor.getCurrentPosition() > -1600) {
                telemetry.addData("pos", rotateMotor.getCurrentPosition());
                telemetry.addLine("Wants to raise");
                telemetry.update();

                try {
                    raiseTo(-1);
                    telemetry.addData("pos", rotateMotor.getCurrentPosition());
                    telemetry.addLine("raising");
                    telemetry.update();
                } catch (InterruptedException e) {}
            }
        }

    }

    @Override
    public void extend() throws InterruptedException {
        extendMotor.setTargetPosition(TARGET_POSITION_TO_EXTEND);
        extendMotor.setPower(-1);
        while (extendMotor.isBusy() && !Thread.currentThread().isInterrupted()){}

    }

    @Override
    public void retract() throws InterruptedException {
        extendMotor.setTargetPosition(1);
        extendMotor.setPower(1);
        while (extendMotor.isBusy() && !Thread.currentThread().isInterrupted()){}

    }

    @Override
    public void raiseTo(int targetPosition) throws InterruptedException {
        rotateMotor.setPower(0.1*targetPosition);
    }

    @Override
    public void stopRaise(){
        rotateMotor.setPower(0);
    }
}
