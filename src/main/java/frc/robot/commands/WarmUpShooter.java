/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Shooter;
import frc.robot.Constants;
import frc.robot.Robot;

public class WarmUpShooter extends CommandBase {
  private Shooter shooter;
  private int startTime;
  private int endTime;
  /**
   * Creates a new WarmUpShooter.
   */
  public WarmUpShooter() {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(Robot.shooter);
    shooter = Shooter.getShooter();
    
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    //Robot.shooting = true;
    startTime = (int) System.currentTimeMillis();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    shooter.spinShooter();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    endTime = (int) System.currentTimeMillis();
    int difference = (int) ((endTime - startTime) / 1000);
    if(difference >= Constants.SHOOTER_WARMUP_TIME) {
      return true;
    }
    return false;
  }
}
