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

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc1735.Stronghold2016.subsystems.*;

/**
 *
 */
public class AutoShootSequence extends CommandGroup {


    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=PARAMETERS
    public AutoShootSequence() {

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=PARAMETERS
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=COMMAND_DECLARATIONS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=COMMAND_DECLARATIONS
    	
    	// The sequence is:
    	// Upon invoking (e.g. from driver trigger right)...
    	// 1) Spin up the shooter to the calculated speed for this distance from the goal.
    	//    (Note:  Command will not complete until the PID says the wheels are at the target speed)
    	addSequential(new EngageAutoShooter());
    	
        // 2) Advance the ball into the shooter for 1 second
        addParallel (new LoadShooter(-1)); // arg is magnitude (negative here is towards the shooter)
        addSequential (new Delay(1));
        // 3) Foomp.  Ball is gone, so stop the shooter and the loader.
        addParallel (new ShooterStop());
        addSequential (new FeederStop());
        
        // We're done.

    	
 
    } 
}
