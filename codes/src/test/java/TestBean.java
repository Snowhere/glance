import controller.UserController;
import org.junit.Test;
import util.BeanFactory;

/**
 * Created by Administrator on 2017/6/11.
 */
public class TestBean {
    @Test
    public void testFactory() {
        UserController userController = BeanFactory.getInstance().getBean(UserController.class);
        userController.email();
    }
}
