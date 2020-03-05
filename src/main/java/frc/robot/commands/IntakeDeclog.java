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

public class IntakeDeclog extends CommandBase {
  private Intake intake;
  private long startTime;
  private long endTime;
  /**
   * Creates a new IntakeDeclog.
   */
  public IntakeDeclog() {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(Robot.intake);
    intake = Intake.getIntake();
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    startTime = System.currentTimeMillis();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    intake.spinFrontIntakeBackwards();
    intake.spinBackIntakeBackwards();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    intake.brakeFront();
    intake.brakeBack();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    endTime = System.currentTimeMillis();
    double difference = (endTime - startTime) / 1000;
    if(difference >= Constants.INTAKE_JOG_BACKWARDS_TIME) {
      return true;
    }
    return false;
  }
}
