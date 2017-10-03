package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

/**
 * Created by domma on 1/20/2017.
 */
public class TeleOp10736 extends OpMode{

    DcMotor motorLeft;
    DcMotor motorRight;
    Servo arm;

    final static double ARM_MIN_RANGE  = 0.01;
    final static double ARM_MAX_RANGE  = 0.99;

    // position of the arm servo.
    double armPosition;

    // amount to change the arm servo position.
    double armDelta = 0.05;


    public void init(){
        motorLeft = hardwareMap.dcMotor.get("motorLeft");
        motorRight = hardwareMap.dcMotor.get("motorRight");
        motorRight.setDirection(DcMotor.Direction.REVERSE);
        arm = hardwareMap.servo.get("servoDoor");

        armPosition = 0.2;




    }


    public void loop(){
        telemetry.addData("door postition = ", armPosition);
        if(Math.abs(gamepad1.left_stick_y)>.1){
            motorLeft.setPower(gamepad1.left_stick_y);
        }
        else{
            motorLeft.setPower(0);
        }

        if(Math.abs(gamepad1.right_stick_y)>.1){
            motorRight.setPower(gamepad1.right_stick_y);
        }
        else{
            motorRight.setPower(0);
        }
        if (gamepad1.a) {
            // if the A button is pushed on gamepad1, increment the position of
            // the arm servo.
            armPosition += armDelta;
        }

        if (gamepad1.y) {
            // if the Y button is pushed on gamepad1, decrease the position of
            // the arm servo.
            armPosition -= armDelta;
        }

        armPosition = Range.clip(armPosition, ARM_MIN_RANGE, ARM_MAX_RANGE);

        arm.setPosition(armPosition);


    }

}


