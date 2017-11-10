package com.qualcomm.ftcrobotcontroller.opmodes;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;
import com.qualcomm.ftccommon.DbgLog;
import com.qualcomm.ftcrobotcontroller.R;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;

/**
 * Created by Anthony on 11/25/2016.
 *
 * color is left, color2 is right
 */
public class duelcolorsensortest extends LinearOpMode {
    ColorSensor color;
    ColorSensor color2;


    public void runOpMode() throws InterruptedException {
        hardwareMap.logDevices();
        color = hardwareMap.colorSensor.get("color");
        color2 = hardwareMap.colorSensor.get("color 2");


        color.enableLed(true);
        color2.enableLed(true);

        float hsvValues1[] = {0F,0F,0F};
        float hsvValues2[] = {0F,0F,0F};


        waitOneFullHardwareCycle();
        waitForStart();


        int reading;
        //used to give meaningful data output for color. 1 = left side blue + right side red, 2 = right side blue and left side red, 3 = null
        //

        while(true){

            Color.RGBToHSV(color.red()*8, color.green()*8, color.blue()*8, hsvValues1);
            Color.RGBToHSV(color2.red()*8, color2.green()*8, color2.blue()*8, hsvValues2);


            if(color.red()>color2.red() && color.red()>15 && color.red()>color.green() && color.red()>color.blue()){
                telemetry.addData("left side = ", "red");
                reading = 2;
            }
            if(color.blue()>color2.blue() && color.blue()>10 && color.blue()>color.red() && color.blue()>color.green()){
                telemetry.addData("left side = ","blue" );
                reading = 1;
            }

            if(color2.red()>color.red() && color2.red()>15 && color2.red()>color2.green() && color2.red()>color2.blue()){
                telemetry.addData("right side = ", "red");
                reading = 1;
            }
            if(color2.blue()>color.blue() && color2.blue()>10 && color2.blue()>color2.red() && color2.blue()>color2.green()) {
                telemetry.addData("right side = ", "blue");
                reading = 2;
            }

            else {
                telemetry.addData("color = ", "none");
                reading = 3;
            }

            telemetry.addData("reading ",  "= " + String.format("%.2f", reading));
        }



    }
}