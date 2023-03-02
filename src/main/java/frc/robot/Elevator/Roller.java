package frc.robot.Elevator;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Hardware.Settings;

public class Roller {
    
    public static VictorSPX
        Lroller,
        Rroller;

    public static double
        Power;

    public static void Initialize () {
        Lroller = new VictorSPX( Settings.Roller_CANID[0] );
        Rroller = new VictorSPX( Settings.Roller_CANID[1] );

        Power = 0;

        Lroller.setInverted( false );
        Rroller.setInverted( true  );
    }

    public static void Periodic () {
        Lroller.set( ControlMode.PercentOutput, Power );
        Rroller.set( ControlMode.PercentOutput, Power );
    }

    public static void Display () {
        SmartDashboard.putNumber( "Roller Power", Power );
    }

//
//
//
    public static void Reset () { Stop();        }
    public static void Drool () { Power = +0.20; }
    public static void Spit  () { Power = +1.00; }
    public static void Suck  () { Power = -0.30; }
    public static void Stop  () { Power = +0.00; }
}
