package com.qull.elasticsearchdemo;

import com.qull.elasticsearchdemo.model.Product;
import com.qull.elasticsearchdemo.service.ProductSearchService;
import com.qull.elasticsearchdemo.service.ProductService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.CollectionUtils;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ElasticsearchDemoApplicationTests {

    @Test
    public void contextLoads() {
    }

    @Autowired
    private ProductSearchService searchService;

    @Autowired
    private ProductService productService;
    @Test
    public void importBulk() {
        List<Product> products = productService.findAll();
        if (CollectionUtils.isEmpty(products)) {
            throw new RuntimeException("商品数据为空");
        }
        System.out.println(searchService.importBulk(products));
    }

    @Test
    public void search() {
        searchService.search();
    }


}
