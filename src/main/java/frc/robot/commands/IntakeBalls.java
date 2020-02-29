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

public class IntakeBalls extends CommandBase {
  private Intake intake;
  private DriveTrain drive;
  /**
   * Creates a new IntakeBalls.
   */
  public IntakeBalls() {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(Robot.intake, Robot.driveTrain);
    intake = Intake.getIntake();
    drive = DriveTrain.getDriveTrain();
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(drive.getVelocity() > 0) {
      intake.spinFrontIntake();
      intake.brakeBack();
    }
    else if(drive.getVelocity() < 0) {
      intake.spinBackIntake();
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
    if(!Robot.intakeOn) {
      return true;
    }

    if(intake.getFrontCount() >= 3 || intake.getBackCount() >= 3 || intake.getTotalCount() >= 5) {
      return true;
    }

    return false;
  }
}
