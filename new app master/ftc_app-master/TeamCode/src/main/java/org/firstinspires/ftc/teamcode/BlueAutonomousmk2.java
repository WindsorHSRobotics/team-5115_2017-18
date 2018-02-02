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
@Autonomous(name = "Blueautonomousmk2", group = "autonomous")
public class BlueAutonomousmk2 extends LinearOpMode {
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

        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters(cameraMonitorViewId);
        parameters.vuforiaLicenseKey = "Aejo+Mn/////AAAAmYNCqjLr6Uddg42ubg+VizqJNbtrK1Kt0bA39uknxmHOwtAspXh3VmGQffi7LjZ4vshTH2jU+q0HDkzlWLa7mVdKrOxOhZS7SA4by5JBrGNlcOoIeE7xQjLZSIRVJb3mKWoijXBWAvi9E261Mm0eCtk45eLhoknnYRlFhJNqAJgHyIHZsaQ2OJYTpP0e4cMRCYpiYKUr0ceEA/sb/aulvf9tpvHMyO2cWXS4MtJLP6nEmjc4JX6F2vXtabQZuSi1cVLLqjf+395Z2pqkjYAMHRYg+s2+sd4y+3wglEKEI2ZeFajmP60WuIFBY5rb583ywQdJqjHgjl2yK4egY2I6Yr0KB2PElwduB383oTJR535A";
        parameters.cameraDirection = VuforiaLocalizer.CameraDirection.BACK;
        this.vuforia = ClassFactory.createVuforiaLocalizer(parameters);
        VuforiaTrackables relicTrackables = this.vuforia.loadTrackablesFromAsset("RelicVuMark");
        VuforiaTrackable relicTemplate = relicTrackables.get(0);
        relicTemplate.setName("relicVuMarkTemplate"); // can help in debugging; otherwise not necessary

        telemetry.addData("Status", "Initialized");

        F1 = hardwareMap.get(DcMotor.class, "F1");
        F2 = hardwareMap.get(DcMotor.class, "F2");
        R1 = hardwareMap.get(DcMotor.class, "R1");
        R2 = hardwareMap.get(DcMotor.class, "R2");
        arm_rotate2 = hardwareMap.get(DcMotor.class, "arm_rotate2");
        arm_rotate = hardwareMap.get(DcMotor.class,"arm_rotate");
        claw_rotate = hardwareMap.get(CRServo.class,"claw_rotate");
        extend = hardwareMap.get(DcMotor.class,"extend");

        claw_front_left = hardwareMap.get(Servo.class, "claw_front_left");
        claw_front_right = hardwareMap.get(Servo.class,"claw_front_right");

        claw_rear_left = hardwareMap.get(Servo.class,"claw_rear_left");
        claw_rear_right = hardwareMap.get(Servo.class,"claw_rear_right");

        claw_front_right.setPosition(0);
        claw_front_left.setPosition(.3);

        F1.setDirection(DcMotorSimple.Direction.FORWARD);
        F2.setDirection(DcMotorSimple.Direction.FORWARD);
        R1.setDirection(DcMotorSimple.Direction.FORWARD);
        R2.setDirection(DcMotorSimple.Direction.FORWARD);

        //Winch.setDirection(DcMotorSimple.Direction.FORWARD);

        F1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        F2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        R1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        R2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        arm_rotate.setMode(DcMotor.RunMode.RUN_USING_ENCODER);



        waitForStart();

        arm_rotate.setPower(.2);
        arm_rotate2.setPower(.2); //rases arm out of the way to extend drawer slides
        sleep(500);

        arm_rotate2.setPower(0);
        arm_rotate.setPower(0);

        extend.setPower(.5);
        sleep(1500);// extends color sensor
        extend.setPower(0);

        if(color_sensor_front.red() > color_sensor_front.blue()){
            telemetry.addData("color = ","red");
            F1.setPower(-.25);
            F2.setPower(-.25);
            R1.setPower(-.25);
            R2.setPower(-.25);
            sleep(150);//reverses back to starting starting position
            F1.setPower(0);
            F2.setPower(0);
            R1.setPower(0);
            R2.setPower(0);
            extend.setPower(-.5);
            sleep(1500);
            extend.setPower(0);

            F1.setPower(.25);
            F2.setPower(.25);
            R1.setPower(.25);
            R2.setPower(.25);
            sleep(150);
            F1.setPower(0);
            F2.setPower(0);
            R1.setPower(0);
            R2.setPower(0);

        }
        else if(color_sensor_front.red() < color_sensor_front.blue()){
            telemetry.addData("color = ","blue");
            F1.setPower(.25);
            F2.setPower(.25);
            R1.setPower(.25);
            R2.setPower(.25);
            sleep(150);//reverses back to starting starting position
            F1.setPower(0);
            F2.setPower(0);
            R1.setPower(0);
            R2.setPower(0);
            extend.setPower(-.5);
            sleep(1500);
            extend.setPower(0);

            F1.setPower(-.25);
            F2.setPower(-.25);
            R1.setPower(-.25);
            R2.setPower(-.25);
            sleep(150);
            F1.setPower(0);
            F2.setPower(0);
            R1.setPower(0);
            R2.setPower(0);

        }
        else{
            sleep(10);

        }

        int lrc = 1750;
        RelicRecoveryVuMark vuMark = RelicRecoveryVuMark.from(relicTemplate);
        if (vuMark == RelicRecoveryVuMark.LEFT) {
            telemetry.addData("vuforia = ", "left");
            lrc = 1450;
        }
        else if(vuMark == RelicRecoveryVuMark.CENTER){
            telemetry.addData("vuforia = ","center");
            lrc = 1650;
        }
        else if(vuMark == RelicRecoveryVuMark.RIGHT){
            telemetry.addData("vuforia = ","Right");
            lrc = 1850;
        }
        else{
            telemetry.addData("vuforia = ","no clue man. I guess just go blindly?");
            lrc = 1750;
        }

        F1.setPower(.5);
        F2.setPower(.5);
        R1.setPower(-.5);
        R2.setPower(-.5);
        sleep(lrc);

        F1.setPower(0);
        F2.setPower(0);
        R1.setPower(0);
        R2.setPower(0);

        arm_rotate.setPower(-.1);
        arm_rotate2.setPower(-.1);
        sleep(500);
        arm_rotate2.setPower(0);
        arm_rotate.setPower(0);

        F1.setPower(.25);
        F2.setPower(-.25);//drives forward to score
        R1.setPower(.25);
        R2.setPower(-.25);
        sleep(700);

        F1.setPower(0);
        F2.setPower(0);
        R1.setPower(0);//stops
        R2.setPower(0);
        sleep(250);
        claw_front_left.setPosition(0);
        claw_front_right.setPosition(35);

        F1.setPower(-.25);
        F2.setPower(.25);
        R1.setPower(-.25);
        R2.setPower(.25);
        sleep(300);//reverses

    }

}
