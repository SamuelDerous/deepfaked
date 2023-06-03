/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.deepfaked.controls;

import java.io.InputStream;
import java.util.Properties;
import static org.hibernate.Hibernate.getClass;

/**
 *
 * @author ZENODotus
 */
public class PropertiesHolder {

    private static final PropertiesHolder INSTANCE = new PropertiesHolder();

    private final Properties props;

    private PropertiesHolder() {
        props = load();
    }

    public static PropertiesHolder getInstance() {
        return INSTANCE;
    }

    public String getProperty(String key) {
        return props.getProperty(key);
    }

    private Properties load() {
        try {
            Properties props = new Properties();
            InputStream in = getClass().getClassLoader().getResourceAsStream("general.properties");
            props.load(in);
            in.close();
            System.out.println(props.toString());
            return props;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
