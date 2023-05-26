package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;

@Config
@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name="servo_test")
public class servo_test extends LinearOpMode {
    public Servo servo;
    public static double possition = 0;

    @Override
    public void runOpMode() {
            FtcDashboard dashboard = FtcDashboard.getInstance();
            telemetry = dashboard.getTelemetry();
            servo = hardwareMap.get(Servo.class, "angle");

            waitForStart();
            while (opModeIsActive() && !isStopRequested()) {
                servo.setPosition(possition);
            }
    }
}
