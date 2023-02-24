package frc.robot.Elevator;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.PneumaticsModuleType;

public class Claw {

    // This is only needed if we want the ability to turn off the compressor,
    // change the pressure sensor, or query the compressor status.
    // Compressor pcmCompressor = new Compressor( 0, PneumaticsModuleType.CTREPCM );

    // TO DO:
    // Check the Phoenix Tuner to see if all CAN devices are showing. It already seems that
    // CAN-15 (Arm motor controller) is not recognized (Out of Frame). This might also mean
    // that the PCM is not part of the chain.

    public static Compressor Comp = new Compressor( 0, PneumaticsModuleType.CTREPCM );

    // Order is forward channel and the reverse channel
    static DoubleSolenoid Lft = new DoubleSolenoid( PneumaticsModuleType.CTREPCM, 0, 1 );
    // static DoubleSolenoid Rgt = new DoubleSolenoid( PneumaticsModuleType.CTREPCM,  3, 4 );
    // DoubleSolenoid exampleDoublePCM = new DoubleSolenoid( PneumaticsModuleType.CTREPCM,  1, 2 );
    // DoubleSolenoid exampleDoublePH  = new DoubleSolenoid( 9, PneumaticsModuleType.REVPH, 4, 5 );

    public static DoubleSolenoid.Value
        State = Value.kOff;

//
//
//
    public static void Initialize () {
        Comp.enableDigital();
        Stop();
    }

    public static void Periodic () {
        Lft.set( State );

        boolean enabled = Comp.isEnabled();
        double Value    = Comp.getCurrent();
        SmartDashboard.putNumber ("PRESSURE", Value   );
        SmartDashboard.putBoolean("ENABLAED", enabled );
        // Rgt.set( State );
    }

    public static void Display () {
    }

//
//
//
    public static void Drop  () { State = Value.kReverse; }
    public static void Grab  () { State = Value.kForward; }
    public static void Reset () { State = Value.kOff;     }
    public static void Stop  () { State = Value.kOff;     }
}
