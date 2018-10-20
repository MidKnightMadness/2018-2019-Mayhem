package org.firstinspires.ftc.teamcode.visual;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.common.AssemblyManager;

/**
 * MineralTest: An example of an Autonomous Test program
 * Displays the mineral view onscreen to test the detection of minerals by the phone camera.
 *
 * Created by Gregory on 9/14/18.
 */

@Autonomous
public class MineralTest extends LinearOpMode {


    @Override
    public void runOpMode() throws InterruptedException {
        Visual.DEBUG = true;
        Visual v = AssemblyManager.newInstance(Visual.class, hardwareMap, telemetry); // Initialize the Visual Assembly

        waitForStart();                                       // Wait for Start

        while (!isStopRequested()) {
            //Thread.sleep(2000);
            v.findGoldMineral(false);                     // Inspect the frame from the camera
        }

        v.stop();                                             // Stop the visual controller (close views...)
    }
}
