import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class GeneralSearch {
    public static void main(String[] args){
        String query = GeneralSearch.querySearch("", "01234", "Joe stronk");
        System.out.println(query);
    }
    // Returns SQL query that selects from any combination
    // ISBN, Book Title, and/or Authors
    public static String querySearch(String title, String isbn, String author) {
        if(title.isEmpty() && isbn.isEmpty() && author.isEmpty()) {
            return "";
        }
        String query = "SELECT * FROM book NATURAL JOIN book_authors NATURAL JOIN authors WHERE ";
        HashMap<String, String> queryMap = new HashMap<>();
        if(!title.isEmpty()) {
            queryMap.put("title", title);
        }
        if(!isbn.isEmpty()) {
            queryMap.put("isbn", isbn);
        }
        if(!author.isEmpty()) {
            queryMap.put("name", author);
        }

        Iterator<Map.Entry<String, String>> qmIter = queryMap.entrySet().iterator();

        while(qmIter.hasNext()) {
            Map.Entry<String, String> e = qmIter.next();
            query = query.concat(e.getKey().toString());
            query = query.concat(" LIKE \"%");              
            query = query.concat(e.getValue().toString());
            query = query.concat("%\"");
            if(qmIter.hasNext()){
                query = query.concat(" and ");
            }
        }
        query = query.concat(";");
        return query;
    }
}