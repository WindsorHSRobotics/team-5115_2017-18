package com.qualcomm.ftcrobotcontroller.opmodes;

        import android.graphics.Color;

        import com.qualcomm.robotcore.hardware.ColorSensor;
        import com.qualcomm.robotcore.hardware.DcMotor;
        import com.qualcomm.robotcore.hardware.Servo;
        import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
        import java.lang.*;

public class WB_MDS_Cap extends LinearOpMode {

    DcMotor M1, M2, M3, M4, collector, launcher;
    ColorSensor sensorRGB_Back;
    ColorSensor sensorRGB_Front;
    Servo Arm;

    public void runOpMode() throws InterruptedException {
        M1 = hardwareMap.dcMotor.get("1");
        M2 = hardwareMap.dcMotor.get("2");
        M3 = hardwareMap.dcMotor.get("3");
        M4 = hardwareMap.dcMotor.get("4");
        collector = hardwareMap.dcMotor.get("collector");
        launcher = hardwareMap.dcMotor.get("launcher");
        Arm = hardwareMap.servo.get("Servo");

        hardwareMap.logDevices();
        sensorRGB_Back = hardwareMap.colorSensor.get("color_Back");
        sensorRGB_Front = hardwareMap.colorSensor.get("color_Front");

        boolean bEnabled = true;
        sensorRGB_Back.enableLed(false);
        sensorRGB_Front.enableLed(false);
        float hsvValuesfront[] = {0F, 0F, 0F};
        float hsvValuesback[] = {0F, 0F, 0F};


        M1.setDirection(DcMotor.Direction.FORWARD);
        M2.setDirection(DcMotor.Direction.FORWARD);
        M3.setDirection(DcMotor.Direction.REVERSE);
        M4.setDirection(DcMotor.Direction.REVERSE);

        waitForStart();
        sleep(15000);      //sleep 15 seconds
        Arm.setPosition(0);
        M1.setPower(.3);
        M2.setPower(-.3);
        M3.setPower(.3);
        M4.setPower(-.3);
        sleep(300);      //move foward and get the robot off the wall


        M1.setPower(0);
        M2.setPower(0);
        M3.setPower(0);
        M4.setPower(0);
        sleep(100);   // Stop the robot and give it .1 sec to stabalize

        launcher.setPower(1);
        sleep(4500);   //launch particles for 3 seconds - two flipper turns
        launcher.setPower(0); //Turn fliper off

        M1.setPower(1);
        M2.setPower(-1);
        M3.setPower(1);
        M4.setPower(-1);
        sleep(1800);        //move foward up to the cap

        M1.setPower(0);
        M2.setPower(0);
        M3.setPower(0);
        M4.setPower(0);

    }
}