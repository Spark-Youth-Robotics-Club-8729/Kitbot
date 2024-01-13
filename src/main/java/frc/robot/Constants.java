// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
  public static class OperatorConstants {
    // Port numbers for driver and operator gamepads. These correspond with the numbers on the USB
    // tab of the DriverStation
    public static final int DRIVER_CONTROLLER_PORT = 0;
    public static final int OPERATOR_CONTROLLER_PORT = 1;
  }

  public static class DriveConstants {
    // PWM ports/CAN IDs for motor controllers
    public static final int BACK_LEFT = 1;
    public static final int FRONT_LEFT = 2;
    public static final int BACK_RIGHT = 3;
    public static final int FRONT_RIGHT = 4;

    // Current limit for drivetrain motors
    public static final int CURRENT_LIMIT = 60;

    // Drive Controller axis
    public static final int DRIVE_AXIS = 1;
    public static final int TURN_AXIS = 4;
    public static final double TURN_PROPORTION = 0.7;
  }

  public static class LauncherConstants {

    // Operator controller buttons
    public static final int SOURCE_INTAKE_BUTTON = 5;
    public static final int LAUNCH_NOTE_BUTTON = 4;

    // PWM ports/CAN IDs for motor controllers
    public static final int FEEDER_ID = 5;
    public static final int LAUNCHER_ID = 6;

    // Current limit for launcher and feed wheels
    public static final int LAUNCHER_CURRENT_LIMIT = 80;
    public static final int FEED_CURRENT_LIMIT = 80;

    // Speeds for wheels when intaking and launching. Intake speeds are negative to run the wheels
    // in reverse
    public static final double LAUNCHER_SPEED = 1;
    public static final double LAUNCH_FEEDER_SPEED = 1;
    public static final double INTAKE_LAUNCHER_SPEED = -1;
    public static final double INTAKE_FEEDER_SPEED = -.2;

    public static final double LAUNCHER_DELAY = 1;
  }

  public static class IntakeConstants{
    // Operator controller buttons
    public static final int GROUND_INTAKE_BUTTON = 6;
    public static final int GROUND_OUTTAKE_BUTTON = 7;

    public static final int INTAKE_ID = 7;
    public static final int INTAKE_CURRENT_LIMIT = 80;

    public static final double INTAKE_SPEED = 0.8;
    public static final double OUT_TAKE_SPEED = -0.8;
  }
}