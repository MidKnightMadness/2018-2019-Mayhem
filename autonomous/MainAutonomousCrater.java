package org.firstinspires.ftc.teamcode.autonomous;

import android.util.Log;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
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
        d.backward();// Wait for Start Button
        Thread.sleep(200);
        telemetry.addLine("Moving back");
        telemetry.update();
        d.stopBack();
        telemetry.addLine("Stopping");
        telemetry.update();
        Thread.sleep(500);
        telemetry.addLine("Sleeping");
        telemetry.update();
        d.beginTranslationSide(Distance.fromInches(-4),0.5);
        telemetry.addLine("Moving to side");
        telemetry.update();

        while (d.isBusy() && !isStopRequested()) {
            telemetry.update();
        }
        telemetry.addData("Stop", isStopRequested());
        telemetry.update();
        d.beginTranslation(Distance.fromInches(8), 0.4);
        telemetry.addLine("MOVE UP");
        telemetry.update();
        Thread.sleep(1000);
        d.beginRotation(Angle.fromDegrees(-90), 0.6);
        telemetry.addLine("ROTATE");
        telemetry.update();
        Thread.sleep(1000);
        Visual.MineralPosition pos = v.findGoldMineral();
        telemetry.addLine(pos.toString());
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
        }).start();
        telemetry.addLine("CLOSE");
        telemetry.update();
        /*d.beginTranslation(Distance.fromInches(15),1);
        d.beginTranslationSide(Distance.fromInches(17),1);*/
        d.beginTranslation(Distance.fromInches(6), 0.4);
        telemetry.addLine("MOVE UP");
        telemetry.update();
        d.beginRotation(Angle.fromDegrees(-5), 0.4);
        while (!isStopRequested() && d.isBusy());
        telemetry.addLine("ADJUSTED");
        telemetry.update();
        Thread.sleep(1000);
        int MINERAL_DISTANCE = 0;
        if(pos == Visual.MineralPosition.LEFT){
            MINERAL_DISTANCE = 0;

        } else if (pos == Visual.MineralPosition.CENTER){
            MINERAL_DISTANCE = -12;

        } else {
            MINERAL_DISTANCE = -29;

        }
        MINERAL_DISTANCE = -29;

        d.beginTranslation(Distance.fromInches(MINERAL_DISTANCE), 0.4);
        telemetry.addLine("MOVING TO MINERAL");
        telemetry.update();
        while (!isStopRequested() && d.isBusy());

        /* (variables for the old mineral detection code
        int IS_GOLD = 0;
        int GOLD_FOUND = 0;
        int encoder = 0;*/

        /* (This is the old mineral detection stuff)

        d.beginTranslation(Distance.fromInches(-40), 0.2);
        telemetry.addLine("MOVING ALONG MINERALS");
        telemetry.update();


        while (d.isBusy()){
            IS_GOLD = v.isGoldMineral(true);
            telemetry.addData("Is Gold? ", IS_GOLD);
            if((GOLD_FOUND == 0) && (IS_GOLD == 1)){
                GOLD_FOUND = 1;
                encoder = d.frontLeft.getCurrentPosition();
            } else if ((GOLD_FOUND == 1) && (IS_GOLD != 1)){
                GOLD_FOUND = -1;
                encoder = d.frontLeft.getCurrentPosition() - encoder;
                d.stopBack();
                break;
            }
            telemetry.addData("Gold Status ", GOLD_FOUND);
            telemetry.addData("Encoder Position", encoder);
            telemetry.update();
        }
        if(GOLD_FOUND != -1){

        } else {
            telemetry.addData("Gold Distance ", encoder);
            telemetry.update();
            d.beginTranslation(Distance.fromEncoderTicks(encoder).subtract(Distance.fromInches(6)), 0.5);
            while (!isStopRequested() && d.isBusy());
        }*/
        d.beginRotation(Angle.fromDegrees(90), 1);
        while (!isStopRequested() && d.isBusy());
        d.beginTranslation(Distance.fromInches(14), 0.5);
        Thread.sleep(1000);
        m.rotate();
        d.stop();
        v.stop();
    }


}
