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
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Hood;

public class HoodShootPosition extends CommandBase {
  private Hood hood;
  private DriveTrain driveTrain;
  private double pos;
  /**
   * Creates a new HoodShootPosition.
   */
  public HoodShootPosition(double hoodManualPosition) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(Robot.hood, Robot.driveTrain);
    hood = Hood.getHood();
    driveTrain = DriveTrain.getDriveTrain();
    pos = hoodManualPosition;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    hood.moveHoodPosition(pos);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if(hood.getPosition() >= (pos - Constants.HOOD_POSITION_FORGIVENESS)
    && hood.getPosition() <= (pos + Constants.HOOD_POSITION_FORGIVENESS)) {
      return true;
    }
    return false;
  }
}
