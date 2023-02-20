package frc.robot.Hardware;

public class Elevator {

    public static void Initialize () {
        ElevArm    .Initialize();
        ElevClaw   .Initialize();
        ElevLift   .Initialize();
        ElevElbow  .Initialize();
    }

    public static void Periodic () {
        ElevArm    .Periodic();
        ElevClaw   .Periodic();
        ElevLift   .Periodic();
        ElevElbow  .Periodic();
    }

    public static void Display () {
        ElevArm    .Display();
        ElevClaw   .Display();
        ElevLift   .Display();
        ElevElbow  .Display();
    }

    public static void Reset () {
        ElevArm    .Reset();
        ElevClaw   .Stop();
        ElevLift   .Reset();
        ElevElbow  .Reset();
    }

    public static void Set ( double A, double C, double L, double W ) {
        ElevArm    .SetPosition( A );
        // ElevClaw   .SetPosition( C );
        ElevLift   .SetPosition( L );
        ElevElbow  .SetPosition( W );
    }

}
