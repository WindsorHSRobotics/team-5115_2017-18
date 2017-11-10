package com.qualcomm.ftcrobotcontroller.opmodes;


import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotorController;

import java.lang.*;

/**
 * Created by Anthony on 11/22/2016.
 */
public class dominicomniwheelwithtrig extends OpMode{
    DcMotor R1, R2, F1, F2;
    double heading, heading_degrees, magnitude, leftstick_xsq, leftstick_ysq;
    double powerF1, powerF2, powerR1, powerR2;


    //initialize; set motors to specific hardware pieces
    public void init(){
        R1 = hardwareMap.dcMotor.get("r1");
        R2 = hardwareMap.dcMotor.get("r2");
        F1 = hardwareMap.dcMotor.get("f1");
        F2 = hardwareMap.dcMotor.get("f2");


        R1.setDirection(DcMotor.Direction.REVERSE);
        R2.setDirection(DcMotor.Direction.REVERSE);
        F1.setDirection(DcMotor.Direction.REVERSE);
        F2.setDirection(DcMotor.Direction.REVERSE);

        R1.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        R2.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        F1.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        F2.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);

    }

    //runs the program
    public void loop(){
        if (gamepad1.b) {
            powerF1 = 1;
            powerF2 = 1;
            powerR1 = -1;
            powerR2 = -1;


        }



        if (gamepad1.y) {
            powerF1 = 1;
            powerF2 = -1;
            powerR1 = 1;
            powerR2 = -1;


        }

        if (gamepad1.x) {
            powerF1 = -1;
            powerF2 = -1;
            powerR1 = 1;
            powerR2 = 1;


        }

        if (gamepad1.a) {
            powerF1 = -1;
            powerF2 = 1;
            powerR1 = -1;
            powerR2 = -1;

        }

        heading = -1*(Math.atan2(gamepad1.left_stick_y, gamepad1.left_stick_x));
        heading_degrees = heading*(180/Math.PI);
        leftstick_xsq = gamepad1.left_stick_x * gamepad1.left_stick_x;
        leftstick_ysq = gamepad1.left_stick_y * gamepad1.left_stick_y;
        if ((leftstick_xsq + leftstick_ysq)>1.0){
            magnitude = 1;
        }
        else {
            magnitude = Math.sqrt(leftstick_xsq + leftstick_ysq);
        }

        telemetry.addData("heading:",  "heading: " + String.format("%.2f", heading_degrees));
        telemetry.addData("pwr:",  "magnitude: " + String.format("%.2f", magnitude));







        //finds and sets motor power
        if(Math.abs(gamepad1.left_stick_x) > .1 || Math.abs(gamepad1.left_stick_y) > .1 || Math.abs(gamepad1.right_stick_x) > .1){
            powerF1 = (-.5*((magnitude * Math.cos((Math.PI/4) - heading))+gamepad1.right_stick_x));
            powerF2 = (-.5*((magnitude * Math.cos((Math.PI/4) + heading))+gamepad1.right_stick_x));
            powerR1 = (-.5*((-magnitude * Math.cos((Math.PI/4) + heading))+gamepad1.right_stick_x));
            powerR2 = (-.5*((-magnitude * Math.cos((Math.PI/4) - heading))+gamepad1.right_stick_x));

        }

        else{
            powerF1 = 0;
            powerF2 = 0;
            powerR1 = 0;
            powerR2 = 0;
        }
        F1.setPower(powerF1);
        F2.setPower(powerF2);
        R1.setPower(powerR1);
        R2.setPower(powerR2);

    } //end of looop()
} //end of omniwheelwithtrig()
