package frc.robot.commands.teleop;

import frc.robot.OI;
import frc.robot.subsystems.Elevator;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TeleElevate extends Command {
	private Elevator elevator;
	private XboxController controller = OI.liftController;

	public TeleElevate(Elevator elevator) {
		this.elevator = elevator;
		requires(elevator);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		elevator.move(-OI.deadBand(controller.getY(Hand.kLeft)));
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
