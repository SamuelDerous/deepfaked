/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.deepfaked.view;

import com.mycompany.deepfaked.controls.AnalyticsButton;
import java.net.URL;
import java.util.List;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author ZENODotus
 */
public abstract class PrntView implements InfoView {

    //private static final String MISSIONINTRO = "Geweldig! Dit zijn de missies die we voor jullie hebben voorzien:";
    protected ImageView dialogImage;

    private final MainScreenController mainScreenController;

    protected int height;

    private Label newText;

    private SimpleIntegerProperty i;

    protected static Stage stage;

    protected Pane root;

    protected Stage introStage;
    protected Scene scene;

    protected EventHandler<KeyEvent> keyHandler;
    //private EventHandler<KeyEvent> keyHandler;
    private EventHandler<KeyEvent> escapeHandler;
    private EventHandler<MouseEvent> mouseHandler;

    private String whole;
    private ImageView keyImage;

    private Timeline testTime;

    public PrntView() {
        mainScreenController = new MainScreenController();
        
    }

    public PrntView(MainScreenController controller, String intro) {
        this.mainScreenController = controller;
        //FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("questions.fxml"));
        //fxmlLoader.setController(this);
        //Scene scene = new Scene(fxmlLoader.load(), 885, 740); 
        introStage = new Stage();
        introStage.setTitle("Missies");
        introStage.setScene(createScene(intro));

        introStage.show();
        

    }
    
    @Override
    public final Scene createScene(String intro) {
        Scene scene = new Scene(play(intro), 700, 600);
        root.addEventHandler(KeyEvent.KEY_PRESSED, createEscapeKeyHandler(testTime, intro));
        createAnalyticsButton();
        return scene;
    }

    @Override
    public Pane createPane() {
        root = new Pane();
        root.setPrefHeight(600);
        root.setPrefWidth(600);
        int randomImage = (int) (Math.random() * 3 + 1);
        BackgroundSize backgroundSize = new BackgroundSize(root.getWidth(), root.getHeight(), false, false, true, true);
        BackgroundImage bossImage = new BackgroundImage(OWNERIMAGES.get(randomImage), BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        //bossImage.setFitHeight(scene.getHeight());
        //bossImage.setFitWidth(scene.getWidth());
        root.setBackground(new Background(bossImage));
        URL resourceDialog = getClass().getResource("/assets/textures/dialog-translucent.png");
        dialogImage = new ImageView(new Image(resourceDialog.toString()));
        dialogImage.setTranslateX(50);
        //dialogImage.set
        dialogImage.minWidth(800);
        dialogImage.setFitWidth(600);
        dialogImage.setTranslateY(root.getPrefHeight() - 200);
        newText = new Label();
        keyImage = new ImageView(new Image(getClass().getResource("/assets/textures/key-C.png").toString()));
        keyImage.setTranslateY(root.getPrefHeight() - 100);
        keyImage.setTranslateX(588);
        keyImage.setVisible(false);
        //newText.set
        newText.setFont(Font.font(16));
        newText.setLayoutX(60);
        System.out.println("Height: " + root.getPrefHeight());
        newText.setTranslateY(root.getPrefHeight() - 170);
        root.getChildren().add(dialogImage);
        root.getChildren().add(newText);
        root.getChildren().add(keyImage);
        return root;
    }

    @Override
    public final Pane play(String intro) {
        root = createPane();
        
        //scene = createScene();
        whole = "";
        //test.bind(Bindings.when);
        i = new SimpleIntegerProperty(0);

        testTime = new Timeline(new KeyFrame(Duration.millis(20), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                whole += intro.charAt(i.get());
                newText.setText(whole);
                i.set(i.get() + 1);
            }
        }));
        keyHandler = (KeyEvent key) -> {
            //KeyCode pressKey = ((KeyEvent)key).getCode();
            if (key.getCode() == KeyCode.C) {
                handler();
                whole = "";
                testTime.play();
                keyImage.setVisible(false);
                root.removeEventHandler(KeyEvent.KEY_PRESSED, keyHandler);
            }
        };
        mouseHandler = (MouseEvent event) -> {
            handler();
        };

        testTime.setCycleCount(Timeline.INDEFINITE);
        testTime.play();
        i.addListener((ov, prevStatus, newStatus) -> {
            //System.out.println(newStatus.intValue());
            //System.out.println(prevStatus.intValue());
            if (newStatus.intValue() == intro.length() - 1) {
                testTime.stop();
                createButtons();
            }
        });

        
        createAnalyticsButton();
        return root;
    }

    public void createAnalyticsButton() {
        AnalyticsButton analyticsButton = new AnalyticsButton();
        analyticsButton.setTranslateX(root.getWidth() - analyticsButton.getPrefWidth() - 5);
        analyticsButton.setTranslateY(5);
        root.getChildren().add(analyticsButton);
    }

    private EventHandler<KeyEvent> createEscapeKeyHandler(Timeline timer, String intro) {
        keyHandler = (KeyEvent key) -> {
            if (newText.getText().length() < intro.length()) {
                if (key.getCode() == KeyCode.ESCAPE) {
                    timer.stop();
                    newText.setText(intro);
                    createButtons();
                    //scene.addEventHandler(KeyEvent.KEY_PRESSED, createYesNoEvent());
                    root.removeEventHandler(KeyEvent.KEY_PRESSED, keyHandler);
                }
            }
        };
        return keyHandler;
    }

    private void handler() {
        whole = "";
        testTime.play();
        keyImage.setVisible(false);
        root.getScene().removeEventHandler(KeyEvent.KEY_PRESSED, keyHandler);
        keyImage.removeEventHandler(MouseEvent.MOUSE_CLICKED, mouseHandler);
    }

    public static Stage getStage() {
        return stage;
    }

    public static List<Image> getOwnerImages() {
        return OWNERIMAGES;
    }

}
