package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AdminLoginDao {
	
	public String login(String id, String pw) {

        DBProperty db = new DBProperty();
        String sql = "SELECT id, pw, `name` from tranin_admin where id=? and pw=?";
        int upd = 0;
        try {
            db.pstmt = db.conn.prepareStatement(sql);
            db.pstmt.setString(1, id);
            db.pstmt.setString(2, pw);

            db.rs=db.pstmt.executeQuery();
            System.out.println(db.rs.toString());
            System.out.println(db.pstmt.toString());
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

	public void setCurrentStatusTrue(String memberId) {
		DBProperty db = new DBProperty();
        PreparedStatement pstmt =null;
		String sql = "UPDATE tranin_admin SET current_status=1 where id=?";
        int upd = 0;
        try {
            pstmt = db.conn.prepareStatement(sql);
            pstmt.setString(1, memberId);
            pstmt.executeUpdate();
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
	}
	
	public void setCurrentStatusFalse(String memberId) {
		DBProperty db = new DBProperty();
        PreparedStatement pstmt =null;
		String sql = "UPDATE tranin_admin SET current_status=0 where id=?";
        int upd = 0;
        try {
            pstmt = db.conn.prepareStatement(sql);
            pstmt.setString(1, memberId);
            pstmt.executeUpdate();
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
	}
	
	public int getCurrentStatus(String memberId) {
		DBProperty db = new DBProperty();
		Connection conn = db.conn;
        PreparedStatement pstmt =null;
        ResultSet rs =null;
		String sql = "SELECT current_status FROM tranin_admin WHERE id=?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, memberId);
            rs = pstmt.executeQuery();
            if(rs.next()) {
            	return rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
                if (rs != null) rs.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return 1;
	}
	
	private Long calculateDifference(String date1, String date2, String value) {
        Timestamp date_1 = stringToTimestamp(date1);
        Timestamp date_2 = stringToTimestamp(date2);
        long milliseconds = date_1.getTime() - date_2.getTime();
        if (value.equals("second"))
            return milliseconds / 1000;
        if (value.equals("minute"))
            return milliseconds / 1000 / 60;
        if (value.equals("hours"))
            return milliseconds / 1000 / 3600;
        else
            return new Long(999999999);
    }

	private Timestamp stringToTimestamp(String date) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date parsedDate = dateFormat.parse(date);
            return new Timestamp(parsedDate.getTime());
        } catch (Exception e) {
            return null;
        }
    }

	public boolean alreadyThirtyMinute(String memberId) {
		DBProperty db = new DBProperty();
		Connection conn = db.conn;
        PreparedStatement pstmt =null;
        ResultSet rs =null;
		String sql = "SELECT session_time FROM tranin_admin WHERE id=?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, memberId);
            rs = pstmt.executeQuery();
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            if(rs.next()) {
            	System.out.println(timestamp.toString());
            	System.out.println(rs.getTimestamp(1).toString());
            	System.out.println(calculateDifference(timestamp.toString(),rs.getTimestamp(1).toString(),"minute"));
            	if(calculateDifference(timestamp.toString(),rs.getTimestamp(1).toString(),"minute")>30) {
            		return true;
            	}
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
                if (rs != null) rs.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
	}

	public void setCurrentIP(String remoteAddr,String memberId) {
		DBProperty db = new DBProperty();
        PreparedStatement pstmt =null;
		String sql = "UPDATE tranin_admin SET last_ip=? where id=?";
        int upd = 0;
        try {
            pstmt = db.conn.prepareStatement(sql);
            pstmt.setString(1, remoteAddr);
            pstmt.setString(2, memberId);
            pstmt.executeUpdate();
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
	}

	public String getCurrentIP(String memberId) {
		DBProperty db = new DBProperty();
		Connection conn = db.conn;
        PreparedStatement pstmt =null;
        ResultSet rs =null;
		String sql = "SELECT last_ip FROM tranin_admin WHERE id=?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, memberId);
            rs = pstmt.executeQuery();
            if(rs.next()) {
            	return rs.getString(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
                if (rs != null) rs.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return "아이피가없음";
	}
}
