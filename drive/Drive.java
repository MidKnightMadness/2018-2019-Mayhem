package org.firstinspires.ftc.teamcode.drive;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.common.AssemblyManager.TeleOpImplementation;
import org.firstinspires.ftc.teamcode.common.AssemblyManager.Implementation;

import org.firstinspires.ftc.teamcode.common.Distance;
import org.firstinspires.ftc.teamcode.common.Angle;

/**
 * Drive: The control interface for the Drive Train
 *
 * Note: All movements are asynchronous.
 *
 * Created by Gregory on 9/10/18.
 */

@Implementation(TankDrive.class)
@TeleOpImplementation(OmniDrive.class)
public abstract class Drive extends OpMode {
    public abstract void init(); // Initialize Motors

    // Begin moving forward or backwards
    public abstract void beginTranslation(Distance distance, double speed); // Move the robot for distance rotations? at speed.
    public abstract void beginTranslationSide(Distance distance, double speed);
    public abstract void beginRotation(Angle rotation, double speed);
    public abstract void beginMovement(Distance distance, Angle rotation, double speed) throws InterruptedException;
    public abstract void backward();
    public abstract void stopBack();

    public abstract boolean isBusy();
}

