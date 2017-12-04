package webapp157A;

public class HasPrivilege {

    private String userId;
    private String field;
    private String access_type;

    public String getUserId() {
        return userId;
    }

    public String getField() {
        return field;
    }

    public String getAccess_type() {
        return access_type;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setField(String field) {
        this.field = field;
    }

    public void setAccess_type(String access_type) {
        this.access_type = access_type;
    }
}
