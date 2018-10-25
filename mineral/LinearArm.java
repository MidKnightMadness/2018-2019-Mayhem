package org.firstinspires.ftc.teamcode.mineral;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.common.Config;
import org.firstinspires.ftc.teamcode.common.Distance;

public class LinearArm extends MineralArm {
    private DcMotor extendMotor;
    private DcMotor rotateMotor;
    private final int TARGET_POSITION_TO_EXTEND = 500;

    @Override
    public void init() {
        extendMotor = hardwareMap.dcMotor.get(Config.MineralArm.MINERAL_EXTEND_MOTOR);
        rotateMotor = hardwareMap.dcMotor.get(Config.MineralArm.MINERAL_ROTATE_MOTOR);
        extendMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        extendMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        extendMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rotateMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rotateMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rotateMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

    }

    @Override
    public void loop() {

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
