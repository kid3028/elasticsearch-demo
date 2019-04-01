package com.qull.elasticsearchdemo.service;

import com.qull.elasticsearchdemo.model.Product;
import com.qull.elasticsearchdemo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author kzh
 * @Description
 * @Date 19-4-1 下午12:33
 */
@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<Product> findAll() {
        return productRepository.findAll();
    }


}
