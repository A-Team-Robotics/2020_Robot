/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;

public class SetRobotBooleansHeld extends CommandBase {
  private int i;
  /**
   * Creates a new SetRobotBooleansHeld.
   */
  public SetRobotBooleansHeld(int index) {
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    switch(i) {
      case 1: {
        if(Robot.isClimbingUp) {
          Robot.isClimbingUp = false;
        }
        else {
          Robot.isClimbingUp = true;
        }
      }
      case 2: {
        if(Robot.isClimbingDown) {
          Robot.isClimbingDown = false;
        }
        else {
          Robot.isClimbingDown = true;
        }
      }
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
