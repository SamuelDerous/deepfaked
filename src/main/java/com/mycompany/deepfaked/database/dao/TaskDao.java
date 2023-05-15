/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.deepfaked.database.dao;

import com.mycompany.deepfaked.database.factory.DeepfakedFactory;
import com.mycompany.deepfaked.database.model.CompletedTask;
import com.mycompany.deepfaked.database.model.Deepfake;
import com.mycompany.deepfaked.database.model.Task;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

/**
 *
 * @author ZENODotus
 */
public class TaskDao {
     public static List<CompletedTask> getTasksForDeepfake(Deepfake deepfake) {
        SessionFactory factory = DeepfakedFactory.getSessionFactory();
        try (Session session = factory.openSession()) {
            List<CompletedTask> completed = new ArrayList<>();
            String hql = "from Task where video = :video";
            Query query = session.createQuery(hql, Task.class);
            query.setParameter("video", deepfake.getId());
            List<Task> result = query.getResultList();
            CompletedTask.resetCounter();
            for(int i = 0; i < result.size(); i++) {
                completed.add(new CompletedTask(result.get(i)));
            }
            return completed;
        }
    }
}
