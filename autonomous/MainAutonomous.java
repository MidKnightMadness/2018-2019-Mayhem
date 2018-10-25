package org.firstinspires.ftc.teamcode.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.common.AssemblyManager;
import org.firstinspires.ftc.teamcode.common.Distance;
import org.firstinspires.ftc.teamcode.drive.Drive;
import org.firstinspires.ftc.teamcode.visual.Visual;

/**
 * Main Autonomous: The main autonomous program that will be run during the tournament.
 *
 * In order for this to be runnable from the Driver Station, it must extend either OpMode or LinearOpMode and be annotated with @Autonomous.
 * Because this will run linearly and not in a constant loop like a normal OpMode, we chose LinearOpMode.
 *
 * This should not directly interface with motors (except maybe Visual Signaling Devices).
 * All interfacing with motors should be done in the appropriate Assembly.
 *
 * To sleep, use Thread.sleep.
 *
 * If you need to use a loop, add a condition if Thread.currentThread().isInterrupted() then you must exit immediately to prevent App Crash.
 *
 * Created by Gregory on 9/10/18.
 */

@Autonomous                                                 // Comment out annotation to remove from list on Driver Station
public class MainAutonomous extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {   // This method is run by the OpMode Manager on init until the stop button is pressed.
        Drive d = AssemblyManager.newInstance(Drive.class, hardwareMap, telemetry); // Initialize all Assemblies required during the Autonomous program by the interface
        Visual v = AssemblyManager.newInstance(Visual.class, hardwareMap, telemetry);

        waitForStart();                                               // Wait for Start Button
        d.beginTranslation(Distance.fromInches(10), 1);                 // Move the Robot (or something)
    }


}
