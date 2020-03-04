/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.Constants;
import frc.robot.subsystems.Climb;

public class ClimbUp extends CommandBase {
  private Climb climb;
  /**
   * Creates a new ClimbUp.
   */
  public ClimbUp() {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(Robot.climb);
    climb = Climb.getClimb();
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    Robot.isClimbingUp = true;
    if(Robot.isClimbingDown) {
      Robot.isClimbingDown = false;
    }
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    climb.climbUp();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    Robot.isClimbingUp = false;
    climb.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if(climb.getTopSwitch()) {
      return true;
    }
    if(!Robot.isClimbingUp) {
      return true;
    }
    return false;
  }
}
