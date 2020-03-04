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
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants;

public class Intake extends SubsystemBase {
  private static Intake intake;
  private WPI_VictorSPX frontMotor;
  private WPI_VictorSPX backMotor;
  public DigitalInput frontSwitch;
  public DigitalInput backSwitch;
  private boolean previousFrontSwitch;
  private boolean previousBackSwitch;
  private int frontCount;
  private int backCount;
  private int totalCount;
  /**
   * Creates a new Intake.
   */
  public Intake() {
    frontMotor = new WPI_VictorSPX(Constants.MOTOR_INTAKE_FRONT);
    backMotor = new WPI_VictorSPX(Constants.MOTOR_INTAKE_BACK);
    frontSwitch = new DigitalInput(Constants.INTAKE_FRONT_LIMIT_SWITCH);
    backSwitch = new DigitalInput(Constants.INTAKE_BACK_LIMIT_SWITCH);

    initialize();
  }

  /**
   * Gets the intake object.
   * @return The intake object.
   */
  public static Intake getIntake() {
    if(intake == null) {
      intake = new Intake();
    }
    
    return intake;
  }

  /**
   * Initializes all variables to startup values.
   */
  public void initialize() {
    frontCount = -1;
    backCount = -1;
    totalCount = -2;
    previousFrontSwitch = false;
    previousBackSwitch = false;
  }

  /**
   * Increment the counters based on which limit switches are true.
   */
  public void incrementCounts() {
    if(!previousFrontSwitch && getFrontLimitSwitch()) {
      frontCount++;
      totalCount++;
      previousFrontSwitch = true;
    }

    if(!previousBackSwitch && getBackLimitSwitch()) {
      backCount++;
      totalCount++;
      previousBackSwitch = true;
    }
  }
  
  /**
   * Gets number of balls from front intake.
   * @return The number of balls from front intake.
   */
  public int getFrontCount() {
    return frontCount;
  }

  /**
   * Gets number of balls from back intake.
   * @return The number of balls from front intake.
   */
  public int getBackCount() {
    return backCount;
  }

  /**
   * Gets number of balls from both intakes.
   * @return The number of balls from front intake.
   */
  public int getTotalCount() {
    return totalCount;
  }

  /**
   * Spins the front intake.
   */
  public void spinFrontIntake() {
    frontMotor.set(Constants.INTAKE_SPEED);
  }

  /**
   * Spins the front intake backwards.
   */
  public void spinFrontIntakeBackwards() {
    frontMotor.set(-Constants.INTAKE_SPEED);
  }

  /**
   * Spins the back intake.
   */
  public void spinBackIntake() {
    backMotor.set(Constants.INTAKE_SPEED);
  }

  /**
   * Spins the back intake backwards.
   */
  public void spinBackIntakeBackwards() {
    backMotor.set(-Constants.INTAKE_SPEED);
  }

  /**
   * Spins the front intake backwards (used for discharging).
   */
  public void dischargeFrontIntake() {
    frontMotor.set(-Constants.INTAKE_SPEED);
  }
  /**
   * Spins the back intake backwards (used for discharging).
   */
  public void dischargeBackIntake() {
    backMotor.set(-Constants.INTAKE_SPEED);
  }

  /**
   * Stops both intakes dead in its tracks.
   */
  public void brakeIntakes() {
    frontMotor.set(0);
    backMotor.set(0);
  }

  /**
   * Stops front intake dead in its tracks.
   */
  public void brakeFront() {
    frontMotor.set(0);
  }

  /**
   * Stops back intake dead in its tracks.
   */
  public void brakeBack() {
    backMotor.set(0);
  }

  /**
   * Get the value of the front limit switch. If activated/pressed, it will return true.
   * @return The value of the front limit switch.
   */
  public boolean getFrontLimitSwitch() {
    return frontSwitch.get();
  }

  /**
   * Get the value of the back limit switch. If activated/pressed, it will return true.
   * @return The value of the back limit switch.
   */
  public boolean getBackLimitSwitch() {
    return backSwitch.get();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putNumber("Intake Balls", totalCount);
    incrementCounts();
    previousFrontSwitch = getFrontLimitSwitch();
    previousBackSwitch = getBackLimitSwitch();
  }
}