package frc.robot.Elevator;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Hardware.Settings;

public class Arm {

    public static TalonSRX Arm;
    public static double   Lo = 23000;
    public static double   Hi = 0;
    public static double   SP = Hi;
    public static double   power = 0;

//
//
//
    public static void Initialize () {
        Arm = new TalonSRX( Settings.Arm_CANID );
        Arm  .setInverted( true );
    }

    public static void Periodic () {

        // Positive = Too low
        // Negative = Too high
        // double curr = GetPosition();
        // double diff = GetPosition() - SP;

        // MOVING THE ARM UP
        // if ( target_position == Hi ) {
        // }
        
        // MOVING THE ARM DOWN
        // else {
        //     if ( curr > 700 ) {
        //         power = 
        //     }


        // }

    }

    public static void Display () {
        SmartDashboard.putNumber("Arm PV", GetPosition() );
        SmartDashboard.putNumber("Arm SP", SP            );
    }

//
//
//
    public static double GetPosition () {
        return Arm.getSelectedSensorPosition();
    }

    public static void SetPosition ( double pos ) {
        SP = pos;
    }

//
//
//
    public static void Reset () {
        Bend();
    }

    public static void Bend () {
        SP = Hi;
    }
    
    public static void Straighten () {
        SP = Lo;
    }

}
