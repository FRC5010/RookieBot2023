// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.util.Units;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {

  public static final class ModuleConstants {
    public static final double kWheelDiameterMeters = Units.inchesToMeters(4);
    public static final double kDriveMotorGearRatio = 1 / 6.12;
    public static final double kTurningMotorGearRatio = 1 / 21.42;
    public static final double kDriveEncoderRot2Meter = kDriveMotorGearRatio * Math.PI * kWheelDiameterMeters;
    public static final double kTurningEncoderRot2Rad = kTurningMotorGearRatio * 2 * Math.PI;
    public static final double kDriveEncoderRPM2MeterPerSec = kDriveEncoderRot2Meter / 60;
    public static final double kTurningEncoderRPM2RadPerSEc = kTurningEncoderRot2Rad / 60;
    public static final double kPTurning = 0.5;
  }

  public static final class DriveConstants {
    public static final double kTrackWidth = Units.inchesToMeters(22.5);
    // Distance between right and left wheels
    public static final double kWheelBase = Units.inchesToMeters(27);
    // Distance between front and back wheels
    public static final SwerveDriveKinematics kDriveKinematics = new SwerveDriveKinematics(
        new Translation2d(kWheelBase / 2, -kTrackWidth / 2), //
        new Translation2d(kWheelBase / 2, kTrackWidth / 2), //
        new Translation2d(-kWheelBase / 2, -kTrackWidth / 2), //
        new Translation2d(-kWheelBase / 2, kTrackWidth / 2)); //

    public static final int kFrontLeftDriveMotorPort = 1;
    public static final int kBackLeftDriveMotorPort = 3;
    public static final int kFrontRightDriveMotorPort = 7;
    public static final int kBackRightDriveMotorPort = 5;

    public static final int kFrontLeftTurningMotorPort = 2;
    public static final int kBackLeftTurningMotorPort = 4;
    public static final int kFrontRightTurningMotorPort = 8;
    public static final int kBackRightTurningMotorPort = 6;

    public static final double kPhysicalMaxSpeedMetersPerSecond = 15;
    public static final boolean kFrontLeftDriveEncoderReversed = false;
    public static final boolean kFrontLeftTurningEncoderReversed = false;
    public static final double kFrontLeftDriveAbsoluteEncoderOffsetRad = 0.0;
    public static final boolean kFrontLeftDriveAbsoluteEncoderReversed = false;
    public static final int kFrontLeftDriveAbsoluteEncoderId = 4;
    public static final boolean kFrontRightDriveEncoderReversed = false;
    public static final boolean kFrontRightTurningEncoderReversed = false;
    public static final int kFrontRightDriveAbsoluteEncoderOffsetRad = 0;
    public static final boolean kFrontRightDriveAbsoluteEncoderReversed = false;
    public static final int kFrontRightDriveAbsoluteEncoderId = 0;
    public static final int kBackLeftDriveAbsoluteEncoderId = 0; 
    public static final boolean kBackLeftDriveEncoderReversed = false;
    public static final boolean kBackLeftTurningEncoderReversed = false;
    public static final double kBackLeftDriveAbsoluteEncoderOffsetRad = 0;
    public static final boolean kBackLeftDriveAbsoluteEncoderReversed = false;
    public static final boolean kBackRightDriveEncoderReversed = false;
    public static final boolean kBackRightTurningEncoderReversed = false;
    public static final int kBackRightDriveAbsoluteEncoderId = 0;
    public static final double kBackRightDriveAbsoluteEncoderOffsetRad = 0;
    public static final boolean kBackRightDriveAbsoluteEncoderReversed = false;
    public static final double kTeleDriveMaxSpeedMetersPerSecond = 5;
    public static final double kTeleDriveMaxAngularSpeedRadiansPerSecond = 5;
    public static final double kTeleDriveMaxAccelerationUnitsPerSecond = 5;
    public static final double kTeleDriveMaxAngularAccelerationUnitsPerSecond = 5;
  }

  public static final class OIConstants {
    public static final double kDeadband = 0.08;
    public static final int kDriverControllerPort = 0;
    public static final int kDriverYAxis = 1;
    public static final int kDriverXAxis = 0;
    public static final int kDriverRotAxis = 4;
    public static final int kDriverFieldOrientedButtonIdx = 1;
    public static final int kOperatorControllerPort = 1;
    public static final int kOperatorYAxis = 1;
    public static final int kOperatorYAxis2 = 5;
  }

  public static final class PivotConstants {
    public static final double pivotConversionFactor = 24.242; // Converts rotation of motor to rotation of axel
    public static final int pivotMotorId = 9;
    public static final int minHallEffectSensorId = 1;
    public static final int maxHallEffectSensorId = 8;
  }

  public static final class ElevatorConstants {
    public static final int elevatorMotorId = 11;
    public static final int minHallEffectSensorId = 0;
  }
}
