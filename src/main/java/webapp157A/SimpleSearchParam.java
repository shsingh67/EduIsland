package webapp157A;

public class SimpleSearchParam implements SearchParam {
    private String sql;

    public String whereClause(String keyword) {
        sql = keyword + " = ?" ;
        return sql;
    }
}
