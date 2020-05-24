/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto2daparte.formularios.controladores;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author selitzia
 */
public class VentanapreloaderController implements Initializable {

    @FXML
    private Circle circleA;
    @FXML
    private Circle circleB;
    @FXML
    private Circle circleC;
    @FXML
    private Label loading;
    @FXML
    private BorderPane bpPreload;

    private double opacity = 0;
    private static BorderPane bpPreloader;

    public static BorderPane getPreload() {
        return VentanapreloaderController.bpPreloader;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        new PantallaSplash().start();
        VentanapreloaderController.bpPreloader = this.bpPreload;
        setRotate(circleA, true, 360, 10);
        setRotate(circleB, true, 180, 18);
        setRotate(circleC, true, 360, 15);
        setCircleMove(circleA, 1, -50);
        setCircleMove(circleB, 1, 50);
        setCircleMove(circleC, 1, -50);
        setMove(loading, 1, -50);
        setText(loading);
        opacityChange();
    }

    private void setText(Label label) {
        label.setText("Loading.");
        Timeline tm = new Timeline();
        tm.getKeyFrames().add(
                new KeyFrame(Duration.millis(1000), (ActionEvent actionEvent) -> {
                    switch (label.getText()) {
                        case "Loading.":
                            label.setText("Loading..");
                            break;
                        case "Loading..":
                            label.setText("Loading...");
                            break;
                        case "Loading...":
                            label.setText("Loading.");
                            break;
                        default:
                            break;
                    }
                }));
        tm.setCycleCount(10);
        tm.play();
    }

    private void setRotate(Circle circle, boolean reverse, int angle, int duration) {
        RotateTransition rotacion = new RotateTransition(Duration.seconds(duration), circle);
        rotacion.setAutoReverse(reverse);
        rotacion.setByAngle(angle);
        rotacion.setDelay(Duration.seconds(0));
        rotacion.setRate(3);
        rotacion.setCycleCount(18);
        rotacion.play();
    }

    private void setCircleMove(Circle circle, int duration, int distance) {
        TranslateTransition move = new TranslateTransition(Duration.seconds(duration), circle);
        move.setNode(circle);
//        double ub = circle.getLayoutX();
        move.setByX(distance);
        move.play();
    }

    private void setMove(Label label, int duration, int distance) {
        TranslateTransition move = new TranslateTransition(Duration.seconds(duration), label);
        move.setNode(label);
        move.setByY(distance);
        move.play();
    }

    private void opacityChange() {
        AnimationTimer timer = new MyTimer();
        timer.start();
    }

    private class MyTimer extends AnimationTimer {

        @Override
        public void handle(long now) {

            doHandle();
        }

        private void doHandle() {

            opacity = opacity + 0.01;
            loading.opacityProperty().set(opacity);
            circleA.opacityProperty().set(opacity);
            circleB.opacityProperty().set(opacity);
            circleC.opacityProperty().set(opacity);

            if (opacity == 1) {

                stop();
            }
        }
    }

}
