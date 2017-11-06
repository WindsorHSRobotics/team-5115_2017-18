package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;
import java.lang.*;
/**
 * Created by Robo-Admin on 9/26/2017.
 */

public class MecanumTest extends OpMode {

    DcMotor M1, M2, M3, M4, winch;
    double servo1pos, servo2pos;
    Servo top;
    Servo bottom;

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
        winch = hardwareMap.dcMotor.get("winch");

        M1.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        M2.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        M3.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        M4.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);

        top = hardwareMap.servo.get("top");
        bottom = hardwareMap.servo.get("bottom");

        /*collector = hardwareMap.dcMotor.get("collector");
        launcher = hardwareMap.dcMotor.get("launcher");
        Arm = hardwareMap.servo.get("Servo");*/

        //establishing directions so all of the motors move in the same direction
        M1.setDirection(DcMotor.Direction.REVERSE);
        M2.setDirection(DcMotor.Direction.FORWARD);
        M3.setDirection(DcMotor.Direction.REVERSE);
        M4.setDirection(DcMotor.Direction.FORWARD);

        servo1pos = .5;
        servo2pos = .5;

    }

    public void loop() {

        double r = Math.hypot(gamepad1.left_stick_x, gamepad1.left_stick_y);
        if(gamepad1.left_bumper){
            r = r/6;
        }
        else if(gamepad1.right_bumper){
            r = r/2;
        }

        if(gamepad1.x){
            servo1pos = .2;
            servo2pos = .8;
        }
        else if(gamepad1.b){
            servo1pos = .8;
            servo2pos = .2;
        }
        else{
            servo1pos = .5;
            servo2pos = .5;
        }
        if(gamepad1.left_trigger>.2){
            winch.setPower(gamepad1.left_trigger);
        }
        else if(gamepad1.right_trigger>.1){
            winch.setPower(-1 * gamepad1.right_trigger);
        }
        else{
            winch.setPower(0);
        }


        double robotAngle = Math.atan2(gamepad1.left_stick_y, gamepad1.left_stick_x);
        double rightX = gamepad1.right_stick_x;
        double pi = Math.PI/4;
        double v1 = (r * -Math.cos(robotAngle + pi)) - rightX;
        double v2 = (r * -Math.cos(robotAngle + pi)) + rightX;
        double v3 = (r * Math.cos(robotAngle - pi)) - rightX;
        double v4 = (r * Math.cos(robotAngle - pi)) + rightX;

        v1 = Range.clip(v1,-1,1);
        v2 = Range.clip(v2,-1,1);
        v3 = Range.clip(v3,-1.0,1.0);
        v4 = Range.clip(v4,-1.0,1.0);

        M1.setPower(v1);
        M2.setPower(v2);
        M3.setPower(v3);
        M4.setPower(v4);

        top.setPosition(servo1pos);
        bottom.setPosition(servo2pos);
        }
    }