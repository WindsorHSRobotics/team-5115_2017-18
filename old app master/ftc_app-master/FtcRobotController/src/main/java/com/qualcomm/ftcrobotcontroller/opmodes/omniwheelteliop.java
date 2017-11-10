package com.qualcomm.ftcrobotcontroller.opmodes;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;
/**
 * Created by Anthony on 10/13/2016.
 */
public class omniwheelteliop extends OpMode{

    DcMotor R1;
    DcMotor R2;
    DcMotor F1;
    DcMotor F2;

    public omniwheelteliop(){

    }

    public void init(){
        R1 = hardwareMap.dcMotor.get("r1");
        R2 = hardwareMap.dcMotor.get("r2");
        F1 = hardwareMap.dcMotor.get("f1");
        F2 = hardwareMap.dcMotor.get("f2");


    }

    public void loop(){
        telemetry.addData("pwr" , "left stick x " + String.format("%.2f", gamepad1.left_stick_x));
        telemetry.addData("pwr" , "left stick y " + String.format("%.2f", gamepad1.left_stick_y));
        telemetry.addData("pwr" , "right stick x " + String.format("%.2f", gamepad1.right_stick_x));

        if(Math.abs(gamepad1.left_stick_x) > .1 || Math.abs(gamepad1.left_stick_y) > .1 || Math.abs(gamepad1.right_stick_x) > .1){
            R1.setPower((gamepad1.left_stick_y + gamepad1.left_stick_x - gamepad1.right_stick_x)/3);
            R2.setPower((-gamepad1.left_stick_y + gamepad1.left_stick_x - gamepad1.right_stick_x)/3);
            F1.setPower((gamepad1.left_stick_y - gamepad1.left_stick_x - gamepad1.right_stick_x)/3);
            F2.setPower((-gamepad1.left_stick_y - gamepad1.left_stick_x - gamepad1.right_stick_x)/3);
        }

        else{
            R1.setPower(0);
            R2.setPower(0);
            F1.setPower(0);
            F2.setPower(0);
        }
    }



}

