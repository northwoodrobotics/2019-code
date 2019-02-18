package frc.robot.commands.teleop;

import frc.robot.OI;
import frc.robot.subsystems.CargoClaw;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TeleClaw extends Command {
	private CargoClaw claw;
	private XboxController controller = OI.liftController;

	public TeleClaw(CargoClaw claw) {
		this.claw = claw;
		requires(claw);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		claw.move(controller.getTriggerAxis(Hand.kRight) - controller.getTriggerAxis(Hand.kLeft));
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}