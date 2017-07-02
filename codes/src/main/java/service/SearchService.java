package service;

import com.jfinal.kit.JsonKit;
import lombok.extern.log4j.Log4j;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.transport.InetSocketTransportAddress;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * elastic search
 *
 * @author STH
 * @create 2017-06-19
 **/
@Log4j
public class SearchService {
    private TransportClient client;

    public SearchService() {
       init();
    }

    private void init() {
        try {
            client = TransportClient.builder().build()
                    .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("47.90.53.246"), 9200));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    public void create(Object object) {
        String json = JsonKit.toJson(object);
        log.info(json);
        IndexResponse response = client.prepareIndex("test-index", "test-type")
                //必须为对象单独指定ID
                .setId("1")
                .setSource(json)
                .execute()
                .actionGet();
        //多次index这个版本号会变
        System.out.println("response.version():" + response.getVersion());

    }

    public String get() {
        GetResponse response = client.prepareGet("test-index", "test-type", "1").get();
        //client.close();
        return response.getSourceAsString();
    }

    public void close() {
        client.close();
    }
}
