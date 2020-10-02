package org.firstinspires.ftc.teamcode.old;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "MechanumDriver (Blocks to Java)", group = "")
public class MechanumDriver extends LinearOpMode {

    private DcMotor arm_mtr;
    private DcMotor clowmoter;
    private DcMotor right_mtr;
    private DcMotor left_mtr;
    private DcMotor front_right_mtr;
    private DcMotor front_left_mtr;
    private Servo right_pinch;
    private Servo wrist;
    private Servo left_pinch;
    private Servo Claw;
    private Servo dumptruck;

    /**
     * This function is executed when this Op Mode is selected from the Driver Station.
     */
    @Override
    public void runOpMode() {
        arm_mtr = hardwareMap.dcMotor.get("arm_mtr");
        clowmoter = hardwareMap.dcMotor.get("clow moter");
        right_mtr = hardwareMap.dcMotor.get("right_mtr");
        left_mtr = hardwareMap.dcMotor.get("left_mtr");
        front_right_mtr = hardwareMap.dcMotor.get("front_right_mtr");
        front_left_mtr = hardwareMap.dcMotor.get("front_left_mtr");
        right_pinch = hardwareMap.servo.get("right_pinch");
        wrist = hardwareMap.servo.get("wrist");
        left_pinch = hardwareMap.servo.get("left_pinch");
        Claw = hardwareMap.servo.get("Claw");
        dumptruck = hardwareMap.servo.get("dumptruck");


        arm_mtr.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        clowmoter.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        right_mtr.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        left_mtr.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        front_right_mtr.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        front_left_mtr.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        right_mtr.setDirection(DcMotorSimple.Direction.REVERSE);
        front_right_mtr.setDirection(DcMotorSimple.Direction.REVERSE);
        arm_mtr.setDirection(DcMotorSimple.Direction.REVERSE);
        clowmoter.setDirection(DcMotorSimple.Direction.FORWARD);
        right_pinch.setDirection(Servo.Direction.REVERSE);
        wrist.setDirection(Servo.Direction.REVERSE);
        right_pinch.setPosition(0);
        left_pinch.setPosition(0);
        wrist.setPosition(0);
        Claw.setPosition(0.6);
        dumptruck.setPosition(0);
        double power = 1.0;
        int dumpPosition = 0;

        waitForStart();
        if (opModeIsActive()) {
            while (opModeIsActive()) {
                if (!gamepad1.dpad_up && !gamepad1.dpad_down && !gamepad1.dpad_left && !gamepad1.dpad_right && !gamepad1.y && !gamepad1.x) {
                    left_mtr.setPower(gamepad1.left_stick_y);
                    right_mtr.setPower(gamepad1.right_stick_y);
                }
                Claw.setPosition(1 - gamepad1.right_trigger);
                arm_mtr.setPower(gamepad2.left_stick_y / 2);
                clowmoter.setPower(gamepad2.right_stick_y / 3);
                wrist.setPosition(gamepad2.left_trigger / 1);
                right_pinch.setPosition(gamepad2.right_trigger);
                left_pinch.setPosition(gamepad2.right_trigger);


                if(gamepad1.a) {
                    power = 1.0;
                }
                if(gamepad1.b) {
                    power = 0.5;
                }
                if(gamepad2.y) {
                    if (dumpPosition == 0) {
                        dumpPosition = 1;
                    } else {
                        dumpPosition = 0;
                    }
                    sleep(250);
                    dumptruck.setPosition(dumpPosition);

                }
                //if(gamepad2.x) {
                //dumptruck.setPosition(0);
                //}



                // forward
                if(gamepad1.dpad_up) {
                    left_mtr.setPower(power);
                    right_mtr.setPower(power);
                    front_left_mtr.setPower(power);
                    front_right_mtr.setPower(power);
                } else if (!gamepad1.dpad_down && !gamepad1.dpad_left && !gamepad1.dpad_right && !gamepad1.y && !gamepad1.x){
                    left_mtr.setPower(0);
                    right_mtr.setPower(0);
                    front_left_mtr.setPower(0);
                    front_right_mtr.setPower(0);

                }

                // back
                if(gamepad1.dpad_down) {
                    left_mtr.setPower(power * -1);
                    right_mtr.setPower(power * -1);
                    front_left_mtr.setPower(power * -1);
                    front_right_mtr.setPower(power * -1);
                } else if (!gamepad1.dpad_up && !gamepad1.dpad_left && !gamepad1.dpad_right && !gamepad1.y && !gamepad1.x){
                    left_mtr.setPower(0);
                    right_mtr.setPower(0);
                    front_left_mtr.setPower(0);
                    front_right_mtr.setPower(0);

                }

                // left
                if(gamepad1.dpad_left) {
                    left_mtr.setPower(power);
                    right_mtr.setPower(power * -1);
                    front_left_mtr.setPower(power * -1);
                    front_right_mtr.setPower(power);
                } else if (!gamepad1.dpad_up && !gamepad1.dpad_down && !gamepad1.dpad_right && !gamepad1.y && !gamepad1.x){
                    left_mtr.setPower(0);
                    right_mtr.setPower(0);
                    front_left_mtr.setPower(0);
                    front_right_mtr.setPower(0);

                }
                // right
                if(gamepad1.dpad_right) {
                    left_mtr.setPower(power * -1);
                    right_mtr.setPower(power);
                    front_left_mtr.setPower(power);
                    front_right_mtr.setPower(power * -1);
                } else if (!gamepad1.dpad_up && !gamepad1.dpad_down && !gamepad1.dpad_left && !gamepad1.y && !gamepad1.x){
                    left_mtr.setPower(0);
                    right_mtr.setPower(0);
                    front_left_mtr.setPower(0);
                    front_right_mtr.setPower(0);

                }

                // clockwise
                if(gamepad1.y) {
                    left_mtr.setPower(power * -1);
                    right_mtr.setPower(power);
                    front_left_mtr.setPower(power * -1);
                    front_right_mtr.setPower(power);
                } else if (!gamepad1.dpad_up && !gamepad1.dpad_down && !gamepad1.dpad_left && !gamepad1.dpad_right && !gamepad1.x){
                    left_mtr.setPower(0);
                    right_mtr.setPower(0);
                    front_left_mtr.setPower(0);
                    front_right_mtr.setPower(0);

                }

                // counterclockwise
                if(gamepad1.x) {
                    left_mtr.setPower(power);
                    right_mtr.setPower(power * -1);
                    front_left_mtr.setPower(power);
                    front_right_mtr.setPower(power * -1);
                } else if (!gamepad1.dpad_up && !gamepad1.dpad_down && !gamepad1.dpad_left && !gamepad1.dpad_right && !gamepad1.y){
                    left_mtr.setPower(0);
                    right_mtr.setPower(0);
                    front_left_mtr.setPower(0);
                    front_right_mtr.setPower(0);

                }
            }
        }
    }
}
