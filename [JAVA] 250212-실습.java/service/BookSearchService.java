package lecture.day7.di.booksearchmvc.service;

import javafx.collections.ObservableList;
import lecture.day7.di.booksearchmvc.dao.BookDAO;
import lecture.day7.di.booksearchmvc.dao.OracleBookDAO;
import lecture.day7.di.booksearchmvc.dao.OracleConnectionMaker;
import lecture.day7.di.booksearchmvc.vo.Book;

public interface BookSearchService {

    public ObservableList<Book> searchBookByKeyword(String keyword);
    public int updateBook(Book book);
    public int deleteBook(Book book);

}
