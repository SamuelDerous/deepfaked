/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.deepfaked.database.dao;

import com.mycompany.deepfaked.database.factory.DeepfakedFactory;
import com.mycompany.deepfaked.database.model.Deepfake;
import com.mycompany.deepfaked.database.model.Question;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

/**
 *
 * @author ZENODotus
 */
public class QuestionDao {
    
    public static List<Question> getQuestionsForDeepfake(Deepfake deepfake) {
        SessionFactory factory = DeepfakedFactory.getSessionFactory();
        try (Session session = factory.openSession()) {
            String hql = "from Question where video = :video";
            Query query = session.createQuery(hql, Question.class);
            query.setParameter("video", deepfake.getVideoId());
            return query.getResultList();
        }
    }
    
    /*
    For test purposes
    */
    public static List<Question> getAllQuestions() {
        SessionFactory factory = DeepfakedFactory.getSessionFactory();
        try (Session session = factory.openSession()) {
            String hql = "from Question";
            Query query = session.createQuery(hql, Question.class);
            //query.setParameter("video", deepfake.getVideoId());
            return query.getResultList();
        }
    }
}
