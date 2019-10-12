package org.ghrobotics.frc2020.commands

import edu.wpi.first.wpilibj.GenericHID
import edu.wpi.first.wpilibj.experimental.command.SendableCommandBase
import org.ghrobotics.frc2020.Controls
import org.ghrobotics.frc2020.subsytems.DriveSubsystem
import org.ghrobotics.lib.wrappers.hid.getX
import org.ghrobotics.lib.wrappers.hid.getY

class TeleopDriveCommand : SendableCommandBase() { //class extends base class

    init{
        //other drive classes can't use drive subsystem

        addRequirements(DriveSubsystem)
    }

    //get values from xbox controller
    val leftJoystick = Controls.driverController.getY(GenericHID.Hand.kLeft)
    val rightJoystick = Controls.driverController.getY(GenericHID.Hand.kRight)



    override fun execute() {


        DriveSubsystem.set(-leftJoystick(), -rightJoystick())
    }

    override fun isFinished(): Boolean {
        return false
    }


}