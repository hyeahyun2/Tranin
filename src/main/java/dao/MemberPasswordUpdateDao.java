package dao;

public class MemberPasswordUpdateDao {

    public String finding(String pw, String id) {

        DBProperty db = new DBProperty();
        String sql = "update tranin_member set pw=? where id=?";


        try {
            db.pstmt = db.conn.prepareStatement(sql);
            db.pstmt.setString(1, pw);
            db.pstmt.setString(2, id);
            db.pstmt.executeUpdate();

            int upd =db.pstmt.executeUpdate();

            if(upd == 1){
                System.out.println("로그인 정보 일치");
                return "hey";
            } else{
                System.out.println("로그인 불일치");
                return null;
            }


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

    }


}


