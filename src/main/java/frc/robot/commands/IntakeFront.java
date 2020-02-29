/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.subsystems.Intake;

public class IntakeFront extends CommandBase {
  private Intake intake;
  /**
   * Creates a new IntakeFront.
   */
  public IntakeFront() {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(Robot.intake);
    intake = Intake.getIntake();
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    if(Robot.intakeFrontOn) {
      Robot.intakeFrontOn = false;
    }
    else {
      Robot.intakeFrontOn = true;
    }
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    intake.brakeFront();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if(!Robot.intakeFrontOn) {
      return true;
    }
    return false;
  }
}