/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.deepfaked;

import com.mycompany.deepfaked.controls.AnalyticsButton;
import com.mycompany.deepfaked.database.dao.MissionsDao;
import com.mycompany.deepfaked.database.dao.ProgressMissionDao;
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

    private static final String MISSIONINTRO = "Geweldig! Dit zijn de missies die we voor jullie hebben voorzien:";

    private static final List<Image> ownerImages = new ArrayList<>(List.of(new Image(Intro.class.getResource("/assets/textures/Owner1.jpg").toString()),
            new Image(Intro.class.getResource("/assets/textures/Owner2.jpg").toString()),
            new Image(Intro.class.getResource("/assets/textures/Owner3.jpg").toString()),
            new Image(Intro.class.getResource("/assets/textures/Owner4.jpg").toString())));

    private final MainScreenController mainScreenController;

    private ImageView dialogImage;

    private List<Mission> missions;

    private int height;

    private Label newText;

    private SimpleIntegerProperty i;

    private static Stage stage;

    private Pane root;

    private Stage introStage;
    private Scene scene;

    EventHandler<KeyEvent> keyHandler;

    private String whole;
    private static Mission mission;

    public Intro() {
        mainScreenController = new MainScreenController();
    }

    public Intro(MainScreenController controller, String intro) {
        this.mainScreenController = controller;
        //FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("questions.fxml"));
        //fxmlLoader.setController(this);
        //Scene scene = new Scene(fxmlLoader.load(), 885, 740); 
        introStage = new Stage();
        introStage.setTitle("Missies");
        introStage.setScene(playScene(intro, false, false));

        introStage.show();

    }

    public static Mission getMission() {
        return mission;
    }

    private Scene createScene(boolean onlyText, boolean yesNo) {
        root = new Pane();
        Scene scene = new Scene(root, 700, 600);
        int randomImage = (int) (Math.random() * 3 + 1);
        URL resourceBoss = getClass().getResource("/assets/textures/tiktokBoss.Jpg");
        BackgroundSize backgroundSize = new BackgroundSize(scene.getWidth(), scene.getHeight(), false, false, true, true);
        BackgroundImage bossImage = new BackgroundImage(ownerImages.get(randomImage), BackgroundRepeat.NO_REPEAT,
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
        if (!onlyText && !yesNo) {
            missions = MissionsDao.getMissions();
            double amount = missions.size() / 3.0;
            height = amount > (Math.round(amount)) ? (int) amount + 1 : (int) amount;
            dialogImage.setFitHeight((height + 1) * 60);
            dialogImage.setTranslateY(scene.getHeight() - ((height + 1) * 60));
            newText.setTranslateY(scene.getHeight() - (height + 1) * 50);
        }
        root.getChildren().add(dialogImage);
        root.getChildren().add(newText);
        //dialogImage.minWidth(800);
        //dialogImage.setFitWidth(600);

        return scene;
    }

    private void createYesNoButtons() {
        Button btnYes = new Button("Ja");
        btnYes.getStylesheets().add(getClass().getResource("/assets/border.css").toString());
        btnYes.getStyleClass().add("button-primary");
        btnYes.setTranslateX(250);
        btnYes.setTranslateY(scene.getHeight() - 40);
        btnYes.setScaleX(1.5);
        btnYes.setScaleY(1.5);
        btnYes.setPrefWidth(50);
        btnYes.setOnAction((event) -> {
            IntroController.getIntroStage().setScene(playScene(MISSIONINTRO, false, false));
        });
        Button btnNo = new Button("Neen");
        btnNo.setScaleX(1.5);
        btnNo.setScaleY(1.5);
        btnNo.setPrefWidth(50);
        btnNo.getStylesheets().add(getClass().getResource("/assets/border.css").toString());
        btnNo.getStyleClass().add("button-primary");
        btnNo.setTranslateX(400);
        btnNo.setTranslateY(scene.getHeight() - 40);
        btnNo.setOnAction((event) -> {
            System.out.println("no is clicked");
        });
        root.getChildren().add(btnYes);
        root.getChildren().add(btnNo);
        scene.addEventHandler(KeyEvent.KEY_PRESSED, createYesNoEvent());
    }

    private void createMissionButtons(int height) {
        List<Button> missionButtons = new ArrayList<>();
        double xBegin = 120;
        double yBegin = scene.getHeight() - (height * 50);
        int teller = 0;
        int heightTeller = 0;
        Button btnMission;
        List<Mission> progressMissions = ProgressMissionDao.getMissionsforGamer(LoginController.getGamer());
        for (int i = 0; i < missions.size(); i++) {
            final Mission missionsGet = missions.get(i);
            final String description = missions.get(i).getDescription();
            //System.out.println(teller + " " + heightTeller);
            btnMission = new Button(missions.get(i).getName());
            btnMission.getStylesheets().add(getClass().getResource("/assets/border.css").toString());
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
                    stage = new Stage();
                    Scene scenetest = new Scene(fxmlLoader.load(), 700, 800);
                    stage.setScene(scenetest);
                    stage.show();
                    IntroController.getIntroStage().hide();

                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            });
            if (progressMissions.contains(missionsGet)) {
                btnMission.setDisable(true);

            } else {
                btnMission.setDisable(false);
            }
            teller++;
            missionButtons.add(btnMission);
            //heightTeller++;
            if (((i - 1) > 0) && ((i + 1) % 3 == 0)) {
                teller = 0;
                heightTeller++;
            }
        }
        root.getChildren().addAll(missionButtons);
    }

    public Scene playScene(String intro, boolean onlyText, boolean yesNo) {
        scene = createScene(onlyText, yesNo);
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
                if (!onlyText && yesNo) {
                    createYesNoButtons();
                } else if (!onlyText) {
                    createMissionButtons(height);
                }
            }
        });
        scene.addEventHandler(KeyEvent.KEY_PRESSED, createEscapeKeyHandler(testTime, intro, height, onlyText, yesNo));
        createAnalyticsButton();
        return scene;

    }

    public void createAnalyticsButton() {
        AnalyticsButton analyticsButton = new AnalyticsButton();
        analyticsButton.setTranslateX(root.getWidth() - analyticsButton.getPrefWidth() - 5);
        analyticsButton.setTranslateY(5);
        root.getChildren().add(analyticsButton);
    }

    public static Stage getStage() {
        return stage;
    }

    public static List<Image> getOwnerImages() {
        return ownerImages;
    }

    private EventHandler<KeyEvent> createEscapeKeyHandler(Timeline timer, String intro, int height, boolean onlyText, boolean yesNo) {
        keyHandler = (KeyEvent key) -> {
            if (newText.getText().length() < intro.length()) {
                if (key.getCode() == KeyCode.ESCAPE) {
                    timer.stop();
                    newText.setText(intro);
                    if (!onlyText && yesNo) {
                        createYesNoButtons();
                    } else if (!onlyText && !yesNo) {
                        createMissionButtons(height);
                    }
                    //scene.addEventHandler(KeyEvent.KEY_PRESSED, createYesNoEvent());
                    scene.removeEventHandler(KeyEvent.KEY_PRESSED, keyHandler);
                }
            }
        };
        return keyHandler;
    }

    private EventHandler<KeyEvent> createYesNoEvent() {
        EventHandler<KeyEvent> yesNoHandler = (KeyEvent key) -> {
            if (key.getCode() == KeyCode.J) {
                LoginController.getIntroStage().setScene(playScene(MISSIONINTRO, false, false));
                //System.out.println("yes pressed");
            } else if (key.getCode() == KeyCode.N) {
                System.out.println("No pressed!");
            }
        };
        return yesNoHandler;
    }

}
