module br.com.professorclaytonandrade.sistemaservicosjavafx {
    requires javafx.controls;
    requires javafx.fxml;


    opens br.com.professorclaytonandrade.sistemaservicosjavafx to javafx.fxml;
    exports br.com.professorclaytonandrade.sistemaservicosjavafx;
    exports br.com.professorclaytonandrade.sistemaservicosjavafx.controller;
    opens br.com.professorclaytonandrade.sistemaservicosjavafx.controller to javafx.fxml;
}