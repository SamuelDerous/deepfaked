/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.deepfaked.view;

/**
 *
 * @author ZENODotus
 */
public class InfoFactory {
    
    public static IntroView createIntroView(MainScreenController controller, String intro) {
        return new IntroView(controller, intro);
    }
    
    public static IntroView createIntroView() {
        return new IntroView();
    }
    
    public static MissionView createMissionView(MainScreenController controller, String intro) {
        return new MissionView(controller, intro);
    }
    
    public static MissionView createMissionView() {
        return new MissionView();
    }
    
    public static TextOnlyView createTextOnlyView(MainScreenController controller, String intro) {
        return new TextOnlyView(controller, intro);
    }
    
    public static TextOnlyView createTextOnlyView() {
        return new TextOnlyView();
    }
}
