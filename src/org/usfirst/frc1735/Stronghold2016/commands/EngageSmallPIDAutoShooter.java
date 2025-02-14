// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc1735.Stronghold2016.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc1735.Stronghold2016.Robot;
import org.usfirst.frc1735.Stronghold2016.RobotMap;

/**
 *
 */
public class EngageSmallPIDAutoShooter extends Command {

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS
 
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
    public EngageSmallPIDAutoShooter() {

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
        requires(Robot.shooter);

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	// This calculates range, and spins up the shooter using the PID controllers to the appropriate speed
    	// Because we need the target RPM to determine if we are at speed, this function returns that critical data
    	m_targetRPM = Robot.shooter.engageSmallPIDShooter();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	// Without an actual PID controller, we have to figure out manually whether
    	// we have reached the target speed... which we don't even know at this point
    	// since it was calculated for us...
    	double leftRPM = RobotMap.shooterLeftPIDShootLeftEncoder.getRate();
    	double rightRPM = RobotMap.shooterRightPIDShootRightEncoder.getRate();
    	
    	// if we are within 2% of the target, claim victory
    	boolean leftDone = ((Math.abs(m_targetRPM - leftRPM)/m_targetRPM) < (m_targetRPM*0.02));
    	boolean rightDone = ((Math.abs(m_targetRPM - rightRPM)/m_targetRPM) < (m_targetRPM*0.02));
    	return (leftDone && rightDone);
    	
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.shooter.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
    
    // Member variables
    double m_targetRPM; //holds the RPM we are trying to reach
}
