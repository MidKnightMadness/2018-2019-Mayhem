package org.firstinspires.ftc.teamcode.autonomous;

import android.util.Log;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.RobotLog;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.common.Angle;
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
public class MainAutonomousCrater extends LinearOpMode {


    @Override
    public void runOpMode() throws InterruptedException {   // This method is run by the OpMode Manager on init until the stop button is pressed.
        telemetry.addLine("HI IM ALIVE");
        telemetry.update();
        Drive d = AssemblyManager.newInstance(Drive.class, hardwareMap, telemetry); // Initialize all Assemblies required during the Autonomous program by the interface
        Visual v = AssemblyManager.newInstance(Visual.class, hardwareMap, telemetry);
        final PullUp p = AssemblyManager.newInstance(PullUp.class, hardwareMap, telemetry);
        RobotLog.a("STARTING!\n\n\n\n\n\n\n\n");
        Log.d("STARTING!!!", "\n\n\n\n\n\n\n\n\n");
        //Hand h = AssemblyManager.newInstance(Hand.class, hardwareMap, telemetry);
        MineralArm m = AssemblyManager.newInstance(MineralArm.class, hardwareMap, telemetry);

        waitForStart();
        v.startTfod();
        p.open(); // Lower bot from hanging position
        d.backward();// Run backward to align bot with lander wall
        Thread.sleep(200);// wait for bot to be aligned
        telemetry.addLine("Moving back");
        telemetry.update();
        d.stopBack();//stop moving back
        telemetry.addLine("Stopping");
        telemetry.update();
        Thread.sleep(500);
        telemetry.addLine("Sleeping");
        telemetry.update();
        d.beginTranslationSide(Distance.fromInches(-4),0.5);//move away from lander bracket
        telemetry.addLine("Moving to side");
        telemetry.update();

        while (d.isBusy() && !isStopRequested()) {
            telemetry.update();
        }//wait for drive train to be done moving
        telemetry.addData("Stop", isStopRequested());
        telemetry.update();
        d.beginTranslation(Distance.fromInches(8), 0.4);//move up from lander
        telemetry.addLine("MOVE UP");
        telemetry.update();
        Thread.sleep(1000);
        d.beginRotation(Angle.fromDegrees(-90), 0.6);//turn robot so camera faces minerals
        telemetry.addLine("ROTATE");
        telemetry.update();
        Thread.sleep(3000);
        Visual.MineralPosition pos = v.findGoldMineral();//use visual to find mineral
        telemetry.addLine(pos.toString());//make telemetry tell us where the mineral is
        Thread.sleep(100);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    p.close();
                } catch (InterruptedException e) {
                    telemetry.addLine(e.getMessage());
                }
            }
        }).start();//creates a thread that closes the pullup arm while the robot keeps moving
        telemetry.addLine("CLOSE");
        telemetry.update();

        d.beginTranslationSide(Distance.fromInches(6), 0.4);//move up more
        telemetry.addLine("MOVE UP");
        telemetry.update();
        d.beginRotation(Angle.fromDegrees(-5), 0.4);//adjust bot angle
        while (!isStopRequested() && d.isBusy());
        telemetry.addLine("ADJUSTED");
        telemetry.update();
        Thread.sleep(1000);
        int MINERAL_DISTANCE;
        if(pos == Visual.MineralPosition.LEFT){
            MINERAL_DISTANCE = 6;

        } else if (pos == Visual.MineralPosition.CENTER){
            MINERAL_DISTANCE = -12;

        } else {
            MINERAL_DISTANCE = -24;

        }//if/elif/else decides where to go depending on where the bot thinks gold is

        d.beginTranslation(Distance.fromInches(MINERAL_DISTANCE), 0.4);//move to gold mineral
        telemetry.addLine("MOVING TO MINERAL");
        telemetry.update();
        while (!isStopRequested() && d.isBusy());


        d.beginTranslationSide(Distance.fromInches(9), 0.5);//pushes mineral
        Thread.sleep(1000);

        d.beginTranslationSide(Distance.fromInches(-9), 0.5);//moves back from mineral
        telemetry.addLine("MOVE OUT");
        telemetry.update();
        Thread.sleep(1000);

        d.beginTranslation(Distance.fromInches(-MINERAL_DISTANCE+100), 0.4);//moves to field wall
        telemetry.addLine("MOVE BACK");
        telemetry.update();
        Thread.sleep(1000);
        d.beginRotation(Angle.fromDegrees(-45), 0.6);//turn to align with field wall
        telemetry.addLine("ROTATE");
        telemetry.update();
        Thread.sleep(500);
        d.beginTranslation(Distance.fromInches(120), 1);//quickly move towards depot
        telemetry.addLine("CHARGING");
        telemetry.update();
        Thread.sleep(5000);
        //unfinished

        m.rotate();
        d.stop();
        v.stop();
    }


}
