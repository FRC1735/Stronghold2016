// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc1735.Stronghold2016.subsystems;

import org.usfirst.frc1735.Stronghold2016.Robot;
import org.usfirst.frc1735.Stronghold2016.RobotMap;
import org.usfirst.frc1735.Stronghold2016.commands.*;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.VictorSP;

import edu.wpi.first.wpilibj.command.Subsystem;


/**
 *
 */
public class Shooter extends Subsystem {

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

	// MANUALLY added these to more easily get at the underlying objects.
	// Note that these lines are not auto-generated if you change the objects in RobotBuilder!
    private final SpeedController leftMotor = RobotMap.shooterLeftPIDLeftMotor;
    private final Encoder shootLeftEncoder = RobotMap.shooterLeftPIDShootLeftEncoder;
    private final SpeedController rightMotor = RobotMap.shooterRightPIDRightMotor;
    private final Encoder shootRightEncoder = RobotMap.shooterRightPIDShootRightEncoder;
    //private final ShooterLeftPID leftPID = Robot.shooterLeftPID;


// Add a constructor to set the sample rate
    public Shooter() {
    	// Based on this comment in the WPILIB documentation for encoders:
    	//   For sensing rate, particularly at high RPM, using 1x or 2x decoding and increasing the number
    	//   of samples to average may substantially help reduce jitter. 
    	// Set the (over)sample rate... once we know what to set it to!
    	// Need to find out what the default is before we change the value.
    	System.out.println("Default value of samplesToAverage for encoder is: " +
    			shootLeftEncoder.getSamplesToAverage());

    	//shootLeftEncoder.setSamplesToAverage(samplesToAverage);
    }
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

        // Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand());
    }
    public void engageShooter(double magnitudeDirection) {
    	//1800 is about the max speed, so start the setPoint approx the right speed
    	double setPoint = magnitudeDirection/1800;
    	
    	double leftError = ((magnitudeDirection+getLeftRPM())/magnitudeDirection)*4;
    	double rightError = ((magnitudeDirection-getRightRPM())/magnitudeDirection)*4;
    	System.out.println(leftError + " " + rightError);
    	leftMotor.set(-1*Math.max(Math.min(-1*(setPoint+leftError),1.0),-1.0));
    	rightMotor.set(Math.max(Math.min(-1*(setPoint+rightError), 1.0), -1.0));
    }
    public void stop() {
    	leftMotor.set(0);
    	rightMotor.set(0);
    }
    // Note:  we expect the motors to return a full-speed rate of approximately 3600 RPM.
    // Use the printRPMs to see if that is true....
    // We may need to change the DistancePerPulse variable to get the proper output value.
    // From WPILIB documentation:
    //   Rate - The current rate of the counter in units/sec.
    //   It is calculated using the DistancePerPulse divided by the period.
    //   If the counter is stopped this value may return Inf or NaN, depending on language.
    //TODO:  WE may need to check for the Inf/NaN and handle it.
    
    public double getLeftRPM() {
    	return shootLeftEncoder.getRate();
    }
 
    public double getRightRPM() {
    	return shootRightEncoder.getRate();
    }
    public void printRPMs() {
    	System.out.println("Shooter RPMs:  Left= " + getLeftRPM() + ", Right= " + getRightRPM());
    }

    // This function is used to figure out how fast we should run the shooter given our distance from the goal.
    public double calculateRPMFromRange(double range) {
    	// Input is the distance reported by the rangefinder, in feet.
    	// Assumptions for initial implementation:
    	// - When fairly close, we need full speed (too close to hit the apex, so need max vertical)
    	// - When mid-range, we need to back off speed-- limited empirical evidence suggested 62.5-75% at around 12ft.  this was our only datapoint
    	// - When at long range, we need full speed to get the distance (hitting just at the apex)
    	// - if graphed with RPM vs Dist, this looks like a valley.  Simplify to two linear regions to make it a V shape.
    	// - Assume left edge is 5' at 1800RPM
    	// - Assume the middle of the V (distance wise) is about 12' at 1200RPM
    	// - Assume right edge is 20' at 1800RPM
    	
    	double targetRPM;
    	//Inverted V can then be implemented as two regions, each a linear equation:
    	if (range <= 12) {
    		// y=mx+b; with above values, this solves to:
    		targetRPM = (-85.7*range) + 2228;
    	}
    	else { // range > 12'
    		
    		targetRPM = (66.6*range) + 408;
    	}
    	
    	// Clamp RPM to be between 1000 and 1800
    	targetRPM = Math.max(Math.min(targetRPM, 1800), 1000);
    	return targetRPM;
    }
    
    // Function to spin up shooter to *just* the right speed to hit the goal
    public void engageAutoShooter() {
    	// First, find out our current distance to the target
    	double range = Robot.range.getRange();
    	
    	// Calculate the RPMs needed to hit a target at that range
    	double targetRPM = calculateRPMFromRange(range);
    	
    	Robot.shooterLeftPID.setSetpoint(targetRPM);
    	Robot.shooterRightPID.setSetpoint(targetRPM);
    	Robot.shooterLeftPID.enable();
    	Robot.shooterRightPID.enable();
    }
    
    //Given a target RPM, how fast should we run the motor (from 0 to 1)
    public double calculateMotorSpeedFromRPM(double requestedRPM) {
    	// We don't really know the motor curve, so let's assume a dumb linear for now:
    	// if RPM can go from 0 to 1800, and motor can go from 0 to 1, then the linear relationship is just RPM/1800
    	return requestedRPM/1800;
    	
    }
 }

