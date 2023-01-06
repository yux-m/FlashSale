package yuxm.flashsale.vo;

import yuxm.flashsale.entity.Product;

import java.math.BigDecimal;
import java.util.Date;

public class ProductVO extends Product {

    private BigDecimal flashsalePrice;
    private Integer stockCount;
    private Date startDate;
    private Date endDate;

    public BigDecimal getFlashsalePrice() {
        return flashsalePrice;
    }

    public Integer getStockCount() {
        return stockCount;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }
}
