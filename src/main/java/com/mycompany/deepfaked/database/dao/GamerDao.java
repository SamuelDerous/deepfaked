/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.deepfaked.database.dao;

import com.mycompany.deepfaked.database.factory.DeepfakedFactory;
import com.mycompany.deepfaked.database.model.Gamer;
import java.math.BigDecimal;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

/**
 *
 * @author ZENODotus
 */
public class GamerDao {
    
    public static boolean gamerDuplicate(String username) {
        SessionFactory factory = DeepfakedFactory.getSessionFactory();
        try (Session session = factory.openSession()) {
            String hql = "from Gamer where userName = :username";
            Query query = session.createQuery(hql, Gamer.class);
            query.setParameter("username", username);
            return !query.getResultList().isEmpty();
        }
    }
    
    public static Gamer getGamer(String username) {
        SessionFactory factory = DeepfakedFactory.getSessionFactory();
        try (Session session = factory.openSession()) {
            String hql = "from Gamer where userName = :username";
            Query query = session.createQuery(hql, Gamer.class);
            query.setParameter("username", username);
            return (Gamer) query.uniqueResult();
        }
    }
    
    public static List<Gamer> getGamers() {
        SessionFactory factory = DeepfakedFactory.getSessionFactory();
        try (Session session = factory.openSession()) {
            String hql = "from Gamer order by followers desc, money desc, userName asc";
            Query query = session.createQuery(hql, Gamer.class);
            return query.getResultList();
        }
    }
    
    public static boolean createGamer(Gamer gamer) {
        SessionFactory factory = DeepfakedFactory.getSessionFactory();
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            session.saveOrUpdate(gamer);
            session.getTransaction().commit();
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
    
    public static boolean addValue(Gamer gamer, double money, int followers) {
        SessionFactory factory = DeepfakedFactory.getSessionFactory();
        try (Session session = factory.openSession()) {
            gamer.setMoney(gamer.getMoney() + money);
            gamer.setFollowers(gamer.getFollowers() + followers);
            session.beginTransaction();
            session.update(gamer);
            session.getTransaction().commit();
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
    
    
    
    
}
