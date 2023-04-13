/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.deepfaked;

import com.mycompany.deepfaked.database.dao.AccountDao;
import com.mycompany.deepfaked.database.dao.DeepfakeDao;
import com.mycompany.deepfaked.database.dao.QuestionDao;
import com.mycompany.deepfaked.database.dao.TaskDao;
import com.mycompany.deepfaked.database.model.Account;
import com.mycompany.deepfaked.database.model.CompletedTask;
import com.mycompany.deepfaked.database.model.Deepfake;
import com.mycompany.deepfaked.database.model.Gamer;
import com.mycompany.deepfaked.database.model.Mission;
import com.mycompany.deepfaked.database.model.Question;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.concurrent.Worker.State;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
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
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * FXML Controller class
 *
 * @author ZENODotus
 */
public class MainScreenController implements Initializable {
    
    private static List<Question> questions;
    
    private EventHandler<KeyEvent> keyHandler;
    private EventHandler<MouseEvent> mouseHandler;
    private ImageView keyImage;
    private Timeline testTime;
    private Pane root;
    private int value = 450;
    private Mission mission;
    private List<CompletedTask> tasks;
    
    private Gamer gamer;
    
    //private Map<Task, Boolean> completedTasks; 
    //private boolean isTaskComplete;
    
    private boolean isProfessorPressed;
    private boolean isGPTPressed;
    
    
    //private Map<Task, Boolean> completedTasks; 
    //private boolean isTaskComplete;
    
    
    private final VBox chatBox = new VBox(5);
    private final List<Label> messages = new ArrayList<>();
    private final int indexMessages = 0;
    private String intro;
    private String whole;
    
    private List<Deepfake> deepfakes;
    private Deepfake deepfake;
    
    @FXML
    private TabPane tbPaneMainScreen;

    @FXML
    private WebView webviewTiktok;
    
    private Pane pnTasks;
    
    @FXML
    private Pane pnGPT;
    
    @FXML
    private ScrollPane scrollTasks;
    
    @FXML
    private Tab tbInformation;
    
    @FXML
    private Tab tbTiktok;
    
    @FXML
    private ToggleButton tglTasks;
    
    @FXML
    private TextField txtMessage;
    
    @FXML
    private ScrollPane scrollMessage;
    
    @FXML
    private ToggleButton btnReal;
    
    @FXML
    private ToggleButton btnFake;
    
    @FXML
    private ToggleButton tgGPT;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        gamer = LoginController.getGamer();
        pnTasks = new Pane();
        //pnTasks.setPrefHeight(200);
        scrollTasks.setVisible(false);
        scrollTasks.getStylesheets().add(this.getClass().getClassLoader().getResource("assets/border.css").toString());
        scrollTasks.getStyleClass().add("paneTasks");
        //pnTasks.setStyle("-fx-background-color: #f2f2f2");
        //pnTasks.setStyle("-fx-border-color: black");
        //pnTasks.getChildren().add(new Label("Dit is een test"));
        mission = Intro.getMission();
        tbInformation.setContent(paneMissionPlay());
        deepfakes = DeepfakeDao.getDeepfakesForMission(mission);
        isProfessorPressed = false;
        isGPTPressed = false;
        //isTaskComplete = false;
        tglTasks.setDisable(true);
        tgGPT.setDisable(true);
        chatBox.setPadding(new Insets(10, 10, 10, 10));
        scrollMessage.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollMessage.setContent(chatBox);
        //pnGPT.setBorder(new Border(new BorderStroke()));
        /*for(int i = 0; i < deepfakes.size(); i++) {
            System.out.println(deepfakes.get(i).getLocation());
        }*/
        btnReal.setDisable(true);
        btnFake.setDisable(true);
        WebEngine webEngine = webviewTiktok.getEngine();
        webEngine.load(this.getClass().getClassLoader().getResource("assets/html/tiktokMovie.html").toString());
        webEngine.getLoadWorker().stateProperty().addListener((observable, oldState, newState) -> {
            if (newState == State.SUCCEEDED) {
                Document document = webEngine.getDocument();
                if(document != null) {
            Element userImg = document.getElementById("userImg");
            String source = userImg.getAttribute("src");
            String avatar = gamer.getAvatar();
            if(avatar == null || avatar.isBlank()) {
                avatar = "https://divedigital.id/wp-content/uploads/2022/07/1-Blank-TikTok-Default-PFP.jpg";
            }
            userImg.setAttribute("src", avatar);
            Account account = getRandomAccount();
            deepfake = getRandomDeepfake();
            Element accountImg = document.getElementById("accountImg");
            Element accountName = document.getElementById("accountName");
            Element deepfakeInformation = document.getElementById("deepfakeInformation");
            Element videoSource = document.getElementById("sourceVideo");
            accountImg.setAttribute("src", account.getAvatar());
            accountName.setTextContent(account.getUserName());
            deepfakeInformation.setTextContent(deepfake.getLabel());
            videoSource.setAttribute("src", deepfake.getLocation());
            //System.out.println(videoSource.getAttribute("src"));
        }
    }
});
        
     
                
    }
    
    private Account getRandomAccount() {
            List<Account> accounts = AccountDao.getAccounts();
            int min = 0;
            int max = accounts.size();
            int accountNumber = (int) (Math.random() * (max - min) + min);
            return accounts.get(accountNumber);
        }
    
    private Deepfake getRandomDeepfake() {
            Deepfake deepfake = null;
            if(!deepfakes.isEmpty()) {
                int min = 0;
                int max = deepfakes.size();
                int deepfakeNumber = (int) (Math.random() * (max - min) + min);
                deepfake = deepfakes.get(deepfakeNumber);
                deepfakes.remove(deepfakeNumber);
                
            }
            return deepfake;
        }
    
    @FXML
    protected void sendMessage() {
        Label message = new Label(txtMessage.getText().trim());
        message.setPrefWidth(scrollMessage.getWidth() - 30);
        message.setLayoutX(message.getLayoutX() + 10);
        Label answer = new Label("Ik heb het antwoord!");
        answer.setPrefWidth(scrollMessage.getWidth() - 30);
        message.setAlignment(Pos.CENTER_LEFT);
        answer.setAlignment(Pos.CENTER_RIGHT);
        messages.add(message);
        messages.add(answer);
        chatBox.getChildren().add(message);
        chatBox.getChildren().add(answer);
        txtMessage.setText("");
        txtMessage.requestFocus();
        
        
        
        
    }
    
    @FXML
    protected void realPressed(ActionEvent event) throws Exception {
        //System.out.println(btnReal.isSelected());
        //System.out.println(btnFake.isSelected());
        if((deepfake.getReal() == 1 && btnReal.isSelected()) ||
                (deepfake.getReal() == 0 && btnFake.isSelected())) {
                System.out.println("correct");
        } else {
            System.out.println("not correct");
        }
        showQuestions(event);
        /*WebEngine tiktok = webviewTiktok.getEngine();
        tiktok.reload();
         tiktok.getLoadWorker().stateProperty().addListener((observable, oldState, newState) -> {
            if (newState == State.SUCCEEDED) {
        Document document = tiktok.getDocument();
        Account account = getRandomAccount();
            deepfake = getRandomDeepfake();
            Element accountImg = document.getElementById("accountImg");
            Element accountName = document.getElementById("accountName");
            Element deepfakeInformation = document.getElementById("deepfakeInformation");
            Element videoSource = document.getElementById("sourceVideo");
            accountImg.setAttribute("src", account.getAvatar());
            accountName.setTextContent(account.getUserName());
            deepfakeInformation.setTextContent(deepfake.getLabel());
            videoSource.setAttribute("src", deepfake.getLocation());
                 }
         });*/
    }
    
    @FXML
    protected void showGPT() {
        isGPTPressed = !isGPTPressed;
        if(isGPTPressed) {
            pnGPT.setVisible(true);
            txtMessage.requestFocus();
        } else {
            pnGPT.setVisible(false);
        }
        
    }

    @FXML
    protected void showTasks() {
        if(isProfessorPressed) {
            scrollTasks.setVisible(true);
        } else {
            scrollTasks.setVisible(false);
        }
        isProfessorPressed = !isProfessorPressed;
    }
    
    public Pane paneMissionPlay() {
        root = new Pane();
        root.setPrefWidth(700);
        root.setPrefHeight(600);
        //Scene scene = new Scene(root, 700, 600);
        
        URL resourceBoss = getClass().getClassLoader().getResource("assets/textures/tiktokBoss.Jpg");
            BackgroundSize backgroundSize = new BackgroundSize(root.getWidth(), root.getHeight(), false, false, true, true);
            BackgroundImage bossImage = new BackgroundImage(new Image(resourceBoss.toString()), BackgroundRepeat.NO_REPEAT,
            BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
            //bossImage.setFitHeight(scene.getHeight());
            //bossImage.setFitWidth(scene.getWidth());
            root.setBackground(new Background(bossImage));
            URL resourceDialog = getClass().getClassLoader().getResource("assets/textures/dialog-translucent.png");
            ImageView dialogImage = new ImageView(new Image(resourceDialog.toString()));
            //System.out.println(root.getPrefHeight() - 200);
            dialogImage.setTranslateY(root.getPrefHeight() - 230);
            dialogImage.minWidth(800);
            dialogImage.setFitWidth(600);
            dialogImage.setTranslateX(50);
            keyImage = new ImageView(new Image(getClass().getClassLoader().getResource("assets/textures/key-C.png").toString()));
            keyImage.setTranslateY(root.getPrefHeight() - 100);
            keyImage.setTranslateX(588);
            keyImage.setVisible(false);
            
            //dialogImage.set
            
        intro = mission.getIntroduction().replace("<User>", LoginController.getGamer().getUserName());
        
        Label newText = new Label("");
        //newText.set
        whole = "";
        newText.setFont(Font.font(16));
        newText.setLayoutX(60);
        newText.setTranslateY(root.getPrefHeight() - 210);
        newText.setWrapText(true);
        
        //newText.set
        newText.setPrefWidth(550);
        SimpleBooleanProperty test;
        test = new SimpleBooleanProperty(true);
        
        //test.bind(Bindings.when);
        var i = new SimpleIntegerProperty(0);
        root.getChildren().add(dialogImage);
        root.getChildren().add(newText);
        root.getChildren().add(keyImage);
        //final String[] lineArray;
        testTime = new Timeline(new KeyFrame(Duration.millis(20), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                whole += intro.charAt(i.get());
                newText.setText(whole);
                //String[] lineArray = newText.getText().split("\n");
                /*if(lineArray.length > 5) {
                    System.out.println("Groter dan vijf");
                }*/
                i.set(i.get() + 1);
            }
        }));
        keyHandler = (KeyEvent key) -> {
            //KeyCode pressKey = ((KeyEvent)key).getCode();
            if(key.getCode() == KeyCode.C) {
                handler();
                whole = "";
                testTime.play();
                keyImage.setVisible(false);
                root.getScene().removeEventHandler(KeyEvent.KEY_PRESSED, keyHandler);
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
           
            if((newStatus.intValue() > value) && intro.charAt(i.get()) == ' ') {
                keyImage.setVisible(true);
                testTime.pause();
                value = 2 * value;
                root.getScene().addEventHandler(KeyEvent.KEY_PRESSED, keyHandler);
                keyImage.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseHandler);
                
                
            }
            if(newStatus.intValue() == intro.length() - 1) {
                testTime.stop();
                tbTiktok.setDisable(false);
                try {
                    Thread.sleep(1500);
                } catch(InterruptedException ie) {
                    ie.printStackTrace();
                }
                //deepfake = getRandomDeepfake();
                //System.out.println(deepfake.getLabel());
                SingleSelectionModel<Tab> selectionModel = tbPaneMainScreen.getSelectionModel();
                selectionModel.select(tbTiktok);
                CompletedTask.resetCounter();
                tasks = TaskDao.getTasksForDeepfake(deepfake);
                //tasks = new ArrayList<>();
                /*completedTasks = tasks.stream().collect(Collectors.toMap(Function.identity(), (a) -> Boolean.FALSE));
                for(Task task : tasks) {
                    System.out.println(completedTasks.get(task));
                }*/
                double value = 20;
                double plus = 0;
                if(!tasks.isEmpty()) {
                    for(int j = 0; j < tasks.size(); j++) {
                        System.out.println(tasks.get(j).getId() + " " + tasks.get(j).getText());
                    }
                    //Label lblTask = new Label("\u2022\t" + tasks.get(0).getText());
                    //ImageView imageComplete = new ImageView(new Image(getClass().getClassLoader().getResource("assets/icons/checknotcomplete.png").toString()));
                    //imageComplete.setTranslateX(220);
                    tasks.get(0).getImage().setTranslateY(value + plus - 5);
                    tasks.get(0).getImage().setOnMouseClicked((MouseEvent e) -> {
                        completeTask(0);
                    });
                    tasks.get(0).getLabel().setOnMouseClicked((MouseEvent e) -> {
                        completeTask(0);
                    });
                    //lblTask.setTranslateX(10);
                    tasks.get(0).getLabel().setTranslateY(value + plus);
                    
                    pnTasks.getChildren().add(tasks.get(0).getLabel());
                    pnTasks.getChildren().add(tasks.get(0).getImage());
                }
                scrollTasks.setContent(pnTasks);
                scrollTasks.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
                scrollTasks.setVisible(true);
                tglTasks.setDisable(false);
                tgGPT.setDisable(false);
                
                
                /*root.getChildren().add(btnYes);
                root.getChildren().add(btnNo);
                scene.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
                    if(key.getCode() == KeyCode.J) {
                        LoginController.getIntroStage().setScene(sceneMissionPlay());
                        //System.out.println("yes pressed");
                    } else if(key.getCode() == KeyCode.N) {
                        System.out.println("No pressed!");
                    }*/
                //});
                
            }
        });
        
        /*AnalyticsButton analyticsButton = new AnalyticsButton();
        analyticsButton.setTranslateX(root.getWidth() - analyticsButton.getPrefWidth() - 5);
        analyticsButton.setTranslateY(5);
        root.getChildren().add(analyticsButton);*/
        
        return root;
//timer.expire();
        //addUINode()
    }
    
    private void showQuestions(ActionEvent event) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("questions.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 700, 600); 
        Stage stage = new Stage();
        stage.setTitle("Questions");
        stage.setScene(scene);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(
            ((Node)event.getSource()).getScene().getWindow());
        stage.show();
        questions = QuestionDao.getQuestionsForDeepfake(deepfake);
    }
    
    private void handler() {
        whole = "";
        testTime.play();
        keyImage.setVisible(false);
        root.getScene().removeEventHandler(KeyEvent.KEY_PRESSED, keyHandler);
        keyImage.removeEventHandler(MouseEvent.MOUSE_CLICKED, mouseHandler);
    }
    
    private void completeTask(int index) {
        double height = 20;
        if (tasks.get(index).isCompleted()) {
            for(int t = index + 1; t < tasks.size(); t++) {
                System.out.println("t: " + t);
                tasks.get(t).setCompleted(false);
                tasks.get(t).setImage(new Image(CompletedTask.NOT_COMPLETE));
                pnTasks.getChildren().remove(tasks.get(t).getLabel());
                pnTasks.getChildren().remove(tasks.get(t).getImage());
                
            }
            tasks.get(index).setCompleted(false);
            tasks.get(index).setImage(new Image(CompletedTask.NOT_COMPLETE));
        } else {
                tasks.get(index).setCompleted(true);
                tasks.get(index).setImage(new Image(getClass().getClassLoader().getResource("assets/icons/checkcomplete.png").toString()));
                if(++index < tasks.size()) {
                    tasks.get(index).getLabel().setTranslateY(tasks.get(index - 1).getLabel().translateYProperty().doubleValue() + tasks.get(index - 1).getLabel().getHeight() + 5);
                    tasks.get(index).getImage().setTranslateY(tasks.get(index - 1).getImage().translateYProperty().doubleValue() + tasks.get(index - 1).getLabel().getHeight() + 5);
                    final int ind = index;
                    pnTasks.getChildren().add(tasks.get(index).getLabel());
                     tasks.get(index).getImage().setOnMouseClicked((MouseEvent e) -> {
                        completeTask(ind);
                        System.out.println("Index: " + ind);
                    });
                    pnTasks.getChildren().add(tasks.get(index).getImage());
                    
                }   
        }
        int amountOfCompleted = 0;
        for(int i = 1; i < tasks.size(); i++) {
                    if(tasks.get(i).isCompleted()) {
                        System.out.println(tasks.get(i).getLabel().getHeight());
                        height += tasks.get(i).getLabel().getHeight() + 20;
                        amountOfCompleted++;
                    } else if (tasks.get(i - 1).isCompleted()) {
                        System.out.println(tasks.get(i).getText());
                        height += tasks.get(i).getLabel().getHeight() + 20;
                        
                    }
                }
        if(amountOfCompleted == tasks.size() - 1) {
            btnReal.setDisable(false);
            btnFake.setDisable(false);
        } else {
            btnReal.setDisable(true);
            btnFake.setDisable(true);
        }
        
        height += 50;
        pnTasks.setPrefHeight(height);
        pnTasks.setMaxHeight(height);
        System.out.println("height: " + height);
        scrollTasks.setContent(pnTasks);
        scrollTasks.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
    }
    
    public static List<Question> getQuestions() {
        return questions;
    }
    
            
    
}
