/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.Constants;

public class Shooter extends SubsystemBase {
  private static Shooter shooter;
  private WPI_TalonSRX motor1;
  private WPI_TalonSRX motor2;
  /**
   * Creates a new Shooter.
   */
  public Shooter() {
    motor1 = new WPI_TalonSRX(Constants.MOTOR_SHOOTER_1_ID);
    motor2 = new WPI_TalonSRX(Constants.MOTOR_SHOOTER_2_ID);
  }

  /**
   * Gets the shooter object.
   * @return The shooter object.
   */
  public static Shooter getShooter() {
    if(shooter == null) {
      shooter = new Shooter();
    }

    return shooter;
  }
  
  /**
   * Spin the shooter.
   */
  public void spinShooter() {
    motor1.set(-Constants.SHOOTER_SPEED);
    motor2.set(-Constants.SHOOTER_SPEED);
  }

  /**
   * Get the speed of motor 1.
   * @return The speed of motor 1.
   */
  public int getMotor1Speed() {
    return motor1.getSelectedSensorVelocity();
  }

  /**
   * Get the speed of motor 2.
   * @return The speed of motor 2.
   */
  public int getMotor2Speed() {
    return motor2.getSelectedSensorVelocity();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
