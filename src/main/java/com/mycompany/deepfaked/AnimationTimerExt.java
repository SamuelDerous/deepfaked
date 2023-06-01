package com.mycompany.deepfaked;

import javafx.animation.AnimationTimer;

/**
 * An extention to the animation timer, to let the coins fall.
 * @author ZENODotus
 */

public abstract class AnimationTimerExt extends AnimationTimer {

    private long sleepNs = 0;

    long prevTime = 0;

    public AnimationTimerExt( long sleepMs) {
        this.sleepNs = sleepMs * 1000000;
    }

    @Override
    public void handle(long now) {

         // some delay
        if ((now - prevTime) < sleepNs) {
            return;
        }

        prevTime = now;

        handle();
    }

    public abstract void handle();

}