/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.deepfaked.database.model;

import java.util.concurrent.atomic.AtomicInteger;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 *
 * @author ZENODotus
 */
public class CompletedTask extends Task {
    
    public final static String COMPLETE = CompletedTask.class.getResource("/assets/icons/checkComplete.png").toString();
    public final static String NOT_COMPLETE = CompletedTask.class.getResource("/assets/icons/checknotcomplete.png").toString();
    private static AtomicInteger count = new AtomicInteger(0);
    
    int id;
    boolean completed;
    Text label;
    ImageView image;
    HBox horizontalBox;
    
    public CompletedTask(Task task) {
        super(task);
        //this.setText("\u2022\t" + this.getText());
        id = count.getAndIncrement();
        this.completed = false;
        label = new Text("\u2022\t" + task.getDescription());
        label.setTranslateX(20);
        //label.setPrefWidth(170);
        label.setWrappingWidth(200);
        label.setFont(Font.font(16));
        image = new ImageView();
        image.setImage(new Image(NOT_COMPLETE));
        image.setTranslateX(50);
        //image.setTranslateX(170);
        horizontalBox = new HBox(label, image);
        horizontalBox.setPrefWidth(200);
        Tooltip tooltip = new Tooltip(this.getLearningObjective().getLabel());
        Tooltip.install(horizontalBox, tooltip);
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

    public Text getLabel() {
        return label;
    }

    public void setLabel(Text label) {
        this.label = label;
    }

    public ImageView getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image.setImage(image);
    }

    public HBox getHorizontalBox() {
        return horizontalBox;
    }

    public void setHorizontalBox(HBox horizontalBox) {
        this.horizontalBox = horizontalBox;
    }
    
    
    
    
    
}
