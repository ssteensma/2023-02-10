package frc.robot.Mode;

import frc.robot.Hardware.AutonChooser;
import frc.robot.Hardware.Autopilot;
import frc.robot.Hardware.Elevator;
import frc.robot.Hardware.Stage;
import frc.robot.Hardware.Swerve;
import frc.robot.Hardware.Path;

public class Autonomous {

    public static String
        AutonChoice = "Do Nothing";

    public static void Initialize () {
        AutonChoice = AutonChooser.chooser.getSelected();
        Stage.Initialize();
    }

    public static void Periodic () {
        Stage.Begin();

        switch ( AutonChoice ) {
            case "Nothing" : Path.Nothing(); break;
            case "Any 1st" : Path.Any_1st(); break;
            case "Any 2nd" : Path.Any_2nd(); break;
            case "Lft 1st" : Path.Lft_1st(); break;
            case "Lft 2nd" : Path.Lft_2nd(); break;
            case "Ctr 1st" : Path.Ctr_1st(); break;
            case "Ctr 2nd" : Path.Ctr_2nd(); break;
            case "Rgt 1st" : Path.Rgt_1st(); break;
            case "Rgt 2nd" : Path.Rgt_2nd(); break;
        }

        Stage.Next();

        // EXECUTE COMMANDS
        Elevator.Periodic();
        Swerve.UpdateRobotRelative( Autopilot.vx, Autopilot.vy, Autopilot.vt );
    }

}
