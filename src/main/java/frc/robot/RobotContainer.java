// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.ElevatorConstants;
import frc.robot.Constants.OIConstants;
import frc.robot.commands.Elevator;
import frc.robot.commands.PivotElevator;
import frc.robot.commands.SwerveJoystickCmd;
import frc.robot.subsystems.ElevatorSubsystem;
import frc.robot.subsystems.PivotSubsystem;
import frc.robot.subsystems.SwerveSubsystem;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final SwerveSubsystem swerveSubsystem = new SwerveSubsystem();
  private final PivotSubsystem pivotSubsystem = new PivotSubsystem(
    new CANSparkMax(Constants.PivotConstants.pivotMotorId, MotorType.kBrushless), 
    new DigitalInput(Constants.PivotConstants.minHallEffectSensorId),
    new DigitalInput(Constants.PivotConstants.maxHallEffectSensorId));
  private final ElevatorSubsystem elevatorSubsystem = new ElevatorSubsystem(new CANSparkMax(
      ElevatorConstants.elevatorMotorId, MotorType.kBrushless), 
      new DigitalInput(ElevatorConstants.minHallEffectSensorId));

  private final Joystick driverJoystick = new Joystick(OIConstants.kDriverControllerPort);
  private final Joystick operatorJoystick = new Joystick(OIConstants.kOperatorYAxis);

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    swerveSubsystem.setDefaultCommand(new SwerveJoystickCmd(
      swerveSubsystem,
      () -> -driverJoystick.getRawAxis(OIConstants.kDriverYAxis), // -
      () -> driverJoystick.getRawAxis(OIConstants.kDriverXAxis), // +
      () -> driverJoystick.getRawAxis(OIConstants.kDriverRotAxis), // +
      () -> !driverJoystick.getRawButton(OIConstants.kDriverFieldOrientedButtonIdx)));
    
    /* pivotSubsystem.setDefaultCommand(
        new PivotElevator(pivotSubsystem, () -> operatorJoystick.getRawAxis(OIConstants.kOperatorYAxis))); */
    
    elevatorSubsystem.setDefaultCommand(new Elevator(elevatorSubsystem, () -> operatorJoystick.getRawAxis(OIConstants.kOperatorYAxis)));
    // Configure the trigger bindings
    configureBindings();
  }

  /**
   * Use this method to define your trigger->command mappings. Triggers can be created via the
   * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with an arbitrary
   * predicate, or via the named factories in {@link
   * edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for {@link
   * CommandXboxController Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller
   * PS4} controllers or {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight
   * joysticks}.
   */
  private void configureBindings() {
    // Schedule `ExampleCommand` when `exampleCondition` changes to `true`
    new JoystickButton(driverJoystick, 2).onTrue(new InstantCommand(() -> swerveSubsystem.zeroHeading()));

    // Schedule `exampleMethodCommand` when the Xbox controller's B button is pressed,
    // cancelling on release.
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An example command will be run in autonomous
    return null;
  }
}