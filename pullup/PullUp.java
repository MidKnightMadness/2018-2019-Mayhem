package org.firstinspires.ftc.teamcode.pullup;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.common.Angle;
import org.firstinspires.ftc.teamcode.common.AssemblyManager;

@AssemblyManager.Implementation(AngularPullUp.class)
public abstract class PullUp extends OpMode {
    public abstract void lower() throws InterruptedException;
    public abstract void raise() throws InterruptedException;
}
