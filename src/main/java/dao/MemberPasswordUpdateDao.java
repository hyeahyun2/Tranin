package dao;

public class MemberPasswordUpdateDao {

    public String finding(String pw, String nickname) {

        DBProperty db = new DBProperty();
        String sql = "update tranin_member set pw=? where nickname=?";


        try {
            db.pstmt = db.conn.prepareStatement(sql);
            db.pstmt.setString(1, pw);
            db.pstmt.setString(2, nickname);
            db.pstmt.executeUpdate();


        } catch (Exception e) {
            e.printStackTrace();

            return null;
        } finally {
            try {
                if (db.pstmt != null) db.pstmt.close();
                if (db.conn != null) db.conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return "hey";
    }


}


