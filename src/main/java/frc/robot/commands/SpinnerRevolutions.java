/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.subsystems.ColorSensor;
import frc.robot.subsystems.Spinner;

public class SpinnerRevolutions extends CommandBase {
  ColorSensor colorSensor;
  Spinner spinner;
  int numDetects;
  String colorStart;
  String previousColor;
  /**
   * Creates a new SpinnerRevolutions.
   */
  public SpinnerRevolutions() {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(Robot.colorSensor, Robot.spinner);
    spinner = Spinner.getSpinner();
    colorSensor = ColorSensor.getColorSensor();
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    
    numDetects = 0;
    colorStart = colorSensor.getMatchedColor();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    spinner.spinRevolutions();
    String currentColor = colorSensor.getMatchedColor();
    if(currentColor.equals(colorStart)) {
      if(!currentColor.equals(previousColor)) {
        numDetects++;
        previousColor = currentColor;
      }
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    spinner.brake();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if(!Robot.spinRevolving) {
      return true;
    }

    if(numDetects >= 7) {
      return true;
    }
    return false;
  }
}
