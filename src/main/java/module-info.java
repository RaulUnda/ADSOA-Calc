module com.example.calcdist {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.calcdist to javafx.fxml;
    exports com.example.calcdist;
    exports com.example.calcdist.app;
    opens com.example.calcdist.app to javafx.fxml;
    exports com.example.calcdist.Central_Node;
    opens com.example.calcdist.Central_Node to javafx.fxml;
}