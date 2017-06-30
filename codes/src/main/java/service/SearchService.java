package service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jfinal.json.Jackson;
import org.elasticsearch.action.get.GetResponse;
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
public class SearchService {
    private TransportClient client;

    public SearchService() throws UnknownHostException {
        client = TransportClient.builder().build()
                .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("host1"), 9300))
                .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("host2"), 9300));
    }

    public void create() {
        // on startup
        //Settings settings = Settings.settingsBuilder().put("cluster.name", "myClusterName").build();
        //TransportClient client = TransportClient.builder().settings(settings).build();
        // on shutdown
        GetResponse response = client.prepareGet("twitter", "tweet", "1").get();
        //client.close();
        ObjectMapper objectMapper = Jackson.getJson().getObjectMapper();
    }

}
