// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class IntakeSubsystem extends SubsystemBase {

  private CANSparkMax leftIntakeMotor;
  private CANSparkMax rightIntakeMotor;
  private DoubleSolenoid intakeSolenoid;

  String intakeState;

  /** Creates a new IntakeSubsystem. */
  public IntakeSubsystem(CANSparkMax leftIntakeMotor, CANSparkMax rightintakeMotor, DoubleSolenoid intakeSolenoid) {

    this.intakeSolenoid = intakeSolenoid;
    intakeSolenoid.set(DoubleSolenoid.Value.kOff);

    this.leftIntakeMotor = leftIntakeMotor;
    this.rightIntakeMotor = rightintakeMotor;

  }

  public void runMotors(double speed) {
    leftIntakeMotor.set(speed);
    rightIntakeMotor.set(speed);
  }

  public void stopMotors() {
    leftIntakeMotor.stopMotor();
    rightIntakeMotor.stopMotor();
  }

  public void toggleIntake() {
    if (intakeSolenoid.isFwdSolenoidDisabled()) {
      setIntakeCone();
    }
    intakeSolenoid.toggle();
  }

  public void setIntakeCube() { // Cube
    intakeSolenoid.set(DoubleSolenoid.Value.kReverse);
  }

  public void setIntakeCone() { // Cone
    intakeSolenoid.set(DoubleSolenoid.Value.kForward);
  }

  public void disableIntake() {
    intakeSolenoid.set(DoubleSolenoid.Value.kOff);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run.
    SmartDashboard.putBoolean("IsIntakeCone", isIntakeCone());
  }

  public boolean isIntakeCone() {
    return intakeSolenoid.get().equals(DoubleSolenoid.Value.kForward);
  }
}
