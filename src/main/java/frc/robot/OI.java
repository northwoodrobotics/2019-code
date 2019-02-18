/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.commands.ToggleCentricMode;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	public static final XboxController driveController = new XboxController(0);
	public static final XboxController liftController = new XboxController(1);
	public static final JoystickButton driveX = new JoystickButton(driveController, 3);

	public OI(Robot robot) {
		driveX.whenPressed(new ToggleCentricMode(robot.drivetrain, driveController));
	}

	/**
	 * Adds a deadzone to, for example, a joystick input that does not completely
	 * zero itself mechanically.
	 * 
	 * @param input  the input value (any double between -1 and 1, inclusively).
	 * @param radius how far from zero the input can be for the output to still be
	 *               zero. This must be greated than 0 and less than 1.
	 * @return the value after application of the deadzone (between -1 and 1,
	 *         inclusively).
	 */
	public static double deadBand(double input) {
		double output;
		double radius = 0.2;
		double maxOutput = 1;
		assert (-1 < input && input < 1) : "input is less than -1 or greater than 1";
		assert (radius < maxOutput) : "deadband radius is greater than or equal to the maximum output";

		if (input > radius) {
			output = ((maxOutput * (input - maxOutput)) / (maxOutput - radius)) + maxOutput;
		} else if (input < -radius) {
			output = ((maxOutput * (input + maxOutput)) / (maxOutput - radius)) - maxOutput;
		} else {
			output = 0;
		}

		assert (Math.abs(output) <= maxOutput) : "expected to output a smaller number than the maxOutput of "
				+ maxOutput;
		return output;
	}
}