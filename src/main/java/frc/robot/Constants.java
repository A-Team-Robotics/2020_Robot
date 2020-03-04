/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide
 * numerical or boolean constants. This class should not be used for any other
 * purpose. All constants should be declared globally (i.e. public static). Do
 * not put anything functional in this class.
 *
 * <p>
 * It is advised to statically import this class (or one of its inner classes)
 * wherever the constants are needed, to reduce verbosity.
 */
public final class Constants {
    // Motors
    public static final int MOTOR_LEFT_1_ID = 0;
    public static final int MOTOR_LEFT_2_ID = 3;
    public static final int MOTOR_RIGHT_1_ID = 1;
    public static final int MOTOR_RIGHT_2_ID = 2;
    public static final int MOTOR_TURRET_TURN_ID = 9;
    public static final int MOTOR_INTAKE_FRONT = 7;
    public static final int MOTOR_INTAKE_BACK = 8;
    public static final int MOTOR_SHOOTER_1_ID = 5;
    public static final int MOTOR_SHOOTER_2_ID = 6;
    public static final int MOTOR_VERTICAL_INTAKE_ID = 10;
    public static final int MOTOR_SPINNER_ID = 11;
    public static final int MOTOR_HOOD_ID = 4;
    public static final int MOTOR_CLIMB_1_ID = 12;
    public static final int MOTOR_CLIMB_2_ID = 13;

    // Turret
    public static final double TURRET_SPIN_SPEED = 0.6;
    public static final int TURRET_LEFT_LIMIT = 3;
    public static final int TURRET_RIGHT_LIMIT = 4;
    public static final double TURRET_AIM_SPEED = 0.5;
    public static final double TURRET_AIM_SPEED_SLOW = 0.15;
    public static final double TURRET_AIM_CLOSE = 7.0;
    public static final double TURRET_SPAN = -4422 + 3348;
    public static final double TURRET_CENTER_SPEED_FAST = 0.55;
    public static final double TURRET_CENTER_SPEED = 0.15;
    public static final int TURRET_MAX_RIGHT = -13450;
    public static final int TURRET_MAX_LEFT = -TURRET_MAX_RIGHT;
    public static final double TURRET_PID_P = 1.5;
    public static final double TURRET_PID_I = 0;
    public static final double TURRET_PID_D = 0.0999999046;
    public static final double TURRET_PID_F = 0;
    public static double lastx;

    public static final int TURRET_0 = 0;
    public static final int TURRET_45 = 1900;
    public static final int TURRET_90 = 3800;
    public static final int TURRET_135 = 5700;
    public static final int TURRET_180 = 7600;

    // Intake
    public static final int INTAKE_FRONT_LIMIT_SWITCH = 1;
    public static final int INTAKE_BACK_LIMIT_SWITCH = 0;
    public static final double INTAKE_SPEED = 0.75;
    public static final double INTAKE_JOG_FORWARDS_TIME = 1;
    public static final double INTAKE_JOG_BACKWARDS_TIME = 0.5;
    public static final int INTAKE_MAX_FRONT = 2;
    public static final int INTAKE_MAX_BACK = 2;
    public static final int INTAKE_MAX = 4;

    // Vertical intake
    public static final double VERTICAL_INTAKE_SPEED = 1;

    // Shooter
    public static final double SHOOTER_SPEED = 0.8;
    public static final int SHOOTER_WARMUP_TIME = 2;
    public static final double SHOOTER_VELOCITY = 10;
    public static final double SHOOT_DISTANCE_FORGIVENESS = 0.5;
    public static final double SHOOT_ANGLE_FORGIVENESS = 1.5;
    public static final double SHOOT_DRIVE_SPEED = 0.5;
    public static final double SHOOT_AIM_SPEED = 0.2;
    // Flywheel position 1064 at 13.94 distance.

    // Spinner
    public static final double SPINNER_REVOLUTION_SPEED = 0.1;
    public static final double SPINNER_COLOR_SEEK_SPEED = 0.15;

    // Hood
    public static final double HOOD_SPEED = 1;
    public static final int HOOD_MAXIMUM_POSITION = 1216;
    public static final int HOOD_MAXIMUM_ANGLE = 45;
    public static final double HOOD_PID_P = 300;
    public static final double HOOD_PID_I = 0;
    public static final double HOOD_PID_D = 200;
    public static final double HOOD_PID_F = 0;

    // Proximity Sensor
    public static final int PROXIMITY_CENSOR_ID = 2;
    public static final double PROXIMITY_VOLTS_TO_DISTANCE_FEET = 100;

    // Climb
    public static final double CLIMB_SPEED = 1;
    public static final int CLIMB_BOTTOM_SWITCH = 6;
    public static final int CLIMB_TOP_SWITCH = 5;

    // Speed controller groups.
    public static final boolean LEFT_MOTORS_INVERTED = false;
    public static final boolean RIGHT_MOTORS_INVERTED = false;

    // Color Sensor.
    public static final double[] RGB_RED = {0.561, 0.232, 0.114};
    public static final double[] RGB_YELLOW = {0.361, 0.524, 0.113};
    public static final double[] RGB_GREEN = {0.197, 0.561, 0.240};
    public static final double[] RGB_BLUE = {0.143, 0.427, 0.429};
    public static final String COLOR_RED = "Red";
    public static final String COLOR_YELLOW = "Yellow";
    public static final String COLOR_GREEN = "Green";
    public static final String COLOR_BLUE = "Blue";
    public static final String COLOR_UNKNOWN = "Unknown";
    public static final String COLOR_NODETECT = "None Detected";
    public static final int COLOR_CLOSEST_PROXIMITY = 150; // Maximum distance is about 105 - 110.

    // Auto drive.
    public static final double AUTO_DRIVE_SPEED = 0.9;
    public static final double AUTO_DRIVE_SLOW_SPEED = 0.55;
    public static final double MOTOR_DELAY_STOP = 0.1;
    public static final double AUTO_TURN_SPEED = 0.7;
    public static final double AUTO_STOP_DELAY_SECONDS = 0.5;
    
    // Controllers.
    public static final int XBOX_CONTROLLER_PORT = 0;
    public static final int JOYSTICK_PORT = 1;
    public static final boolean driveTrainMotorSafety = false;

    // Gyro.
    public static final double GYRO_CYCLE_TIME = 0.02;
    public static final int ENCODER_VALUE_PER_FOOT = 2440;

    // Limelight.
    public static final int LIMELIGHT_PIPELINE_ID = 0;
    public static final double LIMELIGHT_X_FORGIVENESS = 0.1;
    public static final double LIMELIGHT_X_TURRET_FORGIVENESS = 1;
    public static final double LIMELIGHT_X_CLOSE = 2;
    public static final double LIMELIGHT_X_CLOSE_TURN_SPEED = 0.45;
    public static final double LIMELIGHT_X_CENTER_SPEED = 0.51;
    public static final double LIMELIGHT_FOLLOW_SPEED = 0.55;
    public static final double LIMELIGHT_FAST_FOLLOW_SPEED = 0.65;
    public static final double LIMELIGHT_SLOW_FOLLOW_SPEED = 0.5;
    public static final double LIMELIGHT_SLOTH_FOLLOW_SPEED = 0.46;
    public static final double LIMELIGHT_FOCAL_LENGTH = 2.9272791257541;
    public static final double LIMELIGHT_SEEK_SPEED = 0.6;
    public static final double LIMELIGHT_DISTANCE_ACCEPTABLE = 0.01;
    public static final double LIMELIGHT_MINIMUM_VIEWABLE_AREA = 0.38;
    public static final double LIMELIGHT_DEGREES_RELATIVE_TO_GROUND = 13;
    public static final double K = 14.666666666666667 * Math.sqrt(1.723);

    // Field
    public static final int HEIGHT_OF_SHOOT_INCHES = 42;
    public static final int HEIGHT_OF_TARGET_INCHES = 96;
    public static final int HEIGHT_TO_SHOOT_INCHES = HEIGHT_OF_TARGET_INCHES - HEIGHT_OF_SHOOT_INCHES;
}
