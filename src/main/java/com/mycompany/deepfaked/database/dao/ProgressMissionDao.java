/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.deepfaked.database.dao;

import com.mycompany.deepfaked.database.factory.DeepfakedFactory;
import com.mycompany.deepfaked.database.model.Deepfake;
import com.mycompany.deepfaked.database.model.Gamer;
import com.mycompany.deepfaked.database.model.Mission;
import com.mycompany.deepfaked.database.model.ProgressDeepfake;
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
            String hql = "select mission from ProgressMission where gamer = :gamer";
            Query query = session.createQuery(hql, Mission.class);
            query.setParameter("gamer", gamer);
            return query.getResultList();
        }
    }
    
     public static boolean addCompletedMissionForGamer(Gamer gamer, Mission mission) {
        SessionFactory factory = DeepfakedFactory.getSessionFactory();
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            ProgressMission progress = new ProgressMission();
            progress.setGamer(gamer);
            progress.setMission(mission);
            session.saveOrUpdate(progress);
            session.getTransaction().commit();
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
}
