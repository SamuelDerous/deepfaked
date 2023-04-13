/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.deepfaked.database.dao;

import com.mycompany.deepfaked.database.factory.DeepfakedFactory;
import com.mycompany.deepfaked.database.model.Gamer;
import com.mycompany.deepfaked.database.model.Mission;
import com.mycompany.deepfaked.database.model.ProgressMission;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

/**
 *
 * @author ZENODotus
 */
public class ProgressMissionDao {
    
    public static List<Mission> getMissionsforGamer(Gamer gamer) {
        SessionFactory factory = DeepfakedFactory.getSessionFactory();
        try (Session session = factory.openSession()) {
            ProgressMission mission;
            String hql = "from ProgressMission where gamer = :gamer";
            Query query = session.createQuery(hql, Mission.class);
            query.setParameter("gamer", gamer.getGamerId());
            return query.getResultList();
        }
    }
}
