package frc.robot.Elevator;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.PneumaticsModuleType;

public class Slider {

    public static Value
        State = Value.kForward;

    public static DoubleSolenoid
        Lft = new DoubleSolenoid( PneumaticsModuleType.CTREPCM, 0, 1 ),
        Rgt = new DoubleSolenoid( PneumaticsModuleType.CTREPCM, 2, 3 );

    public static void Initialize () {
        Retract();
    }

    public static void Periodic () {
        Lft.set( State );
        Rgt.set( State );
    }

    public static void Display () {
        String state = "Off";
        if ( State == Value.kForward ) { state = "FORWARD";  }
        if ( State == Value.kReverse ) { state = "REVERSE"; }
        SmartDashboard.putString( "SLIDER", state );
    }


    public static void Reset () {
        Retract();
    }

    public static void Extend () {
        State = Value.kReverse;
    }

    public static void Retract () {
        State = Value.kForward;
    }

}
