package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.util.Range;
import java.lang.*;
/**
 * Created by Robo-Admin on 9/26/2017.
 */

public class MecanumTest extends OpMode {

    DcMotor M1, M2, M3, M4, collector, launcher;
    //Servo Arm;
    /*
 * 1 = f1 = front left
 * 2 = f2 = front right
 * 3 = r1 = rear left
 * 4 = r2 = rear right
 *
 */
    //Basic loading information for robot hardware
    public void init() {
        M1 = hardwareMap.dcMotor.get("1");
        M2 = hardwareMap.dcMotor.get("2");
        M3 = hardwareMap.dcMotor.get("3");
        M4 = hardwareMap.dcMotor.get("4");

        M1.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        M2.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        M3.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        M4.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        /*collector = hardwareMap.dcMotor.get("collector");
        launcher = hardwareMap.dcMotor.get("launcher");
        Arm = hardwareMap.servo.get("Servo");*/

        //establishing directions so all of the motors move in the same direction
        M1.setDirection(DcMotor.Direction.REVERSE);
        M2.setDirection(DcMotor.Direction.FORWARD);
        M3.setDirection(DcMotor.Direction.REVERSE);
        M4.setDirection(DcMotor.Direction.FORWARD);

    }

    public void loop() {

        double r = Math.hypot(gamepad1.left_stick_x, gamepad1.left_stick_y);
        if(gamepad1.left_bumper){
            r = r/6;
        }
        else if(gamepad1.right_bumper){
            r = r/2;
        }

        double robotAngle = Math.atan2(gamepad1.left_stick_y, gamepad1.left_stick_x);
        double rightX = gamepad1.right_stick_x;
        double pi = Math.PI/4;
        double v1 = (r * Math.cos(robotAngle-pi)) - rightX;
        double v2 = (r * -Math.cos(robotAngle + pi)) + rightX;
        double v3 = (r * -Math.cos(robotAngle + pi)) - rightX;
        double v4 = (r * Math.cos(robotAngle-pi)) + rightX;

        v1 = Range.clip(v1,-1,1);
        v2 = Range.clip(v2,-1,1);
        v3 = Range.clip(v3,-1.0,1.0);
        v4 = Range.clip(v4,-1.0,1.0);

        M1.setPower(v1);
        M2.setPower(v2);
        M3.setPower(v3);
        M4.setPower(v4);
        }
    }