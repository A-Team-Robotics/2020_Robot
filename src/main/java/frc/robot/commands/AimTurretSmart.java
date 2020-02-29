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
import frc.robot.subsystems.Camera;
import frc.robot.subsystems.Turret;

public class AimTurretSmart extends CommandBase {
  private Camera camera;
  private Turret turret;
  private double x;
  private double lastx;
  private boolean tac;
  /**
   * Creates a new AimTurret.
   */
  public AimTurretSmart(boolean trackAndCenter) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(Robot.turret, Robot.limelight);
    turret = Turret.getTurret();
    camera = Camera.getCamera();
    tac = trackAndCenter;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    lastx = Constants.lastx;
    if(!tac && Robot.isSeekingTurret) {
      Robot.isSeekingTurret = false;
    }
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    x = camera.getX();
    if(x > 0) {
      if(x <= Constants.TURRET_AIM_CLOSE) {
        if(x <= Constants.LIMELIGHT_X_TURRET_FORGIVENESS) {
          turret.stop();
        }
        else {
          turret.turn(-Constants.TURRET_AIM_SPEED_SLOW);
        }
      }
      else {
        if(x <= Constants.LIMELIGHT_X_TURRET_FORGIVENESS) {
          turret.stop();
        }
        else {
          turret.turn(-Constants.TURRET_AIM_SPEED);
        }
      }
    }
    else if(x < 0) {
      if(x >= -Constants.TURRET_AIM_CLOSE) {
        if(x >= -Constants.LIMELIGHT_X_TURRET_FORGIVENESS) {
          turret.stop();
        }
        else {
          turret.turn(Constants.TURRET_AIM_SPEED_SLOW);
        }
      }
      else {
        if(x >= -Constants.LIMELIGHT_X_TURRET_FORGIVENESS) {
          turret.stop();
        }
        else {
          turret.turn(Constants.TURRET_AIM_SPEED);
        }
      }
    }
    else {
      if(lastx > 0) {
        turret.turn(-Constants.TURRET_AIM_SPEED);
      }
      else {
        turret.turn(Constants.TURRET_AIM_SPEED);
      }
    }

    if(x <= -Constants.LIMELIGHT_X_TURRET_FORGIVENESS || x >= Constants.LIMELIGHT_X_TURRET_FORGIVENESS) {
      lastx = x;
      Constants.lastx = x;
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    turret.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if(!tac && x > -Constants.LIMELIGHT_X_TURRET_FORGIVENESS && x < Constants.LIMELIGHT_X_TURRET_FORGIVENESS) {
      if(camera.getArea() != 0) {
        return true;
      }
    }
    if(tac && !Robot.isSeekingTurret) {
      return true;
    }
    return false;
  }
}
