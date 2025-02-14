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
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc1735.Stronghold2016.Robot;

/**
 *
 */
public class ShooterStart extends Command {

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS
 
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
    public ShooterStart() {

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
        requires(Robot.shooter);

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	//m_shooterStrength = SmartDashboard.getNumber("ShooterStrength");
    	//m_shooterRPM = SmartDashboard.getNumber("Target RPM");
    	m_shooterRPM = (double)Robot.shooter.shooterRPMChooser.getSelected();

    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//System.out.println("Hi!  Engaging shooter to " + m_shooterRPM + " RPM");
    	Robot.shooter.engageShooter(m_shooterRPM);
    	Robot.shooter.printRPMs();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false; // We turn the shooter on and leave it on.
        // killing the shooter requires sending a separate STOP command, or an interrupt/cancel.
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
    
    // member variables
    double m_shooterRPM;

}
