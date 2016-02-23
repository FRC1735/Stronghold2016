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

import org.usfirst.frc1735.Stronghold2016.RobotMap;
import org.usfirst.frc1735.Stronghold2016.Robot;

/**
 *
 */
public class DriveWithLimits extends Command {

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS
    private double m_distanceLimit;
    private double m_timeLimit;
    private double m_magnitudeDirection;
 
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS

    // Additional vars not getting created by RobotBuilder
    private boolean m_turnLeft;
    private boolean m_turnRight;
    
    public DriveWithLimits(double distanceLimit, double timeLimit, double magnitudeDirection) {
    	// If left/right turn not specified, assume false.
    	this(distanceLimit, timeLimit, magnitudeDirection, false, false);
    }

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
    public DriveWithLimits(double distanceLimit, double timeLimit, double magnitudeDirection, boolean turnLeft, boolean turnRight) {

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING
        m_distanceLimit = distanceLimit;
        m_timeLimit = timeLimit;
        m_magnitudeDirection = magnitudeDirection;
        m_turnLeft = turnLeft;
        m_turnRight = turnRight;
        
        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING

        // If someone is stupid enough to set both Left and Right, then set both to zero.
        if (turnLeft && turnRight) {
        	System.out.println("Caller of DriveWithLimits command set both turnLeft and turnRight.  Setting both to FALSE.");
        	m_turnLeft = false;
        	m_turnRight = false;
        }
        
        // if turning, a negative magnitude would actually cause us to turn in the opposite direction
        // indicated by the boolean! (left with negative magnitude would actually turn right)
        // TODO:  should we use abs() to prevent negative magnitudes when specifying a turn?

        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
        requires(Robot.driveTrain);

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
        
        //Set the timeout
        setTimeout(timeLimit);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	//Grab the current encoder distance as the starting point
    	m_leftStartDistance = RobotMap.driveTrainLeftMotorEncoder.getDistance();
    	m_rightStartDistance = RobotMap.driveTrainRightMotorEncoder.getDistance();
    	// Grab the current rangefinder distance as an alternate starting point
    	m_rangeStartDistance = Robot.range.getRange();

    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	// Drive until we hit one of the limits.
    	// Time Limit handled by built-in library and handled in the isFinished() function
     	// Magnitude/direction may need compensation
    	//    But for now, just pass it along to go straight
    	double leftMagnitudeDirection  = m_magnitudeDirection;
    	double rightMagnitudeDirection = m_magnitudeDirection;
    		
    	// Distance limit handled in isFinished, but same data may be needed for tracking compensation
    	// (to go straight)
    	// TODO:  Add encoder/distance compensation here if needed
    	
    	// If we are turning, assume we do so differentially (sending same magnitude to both motors, but in opposite directions)
    	// We already enforced the mutex of turnLeft vs turnRight
    	if (m_turnLeft) leftMagnitudeDirection = -leftMagnitudeDirection; 
    	else if (m_turnRight) rightMagnitudeDirection = -rightMagnitudeDirection;
    	
    	//Finally, send the final motor magnitude and direction to the drivetrain:
    	Robot.driveTrain.tankDrive(leftMagnitudeDirection, rightMagnitudeDirection);
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        boolean timedOut = isTimedOut(); // Check for timeout
        double currentLeftDistance = RobotMap.driveTrainLeftMotorEncoder.getDistance();
        double currentRightDistance = RobotMap.driveTrainRightMotorEncoder.getDistance();
        double leftTravel = Math.abs(currentLeftDistance - m_leftStartDistance);
        double rightTravel = Math.abs(currentRightDistance - m_rightStartDistance);
        boolean encoderDistanceReached = (leftTravel > Math.abs(m_distanceLimit)) || (rightTravel > Math.abs(m_distanceLimit));
        
        // get current range.
        double currentRangeDistance = Robot.range.getRange();
        double rangeTravelDistance = m_rangeStartDistance - currentRangeDistance;
        // have we reached the rangefinder distance limit?
        boolean rangeDistanceReached = Math.abs(rangeTravelDistance) >= Math.abs(m_distanceLimit);
        
        //System.out.println("m_distanceLimit = " + m_distanceLimit);
        //System.out.println("R distance traveled is " + rightTravel + " and L distance traveled is " + leftTravel);
        //boolean finished = (timedOut || encoderDistanceReached || rangeDistanceReached);
        boolean finished = (timedOut);
 
        //System.out.println("isFinished returns status= " + finished);
        // Print out the reason(s) why we finished:
        if (finished) {
        	Robot.dbgPrintln("DriveLimitFinished: timedOut= " + timedOut +
        					 "encoderDistanceReached= "       + encoderDistanceReached +
        					 "rangeDistanceReached= "         + rangeDistanceReached);
        }
        return finished;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.driveTrain.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
    // Member Variables
    double m_leftStartDistance;		// starting absolute distance from encoder
    double m_rightStartDistance;	// starting absolute distance from encoder
    double m_rangeStartDistance;    // starting distance according to the rangefinder
}
