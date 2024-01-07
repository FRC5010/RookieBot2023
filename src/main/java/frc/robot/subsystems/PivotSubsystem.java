// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.AbsoluteEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.SparkMaxAbsoluteEncoder.Type;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class PivotSubsystem extends SubsystemBase {
  private final CANSparkMax pivotMotor;
  private final AbsoluteEncoder absolutePivotEncoder;
  private final DigitalInput minHallEffectSensor;
  private final DigitalInput maxHallEffectSensor;
  
  public PivotSubsystem(CANSparkMax pivotMotor, DigitalInput minHallEffectSensor, DigitalInput maxHallEffectSensor) {
    this.pivotMotor = pivotMotor;
    this.absolutePivotEncoder = pivotMotor.getAbsoluteEncoder(Type.kDutyCycle);
    this.minHallEffectSensor = minHallEffectSensor;
    this.maxHallEffectSensor = maxHallEffectSensor;
  }


  
  public double getPivotMotorPosition() {
    return absolutePivotEncoder.getPosition();
  }

  public double getPivotMotorSpeed() {
    return pivotMotor.get();
  }

  public double getPivotMotorCurrent() {
    return pivotMotor.getAppliedOutput();
  }

  public void setPivotSpeed(double speed) {
    if (true)
      pivotMotor.set(speed);
  }

  private boolean ensurePivotSafety() {
    if ((isAtMax() && getPivotMotorSpeed() > 0) || (isAtMin() && getPivotMotorSpeed() < 0)) {
      pivotMotor.set(0);
      return false;
    }
    return true;
  }

  public boolean isAtMin() {
    return !minHallEffectSensor.get();
  }

  public boolean isAtMax() {
    return !maxHallEffectSensor.get();
  }

  @Override
  public void periodic() {
    SmartDashboard.putBoolean("isPivotAtMax", isAtMax());
    SmartDashboard.putBoolean("isPivotAtMin", isAtMin());
    SmartDashboard.putBoolean("IsPivotSafe", ensurePivotSafety());
    SmartDashboard.putNumber("PivotMotorPosition", getPivotMotorPosition());
    SmartDashboard.putNumber("PivotMotorSpeed", getPivotMotorSpeed());
    SmartDashboard.putNumber("PivotMotorCurrent", getPivotMotorCurrent());

  }
}
