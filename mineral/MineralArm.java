package org.firstinspires.ftc.teamcode.mineral;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.common.AssemblyManager;

@AssemblyManager.Implementation(LinearArm.class)
public abstract class MineralArm extends OpMode{
    public abstract void extend() throws InterruptedException;

    public abstract void raiseTo(int targetPosition) throws InterruptedException;
}
