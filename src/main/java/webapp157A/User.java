package webapp157A;

import com.mysql.jdbc.TimeUtil;

import java.sql.Timestamp;
import java.util.Date;

public class User {

    private String userId;
    private String password;
    private Date dateJoined = new Timestamp(System.currentTimeMillis());

    public String getUserId() {
        return userId;
    }

    public String getPassword() {
        return password;
    }

    public Date getDatejoined() {
        return dateJoined;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setDateJoined(Date dateJoined) {
        this.dateJoined = dateJoined;
    }

}
