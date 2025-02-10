import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class TransactionTest {
    public static void main(String[] args) {
        Connection con;
        PreparedStatement pstmt;

        try {
            // 1. 드라이버 로드
            Class.forName("oracle.jdbc.driver.OracleDriver");

            // 2. Connection
            String url = "jdbc:oracle:thin:@localhost:1521:xe";
            String username = "C##JDBC_PRACTICE";
            String password = "1234";
            con = DriverManager.getConnection(url, username, password); // autoCommit mode로 Connection 생성
            // Transaction 설정 시작
            con.setAutoCommit(false); // DB에 영향 안미침4
                // 명시적으로 con.commit() || con.rollback() 호출되면
                // transaction 종료
                // 만약 추가적으로 구문이 나오지 않고 connection이 정상종료되면 con.commit()으로 간주

            // 3. SQL 구문 작성
            String sql = "insert into MEMBERS values(?,?)";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, "3");
            pstmt.setString(2, "kellyy");

            // 4. SQL 구문 실행
            int result = pstmt.executeUpdate();

            // 5. 결과 처리
            System.out.println(result);

//            con.rollback(); // transaction 무효화
            con.commit(); // commit!


            // 6. 리소스 해제
            pstmt.close();
            con.close();

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
}
