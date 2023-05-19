package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import org.firstinspires.ftc.robotcore.external.Telemetry;

public class robot {
    //public Gamepad gamepad1 = new Gamepad();
    public DcMotorEx leftFront,rightFront,leftRear,rightRear, angle;
    public Servo  gear_rack;
    public ModernRoboticsI2cColorSensor colorSensor ;
    public int servo_angle = 0;
    public double gear_pos = 0;
    public int color_number = 0;
    public double gear_pos_vec[]={};

    public void hwmap (HardwareMap hardwaremap){
        leftFront = hardwaremap.get(DcMotorEx.class, "frontLeft");
        rightFront = hardwaremap.get(DcMotorEx.class, "frontRight");
        leftRear = hardwaremap.get(DcMotorEx.class, "backLeft");
        rightRear = hardwaremap.get(DcMotorEx.class, "backRight");
        angle = hardwaremap.get(DcMotorEx.class, "angle");
        gear_rack = hardwaremap.get(Servo.class, "gear_rack");
        colorSensor = hardwaremap.get(ModernRoboticsI2cColorSensor.class, "color sensor");
    }
    public void init(){
        leftFront.setDirection(DcMotorSimple.Direction.REVERSE);
        rightFront.setDirection(DcMotorSimple.Direction.REVERSE);

        leftFront.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        rightFront.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        leftRear.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        rightRear.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        angle.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        leftFront.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);
        rightFront.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);
        leftRear.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);
        rightRear.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);
        angle.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }
    public void gear(Gamepad gamepad1){
        gear_pos+=gamepad1.right_stick_x*0.002;
        if(gamepad1.dpad_left)gear_pos-=0.0002;
        if(gamepad1.dpad_right)gear_pos+=0.0002;
        if(gear_pos<=0)gear_pos=0;
        if(gear_pos>=1)gear_pos=1;
        gear_rack.setPosition(gear_pos);
    }
    public void angle(Gamepad gamepad1){
        servo_angle+=gamepad1.right_stick_y*0.001;
        if(gamepad1.dpad_up)servo_angle+=0.0002;
        if(gamepad1.dpad_down)servo_angle-=0.0002;
        //if(servo_angle>=0.8)servo_angle=0.8;
        //if(servo_angle<=0)servo_angle=0;
        angle.setTargetPosition(servo_angle);
    }
    public void Telemetry_sensor(Telemetry telemetry){
        telemetry.addData("color number", color_number);
        telemetry.update();
    }
    public void Telemetry_Servos(Telemetry telemetry){
        telemetry.addData("angle1",servo_angle);
        telemetry.addData("angle", angle.getCurrentPosition());
        telemetry.addData("gear_rack", gear_rack.getPosition());
        telemetry.update();
    }
    public void Telemetry_motors(double travel, Telemetry telemetry){
        telemetry.addData("travel", travel);
        telemetry.addData("frontleft",leftFront.getCurrentPosition() );telemetry.addLine();
        telemetry.addData("frontRight",rightFront.getCurrentPosition());telemetry.addLine();
        telemetry.addData("backleft",leftRear.getCurrentPosition());telemetry.addLine();
        telemetry.addData("backRight",rightRear.getCurrentPosition());telemetry.addLine();
        telemetry.update();
    }
    public void goFoword(float x){
        leftRear.setPower(x);
        rightRear.setPower(x);
        leftFront.setPower(x);
        rightFront.setPower(x);
    }
    public void mecanum_drive(double x, double y, double rx){
        double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);
        leftFront.setPower((y + x + rx)/ denominator);
        leftRear.setPower((y - x + rx)/ denominator);
        rightFront.setPower((y - x - rx)/ denominator);
        rightRear.setPower((y + x - rx)/ denominator);
    }
}
