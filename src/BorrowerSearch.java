import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class BorrowerSearch {
    public static void main(String[] args){
        String query = BorrowerSearch.querySearch("", "", "Joe");
        System.out.println(query);
    }
    // Returns SQL query that selects from any combination
    // ISBN, cardId, and/or borrower name
    public static String querySearch(String isbn, String cardId, String borrower) {
        if(cardId.isEmpty() && isbn.isEmpty() && borrower.isEmpty()) {
            return "";
        }
        String query = "SELECT * FROM book_loans NATURAL JOIN borrower WHERE ";
        HashMap<String, String> queryMap = new HashMap<>();
        if(!isbn.isEmpty()) {
            queryMap.put("isbn", isbn);
        }
        
        if(!cardId.isEmpty()) {
            queryMap.put("card_id", cardId);
        }
     
        if(!borrower.isEmpty()) {
            queryMap.put("bname", borrower);
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