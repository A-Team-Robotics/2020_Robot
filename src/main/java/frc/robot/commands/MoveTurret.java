/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.OI;
import frc.robot.Robot;
import frc.robot.subsystems.Turret;

public class MoveTurret extends CommandBase {
  private Turret turret;
  /**
   * Creates a new MoveTurret.
   */
  public MoveTurret() {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(Robot.turret);
    turret = Turret.getTurret();
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    turret.moveTurretPosition(Turret.map(OI.getJoystickInstance().getThrottle(), -1, 1, 200, 7500)); // 200, 7500
    if(turret.getLeftLimitSwitch() == false || Robot.turret.getRightLimitSwitch() == false) {
      turret.stop();
    }
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
