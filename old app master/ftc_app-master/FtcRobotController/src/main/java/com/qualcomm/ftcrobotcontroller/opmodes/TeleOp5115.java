package com.qualcomm.ftcrobotcontroller.opmodes;

        import com.qualcomm.robotcore.eventloop.opmode.OpMode;
        import com.qualcomm.robotcore.hardware.DcMotor;
        import com.qualcomm.robotcore.hardware.Servo;
        import com.qualcomm.robotcore.util.Range;
//Created by owner on 1/5/16.

public class TeleOp5115 extends OpMode {

    final static double ARM_MIN_RANGE  = 0.00;
    final static double ARM_MAX_RANGE  = 0.90;

/*
    final static double Bar_MIN_RANGE  = 0.00;
    final static double Bar_MAX_RANGE  = 0.9;
 */

    DcMotor Mleft1;
    DcMotor Mleft2;
    DcMotor Mright1;
    DcMotor Mright2;
    DcMotor collector;
    DcMotor TheBluePill;
    Servo Spin;
    Servo climberFlipper;
   // Servo Arm_Bar;

    double armPosition;
    double armDelta = 0.01;
    double flipperPosition = .9;
    double flipperDelta = 0.01;

    //double barPosition;
    //double barDelta = 0.01;


    public void init() {
        Mleft1 = hardwareMap.dcMotor.get("L1");
        Mleft2 = hardwareMap.dcMotor.get("L2");
        Mright1 = hardwareMap.dcMotor.get("R1");
        Mright2 = hardwareMap.dcMotor.get("R2");

        Mleft1.setDirection(DcMotor.Direction.FORWARD);
        Mleft2.setDirection(DcMotor.Direction.FORWARD);

        Mright1.setDirection(DcMotor.Direction.REVERSE);
        Mright2.setDirection(DcMotor.Direction.REVERSE);

        collector = hardwareMap.dcMotor.get("collector");
        TheBluePill = hardwareMap.dcMotor.get("TheBluePill");
        Spin = hardwareMap.servo.get("Spin");
        climberFlipper = hardwareMap.servo.get("Climber Flipper");
        //Arm_Bar = hardwareMap.servo.get("ArmBar");

        Spin.setPosition(0.45);
        climberFlipper.setPosition(1);
       // Arm_Bar.setPosition(0.9);
    }

    public void loop(){

        double left = gamepad1.left_stick_y / 2;
        double right = gamepad1.right_stick_y / 2;
        double Cpower = gamepad2.left_stick_y;
        double Vpower = gamepad2.right_stick_y/6;


        if(gamepad1.left_trigger > .25){
            left = gamepad1.left_stick_y;
            right = gamepad1.right_stick_y;

        }

        Mleft1.setPower(left);
        Mleft2.setPower(left);

        Mright1.setPower(right);
        Mright2.setPower(right);

        collector.setPower(Cpower);
        TheBluePill.setPower(Vpower);


        telemetry.addData("left tgt pwr", "left  pwr: " + String.format("%.2f", left));
        telemetry.addData("right tgt pwr", "right pwr: " + String.format("%.2f", right));
        telemetry.addData("Text", "*** Robot Data***");

        if (gamepad2.b) {
            armPosition += armDelta;
        }
        if (gamepad2.x) {
            armPosition -= armDelta;
        }

        if (gamepad2.y) {
            flipperPosition += flipperDelta;
        }
        if (gamepad2.a) {
            flipperPosition -= flipperDelta;
        }

        /*if (gamepad2.y) {

            barPosition += barDelta;
        }

        if (gamepad2.a) {

            barPosition -= barDelta;
        }*/


        if (gamepad1.left_bumper){
            Mleft1.setPower(0);
            Mleft2.setPower(0);

            Mright1.setPower(0);
            Mright2.setPower(0);
        }

        armPosition = Range.clip(armPosition, ARM_MIN_RANGE, ARM_MAX_RANGE);
        flipperPosition = Range.clip(flipperPosition, 0, 1);
        Spin.setPosition(armPosition);
        climberFlipper.setPosition(flipperPosition);
       //barPosition = Range.clip(barPosition, Bar_MIN_RANGE, Bar_MAX_RANGE);
       // Arm_Bar.setPosition(barPosition);
    }
}
