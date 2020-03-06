/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.PIDSubsystem;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.TalonSRXConfiguration;

public class Hood extends SubsystemBase {
  private static Hood hood;
  private WPI_TalonSRX motor;
  private TalonSRXConfiguration motorConfig;
  /**
   * Creates a new Hood.
   */
  public Hood() {
    motor = new WPI_TalonSRX(Constants.MOTOR_HOOD_ID);
    motorConfig = new TalonSRXConfiguration();
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

  public void resetSensor() {
    motor.setSelectedSensorPosition(0);
  }

  /**
   * Initialize the hood. Set it to zero.
   */
  public void initialize() {
    motor.configFactoryDefault();
    motor.setSensorPhase(true);
    motor.setSelectedSensorPosition(0);
    motor.clearStickyFaults();
    motor.setNeutralMode(NeutralMode.Coast);

    //motorConfig.clearPositionOnLimitR = true;
    motor.setSensorPhase(false);
    motor.setSelectedSensorPosition(0);
    motorConfig.primaryPID.selectedFeedbackSensor = FeedbackDevice.QuadEncoder;
    motorConfig.slot0.allowableClosedloopError =10;
    motorConfig.slot0.kP = Constants.HOOD_PID_P;
    motorConfig.slot0.kI = Constants.HOOD_PID_I;
    motorConfig.slot0.kD = Constants.HOOD_PID_D;
    motorConfig.slot0.kF = Constants.HOOD_PID_F;
   /* motorConfig.peakOutputForward = Constants.HOOD_SPEED;
    motorConfig.peakOutputReverse = Constants.HOOD_SPEED;*/

    motor.configAllSettings(motorConfig);
  }

  /**
   * Move the hood to a specific angle.
   */
  public void moveHood(double degrees) {
    double position = angleToPosition(degrees);
    moveHoodPosition(position);
  }

  /**
   * Move the hood to a specific position.
   */
  public void moveHoodPosition(double position) {
    
    motor.set(ControlMode.Position, position);
    //motor.set(-.3);
  }

  /**
   * Converts the censor position to an angle in degrees.
   * @return The angle in degrees.
   */
  public double positionToAngle(double pos) {
    if(pos == 0) {
      return 0;
    }
    return (pos * Constants.HOOD_MAXIMUM_ANGLE) / Constants.HOOD_MAXIMUM_POSITION;
  }

  public double angleToPosition(double angle) {
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
    motor.set(ControlMode.Disabled,0);
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
    SmartDashboard.putNumber("Hood Position", getPosition());
    SmartDashboard.putNumber("Hood Motor Power",motor.getBusVoltage());
  }
}
