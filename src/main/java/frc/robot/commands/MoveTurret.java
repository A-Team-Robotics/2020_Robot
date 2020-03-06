/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.OI;
import frc.robot.Robot;
import frc.robot.subsystems.Turret;

public class MoveTurret extends CommandBase {
  private Turret turret;
  private int pos;
  /**
   * Creates a new MoveTurret.
   */
  public MoveTurret(int position) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(Robot.turret);
    turret = Turret.getTurret();
    pos = position;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(turret.getPosition() < pos) {
      turret.turn(Constants.TURRET_SPIN_SPEED);
    }
    else {
      turret.turn(-Constants.TURRET_SPIN_SPEED);
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
    if(turret.getPosition() >= (pos - Constants.TURRET_POSITION_FORGIVENESS) && turret.getPosition() <= (pos + Constants.TURRET_POSITION_FORGIVENESS)) {
      return true;
    }
    return false;
  }
}
