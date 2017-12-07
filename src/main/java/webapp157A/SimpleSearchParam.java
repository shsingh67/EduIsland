package webapp157A;

public class SimpleSearchParam implements SearchParam {
    private String sql;

    public String whereClause(String keyword) {
        if(keyword.equals("description") || keyword.equals("course_id") || keyword.equals("name")) {
            sql = keyword + " like ?";
        } else {
            sql = keyword + " = ?";
        }
        return sql;
    }
}
