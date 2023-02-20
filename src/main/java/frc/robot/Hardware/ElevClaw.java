package frc.robot.Hardware;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

public class ElevClaw {

    public static VictorSPX
        Lhand,
        Rhand;

    public static double
        Lhand_power = 0,
        Rhand_power = 0;

//
//  The Claw consists of two VexPro motor controllers.
//  
    public static void Initialize () {
        VictorSPX Lhand = new VictorSPX( Settings.Lhand_CAN_ID );
        Lhand.setInverted( false );
        
        VictorSPX Rhand = new VictorSPX( Settings.Rhand_CAN_ID );
        Rhand.setInverted( false );
    }

    public static void Periodic () {
        Lhand.set( ControlMode.PercentOutput, Lhand_power );
        Rhand.set( ControlMode.PercentOutput, Rhand_power );
    }

    public static void Display () {
    }

//
//
//
    public static void Grab () {
        Lhand_power = Settings.ClawGrabSpeed;
        Rhand_power = Settings.ClawGrabSpeed;
    }

    public static void Drop () {
        Lhand_power = Settings.ClawDropSpeed;
        Rhand_power = Settings.ClawDropSpeed;
    }

    public static void Stop () {
        Lhand_power = 0;
        Rhand_power = 0;
    }


//
//
//


}
