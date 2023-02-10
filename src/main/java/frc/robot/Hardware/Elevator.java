package frc.robot.Hardware;

public class Elevator {

    public static void Initialize () {
        ElevArm    .Initialize();
        ElevClaw   .Initialize();
        ElevLift   .Initialize();
        ElevWrist  .Initialize();
    }

    public static void Periodic () {
        ElevArm    .Periodic();
        ElevClaw   .Periodic();
        ElevLift   .Periodic();
        ElevWrist  .Periodic();
    }

    public static void Display () {
        ElevArm    .Display();
        ElevClaw   .Display();
        ElevLift   .Display();
        ElevWrist  .Display();
    }

//
//
//
    public static void Reset () {
        ElevArm    .Reset();
        ElevClaw   .Reset();
        ElevLift   .Reset();
        ElevWrist  .Reset();
    }

    public static void Set ( double A, double C, double L, double W ) {
        ElevArm    .SetPosition( A );
        ElevClaw   .SetPosition( C );
        ElevLift   .SetPosition( L );
        ElevWrist  .SetPosition( W );
    }

}
