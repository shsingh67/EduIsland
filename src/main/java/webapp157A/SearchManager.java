package webapp157A;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SearchManager {

    public static HashMap<String, SearchParam> SEARCH_PARAMS;
    static {
        SEARCH_PARAMS = new HashMap<String, SearchParam>();
        SEARCH_PARAMS.put("name", new SimpleSearchParam());
        SEARCH_PARAMS.put("department_id", new SimpleSearchParam());
        SEARCH_PARAMS.put("course_id", new SimpleSearchParam());
        SEARCH_PARAMS.put("units", new SimpleSearchParam());
        SEARCH_PARAMS.put("description", new SimpleSearchParam());
        SEARCH_PARAMS.put("first_name", new SimpleSearchParam());
        SEARCH_PARAMS.put("last_name", new SimpleSearchParam());
        SEARCH_PARAMS.put("email_address", new SimpleSearchParam());
        SEARCH_PARAMS.put("user_ID", new SimpleSearchParam());
    }

    public static HashMap<String, String> params;
    public static ArrayList<Object> values;

    public static String buildCourseQuery(String table) {
        values = new ArrayList<Object>();

        StringBuilder builder = new StringBuilder();
        builder.append("select * from " + table);

        if(params.size() != 0) {
            builder.append(" where ");

            int i = 0;

            for (Map.Entry<String, String> pair : params.entrySet()) {
                SearchParam sp = SEARCH_PARAMS.get(pair.getKey());
                String query = sp.whereClause(pair.getKey());
                if (pair.getKey().equals("description") || pair.getKey().equals("course_id") || pair.getKey().equals("name")) {
                    values.add("%" + pair.getValue() + "%");
                } else {
                    values.add(pair.getValue());
                }
                builder.append(query);
                i++;                                    // come up with a better way of doing this, it's okay for now
                if (i < params.size()) {
                    builder.append(" and ");
                }
            }
        }
        return builder.toString();
    }

    public static void addSearchParams(HashMap<String, String> params) {
        SearchManager.params = new HashMap<String, String>();
        SearchManager.params.putAll(params);
    }

}
