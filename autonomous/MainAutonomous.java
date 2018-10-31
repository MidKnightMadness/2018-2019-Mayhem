package org.firstinspires.ftc.teamcode.autonomous;

import android.util.Log;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.RobotLog;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.common.AssemblyManager;
import org.firstinspires.ftc.teamcode.common.Config;
import org.firstinspires.ftc.teamcode.common.Distance;
import org.firstinspires.ftc.teamcode.drive.Drive;
import org.firstinspires.ftc.teamcode.hand.Hand;
import org.firstinspires.ftc.teamcode.mineral.MineralArm;
import org.firstinspires.ftc.teamcode.pullup.AngularPullUp;
import org.firstinspires.ftc.teamcode.pullup.PullUp;
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
        //Visual v = AssemblyManager.newInstance(Visual.class, hardwareMap, telemetry);
        RobotLog.a("STARTING!\n\n\n\n\n\n\n\n");
        Log.d("STARTING!!!", "\n\n\n\n\n\n\n\n\n");
        PullUp p = AssemblyManager.newInstance(PullUp.class, hardwareMap, telemetry);
        //Hand h = AssemblyManager.newInstance(Hand.class, hardwareMap, telemetry);
        //MineralArm m = AssemblyManager.newInstance(MineralArm.class, hardwareMap, telemetry);


        waitForStart();
        d.backward();// Wait for Start Button
        p.lower(); // Lower bot from hanging position
        Thread.sleep(1000);
        telemetry.addLine("LOWERED");
        d.stopBack();
        d.beginTranslationSide(Distance.fromInches(-20),1);
        while (d.isBusy() && !isStopRequested()) {}
        telemetry.addLine("MOVED 2");
        Thread.sleep(1000);
        p.raise();
        telemetry.addLine("RAISE");
        Thread.sleep(1000);
        /*d.beginTranslation(Distance.fromInches(15),1);
        d.beginTranslationSide(Distance.fromInches(17),1);*/




    }


}
