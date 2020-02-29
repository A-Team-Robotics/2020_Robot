/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.Constants;

public class Spinner extends SubsystemBase {
  private static Spinner spinner;
  private WPI_TalonSRX motor;
  private AnalogInput proximityCensor;
  /**
   * Creates a new Spinner.
   */
  public Spinner() {
    motor = new WPI_TalonSRX(Constants.MOTOR_SPINNER_ID);
    proximityCensor = new AnalogInput(Constants.PROXIMITY_CENSOR_ID);
    motor.setNeutralMode(NeutralMode.Brake);
  }

  /**
   * Gets the spinner object.
   * @return The spinner object.
   */
  public static Spinner getSpinner() {
    if(spinner == null) {
      spinner = new Spinner();
    }
    
    return spinner;
  }

  /**
   * Spin the spinner. This method is used for spinning the Wheel of Fortune wheel a certain number of times.
   */
  public void spinRevolutions() {
    motor.set(Constants.SPINNER_REVOLUTION_SPEED);
  }

  /**
   * Spin the spinner. This method is used for spinning the Wheel of Fortune wheel to a certain color.
   */
  public void spinColorSeek() {
    motor.set(Constants.SPINNER_COLOR_SEEK_SPEED);
  }

  /**
   * Stops the motor dead in its tracks.
   */
  public void brake() {
    motor.set(0);
  }

  public double getCensorVoltage() {
    return proximityCensor.getVoltage();
  }

  public double getCensorReading() {
    return getCensorVoltage() * Constants.PROXIMITY_VOLTS_TO_DISTANCE_FEET;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
