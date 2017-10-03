package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

public class Autonomous10736 extends LinearOpMode {
    DcMotor driveLeft; //drive motors
    DcMotor driveRight;

    public void runOpMode () throws InterruptedException {
        driveLeft = hardwareMap.dcMotor.get("L");
        driveRight = hardwareMap.dcMotor.get("R");

        driveRight.setDirection(DcMotor.Direction.FORWARD);
        driveLeft.setDirection(DcMotor.Direction.FORWARD);
        //wait for start
        waitForStart();
        //drive forward for 5 seconds
        driveRight.setPower(.5);//CHECK THIS.
        driveLeft.setPower(.5);
        sleep(7000);
        driveRight.setPower(0);
        driveLeft.setPower(0);

    }
}
