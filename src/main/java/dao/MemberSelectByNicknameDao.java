package dao;

public class MemberSelectByNicknameDao {

    public String slectHimorHer(String nickname) {

        DBProperty db = new DBProperty();
        String sql = "select count(nickname) from tranin_member where nickname=?";
        try {
            db.pstmt = db.conn.prepareStatement(sql);
            db.pstmt.setString(1, nickname);

            db.rs = db.pstmt.executeQuery();

            db.rs.next();

            if(db.rs.getInt(1) == 1){
                return "duplicate";
            }else{
                return "success";
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

