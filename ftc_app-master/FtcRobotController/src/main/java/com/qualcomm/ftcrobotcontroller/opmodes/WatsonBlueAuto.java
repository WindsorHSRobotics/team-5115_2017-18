package com.qualcomm.ftcrobotcontroller.opmodes;

import android.graphics.Color;

import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import java.lang.*;

public class WatsonBlueAuto extends LinearOpMode{

    DcMotor M1, M2, M3, M4, collector, launcher;

    public void runOpMode() throws InterruptedException {
        M1 = hardwareMap.dcMotor.get("1");
        M2 = hardwareMap.dcMotor.get("2");
        M3 = hardwareMap.dcMotor.get("3");
        M4 = hardwareMap.dcMotor.get("4");
        collector = hardwareMap.dcMotor.get("collector");
        launcher = hardwareMap.dcMotor.get("launcher");

        M1.setDirection(DcMotor.Direction.FORWARD);
        M2.setDirection(DcMotor.Direction.FORWARD);
        M3.setDirection(DcMotor.Direction.REVERSE);
        M4.setDirection(DcMotor.Direction.REVERSE);

        waitForStart();
        sleep(5000);
        collector.setPower(-1);
        M1.setPower(.5);
        M2.setPower(-.5);
        M3.setPower(.5);
        M4.setPower(-.5);
        sleep(1500);
        M1.setPower(0);
        M2.setPower(0);
        M3.setPower(0);
        M4.setPower(0);
        sleep(1500);
        M1.setPower(-.5);
        M2.setPower(.5);
        M3.setPower(-.5);
        M4.setPower(.5);
        sleep(1000 );
        M1.setPower(0);
        M2.setPower(0);
        M3.setPower(0);
        M4.setPower(0);


        /* Works, but seemed to fast and flipper caught balls
        launcher.setPower(.5);
        sleep(8000);
        launcher.setPower(0);
*/


        launcher.setPower(1);
        sleep(10000);
        launcher.setPower(0);





        M1.setPower(1);
        M2.setPower(-1);
        M3.setPower(1);
        M4.setPower(-1);
        sleep(2000);
        M1.setPower(0);
        M2.setPower(0);
        M3.setPower(0);
        M4.setPower(0);
        collector.setPower(0);
        sleep(2000);




    }

}
