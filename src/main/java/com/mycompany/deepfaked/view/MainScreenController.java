/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.deepfaked.view;

import com.mycompany.deepfaked.Intro;
import com.mycompany.deepfaked.controls.AnalyticsButton;
import com.mycompany.deepfaked.controls.DeepfakeDetectionPane;
import com.mycompany.deepfaked.controls.WebsitePane;
import com.mycompany.deepfaked.database.dao.AccountDao;
import com.mycompany.deepfaked.database.dao.DeepfakeDao;
import com.mycompany.deepfaked.database.dao.GamerDao;
import com.mycompany.deepfaked.database.dao.MissionsDao;
import com.mycompany.deepfaked.database.dao.ProgressDeepfakeDao;
import com.mycompany.deepfaked.database.dao.ProgressMissionDao;
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
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.concurrent.Worker.State;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
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
    
    private final Image imageProfessor = new Image(getClass().getResource("/assets/avatars/professor.jpg").toString());
    private final Image imageProfessorRevert = new Image(getClass().getResource("/assets/avatars/professorRevert.jpg").toString());
        
    private double incMoney, totalMoney;
    private int incFollowers, totalFollowers;
    private EventHandler<KeyEvent> keyHandler;
    private EventHandler<KeyEvent> escapeHandler;
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
    private boolean deepfakeCorrect;
    private boolean real;
    
    @FXML
    private AnchorPane anchorRoot;
    
    @FXML
    private TabPane tbPaneMainScreen;

    @FXML
    private WebView webviewTiktok;
    
    private Pane pnTasks;
    
    private DeepfakeDetectionPane pnScanning;
    
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
    private ImageView btnProfessor;
    
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
    
    @FXML
    private Tab tbPersonal;
    
    @FXML
    private Button btnNext;
    
    @FXML
    private AnchorPane anchorPersonal;
    //@FXML
    private WebView webviewPersonal;
    
    @FXML
    private ProgressBar prDeepfake;
    
    @FXML
    private ProgressBar prMission;
    
    @FXML
    private ToggleButton tglFactCheck;
    
    @FXML
    private ToggleButton tglDeepfakeDetection;
    
    @FXML
    private ToggleButton tglSearchEngines;
    
    @FXML
    private Label lblMoney;
    
    @FXML
    private Label lblFollowers;
    
    private boolean isFactCheckPressed;
    
    private boolean isSearchEnginePressed;
    
    private boolean isScanningPressed;
    
    private boolean isScanningCreated;
    
    private WebsitePane factCheckPane;
    
    private WebsitePane searchEnginePane;
    
    private int progressDeepfake;
    private int progressMission;
    private int progress;
    
    private int begin;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        AnalyticsButton analyticsButton = new AnalyticsButton();
        System.out.println(anchorRoot.getPrefWidth());
        analyticsButton.setTranslateX(anchorRoot.getPrefWidth() - 150);
        analyticsButton.setTranslateY(12);
        anchorRoot.getChildren().add(analyticsButton);
        prMission.setProgress(0);
        progress = 0;
        //progressDeepfake = 0; //DeepfakeDao.getDeepfakesForMission(mission).size();
        prDeepfake.setProgress(0);
        tbPaneMainScreen.getTabs().remove(tbPersonal);
        gamer = LoginController.getGamer();
        lblMoney.setText("€ " + gamer.getMoney() + "0");
        lblFollowers.setText("" + gamer.getFollowers());
        pnTasks = new Pane();
        isScanningPressed = false;
        isScanningCreated = false;
        
        //pnTasks.setPrefHeight(200);
        scrollTasks.setVisible(false);
        scrollTasks.getStylesheets().add(this.getClass().getResource("/assets/border.css").toString());
        scrollTasks.getStyleClass().add("paneTasks");
        //pnTasks.setStyle("-fx-background-color: #f2f2f2");
        //pnTasks.setStyle("-fx-border-color: black");
        //pnTasks.getChildren().add(new Label("Dit is een test"));
        mission = Intro.getMission();
        tbInformation.setContent(paneMissionPlay());
        root.sceneProperty().addListener((observable, oldScene, newScene) -> {
        if (newScene != null) {
          root.getScene().addEventHandler(KeyEvent.KEY_PRESSED, escapeHandler);
        }
      });
        
        deepfakes = DeepfakeDao.getDeepfakesForMission(mission);
        
        List<Deepfake> newList = new ArrayList<>();
        List<Deepfake> completedDeepfakes = ProgressDeepfakeDao.getCompletedDeepfakesforGamer(gamer);
        for(Deepfake deep : deepfakes) {
            boolean istaken = false;
            for(Deepfake completed : completedDeepfakes) {
                if(deep.getId() == completed.getId()) {
                    progress += TaskDao.getTasksForDeepfake(deep).size();
                    progress += QuestionDao.getQuestionsForDeepfake(deep).size();
                    istaken = true;
                }
                
            }
            progressMission += TaskDao.getTasksForDeepfake(deep).size();
            progressMission += QuestionDao.getQuestionsForDeepfake(deep).size();
            if(!istaken) {
                newList.add(deep);
            }
            
        }
        deepfakes.clear();
        deepfakes.addAll(newList);
        
        prMission.setProgress(progress * 1.0 / progressMission);
        
        isProfessorPressed = false;
        isGPTPressed = false;
        isFactCheckPressed = false;
        isSearchEnginePressed = false;
        factCheckPane = new WebsitePane();
        factCheckPane.createPane("factcheck", 96, 80);
        factCheckPane.getPane().setVisible(false);
        anchorRoot.getChildren().add(factCheckPane.getPane());
        
        searchEnginePane = new WebsitePane();
        searchEnginePane.createPane("searchEngine", 256, 80);
        searchEnginePane.getPane().setVisible(false);
        anchorRoot.getChildren().add(searchEnginePane.getPane());
        
        //isTaskComplete = false;
        tglTasks.setDisable(true);
        tglFactCheck.setDisable(true);
        tgGPT.setDisable(true);
        tglDeepfakeDetection.setDisable(true);
        tglSearchEngines.setDisable(true);
        chatBox.setPadding(new Insets(10, 10, 10, 10));
        scrollMessage.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollMessage.setContent(chatBox);
        //pnGPT.setBorder(new Border(new BorderStroke()));
        /*for(int i = 0; i < deepfakes.size(); i++) {
            System.out.println(deepfakes.get(i).getLocation());
        }*/
        createTiktokDeepfake();
        
     
                
    }
    
    private int checkMissions() {
        List<Mission> missions = MissionsDao.getMissions();
        List<Mission> missionsOfGamer = ProgressMissionDao.getMissionsforGamer(gamer);
        return missions.size() - missionsOfGamer.size();
    }
    
    private void createTiktokDeepfake() {
        btnReal.setSelected(false);
        btnFake.setSelected(false);
        btnReal.setDisable(true);
        btnFake.setDisable(true);
        deepfake = getRandomDeepfake();
        progressDeepfake = TaskDao.getTasksForDeepfake(deepfake).size();
        progressDeepfake += QuestionDao.getQuestionsForDeepfake(deepfake).size();
        
        WebEngine webEngine = webviewTiktok.getEngine();
        webEngine.loadContent("");
        webEngine.load(getClass().getResource("/assets/html/tiktokMovie.html").toString());
        webEngine.getLoadWorker().stateProperty().addListener((observable, oldState, newState) -> {
            if (newState == State.SUCCEEDED) {
                Document document = webEngine.getDocument();
                if(document != null) {
            Element userImg = document.getElementById("userImg");
            String avatar = gamer.getAvatar();
            if(avatar == null || avatar.isBlank()) {
                avatar = "https://divedigital.id/wp-content/uploads/2022/07/1-Blank-TikTok-Default-PFP.jpg";
            }
            userImg.setAttribute("src", avatar);
            Account account = getRandomAccount();
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
                deepfakeCorrect = true;
                real = true;
        } else {
            deepfakeCorrect = false;
            real = false;
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
    protected void showScanning() {
        isScanningPressed = !isScanningPressed;
        System.out.println("test");
        createScanning();
        /*if(isscanningPressed) {
            createScanning();
        } else {
            pnGPT.setVisible(false);
        }*/
        
    }
    
    private void createScanning() {
        if(isScanningPressed) {
            if(!isScanningCreated) {
                isScanningCreated = true;
                pnScanning = new DeepfakeDetectionPane(deepfake);
                pnScanning.createPane(178, 80);
                pnScanning.getPane().setVisible(true);
                anchorRoot.getChildren().add(pnScanning.getPane());
               
            } else {
                pnScanning.getPane().setVisible(true);
            }
        } else {
            pnScanning.getPane().setVisible(false);
        }
    }

    @FXML
    protected void showTasks() {
        if(isProfessorPressed) {
            btnProfessor.setImage(imageProfessorRevert);
            scrollTasks.setVisible(true);
        } else {
            btnProfessor.setImage(imageProfessor);
            scrollTasks.setVisible(false);
        }
        isProfessorPressed = !isProfessorPressed;
    }
    
    @FXML
    protected void showFactChecks() {
        isFactCheckPressed = !isFactCheckPressed;
        factCheckPane.getPane().setVisible(isFactCheckPressed);
    }
    
    @FXML
    protected void showSearchEngines() {
        isSearchEnginePressed = !isSearchEnginePressed;
        searchEnginePane.getPane().setVisible(isSearchEnginePressed);
    }
    
    public Pane paneMissionPlay() {
        root = new Pane();
        root.setPrefWidth(700);
        root.setPrefHeight(600);
        //Scene scene = new Scene(root, 700, 600);
        int randomImage = (int)(Math.random() * 3 + 1);
        URL resourceBoss = getClass().getResource("/assets/textures/tiktokBoss.Jpg");
            BackgroundSize backgroundSize = new BackgroundSize(root.getWidth(), root.getHeight(), false, false, true, true);
            BackgroundImage bossImage = new BackgroundImage(Intro.getOwnerImages().get(randomImage), BackgroundRepeat.NO_REPEAT,
            BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
            //bossImage.setFitHeight(scene.getHeight());
            //bossImage.setFitWidth(scene.getWidth());
            root.setBackground(new Background(bossImage));
            URL resourceDialog = getClass().getResource("/assets/textures/dialog-translucent.png");
            ImageView dialogImage = new ImageView(new Image(resourceDialog.toString()));
            //System.out.println(root.getPrefHeight() - 200);
            dialogImage.setTranslateY(root.getPrefHeight() - 230);
            dialogImage.minWidth(800);
            dialogImage.setFitWidth(600);
            dialogImage.setTranslateX(50);
            keyImage = new ImageView(new Image(getClass().getResource("/assets/textures/key-C.png").toString()));
            keyImage.setTranslateY(root.getPrefHeight() - 100);
            keyImage.setTranslateX(588);
            keyImage.setVisible(false);
            
            //dialogImage.set
            
        intro = mission.getIntroduction().replace("<User>", LoginController.getGamer().getUserName());
        intro += "\n\n";
        intro += "Het doel van deze missie is " + mission.getGoal().getName() + " (" + mission.getGoal().getDescription() + ").";
        
        
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
        var i = new SimpleIntegerProperty(0);
        
        //test.bind(Bindings.when);
        
        root.getChildren().add(dialogImage);
        root.getChildren().add(newText);
        root.getChildren().add(keyImage);
        //final String[] lineArray;
        testTime = new Timeline(new KeyFrame(Duration.millis(20), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //i.set(i.get() + 1);
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
        begin = 0;
        escapeHandler = (KeyEvent key) -> {
            if(key.getCode() == KeyCode.ESCAPE) {
                if(intro.length() < value) {
                    newText.setText(intro.substring(begin, value < intro.length() ? value : intro.length()));
                    long timestamp = System.currentTimeMillis();
                testTime.stop();
                keyImage.setVisible(false);
                root.getScene().removeEventHandler(KeyEvent.KEY_PRESSED, keyHandler);
                keyImage.removeEventHandler(MouseEvent.MOUSE_CLICKED, mouseHandler);
                PauseTransition pause = new PauseTransition(Duration.seconds(10));
                pause.setOnFinished(event -> {
                    
                    tbTiktok.setDisable(false);
                    SingleSelectionModel<Tab> selectionModel = tbPaneMainScreen.getSelectionModel();
                    selectionModel.select(tbTiktok);
                    setTaskPane();
                });
                pause.play();
                } else {
                    newText.setText(intro.substring(begin, value < intro.length() ? value : intro.length()));
                    begin = value - 1;
                    keyImage.setVisible(true);
                testTime.pause();
                value = 2 * value;
                root.getScene().addEventHandler(KeyEvent.KEY_PRESSED, keyHandler);
                keyImage.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseHandler);
                }
                

            }
        };
        
        
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
            if((newStatus.intValue() >= intro.length())) {
                System.out.println(newStatus.intValue() + " == " + intro.length());
                //System.out.println(whole.charAt(i.get()));
                long timestamp = System.currentTimeMillis();
                testTime.stop();
                PauseTransition pause = new PauseTransition(Duration.seconds(10));
                pause.setOnFinished(event -> {
                    
                    tbTiktok.setDisable(false);
                    SingleSelectionModel<Tab> selectionModel = tbPaneMainScreen.getSelectionModel();
                    selectionModel.select(tbTiktok);
                    setTaskPane();
                });
                pause.play();
                
                /*
                //deepfake = getRandomDeepfake();
                //System.out.println(deepfake.getLabel());
                SingleSelectionModel<Tab> selectionModel = tbPaneMainScreen.getSelectionModel();
                selectionModel.select(tbTiktok);
                setTaskPane();*/
                
                
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
    
    private void setTaskPane() {
        pnTasks.setPrefHeight(50);
        pnTasks.setMaxHeight(50);
        CompletedTask.resetCounter();
        pnTasks.getChildren().clear();
        //tasks.clear();
        System.out.println(deepfake.getLabel());
                tasks = TaskDao.getTasksForDeepfake(deepfake);
                //tasks = new ArrayList<>();
                /*completedTasks = tasks.stream().collect(Collectors.toMap(Function.identity(), (a) -> Boolean.FALSE));
                for(Task task : tasks) {
                    System.out.println(completedTasks.get(task));
                }*/
                double value = 20;
                double plus = 0;
                if(!tasks.isEmpty()) {
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
                    tasks.get(0).getLabel().setTooltip(new Tooltip(tasks.get(0).getLearningObjective().getLabel()));
                    pnTasks.getChildren().add(tasks.get(0).getLabel());
                    pnTasks.getChildren().add(tasks.get(0).getImage());
                }
                scrollTasks.setContent(pnTasks);
                scrollTasks.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
                scrollTasks.setVisible(true);
                tglTasks.setDisable(false);
                isProfessorPressed = true;
                showTasks();
                tgGPT.setDisable(false);
                tglDeepfakeDetection.setDisable(false);
                tglSearchEngines.setDisable(false);
                tglFactCheck.setDisable(false);
                
    }
    
    private void showQuestions(ActionEvent event) throws Exception {
        QuestionsController controller = new QuestionsController(this);
        questions = QuestionDao.getQuestionsForDeepfake(deepfake);
    }
    
    public ToggleButton getParent() {
        return btnReal;
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
                tasks.get(t).setCompleted(false);
                tasks.get(t).setImage(new Image(CompletedTask.NOT_COMPLETE));
                pnTasks.getChildren().remove(tasks.get(t).getLabel());
                pnTasks.getChildren().remove(tasks.get(t).getImage());
                
            }
            tasks.get(index).setCompleted(false);
            tasks.get(index).setImage(new Image(CompletedTask.NOT_COMPLETE));
        } else {
                tasks.get(index).setCompleted(true);
                tasks.get(index).setImage(new Image(getClass().getResource("/assets/icons/checkComplete.png").toString()));
                if(++index < tasks.size()) {
                    tasks.get(index).getLabel().setTranslateY(tasks.get(index - 1).getLabel().translateYProperty().doubleValue() + tasks.get(index - 1).getLabel().getHeight() + 5);
                    tasks.get(index).getImage().setTranslateY(tasks.get(index - 1).getImage().translateYProperty().doubleValue() + tasks.get(index - 1).getLabel().getHeight() + 5);
                    final int ind = index;
                    tasks.get(index).getLabel().setTooltip(new Tooltip(tasks.get(index).getLearningObjective().getLabel()));
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
                        height += tasks.get(i).getLabel().getHeight() + 20;
                        amountOfCompleted++;
                    } else if (tasks.get(i - 1).isCompleted()) {
                        System.out.println(tasks.get(i).getDescription());
                        height += tasks.get(i).getLabel().getHeight() + 20;
                        
                    }
                }
        prDeepfake.setProgress(prDeepfake.getProgress() + (1.0 / progressDeepfake));
        prMission.setProgress(prMission.getProgress() + (1.0 / progressMission));
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

    public void setPrDeepfakeProgress() {
        prDeepfake.setProgress(prDeepfake.getProgress() + (1.0 / progressDeepfake));
        prMission.setProgress(prMission.getProgress() + (1.0 / progressMission));
    }
    
    
    
    @FXML
    protected void loadNext() {
        ProgressDeepfakeDao.addCompletedDeepfakeForGamer(gamer, deepfake);
        tbPaneMainScreen.getTabs().remove(tbPersonal);
        double totalMoney = QuestionsController.getMoney();
        int totalFollowers = QuestionsController.getFollowers();
        if(deepfakeCorrect) {
            totalMoney += deepfake.getValue().getMoney();
            totalFollowers += deepfake.getValue().getFollowers();
        }
        GamerDao.addValue(gamer, totalMoney, totalFollowers);
        
        if(deepfakes == null || deepfakes.isEmpty()) {
            ProgressMissionDao.addCompletedMissionForGamer(gamer, mission);
            if(checkMissions() > 0) {
                Intro intro = new Intro(this, "Geweldig gedaan. Je hebt de missie voltooid. Ga verder naar een andere missie.");
            } else {
                Intro intro = new Intro(this, "Geweldig gedaan. Je hebt alle missis voltooid.");
            }
            Intro.getStage().close();
        } else {
            createTiktokDeepfake();
            tbTiktok.setDisable(false);
            SingleSelectionModel<Tab> selectionModel = tbPaneMainScreen.getSelectionModel();
            selectionModel.select(tbTiktok);
            setTaskPane();
        }
        prDeepfake.setProgress(0);
    }
    
    public void createPersonalTiktok() {
        tbPaneMainScreen.getTabs().add(tbPersonal);
        scrollTasks.setVisible(false);
        tglTasks.setSelected(false);
        tglTasks.setDisable(true);
        pnGPT.setVisible(false);
        tgGPT.setSelected(false);
        tgGPT.setDisable(true);
        tbTiktok.setDisable(true);
        isGPTPressed = false;
        isProfessorPressed = false;
        System.out.print("test tiktok");
        SingleSelectionModel<Tab> selectionModel = tbPaneMainScreen.getSelectionModel();
        selectionModel.select(tbPersonal);
        webviewPersonal = new WebView();
        webviewPersonal.setPrefSize(706, 492);
        anchorPersonal.getChildren().add(webviewPersonal);
        //lblMoney.setText("€ " + QuestionsController.getMoney() + "0");
        //lblFollowers.setText("" + QuestionsController.getFollowers());
        totalMoney = gamer.getMoney() + QuestionsController.getMoney();
        totalFollowers = gamer.getFollowers() + QuestionsController.getFollowers();
        if(deepfakeCorrect) {
            totalMoney += deepfake.getValue().getMoney();
            totalFollowers += deepfake.getValue().getFollowers();
        }
        incMoney = gamer.getMoney();
        incFollowers = gamer.getFollowers();
        Timeline timelineMoney = new Timeline(new KeyFrame(Duration.millis(20), (ActionEvent event) -> {
             if(incMoney < totalMoney) {
                 lblMoney.setText("€ " + ++incMoney + "0");
                 
             }
        }));
         
         Timeline timelineFollowers = new Timeline(new KeyFrame(Duration.millis(20), (ActionEvent event) -> {
            if(incFollowers < totalFollowers) {
                lblFollowers.setText("" + ++incFollowers);
                
            }
        }));
         timelineMoney.setCycleCount(Animation.INDEFINITE);
        timelineFollowers.setCycleCount(Animation.INDEFINITE);
        
         timelineMoney.play();
         timelineFollowers.play();
        
         
        WebEngine webEngine = webviewPersonal.getEngine();
        webEngine.loadContent("");
        webEngine.load(this.getClass().getResource("/assets/html/result.html").toString());
        webEngine.getLoadWorker().stateProperty().addListener((observable, oldState, newState) -> {
            if (newState == State.SUCCEEDED) {
                
                Document document = webEngine.getDocument();
                int amount = 0;
                int comments = 20;
                int sharing = 0;
                if(deepfakeCorrect) {
                    amount = deepfake.getValue().getFollowers();
                    comments = amount - 40;
                    sharing = 150;
                    webEngine.executeScript("comments[0] = \"Je bent een legende\";" 
                            + "comments[1] = \"Wow, dit is helemaal nieuw voor me\";"
                            + "comments[2] = \"Geweldig dat je dit onder onze aandacht brengt\";"
                            + "comments[3] = \"*Neemt zijn kaak op van de vloer*\";"
                            + "comments[4] = \"Ik heb deze video al 600 keer bekeken!\";"
                            + "comments[5] = \"OMG, dit is zo belangrijk!\";"
                            + "comments[6] = \"Jij bent de belangrijkste persoon op Tiktok!\";"
                            + "comments[7] = \"Kon het niet meer met iemand eens zijn dan met jou!\";"
                            + "comments[8] = \"Tiktok is geen wedstrijd, maar jij hebt toch gewonnen!\";"
                            + "comments[9] = \"Wow, dit is het belangrijkste dat iemand ooit op Tiktok heeft verspreid\";"
                            + "comments[10] = \"Dit ga ik delen!\";"
                            + "comments[11] = \"Iedereen moet dit weten!\";"
                            + "comments[12] = \"*Maakt een buiging*\";");
                } else {
                    webEngine.executeScript("comments[0] = \"Maak toch iemand anders iets wijs\";" 
                            + "comments[1] = \"Allez, weer iemand die denkt het licht uitegevonden te hebben\";"
                            + "comments[2] = \"Ga toch van Tiktok af\";"
                            + "comments[3] = \"Dit is zo nep wat jij zegt!\";"
                            + "comments[4] = \"Ik geloof er geen snars van\";"
                            + "comments[5] = \"Man, niemand gelooft jou!\";"
                            + "comments[6] = \"Ik denk dat ik dommer ben geworden sinds ik jouw bericht heb gezien\";"
                            + "comments[7] = \"Geef je dagjob maar nog niet op\";"
                            + "comments[8] = \"Jou volgt toch niemand?\";"
                            + "comments[9] = \"Als je denkt hiermee volgers aan te trekken...\";"
                            + "comments[10] = \"Dit is zo beschamend!\";"
                            + "comments[11] = \"Hoe oud ben jij, vijf?\";"
                            + "comments[12] = \"Dit zou verboden moeten worden\";");
                }
                webEngine.executeScript("runner()");
                webEngine.executeScript("" + 
                "setInterval(function() {if(fol < " + amount + ") {followers.innerHTML = fol + 1; fol++;}}, 10);" +
                "setInterval(function() {if(com < " + comments + ") {comment.innerHTML = com + 1; com++;}}, 10);" +
                "setInterval(function() {if(shar < " + sharing + ") {sharing.innerHTML = shar + 1; shar++;}}, 10);");
                if(document != null) {
                    Element userImg = document.getElementById("userImg");
                    String source = userImg.getAttribute("src");
                    String avatar = gamer.getAvatar();
                    if(avatar == null || avatar.isBlank()) {
                        avatar = "https://divedigital.id/wp-content/uploads/2022/07/1-Blank-TikTok-Default-PFP.jpg";
                    }
                    userImg.setAttribute("src", avatar);
                    
                    //deepfake = getRandomDeepfake();
                    Element accountImg = document.getElementById("accountImg");
                    Element accountName = document.getElementById("accountName");
                    Element deepfakeInformation = document.getElementById("deepfakeInformation");
                    Element videoSource = document.getElementById("sourceVideo");
                    accountImg.setAttribute("src", avatar);
                    accountName.setTextContent(gamer.getUserName());
                    String comment;
                    if(real) {
                        comment = deepfake.getRealComment();
                    } else {
                        comment = deepfake.getFakeComment();
                    }
                    deepfakeInformation.setTextContent(comment);
                    videoSource.setAttribute("src", deepfake.getLocation());
                    
            //deepfakeInformation.setTextContent(deepfake.getLabel());
            //videoSource.setAttribute("src", deepfake.getLocation());
            //System.out.println(videoSource.getAttribute("src"));
                }
            }
        });
        //tbPersonal.
    }
    
    public static List<Question> getQuestions() {
        return questions;
    }

    
    
            
    
}
