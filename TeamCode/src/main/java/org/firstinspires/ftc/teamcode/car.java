package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Config
@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name = "car")
public class car extends LinearOpMode {
    robot robot = new robot();
    double coef = 0.5;
    @Override
    public void runOpMode(){
        robot.init();
        while(opModeIsActive()){
            double throttle = gamepad1.right_trigger-gamepad1.left_trigger;
            double steering = gamepad1.left_stick_x;//pozitiv in stanga
            double denominator = Math.max(Math.abs(throttle) + Math.abs(steering) , 1);
            robot.leftRear.setPower((coef*(throttle+steering))/denominator);
            robot.rightRear.setPower((throttle+steering)/denominator);
            robot.leftFront.setPower((throttle-steering)/denominator);
            robot.rightFront.setPower(coef*(throttle+steering)/denominator);

            double lF = gamepad2.left_trigger;
            double rF = gamepad2.right_trigger;
            double lR = gamepad2.left_stick_y;
            double rR = gamepad2.right_stick_y;
            robot.leftFront.setPower(lF);
            robot.leftRear.setPower(lR);
            robot.rightFront.setPower(rF);
            robot.rightRear.setPower(rR);

        }
    }
}
