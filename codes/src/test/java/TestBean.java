import controller.UserController;
import org.junit.Test;

/**
 * Created by Administrator on 2017/6/11.
 */
public class TestBean {
    @Test
    public void testFactory() throws IllegalAccessException, InstantiationException {
        UserController userController = UserController.class.newInstance();
        userController.email();
    }
}
