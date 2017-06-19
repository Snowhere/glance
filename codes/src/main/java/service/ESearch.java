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
public class ESearch {

    public void create() throws UnknownHostException {
        // on startup
        //Settings settings = Settings.settingsBuilder().put("cluster.name", "myClusterName").build();
        //TransportClient client = TransportClient.builder().settings(settings).build();
        TransportClient client = TransportClient.builder().build()
                .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("host1"), 9300))
                .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("host2"), 9300));


        // on shutdown
        GetResponse response = client.prepareGet("twitter", "tweet", "1").get();
        client.close();
        ObjectMapper objectMapper = Jackson.getJson().getObjectMapper();
    }

}
