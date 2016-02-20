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

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;

import edu.wpi.first.wpilibj.command.Subsystem;


/**
 *
 */
public class DriveTrain extends Subsystem {

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    private final SpeedController leftMotor = RobotMap.driveTrainLeftMotor;
    private final SpeedController rightMotor = RobotMap.driveTrainRightMotor;
    private final RobotDrive robotDrive21 = RobotMap.driveTrainRobotDrive21;
    private final Encoder leftMotorEncoder = RobotMap.driveTrainLeftMotorEncoder;
    private final Encoder rightMotorEncoder = RobotMap.driveTrainRightMotorEncoder;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS


    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

        setDefaultCommand(new DriveWithJoysticks());

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

        // Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand());
    }
    
    // This is just a wrapper so the joystick and direct methods live in the same class for consistency.
    public void tankDriveWithJoysticks(Joystick left, Joystick right) {
        // Collect the Joystick info.
        // We need to filter out very small joystick values and clamp them to zero
        // so that a slightly off-center joystick doesn't send signals to the motor.
        double driveLeft = left.getY();
        double driveRight = right.getY();
        
        //Apply Filter
        if (Math.abs(driveLeft) < Robot.m_joystickFilter) {driveLeft = 0;}
        if (Math.abs(driveRight) < Robot.m_joystickFilter) {driveRight = 0;}

        //We may need to drive the robot BACKWARDS from the joysticks on a frequent basis.
        // Add a button that allows us to reverse what is the "Front" of the robot to make this easier...
        // (Button defined in oi.java)
        if (Robot.oi.reverseDrive.get()) {
        	// if reverseDrive is set, we want to reverse the "front" of the robot.
        	// This means that pushing forward should move the robot backwards.
        	// This basically means that we reverse the polarity of the Y axis of the joystick.
        	// drive inverted (negative Y means "down" on the joystick)
        	this.tankDrive(driveLeft, driveRight);
        }
        else {
        	// Drive normally (negative Y means "up" on the joystick)
        	this.tankDrive(-driveLeft, -driveRight);
        }
    }
       
    public void tankDrive(double driveLeft,double driveRight) {
        // The motors spin faster in one direction than the other.
        // For this robot configuration:
        //     The right side motors spin faster in the forward direction.
        //     The left side motors spin faster in the backward direction.
        // Create a compensator for this
        // Can't add to slower drive if it's already 1.0, so must subtract off faster unit.
        // Actually, we want to use a percentage rather than subtracting a factor.
        // Algo:
        // Only compensate if both values are the same (intending to track straight, or are in autonomous)
        //   if magnitude positive, reduce right side speed.
        //   if magnitude negative, reduce left  side speed.
        
        // We have some funny negative signs floating around, so be sure to use absolute values for this check
//        if (Math.abs(driveLeft) == Math.abs(driveRight)) {
//            System.out.println("Compensating for input drive magnitude Left = " + driveLeft + " Right = " + driveRight);
//            
//            double motorCompensation = SmartDashboard.getNumber("motorCompensation", .1); // a 10% default should be easy to see if we accidentally got it
//        
//            if (driveRight > 0)
//                driveRight = driveRight * motorCompensation; 
//            else
//                driveLeft  = driveLeft  * motorCompensation;
//            //in the zero case, no compensation needed.
//        }
//        // Drive with the compensated value.
//        System.out.println("After Compensation Left = " + driveLeft + " Right = " + driveRight);
    	
        robotDrive21.tankDrive(driveLeft, driveRight); // Optional third arg bool SquaredInputs, when true, decreases sensitivity at low speeds.
    }

    // Function to STOP the drivetrain:
    public void stop() {
    	robotDrive21.tankDrive(0, 0); //left, right
    }
}

