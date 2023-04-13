/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.deepfaked.database.model;

import java.util.concurrent.atomic.AtomicInteger;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;

/**
 *
 * @author ZENODotus
 */
public class CompletedTask extends Task {
    
    public final static String COMPLETE = CompletedTask.class.getClassLoader().getResource("assets/icons/checkcomplete.png").toString();
    public final static String NOT_COMPLETE = CompletedTask.class.getClassLoader().getResource("assets/icons/checknotcomplete.png").toString();
    private static AtomicInteger count = new AtomicInteger(0);
    
    int id;
    boolean completed;
    Label label;
    ImageView image;
    
    public CompletedTask(Task task) {
        super(task);
        //this.setText("\u2022\t" + this.getText());
        id = count.getAndIncrement();
        this.completed = false;
        label = new Label("\u2022\t" + task.getText());
        label.setTranslateX(20);
        label.setPrefWidth(200);
        label.setFont(Font.font(16));
        label.setWrapText(true);
        image = new ImageView();
        image.setImage(new Image(NOT_COMPLETE));
        image.setTranslateX(220);
        
    }
    
    public static void resetCounter() {
        count = new AtomicInteger(0);
    }

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public Label getLabel() {
        return label;
    }

    public void setLabel(Label label) {
        this.label = label;
    }

    public ImageView getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image.setImage(image);
    }
    
    
    
    
}
