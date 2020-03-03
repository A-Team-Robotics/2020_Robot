/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Hood;
import frc.robot.subsystems.Camera;
import frc.robot.Constants;
import frc.robot.RobotPhysics;
import frc.robot.Robot;

public class PositionRobotToShoot extends CommandBase {
  private DriveTrain drive;
  private Hood hood;
  private Camera camera;
  private double x1;
  private double angle1;
  private double x2;
  private double angle2;
  /**
   * Creates a new PositionRobotToShoot.
   */
  public PositionRobotToShoot() {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(Robot.driveTrain, Robot.hood, Robot.limelight);
    hood = Hood.getHood();
    drive = DriveTrain.getDriveTrain();
    camera = Camera.getCamera();
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    x1 = RobotPhysics.getXVector(0);
    angle1 = hood.positionToAngle(hood.getPosition());

    x2 = RobotPhysics.getXDisplacement();
    if(RobotPhysics.acceptableAngle()) {
      angle2 = RobotPhysics.getRequiredAngle();
    }
    else {
      angle2 = -1;
    }
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(angle2 == -1) {
      double x = RobotPhysics.getXVector(0 + hood.positionToAngle(hood.getPosition()));
      if(x < (x2 - Constants.SHOOT_DISTANCE_FORGIVENESS)) {
        drive.autoDrive(Constants.SHOOT_DRIVE_SPEED, 0);
      }
      else if(x > (x2 + Constants.SHOOT_DISTANCE_FORGIVENESS)) {
        drive.autoDrive(-Constants.SHOOT_DRIVE_SPEED, 0);
      }
    }
    else {
      double currentAngle = hood.positionToAngle(hood.getPosition());
      hood.moveHood(angle2);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    hood.stop();
    drive.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if(angle2 == -1) {
      double x = RobotPhysics.getXVector(0 + hood.positionToAngle(hood.getPosition()));
      if(x >= (x2 - Constants.SHOOT_DISTANCE_FORGIVENESS) && x <= (x2 + Constants.SHOOT_DISTANCE_FORGIVENESS)) {
        return true;
      }
    }
    else {
      double angle = hood.positionToAngle(hood.getPosition());
      if(angle >= (angle2 - Constants.SHOOT_ANGLE_FORGIVENESS) && angle <= (angle2 + Constants.SHOOT_ANGLE_FORGIVENESS)) {
        return true;
      }
    }
    return false;
  }
}