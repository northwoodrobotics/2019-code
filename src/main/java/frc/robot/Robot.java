/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

//import frc.robot.commands.ResetDrivetrainEncoders;
import frc.robot.subsystems.*;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 */
public class Robot extends TimedRobot {
	// Subsystems
	public final Drivetrain drivetrain = new Drivetrain();
	public final Elevator elevator = new Elevator();
	public final Beak beak = new Beak();
	public final BeakWrist beakWrist = new BeakWrist();
	public final Claw cargoClaw = new Claw();
	public final ClawWrist clawWrist = new ClawWrist();
	public final RampLatches rampLatches = new RampLatches();

	// Cameras
	private final int imgWidth = 160;
	private final int imgHeight = 120;

	// Compressor
	private final Compressor compressor = new Compressor();

	// Other
	public static OI oi;

	/**
	 * This function is run when the robot is first started up and should be used
	 * for any initialization code.
	 */
	@Override
	public void robotInit() {
		// Subsystems
		drivetrain.init();
		elevator.init();
		cargoClaw.init();
		clawWrist.init();
		beak.init();
		beakWrist.init();
		rampLatches.init();

		// Cameras
		CameraServer.getInstance().startAutomaticCapture("BeakCam", 0).setResolution(imgWidth, imgHeight);
		CameraServer.getInstance().startAutomaticCapture("CargoCam", 1).setResolution(imgWidth, imgHeight);

		// Compressor
		compressor.setClosedLoopControl(true);

		// Other
		oi = new OI(this);
		// SmartDashboard.putData("Reset Encoders", new
		// ResetDrivetrainEncoders(drivetrain));
	}

	/**
	 * This function is called once each time the robot enters Disabled mode. You
	 * can use it to reset any subsystem information you want to clear when the
	 * robot is disabled.
	 */
	@Override
	public void disabledInit() {
	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void autonomousInit() {
	}

	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
	}

	/**
	 * This function is called periodically during operator control.
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {
	}

	@Override
	public void robotPeriodic() {
		SmartDashboard.putString("Centric mode", drivetrain.getCentricMode().toString() + "-CENTRIC");
		SmartDashboard.putBoolean("Beak is front", drivetrain.beakIsFront());
		SmartDashboard.putBoolean("Elevator at top", elevator.isAtTop());
		SmartDashboard.putBoolean("Elevator at bottom", elevator.isAtBottom());
	}
}