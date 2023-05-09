package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;


@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name="TeleOP")
public class TeleOp extends LinearOpMode {
        public DcMotor frontLeft = null;
        public DcMotor frontRight = null;
        public DcMotor backLeft = null;
        public DcMotor backRight = null;

        public Servo angle = null;
        public Servo gear_rack = null;

        public float travel = 0;
        double servo_angle = 0;
        double gear_pos = 0;

    @Override
    public void runOpMode() {

        {
        frontLeft = hardwareMap.get(DcMotor.class, "fata stanga");
        frontRight = hardwareMap.get(DcMotor.class, "fata dreapta");
        backLeft = hardwareMap.get(DcMotor.class, "spate stanga");
        backRight = hardwareMap.get(DcMotor.class, "spate dreapta");

        angle = hardwareMap.get(Servo.class, "angle");
        gear_rack = hardwareMap.get(Servo.class, "gear_rack");

        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);

        frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

        {
            double strafe = gamepad1.left_stick_x*1.1;
            float turn;
            turn=gamepad1.right_trigger+gamepad1.left_trigger;
        }
        waitForStart();
        while(opModeIsActive() && !isStopRequested()) {
            travel=-gamepad1.left_stick_y;
            goFoword(travel);
            Telemetry_motors();
        }

    }
    public void Telemetry_motors(){
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
