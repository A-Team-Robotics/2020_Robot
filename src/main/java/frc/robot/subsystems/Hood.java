/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class Hood extends SubsystemBase {
  private static Hood hood;
  private WPI_TalonSRX motor;
  /**
   * Creates a new Hood.
   */
  public Hood() {
    motor = new WPI_TalonSRX(Constants.MOTOR_HOOD_ID);
  }

  /**
   * Gets the hood object.
   * @return The hood object.
   */
  public static Hood getHood() {
    if(hood == null) {
      hood = new Hood();
    }
    return hood;
  }

  /**
   * Initialize the hood. Set it to zero.
   */
  public void initialize() {
  }

  /**
   * Move the hood.
   */
  public void moveHood() {
    motor.set(Constants.HOOD_SPEED);
  }

  /**
   * Hold the hood in place.
   */
  public void hold() {
    motor.set(0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
