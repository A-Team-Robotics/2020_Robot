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
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.DriveTrain;

public class IntakeJog extends CommandBase {
  private Intake intake;
  private DriveTrain drive;
  private int startTime;
  private int endTime;
  private double difference;
  private boolean forward;
  /**
   * Creates a new IntakeJog.
   */
  public IntakeJog() {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(Robot.intake, Robot.driveTrain);
    intake = Intake.getIntake();
    drive = DriveTrain.getDriveTrain();
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    Robot.intakeOn = false;
    Robot.intakeBackOn = false;
    Robot.intakeFrontOn = false;
    forward = true;
    startTime = (int) System.currentTimeMillis();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    endTime = (int) System.currentTimeMillis();
    difference = (endTime - startTime) / 1000;

    if(forward) {
      if(difference >= Constants.INTAKE_JOG_FORWARDS_TIME) {
        forward = false;
        startTime = (int) System.currentTimeMillis();
      }
    }
    else {
      if(difference >= Constants.INTAKE_JOG_BACKWARDS_TIME) {
        forward = true;
        startTime = (int) System.currentTimeMillis();
      }
    }

    if(drive.getVelocity() > 0) {
      if(forward) {
        intake.spinFrontIntake();
      }
      else {
        intake.spinFrontIntakeBackwards();
      }
      intake.brakeBack();
    }
    else if(drive.getVelocity() < 0) {
      if(forward) {
        intake.spinBackIntake();
      }
      else {
        intake.spinBackIntakeBackwards();
      }
      intake.brakeFront();
    }

    intake.incrementCounts();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    intake.brakeIntakes();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if(!Robot.intakeJogOn) {
      return true;
    }

    if(intake.getFrontCount() >= 3 || intake.getBackCount() >= 3 || intake.getTotalCount() >= 5) {
      return true;
    }

    return false;
  }
}
