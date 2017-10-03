package com.qualcomm.ftcrobotcontroller.opmodes;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;
//Created by owner on 2/1/16.


/*end goal: robot parked in Beacon Repair Zone: 5 pts
            climbers dumped in bucket: 20 pts auto/20 pts teleop
            total: 45 points
 */
public class Autonomous5115 extends LinearOpMode {
    //motor declaration
    DcMotor motorLeft;
    DcMotor motorLeft2;
    DcMotor motorRight;
    DcMotor motorRight2;

    //servo declaration
    Servo climberFlipper;
    //double flipperposition = 0.00;//stuff I don't think we need
    //double flipperdelta = 0.01;

    public void runOpMode() throws InterruptedException{
        //declaration/initialization
        motorLeft = hardwareMap.dcMotor.get("L1");
        motorLeft2 = hardwareMap.dcMotor.get("L2");
        motorRight = hardwareMap.dcMotor.get("R1");
        motorRight2 = hardwareMap.dcMotor.get("R2");


        motorLeft.setDirection(DcMotor.Direction.REVERSE);
        motorLeft2.setDirection(DcMotor.Direction.REVERSE);
        motorRight.setDirection(DcMotor.Direction.FORWARD);
        motorRight2.setDirection(DcMotor.Direction.FORWARD);
        climberFlipper.setPosition(0.00);

        //start program
        waitForStart();

        //drive forward
        motorLeft.setPower(1);
        motorRight.setPower(1);
        motorLeft2.setPower(1);
        motorRight2.setPower(1);
        sleep(4000);

        //park
        motorRight.setPower(0);
        motorLeft.setPower(0);
        motorLeft2.setPower(0);
        motorRight2.setPower(0 );

        //dump climbers
        /*sleep(500);//be sure robot is stable
        climberFlipper.setPosition(0.6);
        sleep(2000);//option to stop program if we want to save for teleop
        climberFlipper.setPosition(0.8);//final dump
        */
    }

}
