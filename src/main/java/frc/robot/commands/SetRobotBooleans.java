/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;

public class SetRobotBooleans extends CommandBase {
  private int i;
  /**
   * Creates a new SetIsSeekingTurret.
   */
  public SetRobotBooleans(int index) {
    // Use addRequirements() here to declare subsystem dependencies.
    i = index;

  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    switch(i) {
      case 0: {
        if(!Robot.isSeekingTurret) {
          Robot.isSeekingTurret = true;
        }
        else {
          Robot.isSeekingTurret = false;
        }
        break;
      }
      case 1: {
        if(!Robot.intakeOn) {
          Robot.intakeOn = true;
        }
        else {
          Robot.intakeOn = false;
        }
        break;
      }
      case 2: {
        if(!Robot.intakeFrontOn) {
          Robot.intakeFrontOn = true;
        }
        else {
          Robot.intakeFrontOn = false;
        }
        break;
      }
      case 3: {
        if(!Robot.intakeBackOn) {
          Robot.intakeBackOn = true;
        }
        else {
          Robot.intakeBackOn = false;
        }
        break;
      }
      case 4: {
        if(!Robot.spinRevolving) {
          Robot.spinRevolving = true;
        }
        else {
          Robot.spinRevolving = false;
        }
        break;
      }
      case 5: {
        if(!Robot.spinColoring) {
          Robot.spinColoring = true;
        }
        else {
          Robot.spinColoring = false;
        }
        break;
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
    return true;
  }
}
