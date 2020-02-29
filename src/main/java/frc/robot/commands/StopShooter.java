/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.*;
import frc.robot.Robot;

public class StopShooter extends CommandBase {
  private Shooter shooter;
  private Intake intake;
  private VerticalIntake verticalIntake;
  /**
   * Creates a new StopShooter.
   */
  public StopShooter() {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(Robot.shooter, Robot.intake, Robot.verticalIntake);
    shooter = Shooter.getShooter();
    intake = Intake.getIntake();
    verticalIntake = VerticalIntake.getVerticalIntake();
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    //Robot.shooting = false;
    shooter.stop();
    intake.brakeBack();
    intake.brakeFront();
    verticalIntake.brake();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
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
