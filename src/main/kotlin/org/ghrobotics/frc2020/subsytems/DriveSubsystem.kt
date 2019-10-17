package org.ghrobotics.frc2020.subsytems

import com.ctre.phoenix.motorcontrol.FeedbackDevice
import org.ghrobotics.frc2020.Constants
import org.ghrobotics.frc2020.commands.TeleopDriveCommand
import org.ghrobotics.lib.commands.FalconSubsystem
import org.ghrobotics.lib.mathematics.units.amp
import org.ghrobotics.lib.mathematics.units.derivedunits.LinearVelocity
import org.ghrobotics.lib.mathematics.units.derivedunits.feetPerSecond
import org.ghrobotics.lib.mathematics.units.millisecond
import org.ghrobotics.lib.motors.ctre.FalconSRX

object DriveSubsystem : FalconSubsystem() { //extends FalconSubsystem base class

    //4 motor variables
    private val leftMaster = FalconSRX(Constants.kDriveLeftMasterId, Constants.kDriveNativeUnitModel)//FalconSRX is motor name
    private val leftSlave = FalconSRX(Constants.kDriveLeftSlaveId, Constants.kDriveNativeUnitModel)
    //get motor ids and native unit conversion

    private val rightMaster = FalconSRX(Constants.kDriveRightMasterId, Constants.kDriveNativeUnitModel)
    private val rightSlave = FalconSRX(Constants.kDriveRightSlaveId, Constants.kDriveNativeUnitModel)


    init{
        //slave motors follow master
        leftSlave.follow(leftMaster)
        rightSlave.follow(rightMaster)


        leftMaster.outputInverted = false
        leftSlave.outputInverted = false

        //invert right side so that it goes straight
        rightMaster.outputInverted = true
        rightSlave.outputInverted = true

        leftMaster.configCurrentLimit(true, FalconSRX.CurrentLimitConfig(
                90.amp,
                500.millisecond,
                38.amp
             )
        )

        rightMaster.configCurrentLimit(true, FalconSRX.CurrentLimitConfig(
                90.amp, //peak current limit
                500.millisecond, //peak current limit duration
                38.amp //continuous current limit

            )
        )



        defaultCommand = TeleopDriveCommand()
        //sets default command to use

    }

    fun set(left: Double, right: Double){
        leftMaster.setDutyCycle(left) //sends it voltage between 0 and 12
        rightMaster.setDutyCycle(right)
    }

}



