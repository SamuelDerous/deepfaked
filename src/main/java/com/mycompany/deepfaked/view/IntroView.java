/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.deepfaked.view;

import com.mycompany.deepfaked.MediaFactory;
import com.mycompany.deepfaked.main.App;
import java.util.Optional;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

/**
 *
 * @author ZENODotus
 */
public class IntroView extends PrntView {
    
    public IntroView() {
        super();
    }
    
    public IntroView(MainScreenController controller, String intro) {
        super(controller, intro);
    }
    
    @Override
    public void createButtons() {
        Button btnYes = new Button("Ja");
        btnYes.getStylesheets().add(getClass().getResource("/assets/border.css").toString());
        btnYes.getStyleClass().add("button-primary");
        btnYes.setTranslateX(250);
        btnYes.setTranslateY(root.getHeight() - 40);
        btnYes.setScaleX(1.5);
        btnYes.setScaleY(1.5);
        btnYes.setPrefWidth(50);
        btnYes.setOnAction((event) -> {
            MediaFactory.getViewStage().setScene(InfoFactory.createMissionView().createScene("Geweldig! Dit zijn de missies die we voor je hebben verzameld:"));
        });
        Button btnNo = new Button("Neen");
        btnNo.setScaleX(1.5);
        btnNo.setScaleY(1.5);
        btnNo.setPrefWidth(50);
        btnNo.getStylesheets().add(getClass().getResource("/assets/border.css").toString());
        btnNo.getStyleClass().add("button-primary");
        btnNo.setTranslateX(400);
        btnNo.setTranslateY(root.getHeight() - 40);
        btnNo.setOnAction((event) -> {
            createGoodbye();
        });
        root.getChildren().add(btnYes);
        root.getChildren().add(btnNo);
        root.addEventHandler(KeyEvent.KEY_PRESSED, createYesNoEvent());
    }

    private EventHandler<KeyEvent> createYesNoEvent() {
        EventHandler<KeyEvent> yesNoHandler = (KeyEvent key) -> {
            if (key.getCode() == KeyCode.J) {
                System.out.println("keycode");
                MediaFactory.getViewStage().setScene(InfoFactory.createMissionView().createScene("Geweldig! Dit zijn de missies die we voor je hebben verzameld:"));
                
            } else if (key.getCode() == KeyCode.N) {
                System.out.println("keycode");
                createGoodbye();
            }
        };
        return yesNoHandler;
    }
    
    private void createGoodbye() {
        Alert alert = new Alert(AlertType.CONFIRMATION, "Spijtig dat we je niet warm kunnen maken voor een aanstelling in ons bedrijf. Misschien een volgende keer.");
        ((Stage)alert.getDialogPane().getScene().getWindow()).getIcons().add(new Image(App.class.getResource("/assets/DeepfakedSplash.png").toString()));
        alert.setTitle("Tot de volgende keer!");
        alert.setHeaderText("Afsluiten van het programma.");
        Optional<ButtonType> result = alert.showAndWait();
        result.ifPresent(btnType -> {
            if(btnType == ButtonType.OK) {
                System.exit(0);
            }
        });
    }
}
