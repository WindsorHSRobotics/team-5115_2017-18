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
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;
import java.math.*;

/**
 * This file contains an example of an iterative (Non-Linear) "OpMode".
 * An OpMode is a 'program' that runs in either the autonomous or the teleop period of an FTC match.
 * The names of OpModes appear on the menu of the FTC Driver Station.
 * When an selection is made from the menu, the corresponding OpMode
 * class is instantiated on the Robot Controller and executed.
 *
 * This particular OpMode just executes a basic Tank Drive Teleop for a two wheeled robot
 * It includes all the skeletal structure that all iterative OpModes contain.
 *
 * Use Android Studios to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this opmode to the Driver Station OpMode list
 */

@TeleOp(name="holonomicmk1", group="teliop")

public class holonomicmk1 extends OpMode
{
    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor F1 = null;
    private DcMotor F2 = null;
    private DcMotor R1 = null;
    private DcMotor R2 = null;
    private DcMotor Winch = null;



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
        Winch = hardwareMap.get(DcMotor.class, "Winch");

        F1.setDirection(DcMotorSimple.Direction.FORWARD);
        F2.setDirection(DcMotorSimple.Direction.FORWARD);
        R1.setDirection(DcMotorSimple.Direction.FORWARD);
        R2.setDirection(DcMotorSimple.Direction.FORWARD);

        Winch.setDirection(DcMotorSimple.Direction.FORWARD);

        F1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        F2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        R1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        R2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);


        // Initialize the hardware variables. Note that the strings used here as parameters
        // to 'get' must correspond to the names assigned during the robot configuration
        // step (using the FTC Robot Controller app on the phone).

        telemetry.addData("Status", "Initialized");
    }

    /*
     * Code to run REPEATEDLY after the driver hits INIT, but before they hit PLAY
     */
    @Override
    public void init_loop() {
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


        angle = Math.atan2(gamepad1.left_stick_x, gamepad1.left_stick_y);
        telemetry.addData("angle: ", angle);

        leftstick_x_sqr = gamepad1.left_stick_x * gamepad1.left_stick_x;
        leftstick_y_sqr = gamepad1.left_stick_y * gamepad1.left_stick_y;
        power = Math.sqrt(leftstick_x_sqr + leftstick_y_sqr);
        telemetry.addData("power: ", power);

        double Pi = Math.PI/4;

        /* powerF1 = (power * Math.cos(angle + Pi)) + gamepad1.right_stick_x;
        powerF2 = (power * -Math.cos(angle - Pi)) - gamepad1.right_stick_x;
        powerR1 = (power * -Math.cos(angle + Pi)) + gamepad1.right_stick_x;
        powerR2 = (power * Math.cos(angle - Pi)) - gamepad1.right_stick_x;*/

        powerF1 = (power * Math.cos(angle - Pi)) + gamepad1.right_stick_x;
        powerF2 = (power * -Math.cos(angle + Pi)) + gamepad1.right_stick_x;
        powerR1 = (power * -Math.cos(angle - Pi)) + gamepad1.right_stick_x;
        powerR2 = (power * Math.cos(angle + Pi)) + gamepad1.right_stick_x;


        powerF1 = Range.clip(powerF1,-1.0, 1.0);
        powerF2 = Range.clip(powerF2,-1.0, 1.0);
        powerR1 = Range.clip(powerR1,-1.0, 1.0);
        powerR2 = Range.clip(powerR2,-1.0, 1.0);

        telemetry.addData("right stick x: ", gamepad1.right_stick_x);

        if(power > .1 || Math.abs(gamepad1.right_stick_x)>.1){
            F1.setPower(powerF1);
            F2.setPower(powerF2);
            R1.setPower(powerR1);
            R2.setPower(powerR2);
        }
        else{
            F1.setPower(0);
            F2.setPower(0);
            R1.setPower(0);
            R2.setPower(0);
        }

        if(gamepad1.left_trigger>.2){
            Winch.setPower(gamepad1.left_trigger);
        }
        else if(gamepad1.right_trigger>.1){
            Winch.setPower(-1 * gamepad1.right_trigger);
        }
        else{
            Winch.setPower(0);
        }



    }



    /*
     * Code to run ONCE after the driver hits STOP
     */
    @Override
    public void stop() {
    }

}
