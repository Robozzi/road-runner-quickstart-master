package org.firstinspires.ftc.teamcode;


import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cColorSensor;
import com.acmerobotics.dashboard.FtcDashboard;

import org.firstinspires.ftc.robotcore.external.Telemetry;

@Config
@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name="TeleOP")
public class TeleOp extends LinearOpMode {
        robot robot = new robot();
        public static int unghi;
        public static double pos;
    @Override
    public void runOpMode() {
        FtcDashboard dashboard = FtcDashboard.getInstance();
        Telemetry dashboardTelemetry = dashboard.getTelemetry();
        robot.hwmap(hardwareMap);
        robot.init();
        robot.colorSensor.enableLed(true);

        waitForStart();
        while(opModeIsActive() && !isStopRequested()) {
            unghi = robot.motor_angle;
            pos = robot.gear_pos;

            double travel = gamepad1.left_stick_y;
            double strafe = -gamepad1.left_stick_x;
            double turn = gamepad1.left_trigger-gamepad1.right_trigger;

            robot.angle(gamepad1);
            robot.mecanum_drive(strafe, travel, turn);
            robot.gear(gamepad1);

            robot.angle.setTargetPosition(unghi);
            robot.gear_rack.setPosition(pos);

            robot.Telemetry_Servos(dashboardTelemetry);
        }
    }
}
