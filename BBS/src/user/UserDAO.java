package user;
//외부라이브러리
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDAO {
	
	private Connection conn;  //DB에 접근하게 해주는 객체
	private PreparedStatement pstmt;
	private ResultSet rs;  //어떠한 정보를 담을수 있는 객체
	//DB 커넥션이 이루어질수 있도록 해준다.
	public UserDAO() {
		try {
			String dbURL = "jdbc:mysql://localhost:3306/BBS";  //로컬호스트에 접속
			String dbID = "root";  //자신의 DB 아이디
			String dbPassword = "0735";  //자신의 DB 패스워드
			Class.forName("org.mariadb.jdbc.Driver");  //DB에 접속할수 있도록 매개체역할을 하는 라이브러리
			//세개의 매개변수를 통해 접속할수 있도록한다.
			//접속이 완료되면 conn 객체에 담기게된다.
			conn = DriverManager.getConnection(dbURL, dbID, dbPassword);
		} catch (Exception e) {  //예외처리
			e.printStackTrace();  //오류가발생한경우 어떤오류인지 출력
		}
	} 
	//로그인을 처리하는 함수
	public int login(String userID, String userPassword) {
		String SQL = "SELECT userPassword FROM USER WHERE userID = ?";  //실제로 DB에 입력할 명령어를 SQL문장으로 입력
		try {
			pstmt = conn.prepareStatement(SQL);  //prepareStatement에 어떠한 정해진 SQL문장을 DB에 삽입하는 형식으로 인스턴스를 가져온다.
			//매개변수로 넘어온 userID 를 ? 에 들어갈수있도록 하여, 존재여부확인
			pstmt.setString(1, userID);
			rs = pstmt.executeQuery();  //결과를 담을수 있는 객체(rs)에 실행한결과를 넣어준다.
			if (rs.next()) {
				if(rs.getString(1).equals(userPassword)) {
					return 1;	//로그인 성공
				}
				else
					return 0;	//비밀번호 불일치
			}
			return -1;	//아이디가 없음
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -2;	//데이터베이스 오류
	}
	
	public int join(User user) {
        PreparedStatement pstmt = null;
		String SQL = "INSERT INTO USER VALUES (?, ?, ?, ?, ?)";
		try {
            pstmt = conn.prepareStatement(SQL);
            pstmt.setString(1, user.getUserID());
            pstmt.setString(2, user.getUserPassword());
            pstmt.setString(3, user.getUserName());
            pstmt.setString(4, user.getUserGender());
            pstmt.setString(5, user.getUserEmail());
            return pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return -1;  //데이터베이스 오류
	}

}
