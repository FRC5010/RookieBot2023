// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.DoubleSolenoid;

public class IntakeSubsystem extends SubsystemBase {
  CANSparkMax leftIntakeMotor;
  CANSparkMax rightIntakeMotor;
  
  /** Creates a new IntakeSubsystem. */
  public IntakeSubsystem() {
 
    leftIntakeMotor = new CANSparkMax(id, MotorType.kBrushless);
    rightIntakeMotor = new CANSparkMax(id, MotorType.kBrushless);
  }

  public void runMotors(double speed) {
    leftIntakeMotor.set(speed);
    rightIntakeMotor.set(speed);
  }

  public void stopMotors() {
    leftIntakeMotor.stopMotor();
    rightIntakeMotor.stopMotor();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
