package frc.robot.Elevator;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Hardware.Settings;

public class Arm {

    public static TalonSRX
        Arm;

    public static double
        angle,
        direction,
        displacement = 0,
        LO        = 0,
        MD        = 60,
        HI        = 90,
        maximum   = -22000,

        PosPV = 0, PosSP = HI, PosER = 0,
        VelPV = 0, VelSP =  0, VelER = 0,

        kF        = 0,
        tolerance = 5, // degrees

        Power     = 0;

//
//
//
    public static void Initialize () {
        Arm = new TalonSRX( Settings.Arm_CANID );
        Arm  .setInverted( true );
        Arm  .setSelectedSensorPosition( 90 );
    }

    public static void Periodic () {

        // POSITION VALUES
        PosPV = GetPosition();
        PosER = PosSP - PosPV;
        VelPV = GetVelocity();

        // VELOCITY SET POINT (Depends on PosER)
        if ( PosER > 0 ) { VelSP = +1200; }
        if ( PosER < 0 ) { VelSP = -700; }

        // ADJUST VELOCITY
        VelER = VelSP - VelPV;
        Power += 0.00005 * VelER;

        // MAXIMUM POWER
        if ( Power < -0.90 ) { Power = -0.90; }
        if ( Power > +0.90 ) { Power = +0.90; }

        // CUT POWER WHEN WITHIN RANGE
        if ( Math.abs(PosER) < 5 ) { Power = 0; }
        if ( PosSP == LO & PosPV < LO ) { Power = 0; }

        // SET POWER
        Arm.set( ControlMode.PercentOutput, Power );
    }

    public static void Display () {
        SmartDashboard.putNumber("Arm Pos PV", GetPosition() );
        SmartDashboard.putNumber("Arm Pos SP", PosSP         );

        SmartDashboard.putNumber("Arm Vel PV", GetVelocity() );
        SmartDashboard.putNumber("Arm Vel SP", VelSP         );

        SmartDashboard.putNumber("ARM POWER", Power );
        SmartDashboard.putNumber("Arm PosER", PosER );
        SmartDashboard.putNumber("Arm VelER", VelER );
    }

//
// POSITION       VELOCITY
//  0 horizontal  + is movign up
// 90 vertical    - is moving down
    public static double GetPosition () {
        double PV = Arm.getSelectedSensorPosition();
        PosPV = 90 - PV / maximum * 90;
        return PosPV;
    }

    public static double GetVelocity () {
        VelPV = Arm.getSelectedSensorVelocity();
        return VelPV;
    }

    public static void SetPosition ( double pos ) {
        PosSP = pos;
    }

//
//
//
    public static void Reset () {
        SetHI();
    }

    public static void SetHI () {
        Power = 0.35;
        PosSP = HI;
    }

    public static void SetMD () {
        Power = 0.00;
        PosSP = MD;
    }
    
    public static void SetLO () {
        Power = 0.10;
        PosSP = LO;
    }

}
