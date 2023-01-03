package dao;

public class MemebrPasswordSelectDao {

    public String finding(String id, String nickName){

        DBProperty db = new DBProperty();
        String sql = "select id, nickname from tranin_member where id=? and nickname=?";


        try {
            db.pstmt = db.conn.prepareStatement(sql);
            db.pstmt.setString(1, id);
            db.pstmt.setString(2, nickName);

            db.rs = db.pstmt.executeQuery();
            while (db.rs.next()) {
                if (nickName.equals(db.rs.getString("nickname"))&&id.equals(db.rs.getString("id"))) {
                    System.out.println("회원 정보 일치");
                    return db.rs.getString("nickname");
                } else {
                    System.out.println("회원 정보 불일치");
                    return null;
                }
            }
        }catch (Exception e) {
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
