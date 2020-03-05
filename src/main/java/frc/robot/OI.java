/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.XboxController.Button;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.POVButton;
import frc.robot.commandGroups.*;
import frc.robot.commands.*;
import frc.robot.Constants;

public class OI {
    private static XboxController controller = new XboxController(Constants.XBOX_CONTROLLER_PORT);
    private static Joystick joystick = new Joystick(Constants.JOYSTICK_PORT);

    public JoystickButton shoot = new JoystickButton(joystick, JoystickMap.TRIGGER);
    public JoystickButton aimTurret = new JoystickButton(joystick, JoystickMap.AIM_TURRET_BUTTON);
    public JoystickButton intake = new JoystickButton(joystick, JoystickMap.INTAKE_BUTTON);
    public JoystickButton intakeFront = new JoystickButton(joystick, JoystickMap.INTAKE_FRONT_BUTTON);
    public JoystickButton intakeBack = new JoystickButton(joystick, JoystickMap.INTAKE_BACK_BUTTON);
    public JoystickButton intakeJog = new JoystickButton(joystick, JoystickMap.INTAKE_JOG_BUTTON);
    public JoystickButton spinRevolution = new JoystickButton(joystick, JoystickMap.SPINNER_REVOLVE_BUTTON);
    public JoystickButton climbUp = new JoystickButton(joystick, JoystickMap.CLIMB_UP_BUTTON);
    public JoystickButton spinColor = new JoystickButton(joystick, JoystickMap.SPINNER_COLOR_BUTTON);
    public JoystickButton climbDown = new JoystickButton(joystick, JoystickMap.CLIMB_DOWN_BUTTON);
    public JoystickButton declogIntake = new JoystickButton(joystick, JoystickMap.INTAKE_DECLOG);

    POVButton turret0 = new POVButton(joystick, 0);
    POVButton turret45 = new POVButton(joystick, 45);
    POVButton turret90 = new POVButton(joystick, 90);
    POVButton turret135 = new POVButton(joystick, 135);
    POVButton turret180 = new POVButton(joystick, 180);
    POVButton turret270 = new POVButton(joystick, 270);

    public OI() {
        shoot.whenHeld(new Shoot(), true);
        shoot.whenReleased(new StopShooter());

        aimTurret.whenPressed(new SetRobotBooleans(0));

        // intake.whenPressed(new SetRobotBooleans(1));
        intakeFront.whenPressed(new SetRobotBooleans(2));
        intakeBack.whenPressed(new SetRobotBooleans(3));
        declogIntake.whenPressed(new IntakeDeclog());
        // intakeJog.whenPressed(new SetRobotBooleans(6));

        spinRevolution.whenPressed(new SetRobotBooleans(4));
        spinColor.whenPressed(new SetRobotBooleans(5));

        climbUp.whenHeld(new ClimbUp());
        climbUp.whenReleased(new SetRobotBooleansHeld(1));
        climbDown.whenHeld(new ClimbDown());
        climbDown.whenReleased(new SetRobotBooleansHeld(2));


        /*
        turret0.whenActive(new TurretPosition(Constants.TURRET_0));
        turret45.whenActive(new TurretPosition(Constants.TURRET_45));
        turret90.whenActive(new TurretPosition(Constants.TURRET_90));
        turret135.whenActive(new TurretPosition(Constants.TURRET_135));
        turret180.whenActive(new TurretPosition(Constants.TURRET_180));
        turret270.whenActive(new MoveTurret());
        */
    }

    public static XboxController getControllerInstant() {
        if (controller == null) {
            controller = new XboxController(Constants.XBOX_CONTROLLER_PORT);
        }
        
        return controller;
    }

    public static Joystick getJoystickInstance() {
        return joystick;
    }
}