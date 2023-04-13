/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.deepfaked.database.dao;

import com.mycompany.deepfaked.database.factory.DeepfakedFactory;
import com.mycompany.deepfaked.database.model.Account;
import com.mycompany.deepfaked.database.model.Gamer;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

/**
 *
 * @author ZENODotus
 */
public class AccountDao {
    public static List<Account> getAccounts() {
        SessionFactory factory = DeepfakedFactory.getSessionFactory();
        try (Session session = factory.openSession()) {
            String hql = "from Account order by real";
            Query query = session.createQuery(hql, Account.class);
            return query.getResultList();
        }
    }
}
