/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.deepfaked.database.dao;

import com.mycompany.deepfaked.database.factory.DeepfakedFactory;
import com.mycompany.deepfaked.database.model.Deepfake;
import com.mycompany.deepfaked.database.model.Gamer;
import com.mycompany.deepfaked.database.model.Mission;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

/**
 *
 * @author ZENODotus
 */
public class DeepfakeDao {
    public static List<Deepfake> getDeepfakesForMission(Mission mission) {
        SessionFactory factory = DeepfakedFactory.getSessionFactory();
        try (Session session = factory.openSession()) {
            String hql = "from Deepfake where mission = :mission";
            Query query = session.createQuery(hql, Gamer.class);
            query.setParameter("mission", mission.getMissionId());
            return query.getResultList();
        }
    }
}
