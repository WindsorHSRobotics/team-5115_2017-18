package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;
import com.qualcomm.ftccommon.ClassManagerFactory;
import com.qualcomm.robotcore.hardware.CRServo;
import java.math.*;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DeviceInterfaceModule;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import android.app.Activity;
import android.graphics.Color;
import android.view.View;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;

/**
 * Created by romer on 2/2/2018.
 */
@Autonomous(name = "redautonomousmk2", group = "autonomous")
public class RedAutonomousmk2 extends LinearOpMode {
    public static final String TAG = "Vuforia VuMark Sample";
    OpenGLMatrix lastLocation = null;
    VuforiaLocalizer vuforia;


    private DcMotor F1 = null;
    private DcMotor F2 = null;
    private DcMotor R1 = null;
    private DcMotor R2 = null;
    //private DcMotor Winch = null;
    private CRServo claw_rotate = null;
    private DcMotor arm_rotate = null;
    private DcMotor extend = null;
    private DcMotor arm_rotate2;

    private Servo claw_front_left = null;
    private Servo claw_front_right = null;
    private Servo claw_rear_left = null;
    private Servo claw_rear_right = null;

    public ColorSensor color_sensor_front = null;
    @Override
    public void runOpMode(){
        //Initialize Vuforia
        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters(cameraMonitorViewId);
        parameters.vuforiaLicenseKey = "Aejo+Mn/////AAAAmYNCqjLr6Uddg42ubg+VizqJNbtrK1Kt0bA39uknxmHOwtAspXh3VmGQffi7LjZ4vshTH2jU+q0HDkzlWLa7mVdKrOxOhZS7SA4by5JBrGNlcOoIeE7xQjLZSIRVJb3mKWoijXBWAvi9E261Mm0eCtk45eLhoknnYRlFhJNqAJgHyIHZsaQ2OJYTpP0e4cMRCYpiYKUr0ceEA/sb/aulvf9tpvHMyO2cWXS4MtJLP6nEmjc4JX6F2vXtabQZuSi1cVLLqjf+395Z2pqkjYAMHRYg+s2+sd4y+3wglEKEI2ZeFajmP60WuIFBY5rb583ywQdJqjHgjl2yK4egY2I6Yr0KB2PElwduB383oTJR535A";
        parameters.cameraDirection = VuforiaLocalizer.CameraDirection.BACK;
        this.vuforia = ClassFactory.createVuforiaLocalizer(parameters);
        VuforiaTrackables relicTrackables = this.vuforia.loadTrackablesFromAsset("RelicVuMark");
        VuforiaTrackable relicTemplate = relicTrackables.get(0);
        relicTemplate.setName("relicVuMarkTemplate"); // can help in debugging; otherwise not necessary

        telemetry.addData(">", "Press Play to start");
        telemetry.update();

        relicTrackables.activate();

        telemetry.addData("Status", "Initialized");

        //Initialize hardware components
        F1 = hardwareMap.get(DcMotor.class, "F1");
        F2 = hardwareMap.get(DcMotor.class, "F2");
        R1 = hardwareMap.get(DcMotor.class, "R1");
        R2 = hardwareMap.get(DcMotor.class, "R2");
        arm_rotate2 = hardwareMap.get(DcMotor.class, "arm_rotate2");
        arm_rotate = hardwareMap.get(DcMotor.class,"arm_rotate");
        claw_rotate = hardwareMap.get(CRServo.class,"claw_rotate");
        extend = hardwareMap.get(DcMotor.class,"extend");
        color_sensor_front = hardwareMap.get(ColorSensor.class,"color_sensor_front");

        claw_front_left = hardwareMap.get(Servo.class, "claw_front_left");
        claw_front_right = hardwareMap.get(Servo.class,"claw_front_right");

        claw_rear_left = hardwareMap.get(Servo.class,"claw_rear_left");
        claw_rear_right = hardwareMap.get(Servo.class,"claw_rear_right");

        claw_front_right.setPosition(0);//grab the block
        claw_front_left.setPosition(.3);

        F1.setDirection(DcMotorSimple.Direction.FORWARD);
        F2.setDirection(DcMotorSimple.Direction.FORWARD);
        R1.setDirection(DcMotorSimple.Direction.FORWARD);
        R2.setDirection(DcMotorSimple.Direction.FORWARD);

        F1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        F2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        R1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        R2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        arm_rotate.setMode(DcMotor.RunMode.RUN_USING_ENCODER);



        waitForStart();

        F1.setPower(.125); //strafe left
        F2.setPower(.125);
        R1.setPower(-.125);
        R2.setPower(-.125);
        sleep(500);
        F1.setPower(0);
        F2.setPower(0);
        R1.setPower(0);
        R2.setPower(0);



        arm_rotate.setPower(.2); //raise arm to get clear of camera and wall
        arm_rotate2.setPower(.2);
        sleep(1000);
        arm_rotate2.setPower(0);
        arm_rotate.setPower(0);

        extend.setPower(-.25); //extend drawer slides (with color sensor)
        sleep(1500);
        extend.setPower(0);

        sleep(1000);

        if(color_sensor_front.red() < color_sensor_front.blue()){ //if red is on left

            telemetry.addData("color = ","red");
            telemetry.update();

            F1.setPower(-.125);//turns left
            F2.setPower(-.125);
            R1.setPower(-.125);
            R2.setPower(-.125);
            sleep(200);
            F1.setPower(0);
            F2.setPower(0);
            R1.setPower(0);
            R2.setPower(0);

            extend.setPower(.25);//retract slide
            sleep(1700);
            extend.setPower(0);

            F1.setPower(.125);//rotate back
            F2.setPower(.125);
            R1.setPower(.125);
            R2.setPower(.125);
            sleep(200);
            F1.setPower(0);
            F2.setPower(0);
            R1.setPower(0);
            R2.setPower(0);

        }
        else if(color_sensor_front.red() > color_sensor_front.blue()){ //if red is on right

            telemetry.addData("color = ","blue");
            telemetry.update();

            F1.setPower(.125); //turns right
            F2.setPower(.125);
            R1.setPower(.125);
            R2.setPower(.125);
            sleep(200);
            F1.setPower(0);
            F2.setPower(0);
            R1.setPower(0);
            R2.setPower(0);

            extend.setPower(.25); //retract the slides
            sleep(1700);
            extend.setPower(0);

            F1.setPower(-.125); //rotate back
            F2.setPower(-.125);
            R1.setPower(-.125);
            R2.setPower(-.125);
            sleep(200);
            F1.setPower(0);
            F2.setPower(0);
            R1.setPower(0);
            R2.setPower(0);

        }
        else{ //nothing detected; give up and move on
            telemetry.addData("color = ", "undetected");
            extend.setPower(.25); //retract the slides
            sleep(1700);
            extend.setPower(0);
        }

        sleep(1000);

        int lrc; //new variable for horizontal movement

        RelicRecoveryVuMark vuMark = RelicRecoveryVuMark.from(relicTemplate); //read the page

        if(vuMark == RelicRecoveryVuMark.LEFT) lrc = 3700; //aim for the left column
        else if(vuMark == RelicRecoveryVuMark.CENTER) lrc = 3220; //aim for the middle column
        else if(vuMark == RelicRecoveryVuMark.RIGHT) lrc = 2700; //aim for the right column
        else lrc = 2520; //if none detected, default to center

        telemetry.addData("VuMark", "%s visible", vuMark);
        telemetry.update();
        sleep(1000);

        F1.setPower(-.125); //strafe left to correct column
        F2.setPower(-.125);
        R1.setPower(.125);
        R2.setPower(.125);
        sleep(lrc);
        F1.setPower(0);
        F2.setPower(0);
        R1.setPower(0);
        R2.setPower(0);

        claw_rotate.setPower(.1); //rotate the claw table
        sleep(350);
        claw_rotate.setPower(0);

        sleep(500);

        F1.setPower(-.25); //reverse
        F2.setPower(.25);
        R1.setPower(-.25);
        R2.setPower(.25);
        sleep(500);
        F1.setPower(0);
        F2.setPower(0);
        R1.setPower(0);
        R2.setPower(0);

        sleep(300);

        claw_rotate.setPower(.3); //rotate the claw table (again)
        sleep(750);
        claw_rotate.setPower(0);

        arm_rotate.setPower(-.1);//lower the arm (in two motions)
        arm_rotate2.setPower(-.1);
        sleep(300);
        arm_rotate2.setPower(0);
        arm_rotate.setPower(0);
        sleep(300);
        arm_rotate.setPower(-.1);
        arm_rotate2.setPower(-.1);
        sleep(300);
        arm_rotate2.setPower(0);
        arm_rotate.setPower(0);

        F1.setPower(.125); //drive forward to score
        F2.setPower(-.125);
        R1.setPower(.125);
        R2.setPower(-.125);
        sleep(1400);
        F1.setPower(0);
        F2.setPower(0);
        R1.setPower(0);
        R2.setPower(0);

        sleep(250);

        claw_front_left.setPosition(0); //open claws; drop block
        claw_front_right.setPosition(35);

        F1.setPower(-.25); //reverse
        F2.setPower(.25);
        R1.setPower(-.25);
        sleep(400);
    }

}
