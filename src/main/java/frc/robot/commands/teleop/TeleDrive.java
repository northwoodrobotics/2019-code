package frc.robot.commands.teleop;

import frc.robot.OI;
import frc.robot.subsystems.Drivetrain;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TeleDrive extends Command {
	private Drivetrain drivetrain;
	private XboxController controller = OI.driveController;

	private double fwd;
	private double strafe;
	private double rotateCW;

	public TeleDrive(Drivetrain drivetrain) {
		this.drivetrain = drivetrain;
		requires(drivetrain);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		fwd = -controller.getY(Hand.kLeft);
		strafe = controller.getX(Hand.kLeft);
		rotateCW = OI.deadBand(controller.getX(Hand.kRight));

		drivetrain.drive(fwd, strafe, rotateCW);
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
