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

    private String whole;
    
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
        introStage.setScene(playScene(intro));

        introStage.show();

    }
    
    @Override
    public Scene createScene() {
        root = new Pane();
        Scene scene = new Scene(root, 700, 600);
        int randomImage = (int) (Math.random() * 3 + 1);
        BackgroundSize backgroundSize = new BackgroundSize(scene.getWidth(), scene.getHeight(), false, false, true, true);
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
        dialogImage.setTranslateY(scene.getHeight() - 200);
        newText = new Label();
        //newText.set
        newText.setFont(Font.font(16));
        newText.setLayoutX(60);
        newText.setTranslateY(scene.getHeight() - 170);
        root.getChildren().add(dialogImage);
        root.getChildren().add(newText);
        return scene;
    }

    @Override
    public final Scene playScene(String intro) {
        scene = createScene();
        whole = "";
        //test.bind(Bindings.when);
        i = new SimpleIntegerProperty(0);

        Timeline testTime = new Timeline(new KeyFrame(Duration.millis(20), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                whole += intro.charAt(i.get());
                newText.setText(whole);
                i.set(i.get() + 1);
            }
        }));

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
        
        scene.addEventHandler(KeyEvent.KEY_PRESSED, createEscapeKeyHandler(testTime, intro, height));
        createAnalyticsButton();
        return scene;
    }
    
    public void createAnalyticsButton() {
        AnalyticsButton analyticsButton = new AnalyticsButton();
        analyticsButton.setTranslateX(root.getWidth() - analyticsButton.getPrefWidth() - 5);
        analyticsButton.setTranslateY(5);
        root.getChildren().add(analyticsButton);
    }
    
    private EventHandler<KeyEvent> createEscapeKeyHandler(Timeline timer, String intro, int height) {
        keyHandler = (KeyEvent key) -> {
            if (newText.getText().length() < intro.length()) {
                if (key.getCode() == KeyCode.ESCAPE) {
                    timer.stop();
                    newText.setText(intro);
                    createButtons();
                    //scene.addEventHandler(KeyEvent.KEY_PRESSED, createYesNoEvent());
                    scene.removeEventHandler(KeyEvent.KEY_PRESSED, keyHandler);
                }
            }
        };
        return keyHandler;
    }
    
    public static Stage getStage() {
        return stage;
    }

    public static List<Image> getOwnerImages() {
        return OWNERIMAGES;
    }
    
}
