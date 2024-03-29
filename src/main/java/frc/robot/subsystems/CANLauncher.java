// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.


package frc.robot.subsystems;


import static frc.robot.Constants.LauncherConstants.*;


import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;


public class CANLauncher extends SubsystemBase {
 CANSparkMax m_launchWheel;
 CANSparkMax m_feedWheel;


 /** Creates a new Launcher. */
 public CANLauncher() {
   m_launchWheel = new CANSparkMax(LAUNCHER_ID, MotorType.kBrushed);
   m_feedWheel = new CANSparkMax(FEEDER_ID, MotorType.kBrushed);


   m_launchWheel.setSmartCurrentLimit(LAUNCHER_CURRENT_LIMIT);
   m_feedWheel.setSmartCurrentLimit(FEED_CURRENT_LIMIT);
  }

  public synchronized void setBothWheels(double speed) {
    m_launchWheel.set(speed);
    m_feedWheel.set(speed);
  }


 
 /**
  * This method is an example of the 'subsystem factory' style of command creation. A method inside
  * the subsytem is created to return an instance of a command. This works for commands that
  * operate on only that subsystem, a similar approach can be done in RobotContainer for commands
  * that need to span subsystems. The Subsystem class has helper methods, such as the startEnd
  * method used here, to create these commands.
  */
 public Command launchCommand() {
   // The startEnd helper method takes a method to call when the command is initialized and one to
   // call when it ends
   return this.startEnd(
       // When the command is initialized, set the wheels to the intake speed values
       () -> {
         setBothWheels(LAUNCH_FEEDER_SPEED);
       },
       // When the command stops, stop the wheels
       () -> {
         stop();
       });
 }


 // An accessor method to set the speed (technically the output percentage) of the launch wheel
 public void setLaunchWheel(double speed) {
   m_launchWheel.set(speed);
 }


 // An accessor method to set the speed (technically the output percentage) of the feed wheel
 public void setFeedWheel(double speed) {
   m_feedWheel.set(speed);
 }


 // A helper method to stop both wheels. You could skip having a method like this and call the
 // individual accessors with speed = 0 instead
 public void stop() {
   setBothWheels(0);
 }
}


