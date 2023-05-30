/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.deepfaked.view;

import com.mycompany.deepfaked.database.dao.MissionsDao;
import com.mycompany.deepfaked.database.dao.ProgressMissionDao;
import com.mycompany.deepfaked.database.model.Mission;
import com.mycompany.deepfaked.main.App;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.stage.Stage;

/**
 *
 * @author ZENODotus
 */
public class MissionView extends PrntView {
    
    private List<Mission> missions;
    private static Mission mission;

    
    public MissionView() {
        super();
    }
    
    public MissionView(MainScreenController controller, String intro) {
        super(controller, intro);
    }
    
    @Override
    public void createButtons() {
        List<Mission> missions = MissionsDao.getMissions();
        List<Button> missionButtons = new ArrayList<>();
        double xBegin = 120;
        double yBegin = root.getHeight() - (2 * 50);
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
                    FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/com/mycompany/deepfaked/view/mainScreen.fxml"));
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
    
    public static Mission getMission() {
        return mission;
    }
}
