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
public class ProgressDeepfakeDao {
    
    public static List<Deepfake> getCompletedDeepfakesforGamer(Gamer gamer) {
        SessionFactory factory = DeepfakedFactory.getSessionFactory();
        try (Session session = factory.openSession()) {
            //ProgressDeepfake deepfak;
            String hql = "select deepfake from ProgressDeepfake where gamer = :gamer";
            Query query = session.createQuery(hql, Deepfake.class);
            query.setParameter("gamer", gamer);
            return query.getResultList();
        }
    }
    
    public static boolean addCompletedDeepfakeForGamer(Gamer gamer, Deepfake deepfake) {
        SessionFactory factory = DeepfakedFactory.getSessionFactory();
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            ProgressDeepfake progress = new ProgressDeepfake();
            progress.setGamer(gamer);
            progress.setDeepfake(deepfake);
            session.saveOrUpdate(progress);
            session.getTransaction().commit();
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
}
