package com.qull.elasticsearchdemo.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 商品表
 * </p>
 *
 * @author kzh
 * @since 2019-03-01
 */
@Data
@Entity
@Table(name = "web_product")
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 商品主键id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "productId")
    private Long productId;

    /**
     * 商家id
     */
    @NotNull(message = "商品所属者不能为空")
    @Column(name = "productOwnerId")
    private Long productOwnerId;

    /**
     * 商品名称
     */
    @NotBlank(message = "商品名称不能为空")
    @Column(name = "productName")
    private String productName;

    /**
     * 商品副标题
     */
    @NotBlank(message = "商品的副标题不能为空")
    @Column(name = "productSubTitle")
    private String productSubTitle;

    /**
     * 商品描述
     */
    @NotBlank(message = "商品描述不能为空")
    @Column(name = "productDescription")
    private String productDescription;

    /**
     * 商品轮播图
     */
    @NotBlank(message = "商品轮播图片不能为空")
    @Column(name = "productImage")
    private String productImage;

    /**
     * 商品最低售价
     */
    @Column(name = "productPrice")
    private BigDecimal productPrice;

    /**
     * 商品原价
     */
    @Column(name = "productOriginalPrice")
    private BigDecimal productOriginalPrice;

    /**
     * 商品最低价sku
     */
    @Column(name = "productSkuName")
    private String productSkuName;

    /**
     * 商品销量
     */
    @Column(name = "productSale")
    private Integer productSale;

    /**
     * 商品库存
     */
    @Column(name = "productStock")
    private Integer productStock;

    @NotNull(message = "是否是虚拟商品，0 虚拟商品 1非虚拟商品")
    @Column(name = "productIsVirtual")
    private Integer productIsVirtual = 0;

    /**
     * 商品参数
     */
    @Column(name = "productParamsValues")
    private String productParamsValues;

    /**
     * 商品分类id
     */
    @NotNull(message = "商品分类不能为空")
    @Column(name = "productCategoryId")
    private Integer productCategoryId;

    /**
     * 是否是必买商品(0 => 必买 1 => 不必买)
     */
    @Column(name = "productIsMust")
    private Integer productIsMust;

    /**
     * 是否每日重置库存 0=>重置 1=> 不重置
     */
    @NotNull(message = "请选择是否重置库存")
    @Column(name = "productIsReset")
    private Integer productIsReset;

    /**
     * 是否超时后不可购买  0=> 是 1 => 不是
     */
    @NotNull(message = "请选择是否可超时购买")
    @Column(name = "productIsTimeout")
    private Integer productIsTimeout;

    /**
     * 商品状态(0 => 上架 1 => 下架  -1 => 删除)
     */
    @Column(name = "productStatus")
    private Integer productStatus;

    /**
     * 商品创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "productCreateTime")
    private Date productCreateTime;

    /**
     * 商品更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "productUpdateTime")
    private Date productUpdateTime;

}
