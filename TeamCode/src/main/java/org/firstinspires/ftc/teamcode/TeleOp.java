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

    public DcMotor angle = null;
    public Servo gear_rack = null;

    //    public int pos = 0;
//   // double travel=gamepad1.left_stick_x;
//    int angle = 0;
//    double gear_pos = 0;
    @Override
    public void runOpMode() throws InterruptedException {


        frontLeft = hardwareMap.get(DcMotor.class, "fata stanga");
        frontRight = hardwareMap.get(DcMotor.class, "fata dreapta");
        backLeft = hardwareMap.get(DcMotor.class, "spate stanga");
        backRight = hardwareMap.get(DcMotor.class, "spate dreapta");

        angle = hardwareMap.get(DcMotor.class, "angle");
        gear_rack = hardwareMap.get(Servo.class, "gear_rack");

        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);

        frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        angle.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        angle.setTargetPosition(0);
        angle.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//        gear_rack.setPosition(0);
        waitForStart();
        while(opModeIsActive() && !isStopRequested()) {goFoword(1);}
        //if(gamepad1.dpad_left)pos++;
        //if(gamepad1.dpad_right)pos--;
//        if(gamepad1.dpad_right)gear_pos+=0.1;
//        if(gamepad1.dpad_left)gear_pos-=0.1;
//        if(gamepad1.dpad_up)angle+=1;
//        if(gamepad1.dpad_down)angle-=1;
        //init.pos(pos);
//        init.strafe(1);
//        telemetry.addData("angle", angle);
//        telemetry.addData("gear_pos", gear_pos);
        // telemetry.addData
        //   telemetry.update();
    }
    public void goFoword(float x){
        frontLeft.setPower(x);
        frontRight.setPower(x);
        backLeft.setPower(x);
        backRight.setPower(x);
    }
}
