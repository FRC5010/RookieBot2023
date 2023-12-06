// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxAlternateEncoder;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.PivotConstants;

public class PivotSubsystem extends SubsystemBase {
  private final CANSparkMax pivotMotor;
  private final RelativeEncoder alternatePivotEncoder;
  private final DigitalInput minHallEffectSensor;
  private final DigitalInput maxHallEffectSensor;
  
  public PivotSubsystem(CANSparkMax pivotMotor, DigitalInput minHallEffectSensor, DigitalInput maxHallEffectSensor) {
    this.pivotMotor = pivotMotor;
    this.alternatePivotEncoder = pivotMotor.getAlternateEncoder(SparkMaxAlternateEncoder.Type.kQuadrature, 8192);
    alternatePivotEncoder.setPositionConversionFactor(PivotConstants.pivotConversionFactor);
    alternatePivotEncoder.setInverted(true);
    this.minHallEffectSensor = minHallEffectSensor;
    this.maxHallEffectSensor = maxHallEffectSensor;
  }

  @Override
  public void periodic() {
    SmartDashboard.putBoolean("Pivot Is Safe", ensurePivotSafety());
    SmartDashboard.putBoolean("Pivot At Minimum", isAtMin());
    SmartDashboard.putNumber("Pivot Position", getPivotMotorPosition());
  }
  
  public double getPivotMotorPosition() {
    return alternatePivotEncoder.getPosition();
  }

  public double getPivotMotorSpeed() {
    return pivotMotor.get();
  }

  public void setPivotSpeed(double speed) {
    if (isValidSpeed(speed))
      pivotMotor.set(speed);
  }

  private boolean ensurePivotSafety() {
    if ((isAtMax() && getPivotMotorSpeed() > 0) || (isAtMin() && getPivotMotorSpeed() < 0)) {
      pivotMotor.set(0);
      return false;
    }
    return true;
  }

  public boolean isValidSpeed(double speed) {
    if ((isAtMin() && speed < 0) || (isAtMax() && speed > 0)) {
      return false;
    }
    return true;
  }

  public boolean isAtMin() {
    if (!minHallEffectSensor.get()) {
      alternatePivotEncoder.setPosition(-8.8);
      return true;
    }
    return false;
  }

  public boolean isAtMax() {
    if (getPivotMotorPosition() >= 35) {
      return true;
    }
    return false;
  }

  public double pivotFeedFoward(double angle) {
    return Math.cos(angle) * 1; // Change 1 to constant
  }
}
