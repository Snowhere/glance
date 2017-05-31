import org.junit.Assert;
import org.junit.Test;
import util.Validator;

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
        Assert.assertTrue(Validator.userName("asfsaf123"));
        Assert.assertFalse(Validator.userName("1234"));
        Assert.assertFalse(Validator.userName("____"));
        Assert.assertFalse(Validator.userName("ads"));
        Assert.assertFalse(Validator.userName("abcdefghijklmnopqrst"));
    }
}
