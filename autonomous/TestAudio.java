package org.firstinspires.ftc.teamcode.autonomous;

import com.qualcomm.ftccommon.SoundPlayer;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import java.io.File;
@Autonomous
public class TestAudio extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        SoundPlayer.getInstance().preload(hardwareMap.appContext, new File("/storage/self/primary/FoundGold.wav"));
        waitForStart();
        Thread.sleep(1000);
        SoundPlayer.PlaySoundParams params = new SoundPlayer.PlaySoundParams();
        params.volume = 1.0f;
        SoundPlayer.getInstance().startPlaying(hardwareMap.appContext, new File("/storage/self/primary/FoundGold.wav"), params, null, null);
        Thread.sleep(10000);
    }
}
