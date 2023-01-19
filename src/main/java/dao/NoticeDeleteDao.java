package dao;

public class NoticeDeleteDao {

    public boolean deleteNotice(int notice_no) {

        DBProperty db = new DBProperty();
        String sql = "delete from notice_bd where `notice_no`=? ";
        int upd = 0;
        try {
            db.pstmt = db.conn.prepareStatement(sql);
            db.pstmt.setInt(1, notice_no);


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

