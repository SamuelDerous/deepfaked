/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.deepfaked.database.factory;

import com.mycompany.deepfaked.controls.PropertiesHolder;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Singleton class to give access to the vvwbank database.
 */
public class DeepfakedFactory {

    private static final SessionFactory SESSION_FACTORY;

    static {
        try {
            // Create the SessionFactory from hibernate.cfg.xml  
            Configuration c = new Configuration();
            c.configure("hibernate.cfg.xml");
            c.setProperty("hibernate.connection.url", PropertiesHolder.getInstance().getProperty("db.url"));
            c.setProperty("hibernate.connection.username", PropertiesHolder.getInstance().getProperty("db.username"));
            c.setProperty("hibernate.connection.password", PropertiesHolder.getInstance().getProperty("db.password"));
            SESSION_FACTORY = c.buildSessionFactory();
        } catch (HibernateException ex) {
            // Make sure you log the exception, as it might be swallowed  
            
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return SESSION_FACTORY;
    }

}