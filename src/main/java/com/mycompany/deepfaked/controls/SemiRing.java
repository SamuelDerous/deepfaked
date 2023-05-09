/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.deepfaked.controls;

import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.ArcTo;
import javafx.scene.shape.FillRule;
import javafx.scene.shape.HLineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;

/**
 *
 * @author ZENODotus
 */
public class SemiRing {
    private Path semiRing;
    
    public SemiRing(double centerX, double centerY, double radius, double innerRadius) {
        semiRing = drawSemiRing(centerX, centerY, radius, innerRadius);
    }
    
    private Path drawSemiRing(double centerX, double centerY, double radius, double innerRadius) {
        Stop[] stops = new Stop[] {
         new Stop(0, Color.GREEN),
         new Stop(0.50, Color.ORANGE),
         new Stop(1, Color.RED)
      };
         LinearGradient gradient =
      new LinearGradient(0.0, 0.0, 1.0, 0.0, true, CycleMethod.NO_CYCLE, stops);
        Path path = new Path();
        path.setFill(gradient); // new LinearGradient(0, 0, 0, 10, Color.BLACK, Color.WHITE));
        path.setStroke(Color.BLACK);
        path.setFillRule(FillRule.EVEN_ODD);

        MoveTo moveTo = new MoveTo();
        moveTo.setX(centerX + innerRadius);
        moveTo.setY(centerY);

        ArcTo arcToInner = new ArcTo();
        arcToInner.setX(centerX - innerRadius);
        arcToInner.setY(centerY);
        arcToInner.setRadiusX(innerRadius);
        arcToInner.setRadiusY(innerRadius);

        MoveTo moveTo2 = new MoveTo();
        moveTo2.setX(centerX + innerRadius);
        moveTo2.setY(centerY);

        HLineTo hLineToRightLeg = new HLineTo();
        hLineToRightLeg.setX(centerX + radius);

        ArcTo arcTo = new ArcTo();
        arcTo.setX(centerX - radius);
        arcTo.setY(centerY);
        arcTo.setRadiusX(radius);
        arcTo.setRadiusY(radius);

        HLineTo hLineToLeftLeg = new HLineTo();
        hLineToLeftLeg.setX(centerX - innerRadius);

        path.getElements().add(moveTo);
        path.getElements().add(arcToInner);
        path.getElements().add(moveTo2);
        path.getElements().add(hLineToRightLeg);
        path.getElements().add(arcTo);
        path.getElements().add(hLineToLeftLeg);
        return path;
    }

    public Path getSemiRing() {
        return semiRing;
    }
    
    
}
