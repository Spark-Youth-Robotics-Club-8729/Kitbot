// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.Constants.*;
import frc.robot.commands.Autos;
import frc.robot.commands.LaunchNote;
import frc.robot.commands.PrepareLaunch;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.CANLauncher;
import frc.robot.commands.ArcadeDriveCommand;

// import frc.robot.subsystems.CANLauncher;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems are defined here.
  private final DriveSubsystem DriveSubsystem = new DriveSubsystem();
  private final CANLauncher LauncherSubsystem = new CANLauncher();
  private final IntakeSubsystem IntakeSubsystem = new IntakeSubsystem();
  // private final CANLauncher LauncherSubsystem = new CANLauncher();

  /*The gamepad provided in the KOP shows up like an XBox controller if the mode switch is set to X mode using the
   * switch on the top.*/

  // driver
  private final Joystick driverController =
      new Joystick(OperatorConstants.DRIVER_CONTROLLER_PORT);

  // operator
  private final Joystick operatorController =
      new Joystick(OperatorConstants.OPERATOR_CONTROLLER_PORT);

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the trigger bindings
    configureBindings();
  }

  /**
   * Use this method to define your trigger->command mappings. Triggers can be accessed via the
   * named factory methods in the Command* classes in edu.wpi.first.wpilibj2.command.button (shown
   * below) or via the Trigger constructor for arbitary conditions
   */
  private void configureBindings() {
    // Set the default command for the drivetrain to drive using the joysticks
    DriveSubsystem.setDefaultCommand(
                                new ArcadeDriveCommand(DriveSubsystem,
                                                () -> -driverController.getRawAxis(DriveConstants.DRIVE_AXIS),
                                                (() -> -driverController.getRawAxis(DriveConstants.TURN_AXIS)
                                                                * DriveConstants.TURN_PROPORTION)));

    /*Create an inline sequence to run when the operator presses and holds the A (green) button. Run the PrepareLaunch
     * command for 1 seconds and then run the LaunchNote command */
    new JoystickButton(operatorController, LauncherConstants.LAUNCH_NOTE_BUTTON)
        .whileTrue(
            new PrepareLaunch(LauncherSubsystem)
                .withTimeout(LauncherConstants.LAUNCHER_DELAY)
                .andThen(new LaunchNote(LauncherSubsystem))
                .handleInterrupt(() -> LauncherSubsystem.stop()));

    // Set up a binding to run the intake command while the operator is pressing and holding the
    // left Bumper
    new JoystickButton(operatorController, LauncherConstants.SOURCE_INTAKE_BUTTON)
                                .whileTrue(LauncherSubsystem.launchCommand());

    //// intake bindings for operator controller
    // in
    new JoystickButton(operatorController, IntakeConstants.GROUND_INTAKE_BUTTON)
                                .whileTrue(IntakeSubsystem.groundIntakeCommand(IntakeConstants.INTAKE_SPEED));
    // out
    new JoystickButton(operatorController, IntakeConstants.GROUND_OUTTAKE_BUTTON)
                                .whileTrue(IntakeSubsystem.groundIntakeCommand(IntakeConstants.OUT_TAKE_SPEED));
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An example command will be run in autonomous
    return Autos.exampleAuto(DriveSubsystem);
  }
}