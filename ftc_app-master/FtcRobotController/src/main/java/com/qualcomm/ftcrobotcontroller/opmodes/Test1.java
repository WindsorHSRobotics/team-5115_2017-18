package com.qualcomm.ftcrobotcontroller.opmodes;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import java.lang.*;




/**
 * Created by Anthony on 12/2/2016.
 * 1 = f1 = front left
 * 2 = f2 = front right
 * 3 = r1 = rear left
 * 4 = r2 = rear right
 *
 */
public class Test1 extends OpMode {

    DcMotor M1, M2, M3, M4, collector, launcher;


    public void init() {
        M1 = hardwareMap.dcMotor.get("1");
        M2 = hardwareMap.dcMotor.get("2");
        M3 = hardwareMap.dcMotor.get("3");
        M4 = hardwareMap.dcMotor.get("4");
        collector = hardwareMap.dcMotor.get("collector");
        launcher = hardwareMap.dcMotor.get("launcher");

        M1.setDirection(DcMotor.Direction.FORWARD);
        M2.setDirection(DcMotor.Direction.FORWARD);
        M3.setDirection(DcMotor.Direction.REVERSE);
        M4.setDirection(DcMotor.Direction.REVERSE);
    }

    public void loop() {
        if (Math.abs(gamepad1.left_stick_x) > .1 || Math.abs(gamepad1.left_stick_y) > .1) {
            M1.setPower((-gamepad1.left_stick_y + gamepad1.left_stick_x) / 3);
            M2.setPower((gamepad1.left_stick_y + gamepad1.left_stick_x) / 3);
            M3.setPower((-gamepad1.left_stick_y + gamepad1.left_stick_x) / 3);
            M4.setPower((gamepad1.left_stick_y + gamepad1.left_stick_x) / 3);
        } else if (gamepad1.b) {
            M1.setPower(-1);
            M2.setPower(-1);
            M3.setPower(1);
            M4.setPower(1);
        } else if (gamepad1.x) {
            M1.setPower(1);
            M2.setPower(1);
            M3.setPower(-1);
            M4.setPower(-1);
        } else {
            M1.setPower(0);
            M2.setPower(0);
            M3.setPower(0);
            M4.setPower(0);
        }


        if (gamepad1.left_trigger > .1) {
            collector.setPower(-.5);
            telemetry.addData("collector", " run");
        } else if (gamepad1.left_bumper) {
            collector.setPower(1);
            telemetry.addData("collector ", "backwards");
        } else {
            collector.setPower(0);
        }
        if (gamepad1.right_trigger > .1) {
            launcher.setPower(.5);
            telemetry.addData("launcher ", "run");
        } else {
            launcher.setPower(0);

        }
        if (gamepad1.right_bumper) {
            launcher.setPower(-.25);
        }
    }

}
