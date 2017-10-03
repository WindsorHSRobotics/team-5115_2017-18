/* Copyright (c) 2014, 2015 Qualcomm Technologies Inc

All rights reserved.

Redistribution and use in source and binary forms, with or without modification,
are permitted (subject to the limitations in the disclaimer below) provided that
the following conditions are met:

Redistributions of source code must retain the above copyright notice, this list
of conditions and the following disclaimer.

Redistributions in binary form must reproduce the above copyright notice, this
list of conditions and the following disclaimer in the documentation and/or
other materials provided with the distribution.

Neither the name of Qualcomm Technologies Inc nor the names of its contributors
may be used to endorse or promote products derived from this software without
specific prior written permission.

NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
"AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE. */

package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpModeManager;
import com.qualcomm.robotcore.eventloop.opmode.OpModeRegister;

/**
 * Register Op Modes
 */
public class FtcOpModeRegister implements OpModeRegister {

  /**
   * The Op Mode Manager will call this method when it wants a list of all
   * available op modes. Add your op mode to the list to enable it.
   *
   * @param manager op mode manager
   */
  public void register(OpModeManager manager) {

    /*
     * register your op modes here.
     * The first parameter is the name of the op mode
     * The second parameter is the op mode class property
     *
     * If two or more op modes are registered with the same name, the app will display an error.
     */

    //manager.register("NullOp", NullOp.class);

    //manager.register("MatrixK9TeleOp", MatrixK9TeleOp.class);
    //manager.register("K9TeleOp", K9TeleOp.class);
    //manager.register("K9Line", K9Line.class);
    //manager.register ("PushBotAuto", PushBotAuto.class);
    //manager.register ("PushBotManual", PushBotManual.class);
    //manager.register ("K9TankDrive", K9TankDrive.class);


   /* manager.register ("Autonomous5115", Autonomous5115.class);
    manager.register ("TeleOp5115", TeleOp5115.class);
    manager.register ("TeleOp10736", TeleOp10736.class);
    manager.register ("Autonomous10736", Autonomous10736.class);
    */
      manager.register("west coast", ExampleTeleopTankMode.class);
      manager.register("holonomic", watson.class);
      manager.register("omni", dominicomniwheelwithtrig.class);
      manager.register("testdrive", MecanumTest.class);

      //manager.register ("Dual Color Sensor Test", duelcolorsensortest.class);
      //manager.register("TeleOp", watson.class);
      //manager.register("TeleOp with Trig", omniwheelwithtrig.class);
      //manager.register("red autonomous", omniwheelautonomous_v1.class);
      //manager.register("color sensor test", MRRGBExample.class);
     // manager.register("Watson2",WatsonBlueAuto.class);
      //manager.register("BlueMiddleCap",WB_All_Cap.class);
      //manager.register("BlueMiddleRamp",WB_All_Ramp.class);
      //manager.register("BlueCornerCap",WB_Corner_All_Cap.class);
      //manager.register("BlueCornerRamp",WB_Corner_All_Ramp.class);
      //manager.register("RedMiddleCap",WR_All_Cap.class);
     // manager.register("RedMiddleRamp",WR_All_Ramp.class);
      /*manager.register("RedCornerCap",WR_Corner_All_Cap.class);
      manager.register("RedCornerRamp",WR_Corner_All_Ramp.class);

      manager.register("BMDSCap",WB_MDS_Cap.class);
      manager.register("BMDSRamp",WB_MDS_Ramp.class);
      manager.register("BCDSCap",WB_CDS_Cap.class);
      manager.register("BCDSRamp",WB_CDS_Ramp.class);

      manager.register("RMDSCap",WR_MDS_Cap.class);
      manager.register("RMDSRamp",WR_MDS_Ramp.class);
      manager.register("RCDSCap",WR_CDS_Cap.class);
      manager.register("RCDSRamp",WR_CDS_Ramp.class);
      */

      //manager.register("LauncherTest",Test1.class);
      //manager.register("SensorTet", SensorTest.class);
      //manager.register("10736",IDontCare.class);

    /*
     * Uncomment any of the following lines if you want to register an op mode.
     */
    //manager.register("MR Gyro Test", MRGyroTest.class);

    //manager.register("AdafruitRGBExample", AdafruitRGBExample.class);
    //manager.register("ColorSensorDriver", ColorSensorDriver.class);

    //manager.register("IrSeekerOp", IrSeekerOp.class);
    //manager.register("CompassCalibration", CompassCalibration.class);
    //manager.register("I2cAddressChangeExample", LinearI2cAddressChange.class);


    //manager.register("NxtTeleOp", NxtTeleOp.class);

    //manager.register("LinearK9TeleOp", LinearK9TeleOp.class);
    //manager.register("LinearIrExample", LinearIrExample.class);


    //manager.register ("PushBotManual1", PushBotManual1.class);
    //manager.register ("PushBotAutoSensors", PushBotAutoSensors.class);
    //manager.register ("PushBotIrEvent", PushBotIrEvent.class);

    //manager.register ("PushBotManualSensors", PushBotManualSensors.class);
    //manager.register ("PushBotOdsDetectEvent", PushBotOdsDetectEvent.class);
    //manager.register ("PushBotOdsFollowEvent", PushBotOdsFollowEvent.class);
    //manager.register ("PushBotTouchEvent", PushBotTouchEvent.class);

    //manager.register("PushBotDriveTouch", PushBotDriveTouch.java);
    //manager.register("PushBotIrSeek", PushBotIrSeek.java);
    //manager.register("PushBotSquare", PushBotSquare.java);
  }
}
