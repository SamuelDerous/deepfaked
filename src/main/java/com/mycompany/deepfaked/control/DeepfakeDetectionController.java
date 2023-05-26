/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.deepfaked.control;

import com.mycompany.deepfaked.database.dao.DeepfakeDao;
import com.mycompany.deepfaked.database.model.Deepfake;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.ArcTo;
import javafx.scene.shape.FillRule;
import javafx.scene.shape.HLineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author ZENODotus
 */
public class DeepfakeDetectionController implements Initializable {
    
    @FXML
    private ProgressBar progressScanVideo;

    private ImageView imgArrow;
    private double amount;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        var i = new SimpleIntegerProperty(0);
         Timeline testTime = new Timeline(new KeyFrame(Duration.millis(20), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //hoeveelheid = hoeveelheid + ((hoeveelheid * 180) / 100);
                progressScanVideo.setProgress(i.get());
                i.set(i.get() + 1);
            }
        }));
        testTime.setCycleCount(Timeline.INDEFINITE);
        testTime.play();
        i.addListener((ov, prevStatus, newStatus) -> {
            //System.out.println(newStatus.intValue());
            //System.out.println(prevStatus.intValue());
            if(newStatus.intValue() > 100) {
                testTime.stop();
                
                
            }
        });
    }

    /*@Override
    public void start(Stage primaryStage) {
        /*List<Deepfake> deepfakes = DeepfakeDao.getAllDeepfakes();
        
        amount =  deepfakes.get(0).getAvatarify();
        amount += deepfakes.get(0).getDeepware();
        amount += deepfakes.get(0).getSeferbekov();
        amount += deepfakes.get(0).getEnsemble();
        */
  /*      amount = 70; //amount / 4;
        
                
        Image image = new Image(getClass().getClassLoader().getResource("assets/icons/arrow.png").toString());
        imgArrow = new ImageView(image);
        imgArrow.setFitWidth(60);
        imgArrow.setPreserveRatio(true);
        double aspectRatio = image.getWidth() / image.getHeight();
        double realWidth = Math.min(imgArrow.getFitWidth(), imgArrow.getFitHeight() * aspectRatio);
        double realHeight = imgArrow.getFitWidth() / aspectRatio;
        imgArrow.setLayoutY(200.0 - (realHeight));
        imgArrow.setLayoutX(200.0 - (imgArrow.getFitWidth() / 2));
        imgArrow.setRotate(0);
        final double upper = amount * 180 / 100;
        System.out.println("upper: " + upper);
        var i = new SimpleIntegerProperty(0);
        amount = 0;
        //hoeveelheid = 1.0;
        Timeline testTime = new Timeline(new KeyFrame(Duration.millis(10), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //hoeveelheid = hoeveelheid + ((hoeveelheid * 180) / 100);
                imgArrow.setRotate(amount++); 
                i.set(i.get() + 1);
            }
        }));
        testTime.setCycleCount(Timeline.INDEFINITE);
        testTime.play();
        i.addListener((ov, prevStatus, newStatus) -> {
            //System.out.println(newStatus.intValue());
            //System.out.println(prevStatus.intValue());
            if(newStatus.intValue() == upper) {
                testTime.stop();
                
                
            }
        });
        
        System.out.println(imgArrow.getLayoutY());
        
        Group root = new Group();
        root.getChildren().add(drawSemiRing(200, 200, 200, 150, Color.LIGHTGREEN, Color.DARKGREEN));
        root.getChildren().add(imgArrow);
        
        //root.getChildren().add(drawSemiRing(350, 350, 200, 30, Color.LIGHTSKYBLUE, Color.DARKBLUE));

        Scene scene = new Scene(root, 450, 250);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private Path drawSemiRing(double centerX, double centerY, double radius, double innerRadius, Color bgColor, Color strkColor) {
        Stop[] stops = new Stop[] {
         new Stop(0, Color.GREEN),
         new Stop(0.50, Color.ORANGE),
         new Stop(1, Color.RED)
      };
        /*LinearGradient linearGradient = new LinearGradient(0, 0, width, height,
            new int[] { 
                0xFF1e5799, 
                0xFF207cca, 
                0xFF2989d8, 
                0xFF207cca }, //substitute the correct colors for these
            new float[] {
                0, 0.40f, 0.60f, 1 },
            Shader.TileMode.REPEAT);*/
     /*   LinearGradient gradient =
      new LinearGradient(0.0, 0.0, 1.0, 0.0, true, CycleMethod.NO_CYCLE, stops);
        Path path = new Path();
        path.setFill(gradient); // new LinearGradient(0, 0, 0, 10, Color.BLACK, Color.WHITE));
        path.setStroke(strkColor);
        path.setFillRule(FillRule.EVEN_ODD);

        MoveTo moveTo = new MoveTo();
        moveTo.setX(centerX + innerRadius);
        moveTo.setY(centerY);

        ArcTo arcToInner = new ArcTo();
        arcToInner.setX(centerX - innerRadius);
        arcToInner.setY(centerY);
        arcToInner.setRadiusX(innerRadius);
        arcToInner.setRadiusY(innerRadius);

        MoveTo moveTo2 = new MoveTo();
        moveTo2.setX(centerX + innerRadius);
        moveTo2.setY(centerY);

        HLineTo hLineToRightLeg = new HLineTo();
        hLineToRightLeg.setX(centerX + radius);

        ArcTo arcTo = new ArcTo();
        arcTo.setX(centerX - radius);
        arcTo.setY(centerY);
        arcTo.setRadiusX(radius);
        arcTo.setRadiusY(radius);

        HLineTo hLineToLeftLeg = new HLineTo();
        hLineToLeftLeg.setX(centerX - innerRadius);

        path.getElements().add(moveTo);
        path.getElements().add(arcToInner);
        path.getElements().add(moveTo2);
        path.getElements().add(hLineToRightLeg);
        path.getElements().add(arcTo);
        path.getElements().add(hLineToLeftLeg);
        System.out.println(path.getLayoutX());
        return path;
    }

    public static void main(String[] args) {
        launch(args);
    }*/
}
    

