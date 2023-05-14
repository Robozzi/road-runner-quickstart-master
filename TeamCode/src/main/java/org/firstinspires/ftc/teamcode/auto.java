package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cColorSensor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.acmerobotics.dashboard.FtcDashboard;

import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
@Config
@com.qualcomm.robotcore.eventloop.opmode.Autonomous(name="Auto")
public class auto extends LinearOpMode {
    public static int x=1;
    @Override
    public void runOpMode() {
        FtcDashboard dashboard = FtcDashboard.getInstance();
        telemetry = dashboard.getTelemetry();
        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);

        Trajectory myTrajectory = drive.trajectoryBuilder(new Pose2d())
                .forward(60)
                .build();
        Trajectory cub1 = drive.trajectoryBuilder(new Pose2d())
                .lineTo(new Vector2d(x,0))
                        .build();
        waitForStart();

        if(isStopRequested()) return;
            telemetry.addData("possition",x);
            telemetry.update();
            drive.followTrajectory(cub1);

    }
}