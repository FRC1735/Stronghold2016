// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc1735.Stronghold2016;

import org.usfirst.frc1735.Stronghold2016.commands.*;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    //// CREATING BUTTONS
    // One type of button is a joystick button which is any button on a joystick.
    // You create one by telling it which joystick it's on and which button
    // number it is.
    // Joystick stick = new Joystick(port);
    // Button button = new JoystickButton(stick, buttonNumber);

    // There are a few additional built in buttons you can use. Additionally,
    // by subclassing Button you can create custom triggers and bind those to
    // commands the same as any other Button.

    //// TRIGGERING COMMANDS WITH BUTTONS
    // Once you have a button, it's trivial to bind it to a button in one of
    // three ways:

    // Start the command when the button is pressed and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenPressed(new ExampleCommand());

    // Run the command while the button is being held down and interrupt it once
    // the button is released.
    // button.whileHeld(new ExampleCommand());

    // Start the command when the button is released  and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenReleased(new ExampleCommand());


    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    public Joystick joyLeft;
    public JoystickButton shooterEngage;
    public JoystickButton loadForward;
    public JoystickButton loadReverse;
    public Joystick joyRight;
    public JoystickButton feedForward;
    public JoystickButton feedReverse;
    public JoystickButton collectInward;
    public JoystickButton collectOutward;
    public JoystickButton deployClimber;
    public JoystickButton retractClimber;
    public Joystick operator;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    
    // Add a joystick button that is used for doing master sensor overrides
    public JoystickButton sensorOverride;
    
    // Add a joystick button to reverse what is the "front" of the robot
    public JoystickButton reverseDrive;

    public OI() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS

        operator = new Joystick(2);
        
        retractClimber = new JoystickButton(operator, 7);
        retractClimber.whenPressed(new EngageClimbDeployer(-1));
        deployClimber = new JoystickButton(operator, 6);
        deployClimber.whenPressed(new EngageClimbDeployer(1));
        collectOutward = new JoystickButton(operator, 2);
        collectOutward.whileHeld(new EngageCollector(-1));
        collectInward = new JoystickButton(operator, 3);
        collectInward.whileHeld(new EngageCollector(1));
        feedReverse = new JoystickButton(operator, 4);
        feedReverse.whileHeld(new FeedBall(-1));
        feedForward = new JoystickButton(operator, 5);
        feedForward.whileHeld(new FeedBall(1));
        joyRight = new Joystick(1);
        
        loadReverse = new JoystickButton(joyRight, 4);
        loadReverse.whileHeld(new LoadShooter(-1));
        loadForward = new JoystickButton(joyRight, 5);
        loadForward.whileHeld(new LoadShooter(1));
        shooterEngage = new JoystickButton(joyRight, 1);
        shooterEngage.whileHeld(new EngageShooter());
        joyLeft = new Joystick(0);
        


        // SmartDashboard Buttons
        SmartDashboard.putData("AutonomousFwd", new AutonomousFwd());
        SmartDashboard.putData("AutonomousBkw", new AutonomousBkw());
        SmartDashboard.putData("AutonomousVariableFwd", new AutonomousVariableFwd());
        SmartDashboard.putData("AutonomousLowbarShoot", new AutonomousLowbarShoot());
        SmartDashboard.putData("CenterTarget", new CenterTarget());

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        
        // This button sets the master debug variable.
        // It can be dynamically set during robot operation to turn on/off debug messages!
        SmartDashboard.putBoolean("Master Debug Enable", Robot.m_debugOn);
        
        // assign a joystick button to override all sensors (in case of catastrophic sensor failure)
        sensorOverride = new JoystickButton(joyLeft, 2);
        
        // Assign a joystick button to reverse what is the "Front" of the robot (to make driving backwards easier)
        reverseDrive = new JoystickButton(joyLeft, 1);
    }
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=FUNCTIONS
    public Joystick getJoyLeft() {
        return joyLeft;
    }

    public Joystick getJoyRight() {
        return joyRight;
    }

    public Joystick getOperator() {
        return operator;
    }


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=FUNCTIONS
}

