package config;

import com.jfinal.config.*;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.c3p0.C3p0Plugin;
import com.jfinal.plugin.redis.RedisPlugin;
import com.jfinal.render.ViewType;
import com.jfinal.template.Engine;
import controller.IndexController;
import model.Code;
import model.User;
import model.UserAuth;

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
        me.setError404View("/WEB-INF/index/404.html");
        me.setError500View("/WEB-INF/index/500.html");
    }

    /**
     * 配置路由
     */
    public void configRoute(Routes me) {
        me.add("/", IndexController.class, "/WEB-INF/index");
    }


    /**
     * 配置插件
     */
    public void configPlugin(Plugins me) {
        // Redis
        RedisPlugin redisPlugin = new RedisPlugin("", "127.0.0.1", "");
        me.add(redisPlugin);

        // 配置C3p0数据库连接池插件
        C3p0Plugin c3p0Plugin = new C3p0Plugin(
                "jdbc:mysql://localhost/codes?useUnicode=true", "", "");
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
    }

    @Override
    public void configHandler(Handlers me) {
    }

    public void configEngine(Engine engine) {

    }
}
