package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import java.lang.*;
import com.qualcomm.robotcore.hardware.DcMotor;

/*KEYMAP

    GAMEPAD 1
        left stick.x and y      drive
        right stick.x           change heading
        right bumper            change direction while held
        left bumper             run overdrive while held
        x                       run collector
        b                       turn launcher on
        y                       turn launcher off

        the sticks combine to drive the robot like a 3rd person shooter.
 */
    /*
    all motors run "clockwise" once reversed
    so if they all have a positive power the robot rotates clockwise
    map: (launcher out is top)
    F1  F2
    R1  R2

    launcher 1 is bottom
    launcher 2 is top
     */
public class omniwheelwithtrig extends OpMode {

    //declarations
    DcMotor R1, R2, F1, F2, collector, launcher1, launcher2;//motors
    double heading, heading_degrees, magnitude, leftstick_xsq, leftstick_ysq, direction, multiplier;//needed for trigonometry and fine control
    int bCount;//controls launcher
    boolean collectorOn;//controls collector

    public omniwheelwithtrig(){
    }

    //initialize; set motors to specific hardware pieces
    public void init(){
        //initialization of motors
        //drive motors:
        R1 = hardwareMap.dcMotor.get("r1");
        R2 = hardwareMap.dcMotor.get("r2");
        F1 = hardwareMap.dcMotor.get("f1");
        F2 = hardwareMap.dcMotor.get("f2");
        //launcher motors:
        launcher1 = hardwareMap.dcMotor.get("launcher1");//bottom
        launcher2 = hardwareMap.dcMotor.get("launcher2");//top
        //collector motor:
        collector = hardwareMap.dcMotor.get("collector");
        //reverses drive motors: we preferred to program in this direction
        R1.setDirection(DcMotor.Direction.REVERSE);
        R2.setDirection(DcMotor.Direction.REVERSE);
        F1.setDirection(DcMotor.Direction.REVERSE);
        F2.setDirection(DcMotor.Direction.REVERSE);
        //cancels out reversing of collector in autonomous
        collector.setDirection(DcMotor.Direction.FORWARD);
        //variables for fine control
        direction = 1;
        multiplier = -.5;
        //variables to control launcher and collector
        collectorOn = false;
        bCount = 0;
    }

    //runs the program
    public void loop(){
        //calculates what angle the left stick is at
        heading = -1*(Math.atan2(gamepad1.left_stick_y, gamepad1.left_stick_x));
        //converts heading to degrees for easier handling
        heading_degrees = heading*(180/Math.PI);
        //sets variables for use later: these set speed in x and y directions
        leftstick_xsq = gamepad1.left_stick_x * gamepad1.left_stick_x;
        leftstick_ysq = gamepad1.left_stick_y * gamepad1.left_stick_y;
        //keeps program from setting motor powers greater than 1 (causing an e-stop)
        if ((leftstick_xsq + leftstick_ysq)>1.0){
            magnitude = 1;
        }
        else {
            magnitude = Math.sqrt(leftstick_xsq + leftstick_ysq);
        }
        //returns information on robot driving to the Driver Station
        telemetry.addData("heading:",  "heading: " + String.format("%.2f", heading_degrees));
        telemetry.addData("pwr:",  "magnitude: " + String.format("%.2f", magnitude));


        /*GAMEPAD 2 CODE CURRENTLY COMMENTED OUT: unnecessary at the moment
        //runs collector with greater precision
        if(Math.abs(gamepad2.left_stick_y)>.1){
           collector.setPower(gamepad2.left_stick_y);
        }
        else{
            collector.setPower(0);
        }\
        //runs launcher with greater precision
        if(Math.abs(gamepad2.right_stick_y)>.1){
            launcher1.setPower(gamepad1.right_stick_y);
        }
        else{
            launcher1.setPower(0);
        }*/

        //puts the robot in reverse
        if(gamepad1.right_bumper){
            direction = -1;
            telemetry.addData("direction :", "back on up");
        }
        else{
            direction = 1;
            telemetry.addData("direction :", "forwards");
        }
        //makes robot drive faster for added (usually unneeded) speed
        if(gamepad1.left_bumper){
            multiplier = 1;
            telemetry.addData("full", " overdrive'");
        }
        else{
            multiplier = .5;
        }
        //runs collector "up" or stops it
        if(gamepad1.x){
            collector.setPower(-1);
        }
        else{
            collector.setPower(0);
        }
        //turns launcher on
        if (gamepad1.b) {
            bCount = 1;
        }
        //turns launcher off
        if (gamepad1.y)
        {
            bCount = 0;
        }
        //actually runs the launcher motors using the variables set above
        if (bCount!=0) {
            launcher1.setPower(1); //faster than launcher 2 to kick the particles up
            launcher2.setPower(.9);
        }
        if (bCount==0) {
            launcher1.setPower(0);
            launcher2.setPower(0);
        }

        /*finds and sets motor power using trigonometry
        the if statement condition checks to make sure the sticks are being intentionally pushed rather than just moved a little
        direction sets forwards or backwards
        multiplier sets overdrive or non-overdrive
        magnitude is speed of driving based on how far the left stick is pushed
        Math.cos((Math.PI/4) + or - heading)) is what direction the robot is going, which impacts how the motor spins
        each motor has a different combination of signs to make them interact correctly
        right stick x value is added at the end of the function to add the ability for robot to change direction */
        if(Math.abs(gamepad1.left_stick_x) > .1 || Math.abs(gamepad1.left_stick_y) > .1 || Math.abs(gamepad1.right_stick_x) > .1){
            double powerF1 = (direction * multiplier *((magnitude * Math.cos((Math.PI/4) - heading))+gamepad1.right_stick_x));
            double powerF2 = (direction * multiplier *((magnitude * Math.cos((Math.PI/4) + heading))+gamepad1.right_stick_x));
            double powerR1 = (direction * multiplier *((-magnitude * Math.cos((Math.PI/4) + heading))+gamepad1.right_stick_x));
            double powerR2 = (direction * multiplier *((-magnitude * Math.cos((Math.PI/4) - heading))+gamepad1.right_stick_x));
            //all these statements make sure no motor is given a power greater then 1 or less than -1, which would cause an e-stop
            if(powerF1 >1){
                powerF1 = 1;
            }
            if(powerF1 < -1){
                powerF1 = -1;
            }

            if(powerF2 < -1){
                powerF2 = -1;
            }
            if(powerF2 > 1){
                powerF2 = 1;
            }

            if(powerR1 >1){
                powerR1 = 1;
            }
            if(powerR1 < -1){
                powerR1 = -1;
            }

            if(powerR2 > 1){
                powerR2 = 1;
            }
            if(powerR2 < -1){
                powerR2 = -1;
            }
            //now that all powers have been calculated and clipped to between -1 and 1, each motor's power is actually set
            F1.setPower(powerF1);
            F2.setPower(powerF2);
            R1.setPower(powerR1);
            R2.setPower(powerR2);
        }
        //if no stick is pushed far enough, the robot will not drive
        else{
            R1.setPower(0);
            R2.setPower(0);
            F1.setPower(0);
            F2.setPower(0);
        }

    } //end of loop()
} //end of omniwheelwithtrig()
