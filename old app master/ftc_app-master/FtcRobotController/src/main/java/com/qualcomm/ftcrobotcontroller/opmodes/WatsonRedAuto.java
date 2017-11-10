
package com.qualcomm.ftcrobotcontroller.opmodes;

        import android.graphics.Color;

        import com.qualcomm.robotcore.hardware.ColorSensor;
        import com.qualcomm.robotcore.hardware.DcMotor;
        import com.qualcomm.robotcore.hardware.Servo;
        import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
        import java.lang.*;

public class WatsonRedAuto extends LinearOpMode {

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
        float hsvValuesfront[] = {0F,0F,0F};
        float hsvValuesback[] = {0F,0F,0F};


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
        sleep(300);      //move foward


        M1.setPower(0);
        M2.setPower(0);
        M3.setPower(0);
        M4.setPower(0);
        sleep(1000);

        launcher.setPower(1);
        sleep(4500);   //launch particles
        launcher.setPower(0);

        //   sleep(1000);

        //   collector.setPower(-1);      //turn on collector

        //   sleep(1000);

        M1.setPower(-1);
        M2.setPower(-1);
        M3.setPower(-1);
        M4.setPower(-1);
        sleep(325);       //rotate 45

        M1.setPower(0);
        M2.setPower(0);
        M3.setPower(0);
        M4.setPower(0);
        sleep(500);

        M1.setPower(1);
        M2.setPower(-1);
        M3.setPower(1);
        M4.setPower(-1);
        sleep(2750);        //move foward

        M1.setPower(0);
        M2.setPower(0);
        M3.setPower(0);
        M4.setPower(0);
        sleep(500);

        M1.setPower(1);
        M2.setPower(1);
        M3.setPower(1);
        M4.setPower(1);
        sleep(325);    //rotate 45

        M1.setPower(0);
        M2.setPower(0);
        M3.setPower(0);
        M4.setPower(0);
        sleep(500);

        M1.setPower(0.4);
        M2.setPower(0.4);
        M3.setPower(-0.4);
        M4.setPower(-0.4);
        sleep(1500);    //move left

        M1.setPower(-0.5);
        M2.setPower(0.5);
        M3.setPower(-0.5);
        M4.setPower(0.5);
        sleep(500);//move reverse

        final float values[] = hsvValuesback;
        final float values2[] = hsvValuesfront;
        int loopcounter;

        for(loopcounter = 0;loopcounter <250;loopcounter++){
            Color.RGBToHSV(sensorRGB_Back.red()*8, sensorRGB_Back.green()*8, sensorRGB_Back.blue()*8, hsvValuesback);
            telemetry.addData("red_Back = ",sensorRGB_Back.red());
            telemetry.addData("blue_Back = ",sensorRGB_Back.blue());
            telemetry.addData("green_Back = ",sensorRGB_Back.green());
            if(sensorRGB_Back.blue()>2.5 && sensorRGB_Back.blue()>sensorRGB_Back.green() && sensorRGB_Back.blue()>sensorRGB_Back.red()){
                telemetry.addData("color_back = ", "blue");
                // blue beacon code
                Arm.setPosition(0);// raise arm
                M1.setPower(-0.5);
                M2.setPower(0.5);
                M3.setPower(-0.5);
                M4.setPower(0.5);
                sleep(500);//move reverse changed to 500

                Arm.setPosition(.55); //drop arm
                M1.setPower(0.4);
                M2.setPower(0.4);
                M3.setPower(-0.4);
                M4.setPower(-0.4);
                sleep(1000);    //move left

                M1.setPower(0.2);
                M2.setPower(-0.2);
                M3.setPower(0.2);
                M4.setPower(-0.2);
                sleep(1600); // move forward and hit beacon

                M1.setPower(0.5);
                M2.setPower(-0.5);
                M3.setPower(0.5);
                M4.setPower(-0.5);
                sleep(1000);//move to next beacon
                break;         //blue beacon code

            }

            else if(sensorRGB_Back.red()>2.5 && sensorRGB_Back.red()>sensorRGB_Back.blue() && sensorRGB_Back.red()>sensorRGB_Back.green()){
                telemetry.addData("color_back = ", "red" );
                //red beacon code
                Arm.setPosition(.55); //Set arm down
                M1.setPower(0.4);
                M2.setPower(0.4);
                M3.setPower(-0.4);
                M4.setPower(-0.4);
                sleep(1000);   // move left

                M1.setPower(-0.2);
                M2.setPower(0.2);
                M3.setPower(-0.2);
                M4.setPower(0.2);
                sleep(1500);//move reverse and hit beacon

                Arm.setPosition(0); //Set arm up just in case we missed
                M1.setPower(0.5);
                M2.setPower(-0.5);
                M3.setPower(0.5);
                M4.setPower(-0.5);
                sleep(1200);//move forward

                //   sleep(1000);//move to next beacon
                break;
            }
            else{
                telemetry.addData("color_back = ", "NA");
                sleep(10);
                //code if sensor doesn't read
            }
        }//end of for loop
        M1.setPower(0.4);
        M2.setPower(0.4);
        M3.setPower(-0.4);
        M4.setPower(-0.4);
        sleep(1500);    //move left

        //   final float values3[] = hsvValuesback;
        //   final float values4[] = hsvValuesfront;

        Arm.setPosition(0); //Set arm up just in case we missed
        M1.setPower(0.5);
        M2.setPower(-0.5);
        M3.setPower(0.5);
        M4.setPower(-0.5);
        // move forward until we sense a color

        for(loopcounter = 0;loopcounter <250;loopcounter++) {
            Color.RGBToHSV(sensorRGB_Front.red() * 8, sensorRGB_Front.green() * 8, sensorRGB_Front.blue() * 8, hsvValuesfront);
            telemetry.addData("red_Front = ", sensorRGB_Front.red());
            telemetry.addData("blue_Front = ", sensorRGB_Front.blue());
            telemetry.addData("green_Front = ", sensorRGB_Front.green());

            if (sensorRGB_Front.blue() > 2.5 && sensorRGB_Front.blue() > sensorRGB_Front.green() && sensorRGB_Front.blue() > sensorRGB_Front.red()) {
                telemetry.addData("color_front = ", "blue");
                // blue beacon code
                Arm.setPosition(0); //Set arm up

                M1.setPower(0.3);
                M2.setPower(-0.3);
                M3.setPower(0.3);
                M4.setPower(-0.3);
                sleep(1000);//move forward

                Arm.setPosition(.55); //Set arm down

                M1.setPower(0.4);
                M2.setPower(0.4);
                M3.setPower(-0.4);
                M4.setPower(-0.4);
                sleep(1000);   // move left

                M1.setPower(-0.2);
                M2.setPower(0.2);
                M3.setPower(-0.2);
                M4.setPower(0.2);
                sleep(1500);//move reverse and hit button
                //blue beacon code

            }
            else if (sensorRGB_Front.red() > 2.5 && sensorRGB_Front.red() > sensorRGB_Front.blue() && sensorRGB_Front.red() > sensorRGB_Front.green()) {
                  telemetry.addData("color_front = ", "red");
                //red beacon code
                Arm.setPosition(.55); //Set arm down
                M1.setPower(0.4);
                M2.setPower(0.4);
                M3.setPower(-0.4);
                M4.setPower(-0.4);
                sleep(500);   // move left

                M1.setPower(0.3);
                M2.setPower(-0.3);
                M3.setPower(0.3);
                M4.setPower(-0.3);
                sleep(1000);//move forward and hit button

                Arm.setPosition(0); //Set arm up just in case
                M1.setPower(-0.5);
                M2.setPower(0.5);
                M3.setPower(-0.5);
                M4.setPower(0.5);
                sleep(1500);//move reverse

                break; //Red beacon code
            } else {
                telemetry.addData("color_front = ", "NA");
                //code if sensor doesn't read
                sleep(10);
            }

        } //end of for loop

        M1.setPower(0.4);
        M2.setPower(0.4);
        M3.setPower(-0.4);
        M4.setPower(-0.4);
        sleep(1000);   // move left

        M1.setPower(-0.5);
        M2.setPower(0.5);
        M3.setPower(-0.5);
        M4.setPower(0.5);
        //set to reverse
        for(loopcounter = 0;loopcounter <150; loopcounter++) {
            Color.RGBToHSV(sensorRGB_Front.red() * 8, sensorRGB_Front.green() * 8, sensorRGB_Front.blue() * 8, hsvValuesfront);
            if (sensorRGB_Front.blue() > 2.5 || sensorRGB_Front.blue() > 2.5) {
                M1.setPower(1);
                M2.setPower(1);
                M3.setPower(1);
                M4.setPower(1);
                sleep(7750);    //rotate 90
                M1.setPower(0.5);
                M2.setPower(-0.5);
                M3.setPower(0.5);
                M4.setPower(-0.5);
                sleep(1350); // move forward and knock the ball off

            } else {
                sleep(10);
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