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
    public int color_number;
    private ElapsedTime runtime = new ElapsedTime();
    public int cub[] = {0, 0, 0, 0};
    public double gear[] = {0.214, 0.352, 0.617};
    public int pos[] = {0, 0, 0};

    @Override
    public void runOpMode() {
        //angle    ->o sa pun motorul la pozitie de luat cubul/lasat (inca nu stiu care sunt pozitiile)
        FtcDashboard dashboard = FtcDashboard.getInstance();
        telemetry = dashboard.getTelemetry();
        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);

        Trajectory pos1 = drive.trajectoryBuilder(new Pose2d())
                .lineTo(new Vector2d(20, 0))
                .build();
        Trajectory pos2 = drive.trajectoryBuilder(new Pose2d())
                .lineTo(new Vector2d(-20, 0))
                .build();

        waitForStart();
        if (isStopRequested()) return;

                int x=1;
                drive.followTrajectory(pos1);
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

                drive.followTrajectory(pos1);
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

                drive.followTrajectory(pos1);
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




        if(cub[1]!=1) {
            drive.followTrajectory(pos2);//ma duc la pozitia 2
            drive.followTrajectory(pos2);//ma duc la pozitia 1
            //angle
            drive.gear_rack.setPosition(gear[cub[1]]);//iau cubul de pe 1
            //angle
            drive.followTrajectory(pos2);//ma duc la pozitia 0
            //angle
            drive.gear_rack.setPosition(1);//las cubul

            if(cub[2]==1){
                drive.followTrajectory(pos1);//ma duc la pozitia 1
                drive.followTrajectory(pos1);//ma duc la pozitia 2
                //angle
                drive.gear_rack.setPosition(gear[1]);//iau cubul 1
                //angle
                drive.followTrajectory(pos2);//ma duc la pozitia 1
                //angle
                drive.gear_rack.setPosition(1);//dau drumul la cub
                //angle
                drive.followTrajectory(pos2);//ma duc la pozitia 0
                //angle
                drive.gear_rack.setPosition(gear[cub[1]]);//iau cubul 2
                //angle
                drive.followTrajectory(pos1);//ma duc la pozitia 1
                drive.followTrajectory(pos1);//ma duc la pozitia 2
                //angle
                drive.gear_rack.setPosition(1);//dau drumul la cub
                cub[2]=cub[1];//fac switch
                cub[1]=1;
                drive.followTrajectory(pos1);//ma duc la pozitia 3, ca sa fie acelasi loc ca si la cazul urmator
            }
            if(cub[3]==1){
                drive.followTrajectory(pos1);//ma duc la pozitia 1
                drive.followTrajectory(pos1);//ma duc la pozitia 2
                drive.followTrajectory(pos1);//ma duc la pozitia 3
                //angle
                drive.gear_rack.setPosition(gear[1]);//iau cubul 1
                //angle
                drive.followTrajectory(pos2);//ma duc la pozitia 2
                drive.followTrajectory(pos2);//ma duc la pozitia 1
                //angle
                drive.gear_rack.setPosition(1);//las cubul
                //angle
                drive.followTrajectory(pos2);//ma duc la pozitia 0
                //angle
                drive.gear_rack.setPosition(gear[cub[1]]);//iau cubul
                //angle
                drive.followTrajectory(pos1);//ma duc la pozitia 1
                drive.followTrajectory(pos1);//ma duc la pozitia 2
                drive.followTrajectory(pos1);//ma duc la pozitia 3
                //angle
                drive.gear_rack.setPosition(1);//las cubul
                //angle
                cub[3]=cub[1];//fac switch
                cub[1]=1;
            }

        }

        if(cub[2]!=2){
                drive.followTrajectory(pos2);//ma duc la pozitia 2
                //angle
                drive.gear_rack.setPosition(gear[3]);//iau cubul 3 ca e sg posibilitate
                //angle
                drive.followTrajectory(pos2);//ma duc la pozitia 1
                drive.followTrajectory(pos2);//ma duc la pozitia 0
                //angle
                drive.gear_rack.setPosition(1);//las cubul
                //angle
                drive.followTrajectory(pos1);//ma duc la pozitia 1
                drive.followTrajectory(pos1);//ma duc la pozitia 2
                drive.followTrajectory(pos1);//ma duc la pozitia 3
                //angle
                drive.gear_rack.setPosition(gear[2]);//iau cubul 2
                //angle
                drive.followTrajectory(pos2);//ma duc la pozitia 2
                //angle
                drive.gear_rack.setPosition(1);//las cubul
                drive.followTrajectory(pos2);//ma duc la pozitia 1
                drive.followTrajectory(pos2);//ma duc la pozitia 0
                //angle
                drive.gear_rack.setPosition(gear[3]);//iau cubul 3
                drive.followTrajectory(pos1);//ma duc la pozitia 1
                drive.followTrajectory(pos1);//ma duc la pozitia 2
                drive.followTrajectory(pos1);//ma duc la pozitia 3
                //angle
                drive.gear_rack.setPosition(1);//las cubul
                //angle
        }

        time = runtime.seconds();
        while (runtime.seconds()-time<5) {
            telemetry.addData("cub1", cub[0]);
            telemetry.addData("cub2", cub[1]);
            telemetry.addData("cub3", cub[2]);
            telemetry.update();
        }

        }
    }
