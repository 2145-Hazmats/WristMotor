// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.OperatorConstants;
import frc.robot.subsystems.WristSubsystem;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here. Loqanz
 *
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final WristSubsystem m_WristSubsystem = new WristSubsystem();

  // Declare and initialize Xbox controller
  private final CommandXboxController m_driverController =
      new CommandXboxController(OperatorConstants.kDriverControllerPort);


  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the trigger bindings
    configureBindings();

    // Default command for WristSubsystem
    m_WristSubsystem.setDefaultCommand(
      Commands.run(
      () ->
      m_WristSubsystem.WristTurnMethod(m_driverController.getLeftY()), m_WristSubsystem));
  }

  /**
   * Use this method to define your trigger->command mappings. Triggers can be created via the
   * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with an arbitrary
   * predicate, or via the named factories in {@link
   * edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for {@link
   * CommandXboxController Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller
   * PS4} controllers or {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight
   * joysticks}.
   */
  private void configureBindings() {
    // Button controls for WristSubsystem
    m_driverController.a().onTrue(
      Commands.run(() -> m_WristSubsystem.WristToAngleMethod(0.0), m_WristSubsystem).withTimeout(1)
    );
    m_driverController.b().onTrue(
      Commands.run(() -> m_WristSubsystem.WristToAngleMethod(90.0), m_WristSubsystem).withTimeout(1)
    );
    m_driverController.y().onTrue(
      Commands.run(() -> m_WristSubsystem.WristToAngleMethod(180.0), m_WristSubsystem).withTimeout(1)
    );
    m_driverController.x().onTrue(
      Commands.run(() -> m_WristSubsystem.WristToAngleMethod(270.0), m_WristSubsystem).withTimeout(1)
    );
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  /*
  public Command getAutonomousCommand() {
    // An example command will be run in autonomous
  }
  */
}
