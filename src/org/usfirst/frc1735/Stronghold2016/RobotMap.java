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

// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.VictorSP;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    public static Relay visionCameraLightRelay;
    public static SpeedController driveTrainLeftMotor;
    public static SpeedController driveTrainRightMotor;
    public static RobotDrive driveTrainRobotDrive21;
    public static Encoder driveTrainLeftMotorEncoder;
    public static Encoder driveTrainRightMotorEncoder;
    public static SpeedController shooterLeftPIDLeftMotor;
    public static Encoder shooterLeftPIDShootLeftEncoder;
    public static SpeedController shooterRightPIDRightMotor;
    public static Encoder shooterRightPIDShootRightEncoder;
    public static DigitalInput feederBallReady;
    public static SpeedController feederRoller;
    public static SpeedController feederLimboBar;
    public static SpeedController collectorExternalUsesFeederRoller;
    public static SpeedController collectDeployDeployMotor;
    public static SpeedController climberDeployLeft;
    public static SpeedController climberDeployRight;
    public static SpeedController climberWinch;
    public static PowerDistributionPanel pDPPowerDistributionPanel;
    public static AnalogInput rangeRangefinder;
    public static AnalogPotentiometer pIDVisionDrivetrainFakeAnalogPotentiometer1;
    public static SpeedController pIDVisionDrivetrainFakeMotor;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    public static void init() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        visionCameraLightRelay = new Relay(0);
        LiveWindow.addActuator("Vision", "Camera Light Relay", visionCameraLightRelay);
        
        driveTrainLeftMotor = new VictorSP(0);
        LiveWindow.addActuator("DriveTrain", "LeftMotor", (VictorSP) driveTrainLeftMotor);
        
        driveTrainRightMotor = new VictorSP(1);
        LiveWindow.addActuator("DriveTrain", "RightMotor", (VictorSP) driveTrainRightMotor);
        
        driveTrainRobotDrive21 = new RobotDrive(driveTrainLeftMotor, driveTrainRightMotor);
        
        driveTrainRobotDrive21.setSafetyEnabled(true);
        driveTrainRobotDrive21.setExpiration(0.1);
        driveTrainRobotDrive21.setSensitivity(0.5);
        driveTrainRobotDrive21.setMaxOutput(1.0);

        driveTrainLeftMotorEncoder = new Encoder(0, 1, false, EncodingType.k4X);
        LiveWindow.addSensor("DriveTrain", "LeftMotorEncoder", driveTrainLeftMotorEncoder);
        driveTrainLeftMotorEncoder.setDistancePerPulse(0.00533049);
        driveTrainLeftMotorEncoder.setPIDSourceType(PIDSourceType.kDisplacement);
        driveTrainRightMotorEncoder = new Encoder(2, 3, false, EncodingType.k4X);
        LiveWindow.addSensor("DriveTrain", "RightMotorEncoder", driveTrainRightMotorEncoder);
        driveTrainRightMotorEncoder.setDistancePerPulse(0.00533049);
        driveTrainRightMotorEncoder.setPIDSourceType(PIDSourceType.kDisplacement);
        shooterLeftPIDLeftMotor = new VictorSP(2);
        LiveWindow.addActuator("ShooterLeftPID", "Left Motor", (VictorSP) shooterLeftPIDLeftMotor);
        
        shooterLeftPIDShootLeftEncoder = new Encoder(5, 6, false, EncodingType.k2X);
        LiveWindow.addSensor("ShooterLeftPID", "ShootLeftEncoder", shooterLeftPIDShootLeftEncoder);
        shooterLeftPIDShootLeftEncoder.setDistancePerPulse(1.0);
        shooterLeftPIDShootLeftEncoder.setPIDSourceType(PIDSourceType.kRate);
        shooterRightPIDRightMotor = new VictorSP(3);
        LiveWindow.addActuator("ShooterRightPID", "Right Motor", (VictorSP) shooterRightPIDRightMotor);
        
        shooterRightPIDShootRightEncoder = new Encoder(7, 8, false, EncodingType.k2X);
        LiveWindow.addSensor("ShooterRightPID", "ShootRightEncoder", shooterRightPIDShootRightEncoder);
        shooterRightPIDShootRightEncoder.setDistancePerPulse(1.0);
        shooterRightPIDShootRightEncoder.setPIDSourceType(PIDSourceType.kRate);
        feederBallReady = new DigitalInput(4);
        LiveWindow.addSensor("Feeder", "Ball Ready", feederBallReady);
        
        feederRoller = new Talon(4);
        LiveWindow.addActuator("Feeder", "Roller", (Talon) feederRoller);
        
        feederLimboBar = new Talon(5);
        LiveWindow.addActuator("Feeder", "Limbo Bar", (Talon) feederLimboBar);
        
        collectorExternalUsesFeederRoller = new Talon(10);
        LiveWindow.addActuator("Collector", "External Uses Feeder Roller", (Talon) collectorExternalUsesFeederRoller);
        
        collectDeployDeployMotor = new Talon(6);
        LiveWindow.addActuator("CollectDeploy", "DeployMotor", (Talon) collectDeployDeployMotor);
        
        climberDeployLeft = new Talon(7);
        LiveWindow.addActuator("Climber", "DeployLeft", (Talon) climberDeployLeft);
        
        climberDeployRight = new Talon(8);
        LiveWindow.addActuator("Climber", "DeployRight", (Talon) climberDeployRight);
        
        climberWinch = new Talon(9);
        LiveWindow.addActuator("Climber", "Winch", (Talon) climberWinch);
        
        pDPPowerDistributionPanel = new PowerDistributionPanel(0);
        LiveWindow.addSensor("PDP", "PowerDistributionPanel", pDPPowerDistributionPanel);
        
        rangeRangefinder = new AnalogInput(0);
        LiveWindow.addSensor("Range", "Rangefinder", rangeRangefinder);
        
        pIDVisionDrivetrainFakeAnalogPotentiometer1 = new AnalogPotentiometer(1, 1.0, 0.0);
        LiveWindow.addSensor("PID Vision Drivetrain", "Fake Analog Potentiometer 1", pIDVisionDrivetrainFakeAnalogPotentiometer1);
        
        pIDVisionDrivetrainFakeMotor = new Talon(19);
        LiveWindow.addActuator("PID Vision Drivetrain", "Fake Motor", (Talon) pIDVisionDrivetrainFakeMotor);
        

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
    }
}
