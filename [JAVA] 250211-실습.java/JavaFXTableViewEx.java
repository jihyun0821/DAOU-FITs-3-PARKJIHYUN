package lecture.day6.booksearch;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.*;


public class JavaFXTableViewEx extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        // FXML로 화면 구성
        Parent root = null;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("book_table.fxml"));

        try {
            root = fxmlLoader.load();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
