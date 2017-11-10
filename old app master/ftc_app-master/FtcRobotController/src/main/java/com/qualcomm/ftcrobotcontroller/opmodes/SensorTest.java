package com.qualcomm.ftcrobotcontroller.opmodes;

import android.graphics.Color;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import java.lang.*;

public class SensorTest extends LinearOpMode {

    ColorSensor sensorRGB;

    public void runOpMode() throws InterruptedException {

        hardwareMap.logDevices();
        sensorRGB = hardwareMap.colorSensor.get("color");

        boolean bEnabled = true;
        sensorRGB.enableLed(true);
        float hsvValues[] = {0F, 0F, 0F};

        final float values[] = hsvValues;

        Color.RGBToHSV(sensorRGB.red() * 8, sensorRGB.green() * 8, sensorRGB.blue() * 8, hsvValues);

        if (sensorRGB.red() > 20 && sensorRGB.red() > sensorRGB.blue() && sensorRGB.red() > sensorRGB.green()) {
            telemetry.addData("color = ", "red");

            if (sensorRGB.blue() > 10 && sensorRGB.blue() > sensorRGB.green() && sensorRGB.blue() > sensorRGB.red()) {
                telemetry.addData("color = ", "blue");
                //blue beacon code
            } else {
                telemetry.addData("color = ", "NA");
                //code if sensor doesn't read
            }
        }
    }
}