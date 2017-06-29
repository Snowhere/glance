package config;

import com.jfinal.config.*;
import com.jfinal.kit.Prop;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.c3p0.C3p0Plugin;
import com.jfinal.plugin.redis.RedisPlugin;
import com.jfinal.render.ViewType;
import com.jfinal.template.Engine;
import com.jfplugin.mail.MailPlugin;
import controller.AboutController;
import controller.CodeController;
import controller.IndexController;
import controller.UserController;
import interceptor.ConstantInfoInterceptor;
import interceptor.RoleInterceptor;
import model.Code;
import model.User;
import model.UserAuth;
import util.BeanFactory;

/**
 * API引导式配置
 */
public class Config extends JFinalConfig {

    /**
     * 配置常量
     */
    public void configConstant(Constants me) {
        me.setDevMode(true);
        me.setViewType(ViewType.JSP);
        me.setMaxPostSize(1024 * 1024 * 30);
        me.setError404View("/WEB-INF/index/404.jsp");
        me.setError500View("/WEB-INF/index/500.jsp");
    }

    /**
     * 配置路由
     */
    public void configRoute(Routes me) {
        me.add("/", IndexController.class, "/WEB-INF/index");
        me.add("/user", UserController.class, "/WEB-INF/user");
        me.add("/code", CodeController.class, "/WEB-INF/code");
        me.add("/about", AboutController.class, "/WEB-INF/about");
    }


    /**
     * 配置插件
     */
    public void configPlugin(Plugins me) {
        Prop prop = PropKit.use("config.properties");

        //email
        me.add(new MailPlugin(PropKit.use("smtp.properties").getProperties()));

        // Redis
        RedisPlugin redisPlugin = new RedisPlugin(prop.get("redis.cache"), prop.get("redis.host"), prop.get("redis.password"));
        me.add(redisPlugin);

        // 配置C3p0数据库连接池插件
        C3p0Plugin c3p0Plugin = new C3p0Plugin(
                prop.get("db.host"), prop.get("db.username"), prop.get("db.password"));
        me.add(c3p0Plugin);

        // 配置ActiveRecord插
        ActiveRecordPlugin arp = new ActiveRecordPlugin(c3p0Plugin);
        arp.setShowSql(true);
        me.add(arp);

        arp.addMapping("code", Code.class);
        arp.addMapping("user", User.class);
        arp.addMapping("user_auth", UserAuth.class);
    }

    @Override
    public void configInterceptor(Interceptors me) {
        me.add(BeanFactory.getInstance().getBean(RoleInterceptor.class));
        me.add(BeanFactory.getInstance().getBean(ConstantInfoInterceptor.class));
    }

    @Override
    public void configHandler(Handlers me) {
    }

    public void configEngine(Engine engine) {

    }
}
