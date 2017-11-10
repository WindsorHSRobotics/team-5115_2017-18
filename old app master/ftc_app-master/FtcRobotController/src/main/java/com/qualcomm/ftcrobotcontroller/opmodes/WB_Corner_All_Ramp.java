package com.qualcomm.ftcrobotcontroller.opmodes;

        import android.graphics.Color;

        import com.qualcomm.robotcore.hardware.ColorSensor;
        import com.qualcomm.robotcore.hardware.DcMotor;
        import com.qualcomm.robotcore.hardware.Servo;
        import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
        import java.lang.*;

public class WB_Corner_All_Ramp extends LinearOpMode {

    DcMotor M1, M2, M3, M4, collector, launcher;
    ColorSensor sensorRGB_Back;
    ColorSensor sensorRGB_Front;
    Servo Arm;

    public void runOpMode() throws InterruptedException {
        M1 = hardwareMap.dcMotor.get("1");
        M2 = hardwareMap.dcMotor.get("2");
        M3 = hardwareMap.dcMotor.get("3");
        M4 = hardwareMap.dcMotor.get("4");
        collector = hardwareMap.dcMotor.get("collector");
        launcher = hardwareMap.dcMotor.get("launcher");
        Arm = hardwareMap.servo.get("Servo");

        hardwareMap.logDevices();
        sensorRGB_Back = hardwareMap.colorSensor.get("color_Back");
        sensorRGB_Front = hardwareMap.colorSensor.get("color_Front");

        boolean bEnabled = true;
        sensorRGB_Back.enableLed(false);
        sensorRGB_Front.enableLed(false);
        float hsvValuesfront[] = {0F, 0F, 0F};
        float hsvValuesback[] = {0F, 0F, 0F};


        M1.setDirection(DcMotor.Direction.FORWARD);
        M2.setDirection(DcMotor.Direction.FORWARD);
        M3.setDirection(DcMotor.Direction.REVERSE);
        M4.setDirection(DcMotor.Direction.REVERSE);

        waitForStart();
        Arm.setPosition(0);
        M1.setPower(.3);
        M2.setPower(-.3);
        M3.setPower(.3);
        M4.setPower(-.3);
        sleep(900);      //move foward and get the robot to the right spot to shoot


        M1.setPower(0);
        M2.setPower(0);
        M3.setPower(0);
        M4.setPower(0);
        sleep(100);   // Stop the robot and give it .1 sec to stabalize

        launcher.setPower(1);
        sleep(4500);   //launch particles for 4.5 seconds - three flipper turns
        launcher.setPower(0); //Turn fliper off

        //   sleep(1000);

        //   collector.setPower(-1);      //turn on collector

        //   sleep(1000);

        M1.setPower(1);
        M2.setPower(1);
        M3.setPower(1);
        M4.setPower(1);
        sleep(325);       //rotate 45 degrees

        M1.setPower(0);
        M2.setPower(0);
        M3.setPower(0);
        M4.setPower(0);
        sleep(500); //  Settle the robot before moving

        M1.setPower(1);
        M2.setPower(-1);
        M3.setPower(1);
        M4.setPower(-1);
        sleep(1500);        //move foward to the middle

        M1.setPower(-1);
        M2.setPower(-1);
        M3.setPower(-1);
        M4.setPower(-1);
        sleep(400);       //rotate 45 degrees

        M1.setPower(0);
        M2.setPower(0);
        M3.setPower(0);
        M4.setPower(0);
        sleep(500);  // Settle the robot before spinning

        M1.setPower(1);
        M2.setPower(-1);
        M3.setPower(1);
        M4.setPower(-1);
        sleep(1800);        //move foward to between the beacons

        M1.setPower(1);
        M2.setPower(1);
        M3.setPower(1);
        M4.setPower(1);
        sleep(1350);    //rotate 270 degrees

        M1.setPower(0);
        M2.setPower(0);
        M3.setPower(0);
        M4.setPower(0);
        sleep(500);  // Settle the robot

        M1.setPower(0.4);
        M2.setPower(0.4);
        M3.setPower(-0.4);
        M4.setPower(-0.4);
        sleep(1500);    //move left up next to the wall

        M1.setPower(-0.5);
        M2.setPower(0.5);
        M3.setPower(-0.5);
        M4.setPower(0.5);
        sleep(500);//set the movement to reverse and move for 1/2 sec

        final float values[] = hsvValuesback;
        final float values2[] = hsvValuesfront;
        int loopcounter;
// This loop will allow the robot to move in reverse for Sleep(10) * 250 or until it detects a color
        for (loopcounter = 0; loopcounter < 250; loopcounter++) {
            //Sense for color
            Color.RGBToHSV(sensorRGB_Back.red() * 8, sensorRGB_Back.green() * 8, sensorRGB_Back.blue() * 8, hsvValuesback);
            telemetry.addData("red_Back = ", sensorRGB_Back.red());
            telemetry.addData("blue_Back = ", sensorRGB_Back.blue());
            telemetry.addData("green_Back = ", sensorRGB_Back.green());
            if (sensorRGB_Back.red() > 2.5 && sensorRGB_Back.red() > sensorRGB_Back.blue() && sensorRGB_Back.red() > sensorRGB_Back.green()) {
                telemetry.addData("color_back = ", "red");

                Arm.setPosition(0);// raise the arm and get it out of the way - We want BLUE
                M1.setPower(-0.5);
                M2.setPower(0.5);
                M3.setPower(-0.5);
                M4.setPower(0.5);
                sleep(1000);//move reverse past red light

                Arm.setPosition(.55); //drop the arm
                M1.setPower(0.4);
                M2.setPower(0.4);
                M3.setPower(-0.4);
                M4.setPower(-0.4);
                sleep(1000);    //move left and make sure we are tight up to the wall

                M1.setPower(0.2);
                M2.setPower(-0.2);
                M3.setPower(0.2);
                M4.setPower(-0.2); // This might need to go to -0.4 to pull the front of the robot past the button towards the wall
                sleep(1600); // move forward and hit BLUE beacon

                M1.setPower(0.5);
                M2.setPower(-0.5);
                M3.setPower(0.5);
                M4.setPower(-0.5);
                sleep(1000);//move faster to next beacon
                break;// THis stops the for loop and jumps below it
                // red beacon code
            } else if (sensorRGB_Back.blue() > 2.5 && sensorRGB_Back.blue() > sensorRGB_Back.green() && sensorRGB_Back.blue() > sensorRGB_Back.red()) {
                telemetry.addData("color_back = ", "blue");
                //blue beacon code
                Arm.setPosition(.55); //Set arm down to hit the BLUE beacon
                M1.setPower(0.4);
                M2.setPower(0.4);
                M3.setPower(-0.4);
                M4.setPower(-0.4);
                sleep(1000);   // move left up tight to the wall

                M1.setPower(-0.2);
                M2.setPower(0.4);  // This higher number pulls the front of the robot past the button towards the wall
                M3.setPower(-0.2);
                M4.setPower(0.2);
                sleep(1000);//move reverse and hit beacon

                Arm.setPosition(0); //Set arm up just in case we missed
                M1.setPower(0.5);
                M2.setPower(-0.5);
                M3.setPower(0.5);
                M4.setPower(-0.5);
                sleep(800);//move forward past the beacon

                //   sleep(1000);//move to next beacon Not needed
                break;// This gets us out of the for loop
            } else {
                telemetry.addData("color_back = ", "NA");
                sleep(10);
                //We haven't detected a color yet so jump back up and check again while moving in reverse
            }
        }//end of for loop
        M1.setPower(0.4);
        M2.setPower(0.4);
        M3.setPower(-0.4);
        M4.setPower(-0.4);
        sleep(1500);    //move left tight to the wall

        //   final float values3[] = hsvValuesback;
        //   final float values4[] = hsvValuesfront;

        Arm.setPosition(0); //Set arm up
        M1.setPower(0.5);
        M2.setPower(-0.5);
        M3.setPower(0.5);
        M4.setPower(-0.5);
        // Set up the robot to move forward

        // Again the for loop moves forward until we sense a color
        for (loopcounter = 0; loopcounter < 250; loopcounter++) {
            Color.RGBToHSV(sensorRGB_Front.red() * 8, sensorRGB_Front.green() * 8, sensorRGB_Front.blue() * 8, hsvValuesfront);
            telemetry.addData("red_Front = ", sensorRGB_Front.red());
            telemetry.addData("blue_Front = ", sensorRGB_Front.blue());
            telemetry.addData("green_Front = ", sensorRGB_Front.green());

            if (sensorRGB_Front.red() > 2.5 && sensorRGB_Front.red() > sensorRGB_Front.blue() && sensorRGB_Front.red() > sensorRGB_Front.green()) {
                telemetry.addData("color_front = ", "red");
                // We sensed RED
                Arm.setPosition(0); //Set arm up again
                M1.setPower(0.3);
                M2.setPower(-0.3);
                M3.setPower(0.3);
                M4.setPower(-0.3);
                sleep(600);//move forward past the beacon

                Arm.setPosition(.55); //Set arm down

                M1.setPower(0.4);
                M2.setPower(0.4);
                M3.setPower(-0.4);
                M4.setPower(-0.4);
                sleep(1000);   // move left up tight to the wall

                M1.setPower(-0.2);
                M2.setPower(0.4);// This higher number pulls the robot forward and around the button
                M3.setPower(-0.2);
                M4.setPower(0.2);
                sleep(2000);//move reverse and hit button and move down the wall
                break;// This gets us out of the for loop
                //red beacon code

            } else if (sensorRGB_Front.blue() > 2.5 && sensorRGB_Front.blue() > sensorRGB_Front.green() && sensorRGB_Front.blue() > sensorRGB_Front.red()) {
                telemetry.addData("color_front = ", "blue");
                //blue beacon code
                Arm.setPosition(.55); //Set arm down
                M1.setPower(0.4);
                M2.setPower(0.4);
                M3.setPower(-0.4);
                M4.setPower(-0.4);
                sleep(1000);   // move left up tight to the wall

                M1.setPower(0.2);
                M2.setPower(-0.2);
                M3.setPower(0.2);
                M4.setPower(-0.4); // This pulls us around the button
                sleep(1100);//move forward and hit button and up the wall towards ramp

                Arm.setPosition(0); //Set arm up just in case we missed it
                M1.setPower(-0.5);
                M2.setPower(0.5);
                M3.setPower(-0.5);
                M4.setPower(0.5);
                sleep(1000);//move reverse  and go past the beacon and down the wall
                break;  // Get out of the for loop
                //BLUE beacon code
            } else {
                telemetry.addData("color_front = ", "NA");
                //code if sensor doesn't read
                sleep(10);// keep going forward for 10 millesec and go back and check for color again
            }
        } //end of for loop
        // We should be between beacons
        M1.setPower(0.4);
        M2.setPower(0.4);
        M3.setPower(-0.4);
        M4.setPower(-0.4);
        sleep(1000);   // move left tight up against the wall

        M1.setPower(0.5);
        M2.setPower(-0.5);
        M3.setPower(0.5);
        M4.setPower(-0.5);
        //set to forward and go until we detect any color
        for (loopcounter = 0; loopcounter < 150; loopcounter++) {
            Color.RGBToHSV(sensorRGB_Front.red() * 8, sensorRGB_Front.green() * 8, sensorRGB_Front.blue() * 8, hsvValuesfront);
            if (sensorRGB_Front.blue() > 2.5 || sensorRGB_Front.red() > 2.5) {
                M1.setPower(1);
                M2.setPower(1);
                M3.setPower(1);
                M4.setPower(1);
                sleep(750);    //rotate 90 and face the cap ball
                M1.setPower(0.5);
                M2.setPower(-0.5);
                M3.setPower(0.5);
                M4.setPower(-0.5);
                sleep(2000); // move forward and knock the ball off and park
                break;
            } else {
                sleep(10); // go forward for 10 millesec and check for color again
            }
        }
        //ends the autonomous
        M1.setPower(0);
        M2.setPower(0);
        M3.setPower(0);
        M4.setPower(0);
        collector.setPower(0);
    }
}