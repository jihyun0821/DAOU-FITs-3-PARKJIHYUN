package lecture.day7.di.booksearchmvc.dao;

import javafx.collections.ObservableList;
import lecture.day7.di.booksearchmvc.vo.Book;

public interface BookDAO {
    public ObservableList<Book> select(String keyword);
    public int insert(Book book);
    public int update(Book book);
    public int delete(int id);
}
