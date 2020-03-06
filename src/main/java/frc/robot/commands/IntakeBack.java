/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.subsystems.Intake;

public class IntakeBack extends CommandBase {
  private Intake intake;
  private boolean pickedUpBall;
  private boolean initialTrue;
  /**
   * Creates a new IntakeFront.
   */
  public IntakeBack() {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(Robot.intake);
    intake = Intake.getIntake();
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    pickedUpBall = false;
    if(intake.getBackLimitSwitch()) {
      initialTrue = true;
    }
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    intake.spinBackIntake();
    if(!intake.getBackLimitSwitch()) {
      initialTrue = false;
    }
    if(intake.getBackLimitSwitch() && !initialTrue) {
      pickedUpBall = true;
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    intake.brakeBack();
    if(pickedUpBall) {
      Robot.intakeBackOn = false;
    }
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if(intake.getBackCount() >= Constants.INTAKE_MAX_BACK) {
      Robot.intakeBackOn = false;
      return true;
    }
    if(intake.getTotalCount() >= Constants.INTAKE_MAX) {
      Robot.intakeBackOn = false;
      return true;
    }
    if(pickedUpBall) {
      return true;
    }
    if(!Robot.intakeBackOn) {
      return true;
    }
    return false;
  }
}
