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
import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.VictorSP;

import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

/**
 *
 */
public class PIDVisionDrivetrain extends PIDSubsystem {

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    private final AnalogPotentiometer fakeAnalogPotentiometer1 = RobotMap.pIDVisionDrivetrainFakeAnalogPotentiometer1;
    private final SpeedController fakeMotor = RobotMap.pIDVisionDrivetrainFakeMotor;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    // Initialize your subsystem here
    public PIDVisionDrivetrain() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=PID
        super("PIDVisionDrivetrain", -0.85, -0.0105, -0.03);
        setAbsoluteTolerance(0.05);
        getPIDController().setContinuous(false);
        LiveWindow.addActuator("PID Vision Drivetrain", "PIDSubsystem Controller", getPIDController());

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=PID

        // Use these to get going:
        // setSetpoint() -  Sets where the PID controller should move the system
        //                  to
        // enable() - Enables the PID controller.
    }

    public void initDefaultCommand() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }

    protected double returnPIDInput() {
        // Return your input value for the PID loop
        // e.g. a sensor, like a potentiometer:
        // yourPot.getAverageVoltage() / kYourMaxVoltage;

        // !BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=SOURCE
        //return fakeAnalogPotentiometer1.get();

        // !END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=SOURCE
    	
    	// Get the vision target centered X position from networkTables
    	
    	double xPos = Robot.vision.getScaledTargetXpos();
    	//System.out.println("PID Input says" + xPos);
    	return xPos;
        
    }

    protected void usePIDOutput(double output) {
        // Use output to drive your system, like a motor
        // e.g. yourMotor.set(output);

        // !BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=OUTPUT
        //leftMotor.pidWrite(output);

        // !END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=OUTPUT
    	
    	// set the output
    	// BUT, because the motor is essentially differential, we need to divide the output in half to get
    	// the effect predicted by the PID controller!
    	System.out.println("TankDrive would have run with output = " + output);
    	Robot.driveTrain.tankDrive(output, -output);
    	
    	// Note:  We could also just drive one side of the robot to avoid the confusion of the differential.
    }
}
