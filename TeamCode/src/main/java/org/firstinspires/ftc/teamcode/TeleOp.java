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
            int blue = robot.colorSensor.blue();
            int green = robot.colorSensor.green();
            int red = robot.colorSensor.red();
//            telemetry.addData("blue", blue);
//            telemetry.addData("red",red);
//            telemetry.addData("green",green);
//            telemetry.addLine();
//            telemetry.update();
//            robot.color_number=robot.colorSensor.readUnsignedByte(ModernRoboticsI2cColorSensor.Register.COLOR_NUMBER);


//            int cub[]={0,0,0};
//            int x=0;
//            //1-cub mic(alb)  2-cum mediu(albastru)  3-cub mare(roz)
//            robot.gear_rack.setPosition(0);
//            if(red!=0||green!=0||blue!=0){
//                if(red>blue){robot.gear_rack.setPosition(0.2);cub[x]=3;x++;}
//                else if(blue>red){robot.gear_rack.setPosition(0.6);cub[x]=2;x++;}
//                else {robot.gear_rack.setPosition(1);cub[x]=1;x++;}
//            }
//            telemetry.addData("cub1",cub[0]);
//            telemetry.addData("cub2",cub[1]);
//            telemetry.addData("cub3", cub[2]);
//            telemetry.update();

            //robot.Telemetry_sensor(telemetry);
            //double travel=-gamepad1.left_stick_y;
            //double strafe = gamepad1.left_stick_x*1.1;
            //float turn;
           // turn=gamepad1.right_trigger-gamepad1.left_trigger;
            //robot.mecanum_drive(strafe,travel,turn);

           // robot.angle(gamepad1);
            robot.gear(gamepad1);
            robot.Telemetry_Servos(telemetry);
        }

    }
}
