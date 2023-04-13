/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.deepfaked.components;

import static com.almasb.fxgl.dsl.FXGL.entityBuilder;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;

/**
 *
 * @author ZENODotus
 */
public class GameFactory implements EntityFactory {
    
    @Spawns("boss")
    public Entity newBoss(SpawnData data) {
        return entityBuilder(data)
                .type(EntityType.BOSS)
                .viewWithBBox("tiktokBoss.jpg")
                .build();
    }
    
    @Spawns("dialog")
    public Entity newDialog(SpawnData data) {
        return entityBuilder(data)
                .type(EntityType.DIALOGBG)
                .viewWithBBox("dialog-translucent.png")
                .scale(1, 0.7)
                .build();
    }
}
