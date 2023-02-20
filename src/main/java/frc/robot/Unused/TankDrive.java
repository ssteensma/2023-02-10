// package frc.robot.Unused;

// import com.ctre.phoenix.motorcontrol.ControlMode;

// import frc.robot.Hardware.Module;
// import frc.robot.Hardware.Navigation;
// import frc.robot.Hardware.Swerve;

// public class TankDrive {
    
//     public static double Offset = 0; // Add to left, Subtract from right

// // These methods simulate TankDrive by using the gyroscope
// // to make the robot drive in a straight line.

//     public static void Strafe ( double Speed, double Heading ) {

//         // ALIGN WHEELS. Align all swerve drive modules so
//         // that the wheels are aligned to the desired heading.
//         for ( int i = 1; i <= 4; i++ ) {
//             Module m = Swerve.module[i];

//             double SP = Heading;
//             double PV = m.GetDirection();

//             // Adjust wheel direction
//         }

//         // ADJUST SPEED DIFFERENTIAL
//         double PV = Navigation.GetDirection();

//         double Power = 0;

//         // LEFT POWER
//         Swerve.FL_module.DriveMotor.set( ControlMode.PercentOutput, Power + Offset );
//         Swerve.RL_module.DriveMotor.set( ControlMode.PercentOutput, Power + Offset );

//         // RIGHT POWER
//         Swerve.FL_module.DriveMotor.set( ControlMode.PercentOutput, Power - Offset );
//         Swerve.RL_module.DriveMotor.set( ControlMode.PercentOutput, Power - Offset );

//     }

// }
