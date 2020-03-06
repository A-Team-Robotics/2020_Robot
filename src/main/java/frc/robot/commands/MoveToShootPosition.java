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
import frc.robot.subsystems.Hood;

public class MoveToShootPosition extends CommandBase {
  private Hood hood;
  /**
   * Creates a new MoveToShootPosition.
   */
  public MoveToShootPosition() {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(Robot.hood);
    hood = Hood.getHood();
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    hood.resetSensor();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    hood.moveHoodPosition(Constants.HOOD_SHOOT_POSITION);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if(hood.getPosition() >= (Constants.HOOD_SHOOT_POSITION - Constants.HOOD_POSITION_FORGIVENESS)
    && hood.getPosition() <= (Constants.HOOD_SHOOT_POSITION + Constants.HOOD_POSITION_FORGIVENESS)) {
      return true;
    }
    return false;
  }
}
