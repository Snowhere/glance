import model.User;
import service.SearchService;

/**
 * Created by Administrator on 2017/6/30.
 */
public class TestSearch {
    public static void main(String args[]) {
        User user = new User();
        user.set("name", "test");
        SearchService searchService = new SearchService();
        searchService.create(user,"test-index","test-type","1");
        System.out.println(searchService.get("test-index","test-type","1"));
        searchService.close();
    }
}
