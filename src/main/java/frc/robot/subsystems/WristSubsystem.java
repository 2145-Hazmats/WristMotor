// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class WristSubsystem extends SubsystemBase {
  
  // New Talon motor
  private final WPI_TalonSRX m_WristSpimer = new WPI_TalonSRX(13);
  // New encoder
  private final Encoder m_WristSpimerEncoder = new Encoder(0, 1,true); 

  public WristSubsystem() {
    // Change distance to angle instead of pulses
    // Distance increasing = clockwise
    // Distance decreasing = counter-clockwise
    m_WristSpimerEncoder.setDistancePerPulse(Constants.WristConstants.PulsesToAngle);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putNumber("WristDistance", m_WristSpimerEncoder.getDistance());
    SmartDashboard.putBoolean("WristDirection", m_WristSpimerEncoder.getDirection());
  }

  // Default command for Wristsubsystem
  // Turns wrist motor with parameter speed
  public void WristTurnMethod(double speed) {
    // Set limit at 0 degrees
    if (m_WristSpimerEncoder.getDistance() < 0 && speed > 0) {
      m_WristSpimer.stopMotor();
    }
    // Set limit at 360 degrees
    else if (m_WristSpimerEncoder.getDistance() > 360 && speed < 0) {
      m_WristSpimer.stopMotor();
    }
    // Spin based on user input. Up = clockwise
    // Because this is negative the < and > are swapped
    else
      m_WristSpimer.set(-speed*Constants.WristConstants.WristSpeed);
  }

  // Turns wrist motor to a specific angle parameter then stops
  public void WristToAngleMethod(double angle) {
    // Stop if angle is close enough
    if ((m_WristSpimerEncoder.getDistance() - Math.abs(angle)) <= 5 && (Math.abs(angle) - m_WristSpimerEncoder.getDistance() <= 5)) {
      m_WristSpimer.stopMotor();
    }
    // If it needs to go counter-clockwise... go counter-clockwise
    else if ((m_WristSpimerEncoder.getDistance() - angle) > 0) {
      m_WristSpimer.set(-Constants.WristConstants.WristSpeed);
    }
    // If it needs to go clockwise... go clockwise
    else if ((m_WristSpimerEncoder.getDistance() - angle) < 0) {
      m_WristSpimer.set(Constants.WristConstants.WristSpeed);
    }
  }

}


