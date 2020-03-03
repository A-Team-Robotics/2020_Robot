/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

public class Climb extends SubsystemBase {
  private static Climb climb;
  private WPI_VictorSPX motor1;
  private WPI_VictorSPX motor2;
  /**
   * Creates a new Climb.
   */
  public Climb() {
    motor1 = new WPI_VictorSPX(Constants.MOTOR_CLIMB_1_ID);
    motor2 = new WPI_VictorSPX(Constants.MOTOR_CLIMB_2_ID);

    motor1.setNeutralMode(NeutralMode.Brake);
    motor2.setNeutralMode(NeutralMode.Brake);
  }

  /**
   * Gets the climb object.
   * @return The climb object.
   */
  public static Climb getClimb() {
    if(climb == null) {
      climb = new Climb();
    }
    return climb;
  }

  /**
   * Sets both motors to spin in the direction to climb up.
   */
  public void climbUp() {
    motor1.set(-Constants.CLIMB_SPEED);
    motor2.set(-Constants.CLIMB_SPEED);
  }

  /**
   * Sets both motors to spin in the direction to climb down.
   */
  public void climbDown() {
    motor1.set(Constants.CLIMB_SPEED);
    motor2.set(Constants.CLIMB_SPEED);
  }

  /**
   * Stops the climb.
   */
  public void stop() {
    motor1.set(0);
    motor2.set(0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
