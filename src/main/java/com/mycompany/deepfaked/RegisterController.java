/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.deepfaked;

import com.mycompany.deepfaked.database.dao.GamerDao;
import com.mycompany.deepfaked.database.model.Gamer;
import java.math.BigDecimal;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.scene.web.WebEngine;
import javafx.stage.Stage;
import org.springframework.security.crypto.bcrypt.BCrypt;

/**
 * FXML Controller class
 *
 * @author ZENODotus
 */
public class RegisterController implements Initializable {
    
    private static final String DATE_PATTERN = "dd/MM/yyyy";
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern(DATE_PATTERN);
    private static Stage infoStage;
    
    private static Gamer gamer;

    @FXML
    private TextField txtConfirm;
    
    @FXML
    private TextField txtUser;
    
    @FXML
    private TextField txtPassword;
    
    @FXML
    private DatePicker dtBirthDate;
    
    @FXML
    private Label lblError;
    
    @FXML
    private Button btnNext;
    
    @FXML
    private TextFlow flTerms;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Text text1 = new Text("Als je doorgaat, ga je akkoort met de ");
        Hyperlink hyperlink1 = new Hyperlink("Gebruiksvoorwaarden");
        hyperlink1.setOnAction((ActionEvent e) -> {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("terms.fxml"));
                Scene scene = new Scene(fxmlLoader.load(), 600, 400);
                TermsController controller = fxmlLoader.<TermsController>getController();
                WebEngine webEngine = controller.getFleTerms().getEngine();
                webEngine.load("https://www.tiktok.com/legal/page/eea/terms-of-service/NL");
                if(infoStage == null) {
                    infoStage = new Stage();
                    infoStage.setX(1100.00);
                    infoStage.setY(300.00);
                }
                infoStage.setScene(scene);
                infoStage.show();
            } catch(Exception ex) {
                
            }
        });
        Text text2 = new Text(" van TikTok en bevestig je dat je het ");
        Hyperlink hyperlink2 = new Hyperlink("Privacybeleid");
        hyperlink2.setOnAction((ActionEvent e) -> {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("terms.fxml"));
                Scene scene = new Scene(fxmlLoader.load(), 600, 400);
                TermsController controller = fxmlLoader.<TermsController>getController();
                WebEngine webEngine = controller.getFleTerms().getEngine();
                webEngine.load("https://www.tiktok.com/legal/page/eea/privacy-policy/NL");
                if(infoStage == null) {
                    infoStage = new Stage();
                }
                infoStage.setScene(scene);
                infoStage.show();
            } catch(Exception ex) {
                
            }
        });
        Text text3 = new Text(" van TikTok hebt gelezen.");
        text1.setFont(new Font(8));
        text2.setFont(new Font(8));
        text3.setFont(new Font(8));
        hyperlink1.setFont(new Font(8));
        hyperlink2.setFont(new Font(8));
        flTerms.getChildren().add(text1);
        flTerms.getChildren().add(hyperlink1);
        flTerms.getChildren().add(text2);
        flTerms.getChildren().add(hyperlink2);
        flTerms.getChildren().add(text3);
    }

@FXML
    protected void showLoginScreen() throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 569);
        App.getStage().setScene(scene);
    }
    
    @FXML
    protected void showAvatarScreen() throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("avatar.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 569);
        scene.getStylesheets().add(getClass().getClassLoader().getResource("assets/border.css").toExternalForm());
        App.getStage().setScene(scene);
    }  

    
    protected void sendCode() throws Exception {

    }

    @FXML
    protected void codeEntered() throws Exception {
        try {
            if(txtUser.getText().length() > 0 && txtPassword.getText().length() > 0 && txtConfirm.getText().length() > 0 && 
                    txtPassword.getText().equals(txtConfirm.getText())
                 &&   dtBirthDate.getValue().toString().length() > 0) {
                btnNext.setDisable(false);
                
            } else {
                btnNext.setDisable(true);
            }
            //System.out.println(dtBirthDate.getValue());
        } catch(Exception ex) {
            btnNext.setDisable(true);
        }
    }
    
    @FXML
    protected void makeGamer() throws Exception {
        boolean error = false;
        String errorMessage = "";
        LocalDate thirteen = LocalDate.now().minusYears(13);
        
        if(dtBirthDate.getValue().isAfter(thirteen)) {
            errorMessage = "Je dient 13 jaar of ouder te zijn om je aan te melden op deze applicatie.\n";
            error = true;
        }
        if(GamerDao.gamerDuplicate(txtUser.getText().trim())) {
            errorMessage += "Deze gebruikersnaam bestaat al in het systeem. Als dit jouw account is, log dan in op het login-scherm.";
            error = true;
        }
        if(!error) {
            gamer = new Gamer();
            gamer.setUserName(txtUser.getText().trim());
            gamer.setPassword(BCrypt.hashpw(txtPassword.getText().trim(), BCrypt.gensalt()));
            gamer.setBirthdate(dtBirthDate.getValue());
            gamer.setFollowers(0);
            gamer.setMoney(new BigDecimal("0.00"));
            if(GamerDao.createGamer(gamer)) {
                showAvatarScreen();
            } else {
                errorMessage = "Er is iets fout gegaan bij het verwerken van je gegevens.";
            }
        }
        
        lblError.setText(errorMessage);
        //showAvatarScreen();
        //showLoginScreen();
        
    }

    public static Gamer getGamer() {
        return gamer;
    }
    
    
            
}
