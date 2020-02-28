/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.Constants;

public class VerticalIntake extends SubsystemBase {
  private static VerticalIntake verticalIntake;
  private WPI_VictorSPX motor;
  /**
   * Creates a new VerticalIntake.
   */
  public VerticalIntake() {
    motor = new WPI_VictorSPX(Constants.MOTOR_VERTICAL_INTAKE_ID);
  }

  /**
   * Gets the vertical intake object.
   * @return The vertical intake object.
   */
  public static VerticalIntake getVerticalIntake() {
    if(verticalIntake == null) {
      verticalIntake = new VerticalIntake();
    }

    return verticalIntake;
  }

  /**
   * Spins the vertical intake.
   */
  public void spin() {
    motor.set(Constants.VERTICAL_INTAKE_SPEED);
  }

  /**
   * Stops the vertical intake motor dead in its tracks.
   */
  public void brake() {
    motor.set(0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
