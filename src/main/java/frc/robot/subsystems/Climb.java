/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.DigitalInput;
import frc.robot.Constants;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

public class Climb extends SubsystemBase {
  private static Climb climb;
  private WPI_VictorSPX motor1;
  private WPI_VictorSPX motor2;
  private DigitalInput bottomSwitch;
  private DigitalInput topSwitch;
  /**
   * Creates a new Climb.
   */
  public Climb() {
    motor1 = new WPI_VictorSPX(Constants.MOTOR_CLIMB_1_ID);
    motor2 = new WPI_VictorSPX(Constants.MOTOR_CLIMB_2_ID);

    motor1.setNeutralMode(NeutralMode.Brake);
    motor2.setNeutralMode(NeutralMode.Brake);

    bottomSwitch = new DigitalInput(Constants.CLIMB_BOTTOM_SWITCH);
    topSwitch = new DigitalInput(Constants.CLIMB_TOP_SWITCH);
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

  /**
   * Get the value of the bottom limit switch. If activated/pressed, then it will return true.
   * @return The value of the bottom limit switch.
   */
  public boolean getBottomSwitch() {
    return bottomSwitch.get();
  }

  /**
   * Get the value of the top limit switch. If activated/pressed, then it will return true.
   * @return The value of the top limit switch.
   */
  public boolean getTopSwitch() {
    return topSwitch.get();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
