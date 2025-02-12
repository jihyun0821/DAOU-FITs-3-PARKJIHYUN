package lecture.day7.di.booksearchmvc.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lecture.day7.di.booksearchmvc.controller.BookSearchController;
import lecture.day7.di.booksearchmvc.dao.*;
import lecture.day7.di.booksearchmvc.service.*;


public class BookSearchView extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        // 싱글턴 OracleConnectionMaker 사용
        ConnectionMaker connectionMaker = OracleConnectionMaker.getInstance();
        BookDAO bookDAO = new OracleBookDAO(connectionMaker);
        BookSearchService service = new BookSearchServiceImpl(bookDAO);

        // FXML로 화면 구성
        // Stage - scene - Parent(Layout manager)
        Parent root = null;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("booksearchmvc.fxml"));



        try {
            root = fxmlLoader.load();
            BookSearchController controller = fxmlLoader.getController();
            controller.setService(service);  // BookSearchController에 setService(BookSearchService service) 메서드 작성 필요
        } catch (Exception e) {
            e.printStackTrace();
        }

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Book Search");
        stage.show();
    }

}
