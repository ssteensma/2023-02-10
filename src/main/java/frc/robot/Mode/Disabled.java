package frc.robot.Mode;

import frc.robot.Hardware.AutonChooser;
import frc.robot.Hardware.Stage;

public class Disabled {
    
    public static void Initialize () {
        Stage.StageNumber = 0;
    }

    public static void Periodic () {
        AutonChooser.AutonPath = AutonChooser.chooser.getSelected();
    }

}
