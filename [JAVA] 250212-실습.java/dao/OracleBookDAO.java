package lecture.day7.di.booksearchmvc.dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lecture.day7.di.booksearchmvc.vo.Book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class OracleBookDAO implements BookDAO {
    private ConnectionMaker connectionMaker;

    public OracleBookDAO(ConnectionMaker connectionMaker) {
        System.out.println("Oracle BookDAO Constructor");
        this.connectionMaker = connectionMaker;
    }

    @Override
    public ObservableList<Book> select(String keyword) {
        ObservableList<Book> books = FXCollections.observableArrayList();
        String sql = "select * from book where btitle like ?";

        try (Connection conn = connectionMaker.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, "%" + keyword + "%");

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Book book = new Book(
                            rs.getString("BISBN"),
                            rs.getString("BTITLE"),
                            rs.getInt("BPRICE"),
                            rs.getString("BAUTHOR")
                    );
                    books.add(book);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return books;
    }

    @Override
    public int insert(Book book) {
        return 0;
    }

    @Override
    public int update(Book book) {
        return 0;
    }

    @Override
    public int delete(int id) {
        return 0;
    }
}
