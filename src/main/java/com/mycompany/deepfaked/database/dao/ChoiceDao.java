/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.deepfaked.database.dao;

import com.mycompany.deepfaked.database.factory.DeepfakedFactory;
import com.mycompany.deepfaked.database.model.Question;
import com.mycompany.deepfaked.database.model.QuestionChoice;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

/**
 *
 * @author ZENODotus
 */
public class ChoiceDao {
    
    public static List<QuestionChoice> getChoicesForQuestion(Question question) {
        SessionFactory factory = DeepfakedFactory.getSessionFactory();
        try (Session session = factory.openSession()) {
            String hql = "from QuestionChoice where question = :question";
            Query query = session.createQuery(hql, QuestionChoice.class);
            query.setParameter("question", question);
            return query.getResultList();
        }
    }
}
