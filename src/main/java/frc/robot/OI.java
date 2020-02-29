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
import frc.robot.commandGroups.*;
import frc.robot.commands.*;
import frc.robot.Constants;

public class OI {
    private static XboxController controller = new XboxController(Constants.XBOX_CONTROLLER_PORT);;
    private static Joystick joystick = new Joystick(Constants.JOYSTICK_PORT);
    private Button buttonPOV;

    public JoystickButton shoot = new JoystickButton(joystick, JoystickMap.TRIGGER);
    public JoystickButton aimTurret = new JoystickButton(joystick, JoystickMap.AIM_TURRET_BUTTON);
    public JoystickButton intake = new JoystickButton(joystick, JoystickMap.INTAKE_BUTTON);
    public JoystickButton intakeFront = new JoystickButton(joystick, JoystickMap.INTAKE_FRONT_BUTTON);
    public JoystickButton intakeBack = new JoystickButton(joystick, JoystickMap.INTAKE_BACK_BUTTON);
    public JoystickButton spinRevolution = new JoystickButton(joystick, JoystickMap.SPINNER_REVOLVE_BUTTON);
    public JoystickButton spinColor = new JoystickButton(joystick, JoystickMap.SPINNER_COLOR_BUTTON);

    public OI() {
        shoot.whenHeld(new Shoot(), true);
        shoot.whenReleased(new StopShooter());

        aimTurret.whenPressed(new SetRobotBooleans(0));

        intake.whenPressed(new SetRobotBooleans(1));

        intakeFront.whenPressed(new SetRobotBooleans(2));

        intakeBack.whenPressed(new SetRobotBooleans(3));

        spinRevolution.whenPressed(new SetRobotBooleans(4));
        spinColor.whenPressed(new SetRobotBooleans(5));
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