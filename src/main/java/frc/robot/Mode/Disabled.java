package frc.robot.Mode;

import frc.robot.Hardware.Driver;
import frc.robot.Hardware.Stage;

public class Disabled {
    
    public static void Initialize () {
        Driver.Initialize();
    }

    public static void Periodic () {
        Stage.StageNumber = 0;
    }

}
