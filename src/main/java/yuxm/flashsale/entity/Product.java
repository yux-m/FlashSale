package yuxm.flashsale.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * Product Table
 * </p>
 *
 * @author yuxm
 * @since 2023-01-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_product")
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * productID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * product name
     */
    private String productName;

    /**
     * product title
     */
    private String productTitle;

    /**
     * product image
     */
    private String productImg;

    /**
     * product description
     */
    private String productDetail;

    /**
     * product price
     */
    private BigDecimal productPrice;

    /**
     * product stock, -1 indicating unlimited stock
     */
    private Integer productStock;

    public Long getId() {
        return id;
    }

    public String getProductName() {
        return productName;
    }

    public String getProductTitle() {
        return productTitle;
    }

    public String getProductImg() {
        return productImg;
    }

    public String getProductDetail() {
        return productDetail;
    }

    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public Integer getProductStock() {
        return productStock;
    }
}
