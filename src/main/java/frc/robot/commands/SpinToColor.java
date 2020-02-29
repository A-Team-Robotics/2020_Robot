/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj.DriverStation;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.subsystems.ColorSensor;
import frc.robot.subsystems.Spinner;

public class SpinToColor extends CommandBase {
  private Spinner spinner;
  private ColorSensor colorSensor;
  private String gameData;
  private String[] colors = {Constants.COLOR_RED, Constants.COLOR_GREEN, Constants.COLOR_BLUE, Constants.COLOR_YELLOW};
  private int colorIndex;
  private boolean cancel;
  private boolean colorMatched;
  private int startGood;
  /**
   * Creates a new SpinToColor.
   */
  public SpinToColor() {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(Robot.colorSensor, Robot.spinner);
    colorSensor = ColorSensor.getColorSensor();
    spinner = Spinner.getSpinner();
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    gameData = DriverStation.getInstance().getGameSpecificMessage();
    if(gameData.length() > 0) {
      // Choose which color to go by. Add or subtract 2 so robot can read perpendicular to field sensors.
      switch (gameData.charAt(0)) {
        case 'B':
          colorIndex = 2 - 2;
          break;
        case 'G' :
          colorIndex = 1 + 2;
          break;
        case 'R' :
          colorIndex = 0 + 2;
          break;
        case 'Y' :
          colorIndex = 3 - 2;
          break;
        default :
          cancel = true;
          break;
      }
    } else {
      cancel = true;
    }
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    String currentColor = colorSensor.getMatchedColor();
    if(!currentColor.equals(colors[colorIndex])) {
      colorMatched = false;
      spinner.spinColorSeek();
    }
    else {
      spinner.brake();
      if(!colorMatched) {
        colorMatched = true;
        startGood = (int) System.currentTimeMillis();
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
    if(!Robot.spinColoring) {
      return true;
    }

    if(startGood != 0) {

      int endGood = (int) System.currentTimeMillis();
      int difference = (int) (endGood - startGood) / 1000;
      if(difference >= 5) return true;
    }
    if(cancel) return true;
    return false;
  }
}
