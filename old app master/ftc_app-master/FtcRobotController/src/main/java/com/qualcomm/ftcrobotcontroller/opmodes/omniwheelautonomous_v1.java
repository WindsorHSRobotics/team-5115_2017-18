package com.qualcomm.ftcrobotcontroller.opmodes;

import android.graphics.Color;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import java.lang.*;

//For BOTH COLORS

    /*
    all motors clockwise
    F1  F2
    R1  R2

    launcher 1 is bottom
    launcher 2 is top
     */
/*
    General plan:
    launch 2 balls for 15 points each (30)
    drive to knock off cap ball for 5 points (5)
    park partially on central vortex base for 5 points (5)
    Total: 40
 */

public class omniwheelautonomous_v1 extends LinearOpMode {

    DcMotor R1, R2, F1, F2, collector, launcher1, launcher2;
    ColorSensor color;

    //double heading, heading_degrees, magnitude, leftstick_xsq, leftstick_ysq;

    public void runOpMode() throws InterruptedException {
        //motor declaration
        R1 = hardwareMap.dcMotor.get("r1");
        R2 = hardwareMap.dcMotor.get("r2");
        F1 = hardwareMap.dcMotor.get("f1");
        F2 = hardwareMap.dcMotor.get("f2");
        launcher1 = hardwareMap.dcMotor.get("launcher1");
        launcher2 = hardwareMap.dcMotor.get("launcher2");
        collector = hardwareMap.dcMotor.get("collector");
        hardwareMap.logDevices();
        color = hardwareMap.colorSensor.get("color");
        color.enableLed(false);

        R1.setDirection(DcMotor.Direction.REVERSE);
        R2.setDirection(DcMotor.Direction.REVERSE);
        F1.setDirection(DcMotor.Direction.REVERSE);
        F2.setDirection(DcMotor.Direction.REVERSE);
        collector.setDirection(DcMotor.Direction.REVERSE);

        //start program
        waitForStart();

        //launch 2 particles: 7s
        launcher1.setPower(1);//bottom motor is faster to kick particles up
        launcher2.setPower(.9);
        collector.setPower(1);
        telemetry.addData("status: ", "launching");

        sleep(7000);

        //stop launching
        launcher1.setPower(0);
        launcher2.setPower(0);
        collector.setPower(0);

        //drive forward: 2.5s to hit cap ball
        //left side
        R1.setPower(.5);
        F1.setPower(.5);
        //right side
        R2.setPower(-.5);
        F2.setPower(-.5);
        telemetry.addData("status: ", "drive to hit cap ball");

        sleep(2500);

        //park on center vortex base
        R1.setPower(0);
        R2.setPower(0);
        F1.setPower(0);
        F2.setPower(0);

        /*FOR PRESSING BEACON; CURRENTLY STILL IN DEVELOPMENT AS ACCURACY IS LESS THAN DESIRED

        all this programming is red-only and would need to be reversed for blue

        //rotate clockwise 90d: 1s //turn sensor to face beacon

        R2.setPower(.5);
        F2.setPower(.5);
        telemetry.addData("status: ", "rotaing");

        sleep(750);

        //drive left for 2.5s
        R1.setPower(-.5);
        F1.setPower(-.5);
        telemetry.addData("status:", " reversing to wall and aligning");

        sleep(2800);
        R1.setPower(0);
        F1.setPower(0);
        R2.setPower(0);
        F2.setPower(0);
        telemetry.addData("status: ", "waiting");
        sleep(400);

        //drive forward .25s
        R1.setPower(.5);
        F1.setPower(.5);
        R2.setPower(-.5);
        F2.setPower(-.5);
        telemetry.addData("status ", "drive forwards");

        sleep(500);

        //drive left .5s
        R2.setPower(.5);
        F1.setPower(-.5);
        telemetry.addData("status: ", "move left to align with beacon");

        sleep(500);

        //drive backward .05s
        R1.setPower(-.2);
        F2.setPower(.2);
        R2.setPower(.2);
        F1.setPower(-.2);
        telemetry.addData("status: ", "back up to beacon");

        sleep(1000);
        R1.setPower(0);
        R2.setPower(0);
        F1.setPower(0);
        F2.setPower(0);
        color.enableLed(true);
        sleep(1000);

        float hsvValues1[] = {0F,0F,0F};//0=red, 1=green, 2=blue
        waitOneFullHardwareCycle();
        float i;
        i = 1;


        while(i<1000) {

            Color.RGBToHSV(color.red() * 8, color.green() * 8, color.blue() * 8, hsvValues1);
            i = i+1;
        }
        float counter;
        counter =0;

        while(counter <= 4) {


            if (color.red()>color.blue() || color.red() > 15) {
                telemetry.addData("color = ", "red");
                break;
            } else if (color.blue()>color.red()) {
                telemetry.addData("color = ", "blue");
                break;
            } else {
                if(counter==4) {
                    break;
                }
                else{
                    telemetry.addData("color = ", "robot gave up");
                    counter++;
                    telemetry.addData("counter = ", counter);
                    R1.setPower(.5);
                    R2.setPower(.5);
                    F1.setPower(-.5);
                    F2.setPower(-.5);
                    sleep(300);
                    R1.setPower(0);
                    R2.setPower(0);
                    F1.setPower(0);
                    F2.setPower(0);
                    sleep(1000);
                }

            }
        }*/
    }
}