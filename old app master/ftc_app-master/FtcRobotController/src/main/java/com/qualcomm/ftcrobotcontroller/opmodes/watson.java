package com.qualcomm.ftcrobotcontroller.opmodes;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import java.lang.*;




/*
 * 1 = f1 = front left
 * 2 = f2 = front right
 * 3 = r1 = rear left
 * 4 = r2 = rear right
 *
 */
public class watson extends OpMode{

    DcMotor M1, M2, M3, M4, collector, launcher;
    //Servo Arm;

    //Basic loading information for robot hardware
    public void init(){
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
        M1.setDirection(DcMotor.Direction.FORWARD);
        M2.setDirection(DcMotor.Direction.FORWARD);
        M3.setDirection(DcMotor.Direction.FORWARD);
        M4.setDirection(DcMotor.Direction.FORWARD);
        int crawl;



    }
    //setting drive pattern for the joystick
    public void loop(){

        //establishing speed mode
        double crawl;
        crawl = .75;
        if(gamepad1.left_bumper){
            crawl = .15;
            telemetry.addData("mode =", " crawl");
        }
        else if(gamepad1.right_bumper){
            crawl = .75;
            telemetry.addData("mode = ", " sprint");
        }
        else{
            crawl = .45;
            telemetry.addData("mode = ", "normal");
        }
        //movement inputs
        if(Math.abs(gamepad1.left_stick_x)>.1 || Math.abs(gamepad1.left_stick_y) >.1){
            M1.setPower(crawl * (-gamepad1.left_stick_y + gamepad1.left_stick_x));
            M2.setPower(crawl * (gamepad1.left_stick_y  + gamepad1.left_stick_x));
            M3.setPower(crawl * (-gamepad1.left_stick_y + gamepad1.left_stick_x));
            M4.setPower(crawl * (gamepad1.left_stick_y  + gamepad1.left_stick_x));
        }
        //pressing the B button moves the robot right
        else if(gamepad1.x){
            M1.setPower(-1);
            M2.setPower(-1);
            M3.setPower(1);
            M4.setPower(1);
        }
        //pressing the X button moves the robot left
        else if(gamepad1.b){
            M1.setPower(1);
            M2.setPower(1);
            M3.setPower(-1);
            M4.setPower(-1);
        }
        //if no button is pressed and no joystick is moved, stop the robot
        else{
            M1.setPower(0);
            M2.setPower(0);
            M3.setPower(0);
            M4.setPower(0);
        }

        // if left trigger is pressed, the, collector will intake
       /* if(gamepad1.left_trigger>.1){
            collector.setPower(-1);
            telemetry.addData("collector", " run");
        }
        // if bumper is pressed, collector will release
        else if(gamepad1.left_bumper){
            collector.setPower(1);
            telemetry.addData("collector ", "backwards");
        }
        else{
            collector.setPower(0); //the collector stops
        }
        //right trigger will push the launching paddle against the axle
        if(gamepad1.right_trigger>.1){
            launcher.setPower(.5);
            telemetry.addData("launcher ", "run");
        }
        else {
            launcher.setPower(0);

        }
        //the launcher will move in the reverse direction
        if(gamepad1.right_bumper){
            launcher.setPower(-.25);
        }
        //raises the servo that controls the wheel, and lowers it when y is pressed
        if(gamepad1.y){
            Arm.setPosition(.55);
        }
        else {
            Arm.setPosition(0);*/

    }


}
