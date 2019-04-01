package com.qull.elasticsearchdemo.repository;

import com.qull.elasticsearchdemo.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author kzh
 * @Description
 * @Date 19-4-1 上午10:26
 */
public interface ProductRepository extends JpaRepository<Product, Long> {

}
