package org.firstinspires.ftc.teamcode.old;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.hardware.NormalizedColorSensor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.Blinker;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gyroscope;
import com.qualcomm.robotcore.hardware.TouchSensor;
import com.qualcomm.robotcore.hardware.ColorSensor;

@Autonomous(name = "MechBlueStackLine (Blocks to Java)", group = "")

public class MechBlueStackLine  extends LinearOpMode{
    private DcMotor arm_mtr;
    private DcMotor clow_moter;
    private DcMotor left_mtr;
    private DcMotor right_mtr;
    private DcMotor front_left_mtr;
    private DcMotor front_right_mtr;



    private Servo claw;
    private Blinker expansion_Hub_1;
    private Blinker expansion_Hub_2;
    private Servo dumptruck;
    private Gyroscope imu_1;
    private Gyroscope imu;
    private Servo left_pinch;
    private Servo right_pinch;
    private TouchSensor touchfront;
    private TouchSensor touch2;
    //private ColorSensor colorsensor;

    private Servo wrist;

    // todo: write your code here

    @Override
    public void runOpMode() {

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        left_mtr = hardwareMap.dcMotor.get("left_mtr");
        right_mtr = hardwareMap.dcMotor.get("right_mtr");
        front_left_mtr = hardwareMap.dcMotor.get("front_left_mtr");
        front_right_mtr = hardwareMap.dcMotor.get("front_right_mtr");

        clow_moter = hardwareMap.dcMotor.get("clow moter");

        touchfront = hardwareMap.touchSensor.get("touchfront");
        touch2 = hardwareMap.touchSensor.get("touch2");


        // values is a reference to the hsvValues array.
        float[] hsvValues = new float[3];
        final float values[] = hsvValues;

        // bPrevState and bCurrState keep track of the previous and current state of the button
        boolean bPrevState = false;
        boolean bCurrState = false;

        // Get a reference to our sensor object.
        //colorSensor = hardwareMap.get(NormalizedColorSensor.class, "colorsensor");

        //colorsensor = hardwareMap.colorSensor.get("colorsensor");




        right_mtr.setDirection(DcMotorSimple.Direction.REVERSE);
        front_right_mtr.setDirection(DcMotorSimple.Direction.REVERSE);
        right_mtr.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        left_mtr.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        front_right_mtr.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        front_left_mtr.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        clow_moter.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);


        waitForStart();
        if (opModeIsActive()) {

            telemetry.addData("Status", "Started");
            telemetry.update();

            // Put run blocks here.
            left_mtr.setPower(0);
            right_mtr.setPower(0);
            front_left_mtr.setPower(0);
            front_right_mtr.setPower(0);

            // go forward
      /*right_mtr.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
      left_mtr.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
      front_right_mtr.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
      front_left_mtr.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

      right_mtr.setMode(DcMotor.RunMode.RUN_TO_POSITION);
      left_mtr.setMode(DcMotor.RunMode.RUN_TO_POSITION);
      front_right_mtr.setMode(DcMotor.RunMode.RUN_TO_POSITION);
      front_left_mtr.setMode(DcMotor.RunMode.RUN_TO_POSITION);

      right_mtr.setTargetPosition(800);
      left_mtr.setTargetPosition(800);
      front_right_mtr.setTargetPosition(800);
      front_left_mtr.setTargetPosition(800);

      left_mtr.setPower(1);
      right_mtr.setPower(1);
      front_left_mtr.setPower(1);
      front_right_mtr.setPower(1);

      while (opModeIsActive() && right_mtr.isBusy() && left_mtr.isBusy()
      && front_right_mtr.isBusy() && front_left_mtr.isBusy()) {
      }
      sleep(1000);
      left_mtr.setPower(0);
      right_mtr.setPower(0);
      front_left_mtr.setPower(0);
      front_right_mtr.setPower(0);
      // stop going forward


       // go backwards
      right_mtr.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
      left_mtr.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
      front_right_mtr.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
      front_left_mtr.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

      right_mtr.setMode(DcMotor.RunMode.RUN_TO_POSITION);
      left_mtr.setMode(DcMotor.RunMode.RUN_TO_POSITION);
      front_right_mtr.setMode(DcMotor.RunMode.RUN_TO_POSITION);
      front_left_mtr.setMode(DcMotor.RunMode.RUN_TO_POSITION);

      right_mtr.setTargetPosition(-800);
      left_mtr.setTargetPosition(-800);
      front_right_mtr.setTargetPosition(-800);
      front_left_mtr.setTargetPosition(-800);

      left_mtr.setPower(1);
      right_mtr.setPower(1);
      front_left_mtr.setPower(1);
      front_right_mtr.setPower(1);

      while (opModeIsActive() && right_mtr.isBusy() && left_mtr.isBusy()
      && front_right_mtr.isBusy() && front_left_mtr.isBusy()) {
      }
      sleep(1000);
      left_mtr.setPower(0);
      right_mtr.setPower(0);
      front_left_mtr.setPower(0);
      front_right_mtr.setPower(0);
      // stop going back
*/
            // go left
            right_mtr.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            left_mtr.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            front_right_mtr.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            front_left_mtr.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

            right_mtr.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            left_mtr.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            front_right_mtr.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            front_left_mtr.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            right_mtr.setTargetPosition(225);
            left_mtr.setTargetPosition(-225);
            front_right_mtr.setTargetPosition(-200);
            front_left_mtr.setTargetPosition(225);

            // reset to not use ticks initially

            right_mtr.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            left_mtr.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            front_right_mtr.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            front_left_mtr.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

            left_mtr.setPower(-.75);
            right_mtr.setPower(.75);
            front_left_mtr.setPower(.75);
            front_right_mtr.setPower(-.75);


            //while (opModeIsActive() && right_mtr.isBusy() && left_mtr.isBusy()
            //&& front_right_mtr.isBusy() && front_left_mtr.isBusy()) {
            //}
            sleep(1875);
            left_mtr.setPower(0);
            right_mtr.setPower(0);
            front_left_mtr.setPower(0);
            front_right_mtr.setPower(0);
            // stop going left


            //go forward until touching foundation
            telemetry.addData("Status", "Going forward");
            telemetry.update();

            right_mtr.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            left_mtr.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            front_right_mtr.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            front_left_mtr.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

            sleep(500);
            left_mtr.setPower(-.5);
            right_mtr.setPower(-.5);
            front_left_mtr.setPower(-.5);
            front_right_mtr.setPower(-.5);

            ElapsedTime timer = new ElapsedTime();
            timer.reset();

            double duration = 0;

            while (!touchfront.isPressed() && timer.time() < 3) {
                // do nothing
            }

            duration = timer.time();


            left_mtr.setPower(0);
            right_mtr.setPower(0);
            front_left_mtr.setPower(0);
            front_right_mtr.setPower(0);

            sleep(250);
            clow_moter.setPower(-1);
            sleep(100);
            clow_moter.setPower(0);



            // back up with the foundation
            telemetry.addData("Status", "backing up");
            telemetry.update();

            sleep(500);
            left_mtr.setPower(.85);
            right_mtr.setPower(.85);
            front_left_mtr.setPower(.85);
            front_right_mtr.setPower(.85);

            //while (!touch2.isPressed()) {
            // do nothing
            //}
            telemetry.addData("Status", duration);
            telemetry.update();

            sleep((long)(duration * 1000));
            left_mtr.setPower(0);
            right_mtr.setPower(0);
            front_left_mtr.setPower(0);
            front_right_mtr.setPower(0);

            sleep(500);
            clow_moter.setPower(1);
            sleep(100);
            clow_moter.setPower(0);


            // go right
            left_mtr.setPower(1);
            right_mtr.setPower(-1);
            front_left_mtr.setPower(-1);
            front_right_mtr.setPower(1);

            sleep(2750);
            left_mtr.setPower(0);
            right_mtr.setPower(0);
            front_left_mtr.setPower(0);
            front_right_mtr.setPower(0);
            // stop going right

// go forward
            left_mtr.setPower(-1);
            right_mtr.setPower(-1);
            front_left_mtr.setPower(-1);
            front_right_mtr.setPower(-1);



            sleep(1000);
            left_mtr.setPower(0);
            right_mtr.setPower(0);
            front_left_mtr.setPower(0);
            front_right_mtr.setPower(0);
            // stop going forward


            // go right
            left_mtr.setPower(1);
            right_mtr.setPower(-1);
            front_left_mtr.setPower(-1);
            front_right_mtr.setPower(1);

            sleep(850);
            left_mtr.setPower(0);
            right_mtr.setPower(0);
            front_left_mtr.setPower(0);
            front_right_mtr.setPower(0);
            // stop going right





/*
sleep(500);
    left_mtr.setPower(0.75);
      right_mtr.setPower(-0.75);
      front_left_mtr.setPower(-0.75);
      front_right_mtr.setPower(0.75);

        //telemetry.addData("Status", colorsensor.argb());

        telemetry.update();

      /*while (colorsensor.blue() <= 300 && colorsensor.red() != colorsensor.blue()) {
      //while (!touch2.isPressed()) {
        // do nothing
          telemetry.addData("Status", colorsensor.red());
        telemetry.update();

      }*/
 /*     sleep(3000);
          //telemetry.addData("Status", colorsensor.blue());
        telemetry.update();

      left_mtr.setPower(0);
      right_mtr.setPower(0);
      front_left_mtr.setPower(0);
      front_right_mtr.setPower(0);

            sleep(500);

          left_mtr.setPower(-0.75);
      right_mtr.setPower(-0.75);
      front_left_mtr.setPower(-0.75);
      front_right_mtr.setPower(-0.75);

      sleep(1000);

      left_mtr.setPower(0);
      right_mtr.setPower(0);
      front_left_mtr.setPower(0);
      front_right_mtr.setPower(0);

            sleep(500);

          left_mtr.setPower(-1);
      right_mtr.setPower(1);
      front_left_mtr.setPower(1);
      front_right_mtr.setPower(-1);

      sleep(2000);

      left_mtr.setPower(0);
      right_mtr.setPower(0);
      front_left_mtr.setPower(0);
      front_right_mtr.setPower(0);

         // go right
         /*
      right_mtr.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
      left_mtr.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
      front_right_mtr.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
      front_left_mtr.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

      right_mtr.setMode(DcMotor.RunMode.RUN_TO_POSITION);
      left_mtr.setMode(DcMotor.RunMode.RUN_TO_POSITION);
      front_right_mtr.setMode(DcMotor.RunMode.RUN_TO_POSITION);
      front_left_mtr.setMode(DcMotor.RunMode.RUN_TO_POSITION);

      right_mtr.setTargetPosition(800);
      left_mtr.setTargetPosition(-800);
      front_right_mtr.setTargetPosition(-800);
      front_left_mtr.setTargetPosition(800);

      left_mtr.setPower(1);
      right_mtr.setPower(1);
      front_left_mtr.setPower(1);
      front_right_mtr.setPower(1);

      while (opModeIsActive() && right_mtr.isBusy() && left_mtr.isBusy()
      && front_right_mtr.isBusy() && front_left_mtr.isBusy()) {
      }
      sleep(1000);
      left_mtr.setPower(0);
      right_mtr.setPower(0);
      front_left_mtr.setPower(0);
      front_right_mtr.setPower(0);
      // stop going right


         // rotate counterclockwise
      right_mtr.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
      left_mtr.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
      front_right_mtr.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
      front_left_mtr.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

      right_mtr.setMode(DcMotor.RunMode.RUN_TO_POSITION);
      left_mtr.setMode(DcMotor.RunMode.RUN_TO_POSITION);
      front_right_mtr.setMode(DcMotor.RunMode.RUN_TO_POSITION);
      front_left_mtr.setMode(DcMotor.RunMode.RUN_TO_POSITION);

      right_mtr.setTargetPosition(-800);
      left_mtr.setTargetPosition(800);
      front_right_mtr.setTargetPosition(-800);
      front_left_mtr.setTargetPosition(800);

      left_mtr.setPower(1);
      right_mtr.setPower(1);
      front_left_mtr.setPower(1);
      front_right_mtr.setPower(1);

      while (opModeIsActive() && right_mtr.isBusy() && left_mtr.isBusy()
      && front_right_mtr.isBusy() && front_left_mtr.isBusy()) {
      }
      sleep(1000);
      left_mtr.setPower(0);
      right_mtr.setPower(0);
      front_left_mtr.setPower(0);
      front_right_mtr.setPower(0);
      // stop rotate counterclockwise


         // rotate clockwise
      right_mtr.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
      left_mtr.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
      front_right_mtr.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
      front_left_mtr.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

      right_mtr.setMode(DcMotor.RunMode.RUN_TO_POSITION);
      left_mtr.setMode(DcMotor.RunMode.RUN_TO_POSITION);
      front_right_mtr.setMode(DcMotor.RunMode.RUN_TO_POSITION);
      front_left_mtr.setMode(DcMotor.RunMode.RUN_TO_POSITION);

      right_mtr.setTargetPosition(800);
      left_mtr.setTargetPosition(-800);
      front_right_mtr.setTargetPosition(800);
      front_left_mtr.setTargetPosition(-800);

      left_mtr.setPower(1);
      right_mtr.setPower(1);
      front_left_mtr.setPower(1);
      front_right_mtr.setPower(1);

      while (opModeIsActive() && right_mtr.isBusy() && left_mtr.isBusy()
      && front_right_mtr.isBusy() && front_left_mtr.isBusy()) {
      }
      sleep(1000);
      left_mtr.setPower(0);
      right_mtr.setPower(0);
      front_left_mtr.setPower(0);
      front_right_mtr.setPower(0);
      // stop rotate clockwise
*/
        }

    }
}