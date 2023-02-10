package frc.robot.Hardware;

import com.ctre.phoenix.sensors.CANCoder;

public class EncTalonFX {

    public CANCoder FalconEncoder;
    public final static  int      kUnitsPerRevolution = 2048;

    public EncTalonFX ( int CanBusID ) {
        FalconEncoder = new CANCoder( CanBusID );
    }
    
}
