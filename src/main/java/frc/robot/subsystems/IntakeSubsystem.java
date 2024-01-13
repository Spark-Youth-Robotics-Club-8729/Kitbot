// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import static frc.robot.Constants.IntakeConstants.*;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class IntakeSubsystem extends SubsystemBase {
  CANSparkMax m_intake;

  /** Creates a new IntakeSubsystem. */
  public IntakeSubsystem() {
    m_intake = new CANSparkMax(kIntakeID, MotorType.kBrushless);
    m_intake.setSmartCurrentLimit(kIntakeCurrentLimit);
  }

  public void setMotor(double speed){
    m_intake.set(speed);
  }

  public void stop(){
    m_intake.set(0);
  }

  // inline command for teleop ground intake
  public Command groundIntakeCommand(double speed) {
    // The startEnd helper method takes a method to call when the command is initialized and one to
    // call when it ends
    return this.startEnd(
        // When the command is initialized, set the wheels to the intake speed values
        () -> {
          setMotor(speed);
        },
        // When the command stops, stop the wheels
        () -> {
          stop();
        });
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
