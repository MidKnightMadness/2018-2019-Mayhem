package org.firstinspires.ftc.teamcode.hand;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import org.firstinspires.ftc.teamcode.hand.VEXServoTest;

import org.firstinspires.ftc.teamcode.common.AssemblyManager.Implementation;

@Implementation(VEXServoTest.class)
public abstract class Hand extends OpMode{
    public abstract void tiltHand(int direction) throws InterruptedException;
}
