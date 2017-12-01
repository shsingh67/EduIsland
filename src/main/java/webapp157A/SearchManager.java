package webapp157A;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SearchManager {

  private final static String COURSE = "course";
  private final static String INSTRUCTOR = "instructor";

  public static HashMap<String, SearchParam> SEARCH_PARAMS;
  static
    {
      SEARCH_PARAMS = new HashMap<String, SearchParam>();
      SEARCH_PARAMS.put("name", new SimpleSearchParam());
      SEARCH_PARAMS.put("department_id", new SimpleSearchParam());

    }

  public static HashMap<String, String> params = new HashMap<String, String>();
  public static ArrayList<Object> values;

  public static String buildQuery() {

      values = new ArrayList<Object>();

       // TODO: 11/24/17 configure dynamic searching for courses and instructors

       StringBuilder builder = new StringBuilder();
       builder.append("select * from " + COURSE + " where ");
       int i = 0;

       for(Map.Entry<String, String>pair: params.entrySet()) {
           SearchParam sp = SEARCH_PARAMS.get(pair.getKey());
           String query =  sp.whereClause(pair.getKey());
           values.add(pair.getValue());
           builder.append(query);
           i++;                                    // come up with a better way of doing this, it's okay for now
           if(i < params.size()) {
               builder.append(" and ");
           }
       }
      return builder.toString();
   }

  public static void addSearchParams(HashMap<String, String> params) {
      SearchManager.params.putAll(params);
   }

}
