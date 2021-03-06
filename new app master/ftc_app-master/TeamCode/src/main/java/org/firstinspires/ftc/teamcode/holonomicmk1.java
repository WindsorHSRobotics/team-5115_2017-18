/* Copyright (c) 2017 FIRST. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted (subject to the limitations in the disclaimer below) provided that
 * the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list
 * of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice, this
 * list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * Neither the name of FIRST nor the names of its contributors may be used to endorse or
 * promote products derived from this software without specific prior written permission.
 *
 * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
 * LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Hardware;
import com.qualcomm.robotcore.util.Range;
import com.qualcomm.ftccommon.ClassManagerFactory;
import com.qualcomm.robotcore.hardware.CRServo;
import java.math.*;
import com.qualcomm.robotcore.hardware.ColorSensor;




@TeleOp(name="holonomicmk1", group="teliop")

public class holonomicmk1 extends OpMode
{
    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor F1 = null;
    private DcMotor F2 = null;
    private DcMotor R1 = null;
    private DcMotor R2 = null;
    //private DcMotor Winch = null;
    private CRServo claw_rotate = null;
    private DcMotor arm_rotate = null;
    private DcMotor arm_rotate2 = null;
    private DcMotor extend = null;

    public Servo claw_front_left = null;
    public Servo claw_front_right = null;
    private Servo Color_Arm = null;
    private Servo claw_rear_left = null;
    private Servo claw_rear_right = null;


       /*
     * Code to run ONCE when the driver hits INIT
     */
    @Override
    public void init() {
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
        Color_Arm = hardwareMap.get(Servo.class,"color_arm");
        claw_rear_left = hardwareMap.get(Servo.class,"claw_rear_left");
        claw_rear_right = hardwareMap.get(Servo.class,"claw_rear_right");


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




        // Initialize the hardware variables. Note that the strings used here as parameters
        // to 'get' must correspond to the names assigned during the robot configuration
        // step (using the FTC Robot Controller app on the phone).

        telemetry.addData("Status", "Initialized v1");
    }

    /*
     * Code to run REPEATEDLY after the driver hits INIT, but before they hit PLAY
     */
    @Override
    public void init_loop() {
        claw_front_right.setPosition(0);
        claw_front_left.setPosition(35);
        claw_rear_right.setPosition(0);
        claw_rear_left.setPosition(35);
        Color_Arm.setPosition(90);

    }

    /*
     * Code to run ONCE when the driver hits PLAY
     */
    @Override
    public void start() {
        runtime.reset();
    }

    /*
     * Code to run REPEATEDLY after the driver hits PLAY but before they hit STOP
     */
    @Override
    public void loop() {
        double leftstick_x_sqr;
        double leftstick_y_sqr;
        double power;
        double angle;

        double powerF1;
        double powerF2;
        double powerR1;
        double powerR2;
        double claw_open = 180;
        double claw_close = 80;
        Color_Arm.setPosition(.5);


        angle = Math.atan2(gamepad1.left_stick_x, gamepad1.left_stick_y);//finds angle of joystick
        telemetry.addData("angle: ", angle);//adds telemetry for troubleshooting code

        leftstick_x_sqr = gamepad1.left_stick_x * gamepad1.left_stick_x;
        leftstick_y_sqr = gamepad1.left_stick_y * gamepad1.left_stick_y;
        power = Math.sqrt(leftstick_x_sqr + leftstick_y_sqr);//finds relative power of joystick using pythagorean theorem
        telemetry.addData("power: ", power);

        double Pi = Math.PI/4;//to avoid having to type this everytime

        powerF2 = (power * Math.cos(angle - Pi)) + gamepad1.right_stick_x;
        powerF1 = (power * -Math.cos(angle + Pi)) + gamepad1.right_stick_x;//assigning each motor power based on calculated sin wave
        powerR1 = (power * -Math.cos(angle - Pi)) + gamepad1.right_stick_x;//right stick is used for y axis rotation
        powerR2 = (power * Math.cos(angle + Pi)) + gamepad1.right_stick_x;


        powerF1 = Range.clip(powerF1,-1.0, 1.0);//clips values to avoid program errors
        powerF2 = Range.clip(powerF2,-1.0, 1.0);
        powerR1 = Range.clip(powerR1,-1.0, 1.0);
        powerR2 = Range.clip(powerR2,-1.0, 1.0);

        telemetry.addData("right stick x: ", gamepad1.right_stick_x);

        if(power > .1 || Math.abs(gamepad1.right_stick_x)>.1){
            F1.setPower(powerF1);
            F2.setPower(powerF2);//applies motor power if joystick is moved
            R1.setPower(powerR1);
            R2.setPower(powerR2);
        }
        else{
            F1.setPower(0);
            F2.setPower(0);
            R1.setPower(0);//stops robot when no joystick is pressed 
            R2.setPower(0);
        }

        double arm_rotate_speed = .5;
        if(gamepad2.a){
            arm_rotate_speed = .8;
        }
        else{
            arm_rotate_speed = .25;
        }

        if(Math.abs(gamepad2.left_stick_y)>.1){
            arm_rotate.setPower(-gamepad2.left_stick_y * arm_rotate_speed);
            arm_rotate2.setPower(-gamepad2.left_stick_y * arm_rotate_speed);
        }
        else{
            arm_rotate.setPower(0);
            arm_rotate2.setPower(0);
        }

        if(gamepad2.left_trigger > .1){
            extend.setPower(gamepad2.left_trigger * .5);
        }
        else if(gamepad2.right_trigger > .1) {
            extend.setPower(-gamepad2.right_trigger * .5);
        }
        else{
            extend.setPower(0);
        }


        if(Math.abs(gamepad2.right_stick_y)>.1){
            claw_rotate.setPower(gamepad2.right_stick_y);
        }
        else{
            claw_rotate.setPower(0);
        }
        if(gamepad2.left_bumper){
            claw_front_left.setPosition(0);
            claw_front_right.setPosition(.3);
        }
        else{
            claw_front_right.setPosition(0);
            claw_front_left.setPosition(.3);
        }
        if(gamepad2.right_bumper){
            claw_rear_left.setPosition(0);
            claw_rear_right.setPosition(.3);
        }
        else{
            claw_rear_left.setPosition(1);
            claw_rear_right.setPosition(0);
        }






    }



    /*
     * Code to run ONCE after the driver hits STOP
     */
    @Override
    public void stop() {
    }

}
