/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.deepfaked.view;

import com.mycompany.deepfaked.main.App;
import com.mycompany.deepfaked.database.dao.GamerDao;
import com.mycompany.deepfaked.database.model.Gamer;
import java.io.File;
import java.io.FilenameFilter;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.stage.FileChooser;

/**
 * FXML Controller class
 *
 * @author ZENODotus
 */
public class AvatarController implements Initializable {
    
    private List<ImageView> humanImages = new ArrayList<>();
    private List<ImageView> otherImages = new ArrayList<>();
    
    private String selectedURL;
    
    @FXML private ScrollPane scrollAvatar;
    
    @FXML
    private FlowPane flowAvatar;
    
    @FXML
    private ChoiceBox cmbGender;
    
    @FXML
    private ImageView imageSelected;
    
    private void initImages(String gender) {
        try {
            String cssDefault = "-fx-border-color: blue;\n"
            + "-fx-border-insets: 5;\n"
            + "-fx-border-width: 3;\n"
            + "-fx-border-style: dashed;\n";
        flowAvatar.getChildren().clear();
        String preamble = "src/main/resources";    
        String directory = "/assets/avatars";
        File dir = new File(preamble + directory + "/" + gender + "/");
        List<String> humanFiles = new ArrayList<>();
        List<String> otherFiles = new ArrayList<>();
        humanFiles = Arrays.asList(dir.list(
            new FilenameFilter() {
                @Override
                public boolean accept(File dir, String name) {
                    return name.endsWith(".png");
                }
            }));
        for(String humanFile : humanFiles) {
            URL resource = getClass().getResource(directory + "/" + gender + "/" + humanFile);
            System.out.println(resource.toString());
            ImageView image = new ImageView(new Image(resource.toString()));
            image.setFitHeight(100);
            image.setFitWidth(100);
            image.addEventFilter(MouseEvent.MOUSE_CLICKED, eventFilter -> {
                //System.out.println(image.getImage().getUrl());
                selectedURL = image.getImage().getUrl();
                Image test = new Image(selectedURL);
                imageSelected.setImage(test);
            
            });
            if(gender.equals("human")) {
                humanImages.add(image);
            } else {
                otherImages.add(image);
            }
            flowAvatar.getChildren().add(image);
            
        }
        scrollAvatar.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);
        scrollAvatar.setHbarPolicy(ScrollBarPolicy.NEVER);
        scrollAvatar.setContent(flowAvatar);
        
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initImages("human");
    }
    
    @FXML
    protected void showAvatars(ActionEvent event) {
        if(cmbGender.getValue().equals("Andere")) {
            initImages("other");
        } else {
            initImages("human");
        }
    }
    
    @FXML
    protected void openAvatar(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Kies je eigen avatar");
        File file = fileChooser.showOpenDialog(App.getStage());
        if(file != null) {
            try {
            selectedURL = file.toURI().toString();
            System.out.println(selectedURL);
            Image image = new Image(selectedURL);
            imageSelected.setImage(image);
            } catch(Exception ex) {
                //ex.printStackTrace();
                Alert alert = new Alert(AlertType.ERROR, "Deze avatar kan niet geopend worden.");
                alert.showAndWait();
                openAvatar(new ActionEvent());
            }
        }
    }
    
    @FXML
    protected void saveAvatar(ActionEvent event) throws Exception {
        if(selectedURL != null) {
            Gamer gamer = RegisterController.getGamer();
            gamer.setAvatar(selectedURL);
            GamerDao.createGamer(gamer);  
        } 
        showLoginScreen();
        
    }

@FXML
    protected void showLoginScreen() throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/com/mycompany/deepfaked/view/login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 569);
        App.getStage().setScene(scene);
    }  
    
    }