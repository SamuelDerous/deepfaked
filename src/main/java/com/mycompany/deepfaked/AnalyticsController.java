/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.deepfaked;

import com.mycompany.deepfaked.database.dao.GamerDao;
import com.mycompany.deepfaked.database.dao.MissionsDao;
import com.mycompany.deepfaked.database.dao.ProgressMissionDao;
import com.mycompany.deepfaked.database.model.Gamer;
import com.mycompany.deepfaked.database.model.Mission;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 *
 * @author ZENODotus
 */
public class AnalyticsController implements Initializable {
    
    @FXML
    private ScrollPane pnBadges;
    
    @FXML
    private PieChart chartProgress;
    
    @FXML
    private TableView lstLeaderBoard;
    
    @FXML
    private TableColumn colUserName;
    
    @FXML
    private TableColumn colFollowers;
    
    @FXML
    private TableColumn colMoney;
    
    @FXML
    private Button btnClose;
    
    @FXML
    protected void closeScreen() {
        Stage stage = (Stage) btnClose.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        URL resource = getClass().getClassLoader().getResource("assets/icons/award.png");
        URL resourceGray = getClass().getClassLoader().getResource("assets/icons/award-gray.png");
        List<Gamer> gamers = GamerDao.getGamers();
        ObservableList<Gamer> testList = FXCollections.observableList(gamers);
        System.out.println(testList.size());
        List<Pane> badgePanels = new ArrayList<>();
        List<Mission> missions = MissionsDao.getMissions();
        List<Mission> completedMissions = ProgressMissionDao.getMissionsforGamer(LoginController.getGamer());
        double teller = 0;
        double placementY = 0;
        Pane paneBadges = new Pane();
        double heightPane = missions.size() / 4.0;
        paneBadges.setPrefHeight(180 * (heightPane > missions.size() ? Math.round(heightPane) + 1 : Math.round(heightPane)));
        for(int i = 0; i < missions.size(); i++) {
            Image image;
            Label lblMission = new Label();
            if(completedMissions.contains(missions.get(i))) {
                image = new Image(resource.toString());
                lblMission.setTextFill(Color.GOLD);
            } else {
                image = new Image(resourceGray.toString());
                lblMission.setTextFill(Color.GRAY);
            }
            VBox pnBadge = new VBox();
            pnBadge.setPrefWidth(150);
            pnBadge.setAlignment(Pos.CENTER);
            lblMission.setText(missions.get(i).getName());
            //lblMission.setPrefWidth(pnBadge.getPrefWidth());
            //lblMission.setTextAlignment(TextAlignment.CENTER);
            
            //lblMission.setTranslateX(20);
            //lblMission.setTranslateY(pnBadge.getHeight());
            ImageView imageView = new ImageView(image);
            imageView.setFitHeight(150);
            imageView.setFitWidth(100);
            //Pane pnBadge = new Pane();
            pnBadge.getChildren().add(imageView);
            pnBadge.getChildren().add(lblMission);
            pnBadge.setTranslateX(teller * 110);
            pnBadge.setTranslateY(placementY);
            badgePanels.add(pnBadge);
            teller++;
            if(i > 0 && (i + 1) % 4 == 0) {
                placementY += 170;
                teller = 0;
            }
            
        }
        
        paneBadges.getChildren().addAll(badgePanels);
        pnBadges.setContent(paneBadges);
        pnBadges.setPannable(true);
        pnBadges.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);
        
        colUserName.setCellValueFactory(new PropertyValueFactory<>("userName"));
        colFollowers.setCellValueFactory(new PropertyValueFactory<>("followers"));
        colMoney.setCellValueFactory(new PropertyValueFactory<>("money"));
        lstLeaderBoard.setItems(testList);
        double progress = (completedMissions.size() * 100) / missions.size();
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
            new PieChart.Data("Missies", 100 - progress),
            new PieChart.Data("Vervolledigd", progress));
        chartProgress.setData(pieChartData);
        chartProgress.setTitle("Vooruitgang");
        
    }
    
}
