module com.mycompany.examenjavafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;
    requires jasperreports;

    opens com.mycompany.examenjavafx to javafx.fxml;
    opens com.mycompany.examenjavafx.models to javafx.base;
    exports com.mycompany.examenjavafx;
}
