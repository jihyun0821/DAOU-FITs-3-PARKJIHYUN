package lecture.day6.booksearch;

import javafx.beans.binding.Bindings;
import javafx.collections.*;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.*;
import javafx.util.converter.IntegerStringConverter;
import lecture.day6.booksearch.vo.Book;

import java.net.URL;
import java.sql.*;
import java.util.*;

public class JavaFXTableViewController implements Initializable {
    @FXML
    private TableView<Book> tableView;
    @FXML
    private Button searchBtn;
    @FXML
    private TextField searchField;

    @FXML
    private TableColumn<Book, String> isbnCol;
    @FXML
    private TableColumn<Book, String> titleCol;
    @FXML
    private TableColumn<Book, Integer> priceCol;
    @FXML
    private TableColumn<Book, String> authorCol;

    static String db_url = "jdbc:oracle:thin:@localhost:1521:xe";
    static String username = "C##JDBC_PRACTICE";
    static String password = "1234";

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // TableView를 편집 가능하도록 설정
        tableView.setEditable(true);

        // === ISBN 컬럼 설정 (문자열) ===
        isbnCol.setCellValueFactory(new PropertyValueFactory<>("bisbn"));
        isbnCol.setCellFactory(TextFieldTableCell.forTableColumn());
        isbnCol.setOnEditCommit(event -> {
            Book book = event.getTableView().getItems().get(event.getTablePosition().getRow());
//            String oldISBN = event.getOldValue(); // 수정 전의 ISBN
            String oldISBN = book.getBisbn();
            String newISBN = event.getNewValue();
            book.setBisbn(newISBN);
            updateBookField("BISBN", newISBN, oldISBN);
        });

        // === 제목 컬럼 설정 (문자열) ===
        titleCol.setCellValueFactory(new PropertyValueFactory<>("btitle"));
        titleCol.setCellFactory(TextFieldTableCell.forTableColumn());
        titleCol.setOnEditCommit(event -> {
            Book book = event.getTableView().getItems().get(event.getTablePosition().getRow());
            String newTitle = event.getNewValue();
            book.setBtitle(newTitle);
            updateBookField("BTITLE", newTitle, book.getBisbn());
        });


        // === 가격 컬럼 설정 (정수) ===
        priceCol.setCellValueFactory(new PropertyValueFactory<>("bprice"));
        // 정수 값을 편집하기 위해 IntegerStringConverter 사용
        priceCol.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        priceCol.setOnEditCommit(event -> {
            Book book = event.getTableView().getItems().get(event.getTablePosition().getRow());
            Integer newPrice = event.getNewValue();
            book.setBprice(newPrice);
            updateBookField("BPRICE", newPrice, book.getBisbn());
        });

        // === 저자 컬럼 설정 (문자열) ===
        authorCol.setCellValueFactory(new PropertyValueFactory<>("bauthor"));
        authorCol.setCellFactory(TextFieldTableCell.forTableColumn());
        authorCol.setOnEditCommit(event -> {
            Book book = event.getTableView().getItems().get(event.getTablePosition().getRow());
            String newAuthor = event.getNewValue();
            book.setBauthor(newAuthor);
            updateBookField("BAUTHOR", newAuthor, book.getBisbn());
        });

        // 행 우클릭 시 컨텍스트 메뉴(삭제 메뉴) 설정
        tableView.setRowFactory(tv -> {
            TableRow<Book> row = new TableRow<>();
            ContextMenu contextMenu = new ContextMenu();

            MenuItem deleteItem = new MenuItem("삭제");
            deleteItem.setOnAction(event -> {
                Book selectedBook = row.getItem();
                if (selectedBook != null) {
                    // DB에서 해당 도서 삭제
                    deleteBook(selectedBook);
                    // TableView에서 삭제
                    tableView.getItems().remove(selectedBook);
                }
            });
            contextMenu.getItems().add(deleteItem);

            // 빈 행에는 컨텍스트 메뉴가 뜨지 않도록 설정
            row.contextMenuProperty().bind(
                    Bindings.when(row.emptyProperty())
                            .then((ContextMenu) null)
                            .otherwise(contextMenu)
            );
            return row;
        });

        // 검색 버튼 클릭 시 TableView에 데이터 채우기
        searchBtn.setOnAction(event -> {
            ObservableList<Book> books = FXCollections.observableArrayList();
            String searchText = searchField.getText();
            System.out.println("검색어: " + searchText);

            try {
                // Oracle JDBC 드라이버 로드
                Class.forName("oracle.jdbc.driver.OracleDriver");

                Connection con = DriverManager.getConnection(db_url, username, password);

                String sql = "SELECT * FROM BOOK WHERE BTITLE LIKE ?";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, "%" + searchText + "%");
                ResultSet rs = ps.executeQuery();

                while (rs.next()) {
                    // Book 생성 시, 기본키(ID)도 포함되어 있어야 합니다.
                    books.add(new Book(
                            rs.getString("BISBN"),
                            rs.getString("BTITLE"),
                            rs.getInt("BPRICE"),
                            rs.getString("BAUTHOR")
                    ));
                }
                tableView.setItems(books);

                // 자원 해제
                rs.close();
                ps.close();
                con.close();
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
        });
    }

    private void updateBookField(String columnName, Object newValue, Object keyValue) {
        // 허용된 컬럼명 목록 (대소문자 주의: DB 컬럼명에 맞게 지정)
        List<String> allowedColumns = Arrays.asList("BISBN", "BTITLE", "BPRICE", "BAUTHOR");
        if (!allowedColumns.contains(columnName)) {
            throw new IllegalArgumentException("허용되지 않은 컬럼명: " + columnName);
        }

        // 예시 SQL: 키로 ISBN을 사용
        String sql = "UPDATE BOOK SET " + columnName + " = ? WHERE BISBN = ?";

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            try (Connection con = DriverManager.getConnection(db_url, username, password);
                 PreparedStatement ps = con.prepareStatement(sql)) {

                // newValue의 타입에 따라 PreparedStatement의 setter 선택
                if (newValue instanceof String) {
                    ps.setString(1, (String) newValue);
                } else if (newValue instanceof Integer) {
                    ps.setInt(1, (Integer) newValue);
                } else {
                    ps.setObject(1, newValue);
                }

                // keyValue는 기본적으로 문자열(ISBN)이라고 가정
                ps.setString(2, keyValue.toString());

                int affectedRows = ps.executeUpdate();
                if (affectedRows == 0) {
                    System.err.println("DB 업데이트 실패: " + columnName + " 컬럼이 업데이트되지 않음.");
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    private void deleteBook(Book book) {
        String sql = "DELETE FROM BOOK WHERE BISBN = ?";
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection(db_url, username, password);
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, book.getBisbn());

            int affectedRows = ps.executeUpdate();
            if (affectedRows == 0) {
                System.err.println("DB 삭제 실패: " + book.getBisbn() + " 삭제되지 않음.");
            }
            ps.close();
            con.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

}
