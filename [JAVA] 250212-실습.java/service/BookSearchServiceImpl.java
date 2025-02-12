package lecture.day7.di.booksearchmvc.service;

import javafx.collections.ObservableList;
import lecture.day7.di.booksearchmvc.dao.*;
import lecture.day7.di.booksearchmvc.vo.Book;

public class BookSearchServiceImpl implements BookSearchService {
    private BookDAO bookDAO;
    public BookSearchServiceImpl(BookDAO bookDAO) {
        System.out.println("BookSearchServiceImpl constructor");
        this.bookDAO = bookDAO;
    }

    public ObservableList<Book> searchBookByKeyword(String keyword) {
        System.out.println("BookSearchServiceImpl searchBookByKeyword");
        // 로직 처리
        // 인자로 들어온 키워드를 이용해 Db 조회 -> 리스트로 리턴
        // DB처리를 해아함 -> DAO 생성 후 DB 처리 위임
        return bookDAO.select(keyword);
    }

    public int updateBook(Book book) {
        return 0;
    }

    @Override
    public int deleteBook(Book book) {
        return 0;
    }
}
