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

import org.usfirst.frc1735.Stronghold2016.RobotMap;
import org.usfirst.frc1735.Stronghold2016.commands.*;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.networktables.NetworkTable;


/**
 *
 */
public class Vision extends Subsystem {
	NetworkTable table;
	public static final double xRes = 320; // this is the maximum resolution in pixels for the x (horizontal) direction

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

	// create a new constructor
	public Vision() {		
    // Get a pointer to the networkTable.  "StrongholdContours" is the name we entered into the publish box in GRIP
    table = NetworkTable.getTable("GRIP/StrongholdContours");
	}
    


    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

        // Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand());
    }
    
    public double getRawTargetXpos() {
    	double xPos; // return value
    	
    	// 1) Get the current list of targets found.  There might be more than one visible at a time
       	// first, get the vision system data for the target
    	double[] defaultValue = new double[0]; // set up a default value in case the table isn't published yet

    	// Get all needed table items at the same time, in case the table updates between reads.
    	// (could end up with different array sizes)
    	double[] targetX = table.getNumberArray("centerX", defaultValue);
		double[] areas = table.getNumberArray("area", defaultValue);
		if (targetX.length != areas.length) {
			// here the table updated in the middle; we'll have to punt
			System.out.println("NetworkTable udpated in the middle of getRawTargetXpos; returning first valid entry");
	    	if (targetX.length==0)
	    	{
	    		// we didn't find ANY object.  Return a perfectly centered answer so that the system doesn't
	    		// try to adapt
	    		xPos = xRes/2;
	    	}
	    	else xPos = targetX[0];
	    	return xPos;
		}
   	
    	// For initial debug, just print out the table so we can see what's going on
/*
    	System.out.print("centerX: ");
    	for (double xval : targetX) { // for each target found,
    		System.out.print(xval + " ");
    	}
    	System.out.println();
*/    	
    	
    	// 2) Choose the one that has the largest area.  This is PROBABLY the closest target (and most in-line)
    	//    Don't want to choose the one closest to the center because that might actually be the target
    	//    for a different face that's very oblique to our robot position.
		int largestIdx = 0;
    	if (targetX.length > 1) {
    		double largest = 0;
    		for (int c = 0; c < areas.length; c++) {
    			if (areas[c] > largest){
    				largest = areas[c];
    				largestIdx = c;
    			}
    		}
    	}
    	
    	if (targetX.length==0)
    	{
    		// we didn't find ANY object.  Return a perfectly centered answer so that the system doesn't
    		// try to adapt
    		xPos = xRes/2;
    	}
    	else xPos = targetX[largestIdx];
    	
    	return xPos;
    }
    
    public double getScaledTargetXpos() {
    	// get the raw position
    	double raw = getRawTargetXpos();
    	// Scale the resolution
       	// Find out how far (magnitude and direction) off-center that target is (the "error")
    	//    	positions in the NetworkTable are in pixels relative to the camera resolution, with (0,0) being the upper left corner
    	//    	and (resolutionx,resolutiony) being the bottom right.  We would want to convert to a different scale
    	//    	where 0,0 is center, and the extents are -1 and +1 (like a joystick input!)
    	// 	  The equation for this (see "identifying and processing the targets" under Vision on the Screensteps pages)
    	//		Aim = (Pixel - resolution/2)/(resolution/2) for each of x and y direction
    	double scaled = (raw-xRes/2)/(xRes/2);
    	return scaled;
    }
    
    public double getXposErr()
    {
    	double scaledXpos = getScaledTargetXpos();
    	// We want the target to be centered at 0,0.
    	// Error is therefore equal to the xPos value itself.
    	return scaledXpos;
    }
    
}

