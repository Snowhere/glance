package service;

import com.jfinal.kit.JsonKit;
import lombok.extern.log4j.Log4j;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.index.query.QueryBuilders;

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

    public void init(String host,int port) {
        try {
            client = TransportClient.builder().build()
                    .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(host),port));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    public IndexResponse create(Object object, String index, String type, String id) {
        String json = JsonKit.toJson(object);
        log.info("put to es :" + json);
        IndexResponse response = client.prepareIndex(index, type)
                .setId(id)
                .setSource(json)
                .execute()
                .actionGet();
        return response;
    }

    public GetResponse get(String index, String type, String id) {
        GetResponse response = client.prepareGet(index, type, id).get();
        return response;
    }

    public String getData(String index, String type, String id) {
        return get(index, type, id).getSourceAsString();
    }

    public SearchResponse search(String keyword, String index, String type) {
        SearchResponse response = client.prepareSearch(index)
                .setTypes(type)
                .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
                .setQuery(QueryBuilders.termQuery("multi", "test"))
                //.setPostFilter(QueryBuilders.rangeQuery("age").from(12).to(18))
                .setFrom(0).setSize(60).setExplain(true)
                .execute()
                .actionGet();
        return response;
    }

    public void close() {
        client.close();
    }
}
