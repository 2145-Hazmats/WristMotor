// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.StartEndCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotContainer;
import frc.robot.Constants;

public class WristSubsystem extends SubsystemBase {
  
  private final WPI_TalonSRX m_WristSpimer = new WPI_TalonSRX(13);
  private final Encoder m_WristSpimerEncoder = new Encoder(0, 1); 

  /** Creates a new WristSubsystem. */
  public WristSubsystem() {
    m_WristSpimerEncoder.setReverseDirection(true);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putNumber("WristPulses", m_WristSpimerEncoder.getRaw());
    SmartDashboard.putNumber("WristRate", m_WristSpimerEncoder.getRate());
    SmartDashboard.putNumber("WristDistance", m_WristSpimerEncoder.getDistance());
    SmartDashboard.putNumber("WristDistancePerPulse", m_WristSpimerEncoder.getDistancePerPulse());
    SmartDashboard.putBoolean("WristDirection", m_WristSpimerEncoder.getDirection());

    SmartDashboard.putNumber("WristPulsesToAngle",Constants.WristConstants.PulsesToAngle);
    SmartDashboard.putNumber("WristPulsesToAngle2",360/m_WristSpimerEncoder.getDistance());

    m_WristSpimerEncoder.setDistancePerPulse(Constants.WristConstants.PulsesToAngle);
  }

  public void WristTurnMethod (double speed) {
    if (m_WristSpimerEncoder.getDistance() < 0 && speed < 0) {
      m_WristSpimer.set(0.0);
    }
    else if (m_WristSpimerEncoder.getDistance() > 360 && speed > 0) {
      m_WristSpimer.set(0.0);
    }
    else
      m_WristSpimer.set(speed/5);
  }

  public Command wristTurnCommand (double speed){
    return new StartEndCommand(() -> this.WristTurnMethod (speed),() -> this.WristTurnMethod(0.0),this);
  }
}


