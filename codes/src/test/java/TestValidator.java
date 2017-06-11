import org.junit.Assert;
import org.junit.Test;
import validate.UserValidate;
import validate.Validator;

/**
 * Validator
 *
 * @author STH
 * @create 2017-05-31
 **/
public class TestValidator {
    @Test
    public void email() {
        Assert.assertTrue(Validator.isEmail("124@g.com"));
        Assert.assertFalse(Validator.isEmail("qwer.com"));
    }

    @Test
    public void url() {
        Assert.assertTrue(Validator.isUrl("http://www.baidu.com"));
    }

    @Test
    public void user() {
        UserValidate userValidate=new UserValidate();
        Assert.assertTrue(userValidate.checkName("asfsaf123"));
        Assert.assertFalse(userValidate.checkName("1234"));
        Assert.assertFalse(userValidate.checkName("____"));
        Assert.assertFalse(userValidate.checkName("ads"));
        Assert.assertFalse(userValidate.checkName("abcdefghijklmnopqrst"));
    }
}
