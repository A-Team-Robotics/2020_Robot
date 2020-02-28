/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Turret extends SubsystemBase {
  private static Turret turret;
  private WPI_TalonSRX motor;
  private DigitalInput leftTurretSwitch;
  private DigitalInput rightTurretSwitch;
  
  /**
   * Creates a new Turret.
   */
  public Turret() {
    motor = new WPI_TalonSRX(Constants.MOTOR_TURRET_TURN_ID);
    leftTurretSwitch = new DigitalInput(Constants.TURRET_LEFT_LIMIT);
    rightTurretSwitch = new DigitalInput(Constants.TURRET_RIGHT_LIMIT);
  }
  /**
   * Gets the turret object.
   * @return The turret object.
   */
  public static Turret getTurret() {
    if(turret == null) {
      turret = new Turret();
    }

    return turret;
  }

  /**
   * Turn the robot, checking for the limit switches.
   * @param speed The speed to turn (from 0 to 1).
   */
  public void turn(double speed) {
    if(speed < 0 && (getPosition() <= Constants.TURRET_MAX_RIGHT || !getRightLimitSwitch())) {
      stop();
    }
    else if(speed > 0 && (getPosition() >= Constants.TURRET_MAX_LEFT || !getLeftLimitSwitch())) {
      stop();
    }
    else {
      motor.set(speed);
    }
  }

  /**
   * Position the turret to its absolute center.
   */
  public void center() {
    int middle = (int) (Constants.TURRET_SPAN / 2) - 55;
    int position = getPosition();

    if(position > middle) {
      do {
        if(Math.abs(getPosition()) < 2000) {
          turn(-Constants.TURRET_CENTER_SPEED);
        }
        else {
          turn(-Constants.TURRET_CENTER_SPEED_FAST);
        }
      }
      while(getPosition() > middle);
    }
    else if(position < middle) {
      do {
        if(Math.abs(getPosition()) < 2000) {
          turn(Constants.TURRET_CENTER_SPEED);
        }
        else {
          turn(Constants.TURRET_CENTER_SPEED_FAST);
        }
      }
      while(getPosition() < middle);
    }
  }

  /**
   * Reset the motor encoder.
   */
  public void resetEncoder() {
    motor.setSelectedSensorPosition(0);
  }

  public int getPosition() {
    return motor.getSelectedSensorPosition();
  }

  /**
   * Turn the turret from the controller input.
   * @param controller The Xbox Controller.
   */
  public void turnTurret(XboxController controller) {
    if(controller.getYButton() && getRightLimitSwitch()) {
      turn(-Constants.TURRET_SPIN_SPEED);
    }
    else if(controller.getXButton() && getLeftLimitSwitch()) {
      turn(Constants.TURRET_SPIN_SPEED);
    }
    else {
      stop();
    }
  }

  /**
   * Stops the motor.
   */
  public void stop() {
    motor.set(0);
  }

  /**
   * Get the value of the left limit switch. If activated/pressed, then it will return true.
   * @return The value of the left limit switch.
   */
  public boolean getLeftLimitSwitch() {
    return leftTurretSwitch.get();
  }

  /**
   * Get the value of the right limit switch. If activated/pressed, then it will return true.
   * @return The value of the right limit switch.
   */
  public boolean getRightLimitSwitch() {
    return rightTurretSwitch.get();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
