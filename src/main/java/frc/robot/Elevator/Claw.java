package frc.robot.Elevator;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.PneumaticsModuleType;

public class Claw {

    // This is only needed if we want the ability to turn off the compressor,
    // change the pressure sensor, or query the compressor status.
    public static Compressor Comp = new Compressor( 0, PneumaticsModuleType.CTREPCM );

    // Order is forward channel and the reverse channel
    static DoubleSolenoid Lft = new DoubleSolenoid( PneumaticsModuleType.CTREPCM, 0, 1 );
    static DoubleSolenoid Rgt = new DoubleSolenoid( PneumaticsModuleType.CTREPCM, 3, 4 );
    // DoubleSolenoid exampleDoublePH  = new DoubleSolenoid( 9, PneumaticsModuleType.REVPH, 4, 5 );

    public static DoubleSolenoid.Value
        State = Value.kOff;

//
//
//
    public static void Initialize () {
        Drop();
    }

    public static void Periodic () {
        Lft.set( State );
        Rgt.set( State );
    }

    public static void Display () {
        // SmartDashboard.putBoolean("ENABLAED", enabled );
    }

//
//
//
    public static void Drop  () { State = Value.kReverse; }
    public static void Grab  () { State = Value.kForward; }
    public static void Reset () { State = Value.kReverse; }
    public static void Stop  () { State = Value.kOff;     }

    public static void Toggle () {
        State = State == Value.kForward ? Value.kReverse : Value.kForward;
    }
}
