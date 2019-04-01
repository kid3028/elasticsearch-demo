package com.qull.elasticsearchdemo.service;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsRequest;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;

/**
 * @Author kzh
 * @Description
 * @Date 19-4-1 上午10:18
 */
@Service
@Slf4j
public class ProductSearchService {
    // TODO 索引
    private static final String index = "product_index";
    // TODO 类型
    private static final String type = "product_type";
    // TODO 获取实体id的方法
    private static final String idMethod = "getProductId";

    @Autowired
    private TransportClient client;

    public boolean importBulk(Collection<?> dataSource) {

        if (!client.admin().indices().exists(new IndicesExistsRequest().indices(index)).actionGet().isExists()) {
            client.admin().indices().create(new CreateIndexRequest().index(index)).actionGet();
        }

        BulkRequestBuilder bulkRequest= client.prepareBulk();
        dataSource.forEach(data -> {
            try {
                Method getProductIdMethod = data.getClass().getDeclaredMethod(idMethod);
                bulkRequest.add(new IndexRequest(index, type, String.valueOf(getProductIdMethod.invoke(data)))
                        .source(JSON.toJSONString(data), XContentType.JSON));
            } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
                e.printStackTrace();
                log.error("添加文档信息失败");
            }
        });
        RestStatus status = bulkRequest.execute().actionGet().status();
        return RestStatus.OK.equals(status);
    }

    public void search() {
        SearchHits hits = client.prepareSearch(index).setTypes(type).setQuery(QueryBuilders.matchAllQuery()).execute().actionGet().getHits();
        System.out.println(hits.getTotalHits());
        for (SearchHit hit : hits.getHits()) {
            System.out.println(hit.getSourceAsString());
        }
    }

}
