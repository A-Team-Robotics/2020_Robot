/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import frc.robot.subsystems.*;

/**
 * Add your docs here.
 */
public class RobotPhysics {
    private static DriveTrain drive = DriveTrain.getDriveTrain();
    private static Camera camera = Camera.getCamera();
    private static Hood hood = Hood.getHood();
    private static final double AC = -9.81;

    /**
     * Get the x-component of a vector.
     * @param angleDegrees The angle of the vector.
     * @return The x-component of the vector.
     */
    public static double getXVector(double angleDegrees) {
        return camera.getObjectDistance() * Math.cos((angleDegrees + Constants.LIMELIGHT_DEGREES_RELATIVE_TO_GROUND) * (Math.PI / 180));
    }

    /**
     * Determine the time in seconds it takes for the balls to reach the height of the target.
     * @return The time in seconds.
     */
    public static double[] getTime() {
        double a = 0.5 * AC;
        double b = Constants.SHOOTER_VELOCITY;
        double c = -Constants.HEIGHT_TO_SHOOT_INCHES;
        double t1 = 0;
        double t2 = 0;

        if((Math.pow(b, 2) - (4 * a * c)) < 0) {
            double[] ans = {-1, -1};
            return ans;
        }
        else {
            // Quadratic equation.
            t1 = (-b + Math.sqrt(Math.pow(b, 2) - (4 * a * c))) / (2 * a);
            t2 = (-b - Math.sqrt(Math.pow(b, 2) - (4 * a * c))) / (2 * a);
        }

        double[] t = {t1, t2};
        return t;
    }
    
    /**
     * Get the x-displacement the robot must be in order to shoot the balls into the target.
     * @return The ideal x-displacement.
     */
    public static double getXDisplacement() {
        double[] t = getTime();
        if(t[0] == -1) {
            return -1;
        }
        double x1 = Constants.SHOOTER_VELOCITY * t[0];
        double x2 = Constants.SHOOTER_VELOCITY * t[1];

        if(x1 <= 0) {
            return x2;
        }
        else if(x2 <= 0) {
            return x1;
        }
        else {
            if(x1 <= x2) {
                return x1;
            }
            return x2;
        }
    }

    /**
     * Determines whether or not the robot can position the hood at any angle in order to reach the target.
     * If this method returns false, then the robot should use the x-displacement method instead to determine
     * the ideal distance to shoot into the target.
     * @return True/false if the robot can position the hood.
     */
    public static boolean acceptableAngle() {
        if(getRequiredAngle() == -1) {
            return false;
        }
        return true;
    }

    /**
     * Gets the required angle the robot must position its hood in order to reach the target. If this method
     * returns -1, then there is no such angle.
     * <p>When compiling, it is recommended to run the method acceptableAngle() to check if there is an angle
     * the robot can position to first. That way the robot will not glitch out when -1 is returned.
     * @return The angle to position the hood.
     */
    public static double getRequiredAngle() {
        double[] t = getTime();

        double angle1 = Math.asin((Constants.HEIGHT_TO_SHOOT_INCHES - (0.5 * AC * Math.pow(t[0], 2))) / (Constants.SHOOTER_VELOCITY * t[0])) * (180 / Math.PI);
        double angle2 = Math.asin((Constants.HEIGHT_TO_SHOOT_INCHES - (0.5 * AC * Math.pow(t[1], 2))) / (Constants.SHOOTER_VELOCITY * t[1])) * (180 / Math.PI);

        if(angle1 < 0) {
            if(angle2 < 0) {
                return -1;
            }
            return angle2;
        }
        else {
            if(angle1 <= angle2) {
                return angle1;
            }
            return angle2;
        }
    }
}