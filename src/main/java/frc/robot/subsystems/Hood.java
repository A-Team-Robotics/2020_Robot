/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class Hood extends SubsystemBase {
  private static Hood hood;
  private WPI_TalonSRX motor;
  /**
   * Creates a new Hood.
   */
  public Hood() {
    motor = new WPI_TalonSRX(Constants.MOTOR_HOOD_ID);
    initialize();
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
    motor.configFactoryDefault();
    motor.setSensorPhase(true);
    motor.setSelectedSensorPosition(0);
    motor.clearStickyFaults();
    motor.setNeutralMode(NeutralMode.Brake);
  }

  /**
   * Move the hood.
   */
  public void moveHood(boolean up) {
    if(up) {
      motor.set(Constants.HOOD_SPEED);
    }
    else {
      motor.set(-Constants.HOOD_SPEED);
    }
  }

  /**
   * Move the hood.
   */
  public void moveHood(double controlSpeed) {
    if(getPosition() > getPositionBySpeed(controlSpeed)) {
      motor.set(-Constants.HOOD_SPEED);
    }
    else {
      motor.set(Constants.HOOD_SPEED);
    }
  }

  /**
   * Move the hood to a specific angle.
   */
  public void moveHood(int degrees) {
    double currentAngle = positionToAngle(getPosition());
    //System.out.println(currentAngle + " - " + degrees);

    if(currentAngle < Constants.HOOD_MAXIMUM_ANGLE) {

      if(currentAngle >= (degrees + 2)) {
        motor.set(Constants.HOOD_SPEED);

      //System.out.println("Curret sensor reading - " + motor.getSelectedSensorPosition(0) + "getting to sensor pos "+ angleToPosition(degrees));
        //motor.set(ControlMode.Position,angleToPosition(degrees));
      }
      else if(currentAngle <= (degrees - 2)) {
        motor.set(-Constants.HOOD_SPEED);
       // System.out.println("Curret sensor reading - " + motor.getSelectedSensorPosition(0) + "getting to sensor pos "+ angleToPosition(degrees));
        //motor.set(ControlMode.Position,angleToPosition(degrees));
      }
      else {
        stop();
      }
    }
    else {
      stop();
    }
  }

  public void HoldPos(){
    System.out.println("Holding pos - " + motor.getSelectedSensorPosition(0) + "the motor voltage is " + motor.getBusVoltage());
    ;
    motor.set(ControlMode.Disabled,0.0);
    //motor.set(ControlMode.Position,motor.getSelectedSensorPosition(0));
  }

  /**
   * Converts the censor position to an angle in degrees.
   * @return The angle in degrees.
   */
  public double positionToAngle(int pos) {
    if(pos == 0) {
      return 0;
    }
    return (pos * Constants.HOOD_MAXIMUM_ANGLE) / Constants.HOOD_MAXIMUM_POSITION;
  }

  public double angleToPosition(int angle) {
    return (angle * Constants.HOOD_MAXIMUM_POSITION) / Constants.HOOD_MAXIMUM_ANGLE;
  }

  /**
   * Converts the control value from the joystick into a motor position.
   * @return The motor position.
   */
  public int getPositionBySpeed(double controlSpeed) {
    double cs = controlSpeed + 1;
    return (int) ((Constants.HOOD_MAXIMUM_POSITION / 2) * cs);
  }

  /**
   * Converts the control value from the joystick into an angle in degrees.
   * @return The angle in degrees.
   */
  public int getAngleBySpeed(double controlSpeed) {
    double cs = controlSpeed + 1;
    return (int) ((cs * Constants.HOOD_MAXIMUM_ANGLE) / 2);
  }

  /**
   * Stops the hood from moving.
   */
  public void stop() {
    motor.set(-0.03);
  }

  /**
   * Gets the position of the hood relative to its default position.
   * @return The position of the hood.
   */
  public int getPosition() {
    return motor.getSelectedSensorPosition(0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
