package lecture.day7.di.booksearchmvc.dao;

import java.sql.Connection;

public interface ConnectionMaker {
    public Connection getConnection() throws Exception;
}
