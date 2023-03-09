package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import frc.robot.Mode.Autonomous;
import frc.robot.Mode.Disabled;
import frc.robot.Mode.Simulation;
import frc.robot.Mode.Test;
import frc.robot.Mode.Teleop;
import frc.robot.Mode.Onabot;

public class Robot extends TimedRobot {
  @Override public void robotInit          () { Onabot     .Initialize(); }
  @Override public void robotPeriodic      () { Onabot     .Periodic();   }

  @Override public void autonomousInit     () { Autonomous .Initialize(); }
  @Override public void autonomousPeriodic () { Autonomous .Periodic();   }

  @Override public void disabledInit       () { Disabled   .Initialize(); }
  @Override public void disabledPeriodic   () { Disabled   .Periodic();   }

  @Override public void teleopInit         () { Teleop     .Initialize(); }
  @Override public void teleopPeriodic     () { Teleop     .Periodic();   }

  @Override public void testInit           () { Test       .Initialize(); }
  @Override public void testPeriodic       () { Test       .Periodic();   }

  @Override public void simulationInit     () { Simulation .Initialize(); }
  @Override public void simulationPeriodic () { Simulation .Periodic();   }
}
