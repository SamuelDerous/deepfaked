package com.mycompany.deepfaked.view;

import com.mycompany.deepfaked.MediaFactory;
import com.mycompany.deepfaked.main.App;
import com.mycompany.deepfaked.database.dao.GamerDao;
import com.mycompany.deepfaked.database.model.Gamer;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.scene.web.WebEngine;
import javafx.stage.Stage;
import org.springframework.security.crypto.bcrypt.BCrypt;

public class LoginController implements Initializable {
    
    private static Stage introStage;
    private static Stage infoStage;
    
    private final static String INTRO =  "Dit is de CEO van Tiktok. Momenteel worden we overspoeld door fake video's.\n "
            + "Jullie zijn de beste in jullie vak, en wij hebben de beste nodig.\n"
            + "Daarom hebben we een test opgesteld waar jullie tegen elkaar zullen strijden.\n"
            + "De beste kan rekenen op een belangrijke functie binnen ons bedrijf.\n"
            + "Als u wilt deelnemen, druk dan op de knop 'Ja' of druk op de toets 'j'.\n";
    
    private static Gamer gamer;
    
    @FXML
    private TextField txtUsername;
    
    @FXML
    private TextField txtPassword;
    
    @FXML
    private Label lblError;
    
    @FXML
    private TextFlow flTerms;
    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Text text1 = new Text("Als je doorgaat, ga je akkoort met onze ");
        Hyperlink hyperlink1 = new Hyperlink("Gebruiksvoorwaarden");
        hyperlink1.setOnAction((ActionEvent e) -> {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/com/mycompany/deepfaked/view/terms.fxml"));
                Scene scene = new Scene(fxmlLoader.load(), 600, 400);
                TermsController controller = fxmlLoader.<TermsController>getController();
                WebEngine webEngine = controller.getFleTerms().getEngine();
                webEngine.load("https://www.tiktok.com/legal/page/eea/terms-of-service/NL");
                if(infoStage == null) {
                    infoStage = new Stage();
                    infoStage.getIcons().add(new Image(App.class.getResource("/assets/DeepfakedSplash.png").toString()));
                    infoStage.setTitle("Deepfaked");
                    infoStage.setX(1100.00);
                    infoStage.setY(300.00);
                }
                infoStage.setResizable(false);
                infoStage.setScene(scene);
                infoStage.show();
            } catch(Exception ex) {
                
            }
        });
        Text text2 = new Text(" en erken je dat je ons ");
        Hyperlink hyperlink2 = new Hyperlink("Privacybeleid");
        hyperlink2.setOnAction((ActionEvent e) -> {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/com/mycompany/deepfaked/view/terms.fxml"));
                Scene scene = new Scene(fxmlLoader.load(), 600, 400);
                TermsController controller = fxmlLoader.<TermsController>getController();
                WebEngine webEngine = controller.getFleTerms().getEngine();
                webEngine.load("https://www.tiktok.com/legal/page/eea/privacy-policy/NL");
                if(infoStage == null) {
                    infoStage = new Stage();
                    infoStage.getIcons().add(new Image(App.class.getResource("/assets/DeepfakedSplash.png").toString()));
                    infoStage.setTitle("Deepfaked");
                }
                infoStage.setResizable(false);
                infoStage.setScene(scene);
                infoStage.show();
            } catch(Exception ex) {
                
            }
        });
        Text text3 = new Text(" hebt gelezen om te weten te komen hoe we je gegevens verzamelen, gebruiken en delen, en ons ");
        Hyperlink hyperlink3 = new Hyperlink("cookiebeleid");
        hyperlink3.setOnAction((ActionEvent e) -> {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/com/mycompany/deepfaked/view/terms.fxml"));
                Scene scene = new Scene(fxmlLoader.load(), 600, 400);
                TermsController controller = fxmlLoader.<TermsController>getController();
                WebEngine webEngine = controller.getFleTerms().getEngine();
                webEngine.load("https://www.tiktok.com/legal/page/global/tiktok-website-cookies-policy/NL");
                if(infoStage == null) {
                    infoStage = new Stage();
                    infoStage.getIcons().add(new Image(App.class.getResource("/assets/DeepfakedSplash.png").toString()));
                    infoStage.setTitle("Deepfaked");
                }
                infoStage.setResizable(false);
                infoStage.setScene(scene);
                infoStage.show();
            } catch(Exception ex) {
                
            }
        });
        Text text4 = new Text(" om te weten te komen hoe we cookies gebruiken.");
        text1.setFont(new Font(8));
        text2.setFont(new Font(8));
        text3.setFont(new Font(8));
        text4.setFont(new Font(8));
        hyperlink1.setFont(new Font(8));
        hyperlink2.setFont(new Font(8));
        hyperlink3.setFont(new Font(8));
        flTerms.getChildren().add(text1);
        flTerms.getChildren().add(hyperlink1);
        flTerms.getChildren().add(text2);
        flTerms.getChildren().add(hyperlink2);
        flTerms.getChildren().add(text3);
        flTerms.getChildren().add(hyperlink3);
        flTerms.getChildren().add(text4);
    }
    
    @FXML
    protected void showManual() {
        try {
                FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/com/mycompany/deepfaked/view/terms.fxml"));
                Scene scene = new Scene(fxmlLoader.load(), 800, 500);
                TermsController controller = fxmlLoader.<TermsController>getController();
                WebEngine webEngine = controller.getFleTerms().getEngine();
                webEngine.load(getClass().getResource("/assets/html/manual/manual.html").toString());
                if(infoStage == null) {
                    infoStage = new Stage();
                    infoStage.getIcons().add(new Image(App.class.getResource("/assets/DeepfakedSplash.png").toString()));
                    infoStage.setTitle("Deepfaked");
                }
                infoStage.setResizable(false);
                infoStage.setScene(scene);
                infoStage.show();
            } catch(Exception ex) {
                
            }
    }
    @FXML
    protected void enterPressed(KeyEvent event) throws Exception {
        if(event.getCode() == KeyCode.ENTER) {
            showIntro();
        }
    }
    
    
    @FXML
    protected void showRegisterScreen() throws Exception {
        lblError.setText("");
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/com/mycompany/deepfaked/view/register.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 569);
        
        //Stage stage = new Stage();
        //stage.setTitle("Deepfaked");
        App.getStage().setScene(scene);
    }

    @FXML
    protected void showIntro() throws Exception {
        lblError.setText("");
        Gamer newGamer = GamerDao.getGamer(txtUsername.getText());
        String errorMessage = "";
        if(newGamer != null) {
        try {
        if(BCrypt.checkpw(txtPassword.getText(), newGamer.getPassword())) {
            gamer = newGamer;
            MediaFactory.createIntro();
        } else {
            errorMessage = "Je login is niet correct.";
            lblError.setText(errorMessage);
        }
        } catch(Exception ex) {
            errorMessage = "Er is iets fout gegaan. Probeer het later opnieuw.";
            lblError.setText(errorMessage);
            ex.printStackTrace();
        }
        } else {
            errorMessage = "Je login is niet correct.";
            lblError.setText(errorMessage);
        }
        //HelloApplication.getStage().setScene(scene);
    }
    
    public static void makePersonalTab() {
        
    }
    

    public static Stage getIntroStage() {
        return introStage;
    }
    
    public static Gamer getGamer() {
        return gamer;
    }
    
    
}