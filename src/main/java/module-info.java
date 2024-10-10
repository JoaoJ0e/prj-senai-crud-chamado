module br.com.professorclaytonandrade.sistemaservicosjavafx {
    requires javafx.controls;
    requires javafx.fxml;


    opens br.com.professorclaytonandrade.sistemaservicosjavafx to javafx.fxml;
    exports br.com.professorclaytonandrade.sistemaservicosjavafx;
}