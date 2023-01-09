package dao;

public class AdminLoginDao {
	
	DBProperty dbProperty = null;

	public String login(String id, String pw) {

        DBProperty db = new DBProperty();
        String sql = "SELECT id, pw, name from tranin_admin where id=? and pw=?";
        int upd = 0;
        try {
            db.pstmt = db.conn.prepareStatement(sql);
            db.pstmt.setString(1, id);
            db.pstmt.setString(2, pw);

            db.rs=db.pstmt.executeQuery();
            while(db.rs.next()){
                if(id.equals(db.rs.getString("id"))){
                    return db.rs.getString("id");
                }else{
                    System.out.println("로그인 정보 불일치");
                    return null;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (db.pstmt != null) db.pstmt.close();
                if (db.conn != null) db.conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return null;
    }
}
