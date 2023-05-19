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
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
@Config
@com.qualcomm.robotcore.eventloop.opmode.Autonomous(name="Auto")
public class auto extends LinearOpMode {
    public int color_number ;
    private ElapsedTime runtime = new ElapsedTime();
    public int cub[]={0,0,0,0};
    public double gear[]={0.214,0.352,0.617};
    public int pos[]={0,0,0};
    @Override
    public void runOpMode() {
        FtcDashboard dashboard = FtcDashboard.getInstance();
        telemetry = dashboard.getTelemetry();
        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);



        Trajectory pos0 = drive.trajectoryBuilder(new Pose2d())
               .lineTo(new Vector2d(0,0))
                .build();
        Trajectory pos1 = drive.trajectoryBuilder(new Pose2d())
                .lineTo(new Vector2d(10,0))
                        .build();
        Trajectory pos2 = drive.trajectoryBuilder(new Pose2d())
                        .lineTo(new Vector2d(20, 0))
                                .build();
        Trajectory pos3 = drive.trajectoryBuilder(new Pose2d())
                        .lineTo(new Vector2d(30,0))
                                .build();

        waitForStart();
        if(isStopRequested()) return;


                int x=1;
                double time =runtime.seconds();
                while (runtime.seconds()-time<2)
                {
                    int blue = drive.colorSensor.blue();
                    int green = drive.colorSensor.green();
                    int red = drive.colorSensor.red();
                    if(red!=0||green!=0||blue!=0){
                        if(red>blue){cub[x]=2;}
                        else if(blue>red){cub[x]=1;}
                        else {cub[x]=0;}
                    }
                }
                drive.gear_rack.setPosition(gear[cub[x]]);
                x++;

                time =runtime.seconds();
                while (runtime.seconds()-time<5) {
                    int blue = drive.colorSensor.blue();
                    int green = drive.colorSensor.green();
                    int red = drive.colorSensor.red();
                    if (red != 0 || green != 0 || blue != 0) {
                        if (red > blue) {
                            cub[x] = 2;
                        } else if (blue > red) {
                            cub[x] = 1;
                        } else {
                            cub[x] = 0;
                        }
                    }
                }
                drive.gear_rack.setPosition(gear[cub[x]]);
                x++;

                time =runtime.seconds();
                while (runtime.seconds()-time<5) {
                    int blue = drive.colorSensor.blue();
                    int green = drive.colorSensor.green();
                    int red = drive.colorSensor.red();
                    if (red != 0 || green != 0 || blue != 0) {
                        if (red > blue) {
                            cub[x] = 2;
                        } else if (blue > red) {
                            cub[x] = 1;
                        } else {
                            cub[x] = 0;
                        }
                    }
                }
                drive.gear_rack.setPosition(gear[cub[x]]);



        time = runtime.seconds();
        while (runtime.seconds()-time<5) {
            telemetry.addData("cub1", cub[0]);
            telemetry.addData("cub2", cub[1]);
            telemetry.addData("cub3", cub[2]);
            telemetry.update();
        }

        }


}