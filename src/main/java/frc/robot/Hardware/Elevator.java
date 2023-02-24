package frc.robot.Hardware;

import frc.robot.Elevator.Claw;
import frc.robot.Elevator.Lift;
import frc.robot.Elevator.Roller;

public class Elevator {

    public static void Initialize () {
        // Arm           .Initialize();
        Claw          .Initialize();
        Lift          .Initialize();
        Roller        .Initialize();
    }

    public static void Periodic () {
        // Arm           .Periodic();
        Claw          .Periodic();
        Lift          .Periodic();
        Roller        .Periodic();
    }

    public static void Display () {
        // Arm           .Display();
        Claw          .Display();
        Lift          .Display();
        Roller        .Display();
    }

    public static void Reset () {
        // Arm           .Reset();
        Claw          .Reset();
        Lift          .Reset();
        Roller        .Reset();
    }
}
