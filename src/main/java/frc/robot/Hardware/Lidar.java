package frc.robot.Hardware;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.I2C.Port;

public class Lidar {

    private static I2C i2c;
	private static byte[] distance;
    
    private final        int LIDAR_ADDR              = 0x62;
	private final static int LIDAR_CONFIG_REGISTER   = 0x00;
	private final static int LIDAR_DISTANCE_REGISTER = 0x8f;
	
    public Lidar () {
        i2c      = new I2C( Port.kMXP, LIDAR_ADDR );
	    distance = new byte[2];
     }

    public static double Display () {
        return 0;
    }

    public static double GetDistance () {
        // Initiate measurement
		i2c.write( LIDAR_CONFIG_REGISTER, 0x04 );

        // Read in measurement
        i2c.read ( LIDAR_DISTANCE_REGISTER, 2, distance );

        return (int)Integer.toUnsignedLong(distance[0] << 8)
            + Byte.toUnsignedInt(distance[1]);
    }

}
