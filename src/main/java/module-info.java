module com.mycompany.deepfaked {
    requires java.naming;
    requires javafx.controls;
    requires javafx.web;
    requires javafx.media;
    requires javafx.fxml;
    requires java.base;
    requires jakarta.persistence;
    requires org.hibernate.orm.core;
    requires spring.security.crypto;
    requires uk.co.caprica.vlcj;
    requires uk.co.caprica.vlcj.javafx;
    requires org.json;
    
    opens com.mycompany.deepfaked to javafx.fxml;
    opens com.mycompany.deepfaked.view to javafx.fxml;
    opens com.mycompany.deepfaked.model to javafx.fxml;
    opens com.mycompany.deepfaked.database.model to org.hibernate.orm.core;
    
    exports com.mycompany.deepfaked;
    exports com.mycompany.deepfaked.main;
    exports com.mycompany.deepfaked.view;
    exports com.mycompany.deepfaked.database.model;
}
