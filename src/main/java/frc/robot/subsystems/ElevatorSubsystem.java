// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxAlternateEncoder;

import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ElevatorSubsystem extends SubsystemBase {
  private final CANSparkMax elevatorMotor;
  private final RelativeEncoder alternateElevatorEncoder;
  private final DigitalInput minHallEffectSensor;

  /** Creates a new ElevatorSubsystem. */
  public ElevatorSubsystem(CANSparkMax elevatorMotor, DigitalInput minHallEffectSensor) {
    this.elevatorMotor = elevatorMotor;
    this.alternateElevatorEncoder = elevatorMotor.getAlternateEncoder(SparkMaxAlternateEncoder.Type.kQuadrature, 8192);
    alternateElevatorEncoder.setPositionConversionFactor(Units.inchesToMeters(15.45));
    this.minHallEffectSensor = minHallEffectSensor;
  }

  public double getElevatorMotorPosition() {
    return alternateElevatorEncoder.getPosition();
  }

  public void zeroElevatorMotorEncoder() {
    alternateElevatorEncoder.setPosition(0);
  }

  public double getElevatorMotorSpeed() {
    return elevatorMotor.get();
  }

  public void setElevatorMotorSpeed(double speed) {
    if (isValidSpeed(speed)) {
      elevatorMotor.set(speed);
    } else {
      elevatorMotor.set(0);
    }
  }

  public boolean ensureElevatorSafety() {
    if (elevatorAtMin() && getElevatorMotorSpeed() < 0) {
      elevatorMotor.set(0);
      zeroElevatorMotorEncoder();
      return false;
    } else if (elevatorAtMax() && getElevatorMotorSpeed() > 0) {
      elevatorMotor.set(0);
      return false;
    }
    return true;
  }

  public boolean isValidSpeed(double speed) {
    if ((elevatorAtMin() && speed < 0) || (elevatorAtMax() && speed > 0)) {
      return false;
    }
    return true;
  }

  public boolean elevatorAtMin() {
    return !minHallEffectSensor.get();
  }

  public boolean elevatorAtMax() {
    if (getElevatorMotorPosition() >= 1.1) {
      return true;
    }
    return false;
  }

  public double feedFoward(double angle) {
    return Math.sin(angle) * 1; // Change 1 to actual contant value
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putBoolean("Minimum HallEffect Sensor", elevatorAtMin());
    SmartDashboard.putNumber("Elevator Motor Position", getElevatorMotorPosition());
    SmartDashboard.putNumber("Elevator Motor Speed", getElevatorMotorSpeed());
    SmartDashboard.putBoolean("Elevator Is At Max", elevatorAtMax());
    SmartDashboard.putBoolean("Elevator Is Safe", ensureElevatorSafety());
  }
}
