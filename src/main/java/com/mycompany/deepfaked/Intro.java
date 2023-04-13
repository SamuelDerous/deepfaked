/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.deepfaked;

import com.mycompany.deepfaked.controls.AnalyticsButton;
import com.mycompany.deepfaked.database.dao.MissionsDao;
import com.mycompany.deepfaked.database.model.Mission;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
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
public class Intro {
    
    //var i;
    
    private String intro;
    private String whole;
    private static Mission mission;

    public static Mission getMission() {
        return mission;
    }
    
    public Scene scenePlay() {
        Pane root = new Pane();
        Scene scene = new Scene(root, 700, 600);
        
        URL resourceBoss = getClass().getClassLoader().getResource("assets/textures/tiktokBoss.Jpg");
            BackgroundSize backgroundSize = new BackgroundSize(scene.getWidth(), scene.getHeight(), false, false, true, true);
            BackgroundImage bossImage = new BackgroundImage(new Image(resourceBoss.toString()), BackgroundRepeat.NO_REPEAT,
            BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
            //bossImage.setFitHeight(scene.getHeight());
            //bossImage.setFitWidth(scene.getWidth());
            root.setBackground(new Background(bossImage));
            URL resourceDialog = getClass().getClassLoader().getResource("assets/textures/dialog-translucent.png");
            ImageView dialogImage = new ImageView(new Image(resourceDialog.toString()));
            dialogImage.setTranslateX(50);
            //dialogImage.set
            dialogImage.setTranslateY(scene.getHeight() - 200);
            dialogImage.minWidth(800);
            dialogImage.setFitWidth(600);
          //  root.getChildren().add(dialogImage);
            
            //root.getChildren().add(bossImage);
            //root.getChildren().add(dialogImage);
        //getGameWorld().addEntityFactory(new GameFactory());
        //getGameWorld().get
        //ImageView bosImage = new I
        //spawn("boss", 0, 0);
        //spawn("dialog", 50, getAppHeight() - 200);
        //this.getStage().getScene().getStylesheets().add(getClass().getResource("assets/border.css").toExternalForm());
        intro = "Dit is de CEO van Tiktok. Momenteel worden we overspoeld door fake video's.\n";
        intro += "Jullie zijn de beste in jullie vak, en wij hebben de beste nodig.\n";
        intro += "Daarom hebben we een test opgesteld waar jullie tegen elkaar zullen strijden.\n";
        intro += "De beste kan rekenen op een belangrijke functie binnen ons bedrijf.\n";
        intro += "Als u wilt deelnemen, druk dan op de knop 'Ja' of druk op de toets 'j'.\n";
        Button btnYes = new Button("Ja");
        btnYes.getStylesheets().add(getClass().getClassLoader().getResource("assets/border.css").toString());
        btnYes.getStyleClass().add("button-primary");
        btnYes.setTranslateX(250);
        btnYes.setTranslateY(scene.getHeight() - 40);
        btnYes.setScaleX(1.5);
        btnYes.setScaleY(1.5);
        btnYes.setPrefWidth(50);
        btnYes.setOnAction((event) -> {
            LoginController.getIntroStage().setScene(sceneMissionPlay());
        });
        Button btnNo = new Button("Neen");
        btnNo.setScaleX(1.5);
        btnNo.setScaleY(1.5);
        btnNo.setPrefWidth(50);
        btnNo.getStylesheets().add(getClass().getClassLoader().getResource("assets/border.css").toString());
        btnNo.getStyleClass().add("button-primary");
        btnNo.setTranslateX(400);
        btnNo.setTranslateY(scene.getHeight() - 40);
        btnNo.setOnAction((event) -> {
            System.out.println("no is clicked");
        });
        /*Text newText = new Text("");
        whole = "";
        newText.setFont(Font.font(16));
        newText.setTranslateX(70);
        newText.setTranslateY(getAppHeight() - 150);
        SimpleBooleanProperty test;
        test = new SimpleBooleanProperty(true);
        
        //test.bind(Bindings.when);
        var i = new SimpleIntegerProperty(0);
        addUINode(newText);
        TimerAction timer = getGameTimer().runAtIntervalWhile(() -> {
            whole += intro.charAt(i.get());
            newText.setText(whole);
            i.set(i.get() + 1);
            test.set(i.get() < intro.length());
        }, Duration.millis(20), test);
        i.addListener((ov, prevStatus, newStatus) -> {
            //System.out.println(newStatus.intValue());
            //System.out.println(prevStatus.intValue());
            if(newStatus.intValue() == intro.length() - 1) {
                timer.expire();
                addUINode(btnYes);
                addUINode(btnNo);
                
            }
        });*/
        Label newText = new Label("");
        //newText.set
        whole = "";
        newText.setFont(Font.font(16));
        newText.setLayoutX(60);
        newText.setTranslateY(scene.getHeight() - 170);
        //newText.set
        SimpleBooleanProperty test;
        test = new SimpleBooleanProperty(true);
        
        //test.bind(Bindings.when);
        var i = new SimpleIntegerProperty(0);
        root.getChildren().add(dialogImage);
        root.getChildren().add(newText);
        
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
            if(newStatus.intValue() == intro.length() - 1) {
                testTime.stop();
                root.getChildren().add(btnYes);
                root.getChildren().add(btnNo);
                scene.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
                    if(key.getCode() == KeyCode.J) {
                        LoginController.getIntroStage().setScene(sceneMissionPlay());
                        //System.out.println("yes pressed");
                    } else if(key.getCode() == KeyCode.N) {
                        System.out.println("No pressed!");
                    }
                });
                
            }
        });
        AnalyticsButton analyticsButton = new AnalyticsButton();
        analyticsButton.setTranslateX(root.getWidth() - analyticsButton.getPrefWidth() - 5);
        analyticsButton.setTranslateY(5);
        root.getChildren().add(analyticsButton);
        return scene;
//timer.expire();
        //addUINode()
    }
    
    public Scene sceneMissionPlay() {
        Pane root = new Pane();
        Scene scene = new Scene(root, 700, 600);
        
        URL resourceBoss = getClass().getClassLoader().getResource("assets/textures/tiktokBoss.Jpg");
            BackgroundSize backgroundSize = new BackgroundSize(scene.getWidth(), scene.getHeight(), false, false, true, true);
            BackgroundImage bossImage = new BackgroundImage(new Image(resourceBoss.toString()), BackgroundRepeat.NO_REPEAT,
            BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
            //bossImage.setFitHeight(scene.getHeight());
            //bossImage.setFitWidth(scene.getWidth());
            List<Mission> missions = MissionsDao.getMissions();
            root.setBackground(new Background(bossImage));
            URL resourceDialog = getClass().getClassLoader().getResource("assets/textures/dialog-translucent.png");
            ImageView dialogImage = new ImageView(new Image(resourceDialog.toString()));
            dialogImage.setTranslateX(50);
            //dialogImage.set
            double amount = missions.size() / 3.0;
            int height = amount > (Math.round(amount)) ? (int) amount + 1 : (int) amount;
            //System.out.println(height);
            dialogImage.setFitHeight((height + 1) * 60);
            dialogImage.setTranslateY(scene.getHeight() - ((height + 1) * 60));
            dialogImage.minWidth(800);
            dialogImage.setFitWidth(600);
          //  root.getChildren().add(dialogImage);
            
            //root.getChildren().add(bossImage);
            //root.getChildren().add(dialogImage);
        //getGameWorld().addEntityFactory(new GameFactory());
        //getGameWorld().get
        //ImageView bosImage = new I
        //spawn("boss", 0, 0);
        //spawn("dialog", 50, getAppHeight() - 200);
        //this.getStage().getScene().getStylesheets().add(getClass().getResource("assets/border.css").toExternalForm());
        intro = "Geweldig! Dit zijn de missies die we voor jullie hebben voorzien:";
        
        List<Button> missionButtons = new ArrayList<>();
        double xBegin = 120;
        double yBegin = scene.getHeight() - (height * 50);
        int teller = 0;
        int heightTeller = 0;
        Button btnMission;
        for(int i = 0; i < missions.size(); i++) {
            final Mission missionsGet = missions.get(i);
            final String description = missions.get(i).getDescription();
            //System.out.println(teller + " " + heightTeller);
            btnMission = new Button(missions.get(i).getName());
            btnMission.getStylesheets().add(getClass().getClassLoader().getResource("assets/border.css").toString());
            Tooltip test = new Tooltip(description);
            test.setPrefWidth(150);
            test.setWrapText(true);
            btnMission.setTooltip(test);
            btnMission.getStyleClass().add("button-primary");
            btnMission.setTranslateX(xBegin + (teller * 180));
            btnMission.setTranslateY(yBegin + (heightTeller * 50));
            btnMission.setScaleX(1.5);
            btnMission.setScaleY(1.5);
            btnMission.setPrefWidth(100);
            
            btnMission.setOnAction((event) -> {
                mission = missionsGet;
                try {
                FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("mainScreen.fxml"));
                Stage stage = new Stage();
                Scene scenetest = new Scene(fxmlLoader.load(), 700, 700);
                stage.setScene(scenetest);
                stage.show();
                LoginController.getIntroStage().hide();
                
            } catch(IOException ex) {
                ex.printStackTrace();
            }
            });
            teller++;
            missionButtons.add(btnMission);
            //heightTeller++;
            if(((i - 1) > 0) && ((i + 1) % 3 == 0)) {
                teller = 0;
                heightTeller++;
            }
        }
        Label newText = new Label("");
        //newText.set
        whole = "";
        newText.setFont(Font.font(16));
        newText.setLayoutX(60);
        newText.setTranslateY(scene.getHeight() - (height + 1) * 50);
        //newText.set
        SimpleBooleanProperty test;
        test = new SimpleBooleanProperty(true);
        
        //test.bind(Bindings.when);
        var i = new SimpleIntegerProperty(0);
        root.getChildren().add(dialogImage);
        root.getChildren().add(newText);
        
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
            if(newStatus.intValue() == intro.length() - 1) {
                testTime.stop();
                root.getChildren().addAll(missionButtons);
            }
        });
        AnalyticsButton analyticsButton = new AnalyticsButton();
        analyticsButton.setTranslateX(root.getWidth() - analyticsButton.getPrefWidth() - 5);
        analyticsButton.setTranslateY(5);
        root.getChildren().add(analyticsButton);
        
        return scene;
//timer.expire();
        //addUINode()
    }
    
    
    
    
    
   /* @Override
    protected void initUI() {
        Text intro = new Text("Hello, this is Sam!");
        intro.setFont(Font.font(24));
        intro.setTranslateX(getAppWidth() - 200);
        intro.setTranslateY(getAppHeight() - 50);
        //intro.fillProperty().bind(getop("stageColor"));
        //intro.textProperty().bind(getip("score").asString());
         addUINode(intro);

        //Group dpadView = getInput().createVirtualDpadView();

        //addUINode(dpadView, 0, 625);
    }*/
    
    /*public static void main(String[] args) {
        //launch(args);
    }*/
    
    
}
