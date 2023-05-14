package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cColorSensor;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name="TeleOP")
public class TeleOp extends LinearOpMode {
        robot robot = new robot();
    @Override
    public void runOpMode() {
        robot.hwmap(hardwareMap);
        robot.init();
        robot.colorSensor.enableLed(true);

        waitForStart();
        while(opModeIsActive() && !isStopRequested()) {
            robot.color_number=robot.colorSensor.readUnsignedByte(ModernRoboticsI2cColorSensor.Register.COLOR_NUMBER);

            double travel=-gamepad1.left_stick_y;
            double strafe = gamepad1.left_stick_x*1.1;
            float turn;
            turn=gamepad1.right_trigger-gamepad1.left_trigger;
            //robot.mecanum_drive(strafe,travel,turn);

            robot.angle(gamepad1);
            robot.gear(gamepad1);
            robot.Telemetry_Servos(telemetry);
        }

    }
}
