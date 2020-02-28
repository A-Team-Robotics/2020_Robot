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

public class StartShoot extends CommandBase {
  private Intake intake;
  private VerticalIntake verticalIntake;
  private Shooter shooter;
  /**
   * Creates a new StartShoot.
   */
  public StartShoot() {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(Robot.shooter, Robot.intake, Robot.verticalIntake);
    intake = Intake.getIntake();
    verticalIntake = VerticalIntake.getVerticalIntake();
    shooter = Shooter.getShooter();
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    Robot.shooting = true;
    intake.initialize();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    intake.spinFrontIntake();
    intake.spinBackIntake();
    verticalIntake.spin();
    shooter.spinShooter();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    if(interrupted) {
      new StopShooter();
    }
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
