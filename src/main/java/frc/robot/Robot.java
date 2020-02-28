/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.subsystems.*;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commandGroups.*;
import frc.robot.commands.*;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  private Command m_autonomousCommand;
  public static OI m_oi;

  private RobotContainer m_robotContainer;
  public static DriveTrain driveTrain;
  private AutonomousDriving autoDrive;
  public static Camera limelight;
  public static Turret turret;
  public static ColorSensor colorSensor;
  public static Intake intake;
  public static Shooter shooter;
  public static VerticalIntake verticalIntake;
  public static Spinner spinner;

  public static boolean isSeeking;
  public static boolean isFollowing;
  public static boolean cancelSeekAndFollow;
  public static boolean isSeekingTurret;
  public static boolean intakeOn;
  public static boolean shooting;

  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    // Instantiate our RobotContainer.  This will perform all our button bindings, and put our
    // autonomous chooser on the dashboard.    
    driveTrain = DriveTrain.getDriveTrain();
    limelight = Camera.getCamera();
    turret = Turret.getTurret();
    turret.resetEncoder();
    colorSensor = ColorSensor.getColorSensor();
    intake = Intake.getIntake();
    shooter = Shooter.getShooter();
    verticalIntake = VerticalIntake.getVerticalIntake();
    spinner = Spinner.getSpinner();
    m_oi = new OI();

    cancelSeekAndFollow = false;

    if (driveTrain == null){
      System.out.println("Drive train is null.");
    }
    driveTrain.init();
  }

  /**
   * This function is called every robot packet, no matter the  mode. Use this for items like
   * diagnostics that you want ran during disabled, autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    // Runs the Scheduler.  This is responsible for polling buttons, adding newly-scheduled
    // commands, running already-scheduled commands, removing finished or interrupted commands,
    // and running subsystem periodic() methods.  This must be called from the robot's periodic
    // block in order for anything in the Command-based framework to work.
    CommandScheduler.getInstance().run();
  }

  /**
   * This function is called once each time the robot enters Disabled mode.
   */
  @Override
  public void disabledInit() {
    limelight.turnOffLED();
  }

  @Override
  public void disabledPeriodic() {
    limelight.turnOffLED();
  }

  /**
   * This autonomous runs the autonomous command selected by your {@link RobotContainer} class.
   */
  @Override
  public void autonomousInit() {

    //m_autonomousCommand = m_robotContainer.getAutonomousCommand();
    //m_autonomousCommand = new AutonomousDriving();
    new WarmUpShooter().schedule();
    // schedule the autonomous command (example)
    if (m_autonomousCommand != null) {
      m_autonomousCommand.schedule();
    }
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    // System.out.println("Total Distance Travelled: " + driveTrain.getDistance(false));
  }

  @Override
  public void teleopInit() {
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.

    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
    SmartDashboard.putBoolean("Turret Follow", isSeekingTurret);
    driveTrain.manualDrive(m_oi.getControllerInstant());
    turret.turnTurret(m_oi.getControllerInstant());

    XboxController instant = OI.getControllerInstant();

    if(instant.getPOV() > 0 && instant.getPOV() < 180) {
      if(!isSeeking) {
        isSeeking = true;
        cancelSeekAndFollow = false;
        new SeekAndFollow(1).schedule();
      }
    }
    else if(instant.getPOV() > 180 && instant.getPOV() < 360) {
      if(!isSeeking) {
        isSeeking = true;
        cancelSeekAndFollow = false;
        new SeekAndFollow(-1).schedule();
      }
    }
    else if(instant.getPOV() == 0) {
      if(!isFollowing) {
        isFollowing = true;
        cancelSeekAndFollow = false;
      }
    }
    else if(instant.getPOV() == 180) {
      cancelSeekAndFollow = true;
    }
    
    if(instant.getAButtonPressed()) {
      // new AimTurret().schedule();
      if(isSeekingTurret) isSeekingTurret = false;
      else {
        Constants.lastx = 0;
        isSeekingTurret = true;
      }
    }
    if(instant.getBButton()) {
      turret.center();
    }
    if(instant.getBackButtonPressed()) {
      if(intakeOn) intakeOn = false;
      else intakeOn = true;
    }

    if(isSeekingTurret) new Target_TurretFollow().schedule();
    if(isFollowing) new FollowObject(10).schedule();
    if(intakeOn) new IntakeBalls().schedule();
    if(shooting) new WarmUpShooter().schedule();
  }

  @Override
  public void testInit() {
    // Cancels all running commands at the start of test mode.
    CommandScheduler.getInstance().cancelAll();
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
    // shooter.spinShooter();
    /*
    verticalIntake.spin();
    intake.spinBackIntake(Constants.INTAKE_SPEED);
    intake.spinFrontIntake(Constants.INTAKE_SPEED);
    */
  }
}
