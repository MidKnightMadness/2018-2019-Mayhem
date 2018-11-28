package org.firstinspires.ftc.teamcode.hand;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.common.AssemblyManager;

@AssemblyManager.Implementation(Shovel.class)
public abstract class Hand extends OpMode{
    public abstract void tiltHand(int direction) throws InterruptedException;
}
