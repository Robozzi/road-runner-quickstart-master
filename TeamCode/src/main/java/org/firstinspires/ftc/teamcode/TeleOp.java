package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cColorSensor;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name="TeleOP")
public class TeleOp extends LinearOpMode {
        public DcMotor frontLeft = null;
        public DcMotor frontRight = null;
        public DcMotor backLeft = null;
        public DcMotor backRight = null;

        public Servo angle = null;
        public Servo gear_rack = null;

        public ModernRoboticsI2cColorSensor colorSensor = null;

        public float travel = 0;
        public double servo_angle = 0;
        public double gear_pos = 0;
        public int color_number = 0;

    @Override
    public void runOpMode() {

        {
        frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        frontRight = hardwareMap.get(DcMotor.class, "frontRight");
        backLeft = hardwareMap.get(DcMotor.class, "backLeft");
        backRight = hardwareMap.get(DcMotor.class, "backRight");

        angle = hardwareMap.get(Servo.class, "angle");
        gear_rack = hardwareMap.get(Servo.class, "gear_rack");

        colorSensor = hardwareMap.get(ModernRoboticsI2cColorSensor.class, "color sensor");

        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        frontRight.setDirection(DcMotorSimple.Direction.REVERSE);

        frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        frontLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        frontRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

        colorSensor.enableLed(true);

        waitForStart();
        while(opModeIsActive() && !isStopRequested()) {
            color_number=colorSensor.readUnsignedByte(ModernRoboticsI2cColorSensor.Register.COLOR_NUMBER);
           /* travel=-gamepad1.left_stick_y;
            double strafe = gamepad1.left_stick_x*1.1;
            float turn;
            turn=gamepad1.right_trigger-gamepad1.left_trigger;
            mecanum_drive(strafe,travel,turn);
            servo_angle+=gamepad1.right_stick_y*0.001;
            if(gamepad1.dpad_up)servo_angle+=0.0002;
            if(gamepad1.dpad_down)servo_angle-=0.0002;
            if(servo_angle>=0.8)servo_angle=0.8;
            if(servo_angle<=0)servo_angle=0;
            angle.setPosition(servo_angle);
            gear_pos+=gamepad1.right_stick_x*0.001;
            if(gamepad1.dpad_left)gear_pos-=0.0002;
            if(gamepad1.dpad_right)gear_pos+=0.0002;
            if(gear_pos<=0)gear_pos=0;
            if(gear_pos>=1)gear_pos=1;
            gear_rack.setPosition(gear_pos);
            Telemetry_Servos();*/

        }

    }

    public void Telemetry_sensor(){
        telemetry.addData("color number", color_number);
        telemetry.update();
    }
    public void Telemetry_Servos(){
        telemetry.addData("angle1",servo_angle);
        telemetry.addData("angle", angle.getPosition());
        telemetry.addData("gear_rack", gear_rack.getPosition());
        telemetry.update();
    }
    public void Telemetry_motors(){
        telemetry.addData("travel", travel);
        telemetry.addData("frontleft",frontLeft.getCurrentPosition() );telemetry.addLine();
        telemetry.addData("frontRight",frontRight.getCurrentPosition());telemetry.addLine();
        telemetry.addData("backleft",backLeft.getCurrentPosition());telemetry.addLine();
        telemetry.addData("backRight",backRight.getCurrentPosition());telemetry.addLine();
        telemetry.update();
    }
    public void goFoword(float x){
        frontLeft.setPower(x);
        frontRight.setPower(x);
        backLeft.setPower(x);
        backRight.setPower(x);
    }
    public void mecanum_drive(double x, double y, double rx){
        double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);
        frontLeft.setPower((y + x + rx)/ denominator);
        backLeft.setPower((y - x + rx)/ denominator);
        frontRight.setPower((y - x - rx)/ denominator);
        backRight.setPower((y + x - rx)/ denominator);
    }
}
