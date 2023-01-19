package dao;

public class NoticeDeleteinsertDB {


    public boolean insertToDB(String title, String content) {
        DBProperty db = new DBProperty();
        String sql = "insert into notice_deteled_bd (title, content) values(?,?)";
        int upd = 0;
        try {
            db.pstmt = db.conn.prepareStatement(sql);
            db.pstmt.setString(1, title);
            db.pstmt.setString(2, content);

            // 1
            upd = db.pstmt.executeUpdate(); // 영향 받은 행 개수 반환

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

        return (upd == 1); // 성공 -> 1 -> true
    }

}



