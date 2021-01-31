package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Blinker;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gyroscope;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;

import java.util.List;

@Autonomous(name = "RedAuto", group = "")

public class RedAutonomous extends LinearOpMode{
    private static final String TFOD_MODEL_ASSET = "UltimateGoal.tflite";
    private static final String LABEL_FIRST_ELEMENT = "Quad";
    private static final String LABEL_SECOND_ELEMENT = "Single";
    private VuforiaLocalizer vuforia;

    private TFObjectDetector tfod;

    private static final String VUFORIA_KEY = "";
    private DcMotor arm_mtr;
    private DcMotor clow_moter;
    private DcMotor left_mtr;
    private DcMotor right_mtr;
    private DcMotor front_left_mtr;
    private DcMotor front_right_mtr;
    private DcMotor Conveyor;
    private Servo Claw;
    private Blinker expansion_Hub_1;
    private Blinker expansion_Hub_2;
    private Servo dumptruck;
    private Gyroscope imu_1;
    private Gyroscope imu;
    private Servo left_pinch;
    private Servo right_pinch;
    private TouchSensor touchfront;
    private TouchSensor touch2;
    private DcMotor Shooter;
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
        Shooter = hardwareMap.dcMotor.get("Shooter");
        Conveyor = hardwareMap.dcMotor.get("Conveyor");
        clow_moter = hardwareMap.dcMotor.get("clow moter");
        Claw = hardwareMap.servo.get("Claw");

        touchfront = hardwareMap.touchSensor.get("touchfront");
        touch2 = hardwareMap.touchSensor.get("touch2");


        // values is a reference to the hsvValues array.
        float[] hsvValues = new float[3];
        final float values[] = hsvValues;

        // bPrevState and bCurrState keep track of the previous and current state of the button
        boolean bPrevState = false;
        boolean bCurrState = false;

        String mode="";

        Claw.setPosition(0);

        right_mtr.setDirection(DcMotorSimple.Direction.REVERSE);
        right_mtr.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        left_mtr.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        front_right_mtr.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        front_left_mtr.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        clow_moter.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        right_mtr.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        left_mtr.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        front_right_mtr.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        front_left_mtr.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);


        initVuforia();
        initTfod();

        /**
         * Activate TensorFlow Object Detection before we wait for the start command.
         * Do it here so that the Camera Stream window will have the TensorFlow annotations visible.
         **/
        if (tfod != null) {
            tfod.activate();

            // The TensorFlow software will scale the input images from the camera to a lower resolution.
            // This can result in lower detection accuracy at longer distances (> 55cm or 22").
            // If your target is at distance greater than 50 cm (20") you can adjust the magnification value
            // to artificially zoom in to the center of image.  For best results, the "aspectRatio" argument
            // should be set to the value of the images used to create the TensorFlow Object Detection model
            // (typically 1.78 or 16/9).

            // Uncomment the following line if you want to adjust the magnification and/or the aspect ratio of the input images.
            //tfod.setZoom(2.5, 1.78);
        }

        /** Wait for the game to begin */
        telemetry.addData(">", "Press Play to start op mode");
        telemetry.update();

        waitForStart();
        if (opModeIsActive()) {

            halt();
            sleep(1000);
            turnRight(0.5);
            haltSequence(450);
            goForward(.5);
            haltSequence(150);
            turnLeft(.5);
            haltSequence(450);
            goForward(0.4);
            sleep(450);
            halt();
            sleep(1000);

            telemetry.addData("Status", "Started");
            telemetry.update();
            if (tfod != null) {
                // getUpdatedRecognitions() will return null if no new information is available since
                // the last time that call was made.
                List<Recognition> updatedRecognitions = tfod.getUpdatedRecognitions();
                if (updatedRecognitions != null) {
                    telemetry.addData("# Object Detected", updatedRecognitions.size());
                    if (updatedRecognitions.size() == 0) {
                        mode = "A";
                    }
                    // step through the list of recognitions and display boundary info.
                    int i = 0;
                    for (Recognition recognition : updatedRecognitions) {
                        telemetry.addData(String.format("label (%d)", i), recognition.getLabel());
                        if (recognition.getLabel() =="Quad"){
                            mode = "C";
                        } else {
                            mode = "B";
                        }

                        telemetry.addData(String.format("  left,top (%d)", i), "%.03f , %.03f",
                                recognition.getLeft(), recognition.getTop());
                        telemetry.addData(String.format("  right,bottom (%d)", i), "%.03f , %.03f",
                                recognition.getRight(), recognition.getBottom());


                    }
                    //telemetry.addData(String.format("Mode (%s)", mode), "");

                    telemetry.update();
                    sleep(2000);
                }
            }

        }

        // go somewhere based on mode
            if (mode == "A") {
                doModeA();
            } else if (mode == "B") {
                doModeB();
            } else if (mode == "C") {
                doModeC();
            } else {
                // not sure what to do here
            }
        if (tfod != null) {
             tfod.shutdown();

        }


    }
    private void doModeA() {
        turnRight(.5);
        haltSequence(250);
        goForward(.5);
        haltSequence(800);
        turnLeft(0.5);
        haltSequence(250);
        goForward(.5);
        haltSequence(650);
        clawSequence();                     //claw is here
        goBackwards(.5);
        haltSequence(150);
        turnLeft(.5);
        haltSequence(450);
        goForward(.5);
        haltSequence(350);
        turnRight(.5);
        haltSequence(450);
        shootSequence();                    //shoot is here
        parkOnLine();
}
    private void doModeB()  {
        turnRight(.5);
        haltSequence(250);
        goForward(.5);
        haltSequence(800);
        turnLeft(0.5);
        haltSequence(250);
        goForward(.5);
        haltSequence(800);
        turnLeft(0.5);
        haltSequence(250);
        goForward(0.5);
        haltSequence(350);
        clawSequence();                     //claw is here
        goBackwards(.5);
        haltSequence(250);
        turnLeft(.5);
        haltSequence(250);
        goForward(.5);
        haltSequence(300);
        turnRight(.5);
        haltSequence(450);
        goBackwards(.5);
        haltSequence(300);
        shootSequence();                    //shoot is here
        parkOnLine();
    }
    private void doModeC() {
        turnRight(.5);
        haltSequence(250);
        goForward(.5);
        haltSequence(800);
        turnLeft(0.5);
        haltSequence(250);
        goForward(.5);
        haltSequence(1400);
        clawSequence();                     //claw is here
        goBackwards(.5);
        haltSequence(900);
        turnLeft(.5);
        haltSequence(450);
        goForward(.5);
        haltSequence(400);
        turnRight(.5);
        haltSequence(450);
        shootSequence();                    //shoot is here
        parkOnLine();
    }



    private void strafeRight(double power) {
        left_mtr.setPower(power * -1);
        right_mtr.setPower(power);
        front_left_mtr.setPower(power);
        front_right_mtr.setPower(power * -1);
        }

    private void turnLeft(double power) {
        left_mtr.setPower(power * -1);
        right_mtr.setPower(power * 1);
        front_left_mtr.setPower(power * -1);
        front_right_mtr.setPower(power * 1);
    }

    private void strafeLeft(double power) {
        left_mtr.setPower(power);
        right_mtr.setPower(power * -1);
        front_left_mtr.setPower(power * -1);
        front_right_mtr.setPower(power);
    }

    private void turnRight(double power) {
        left_mtr.setPower(power * 1);
        right_mtr.setPower(power * -1);
        front_left_mtr.setPower(power * 1);
        front_right_mtr.setPower(power * -1);
    }

    private void goForward(double power) {
        left_mtr.setPower(power);
        right_mtr.setPower(power);
        front_left_mtr.setPower(power);
        front_right_mtr.setPower(power);
    }

    private void goBackwards(double power) {
        left_mtr.setPower(power * -1);
        right_mtr.setPower(power * -1);
        front_left_mtr.setPower(power * -1);
        front_right_mtr.setPower(power * -1);
    }
    private void halt() {
        left_mtr.setPower(0);
        right_mtr.setPower(0);
        front_right_mtr.setPower(0);
        front_left_mtr.setPower(0);
    }
    private void shootSequence() {
        Shooter.setPower(-1);
        sleep(4000);
        Conveyor.setPower(-1);
        sleep(10000);
        Shooter.setPower(0);
        Conveyor.setPower(0);
        sleep(1000);
    }
    private void clawSequence() {
        Claw.setPosition(1);
        sleep(2000);
    }
    private void haltSequence(long duration) {
        sleep(duration);
        halt();
        sleep(500);
    }
    private void parkOnLine() {
        goForward(.5);
        haltSequence(200);
    }


    /**
     * Initialize the Vuforia localization engine.
     */
    private void initVuforia() {
        /*
         * Configure Vuforia by creating a Parameter object, and passing it to the Vuforia engine.
         */
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters();

        parameters.vuforiaLicenseKey = VUFORIA_KEY;
        parameters.cameraDirection = VuforiaLocalizer.CameraDirection.BACK;

        //  Instantiate the Vuforia engine
        vuforia = ClassFactory.getInstance().createVuforia(parameters);

        // Loading trackables is not necessary for the TensorFlow Object Detection engine.
    }

    /**
     * Initialize the TensorFlow Object Detection engine.
     */
    private void initTfod() {
        int tfodMonitorViewId = hardwareMap.appContext.getResources().getIdentifier(
                "tfodMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        TFObjectDetector.Parameters tfodParameters = new TFObjectDetector.Parameters(tfodMonitorViewId);
        tfodParameters.minResultConfidence = 0.8f;
        tfod = ClassFactory.getInstance().createTFObjectDetector(tfodParameters, vuforia);
        tfod.loadModelFromAsset(TFOD_MODEL_ASSET, LABEL_FIRST_ELEMENT, LABEL_SECOND_ELEMENT);
    }
}