package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import java.lang.*;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by Anthony on 10/23/2016.
 */
public class omniwheelwithouttrigdemo extends OpMode{

    DcMotor R1;
    DcMotor R2;
    DcMotor F1;
    DcMotor F2;

    public omniwheelwithouttrigdemo(){

    }

    public void init(){
        DcMotor R1 = hardwareMap.dcMotor.get("r1");
        DcMotor R2 = hardwareMap.dcMotor.get("r2");
        DcMotor F1 = hardwareMap.dcMotor.get("f1");
        DcMotor F2 = hardwareMap.dcMotor.get("f2");
    }

    public void loop(){

        F1.setPower(gamepad1.left_stick_y);
        F2.setPower(-gamepad1.left_stick_y);
        R1.setPower(gamepad1.left_stick_y);
        R2.setPower(-gamepad1.left_stick_y);



        F1.setPower(gamepad1.left_stick_x);
        F2.setPower(gamepad1.left_stick_y);
        R1.setPower(-gamepad1.left_stick_x);
        R2.setPower(-gamepad1.left_stick_x);



        F1.setPower(gamepad1.right_stick_x);
        F2.setPower(gamepad1.right_stick_x);
        R1.setPower(gamepad1.right_stick_x);
        R2.setPower(gamepad1.right_stick_x);








    }





}




