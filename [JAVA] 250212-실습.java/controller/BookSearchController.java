package lecture.day7.di.booksearchmvc.controller;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import lecture.day7.di.booksearchmvc.service.BookSearchService;
import lecture.day7.di.booksearchmvc.vo.Book;

import java.net.URL;
import java.util.ResourceBundle;

public class BookSearchController implements Initializable {
    private BookSearchService service;

    @FXML
    TextField textField;
    @FXML
    Button searchBtn;
    @FXML
    Button modifyBtn;
    @FXML
    TableView<Book> tableView;
    @FXML TableColumn<Book, String> isbnCol;
    @FXML TableColumn<Book, String> titleCol;
    @FXML TableColumn<Book, String> authorCol;
    @FXML TableColumn<Book, Integer> priceCol;

    // 기본 생성자 반드시 있어야 함
    public BookSearchController() {
        System.out.println("Controller Default Constructor");
    }

    // setter 메서드로 의존성 주입
    public void setService(BookSearchService service) {
        System.out.println("Controller Constructor with Service");
        this.service = service;
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // view가 실행되면서 내부에서 controller 객체가 만들어짐 - 내가 안해도 initialize 메서드가 돌아간다는 뜻인듯
        System.out.println("Book Search Controller");
        // View controls에 대한 이벤트 등록
        // 이벤트 처리 등록하려면  View에 있는 control에 대한 reference 있어야함
        // vo 필드를 해당 컬럼에 설정
        isbnCol.setCellValueFactory(new PropertyValueFactory<>("bisbn"));
        titleCol.setCellValueFactory(new PropertyValueFactory<>("btitle"));
        authorCol.setCellValueFactory(new PropertyValueFactory<>("bauthor"));
        priceCol.setCellValueFactory(new PropertyValueFactory<>("bprice"));

        searchBtn.setOnAction(event -> {
            // 검색 버튼이 클릭 되면 키워드를 이용한 검색 작업 진행
            // 로직 처리를 위해 Service 객체 있어야함

            service.searchBookByKeyword(textField.getText()); // 비즈니스 로직에 근거한 이름 짓기
            ObservableList<Book> list = service.searchBookByKeyword(textField.getText());
            tableView.setItems(list);

        });




    }
}
