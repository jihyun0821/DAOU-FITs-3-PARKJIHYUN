import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

// 책 제목에 특정 keyword가 들어있는 책을 검색해서 출력하는 프로그램을 작성해보세요!
// 일반 Java application으로 작성합니다.
// ISBN, 책제목, 책저자, 책가격 순으로 출력합니다.

public class SecondConnection {
    public static void main(String[] args) {
        try {
          // 1. 드라이버 로드
            Class.forName("oracle.jdbc.driver.OracleDriver");

          // 2. Connection
            String url = "jdbc:oracle:thin:@localhost:1521:xe";
            String username = "C##JDBC_PRACTICE";
            String password = "1234";
            Connection con = DriverManager.getConnection(url, username, password);
          
          // 3. SQL 구문 작성  
            Scanner sc = new Scanner(System.in);
            System.out.print("책 제목을 입력하세요 : ");
            String search_title = sc.nextLine();
//            String sql = "select * from book where title like '%"+search_title+"%'";
//            String sql = "select * from book where BTITLE like '%%%s%%'".formatted(search_title);
            String sql = "select * from book where BTITLE like ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, "%"+search_title+"%");

          // 4. SQL 구문 실행
            ResultSet rs = ps.executeQuery();
            int idx = 1;
          
          // 5. 결과 처리
            while (rs.next()) {
                System.out.print(idx+" ");
                System.out.print(rs.getString("BISBN")+"   ");
                System.out.print(rs.getString("BTITLE")+"   ");
                System.out.print(rs.getString("BAUTHOR")+"   ");
                System.out.println(rs.getString("BPRICE")+"   ");
                System.out.println("-----------------------------------------");
                idx++;

            }
          // 6. 리소스 해제
            rs.close();
            psmt.close();
            con.close();


        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
