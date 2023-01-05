package yuxm.flashsale.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * Order Table
 * </p>
 *
 * @author yuxm
 * @since 2023-01-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_order")
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * orderID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * userID
     */
    private Long userId;

    /**
     * productID
     */
    private Long productId;

    /**
     * address ID
     */
    private Long deliveryAddrId;

    /**
     * product name
     */
    private String productName;

    /**
     * #product
     */
    private Integer productCount;

    /**
     * product price
     */
    private BigDecimal productPrice;

    /**
     * 1-pc, 2-android, 3-ios
     */
    private Integer orderChannel;

    /**
     * order status，0-unpaied，1-paid，2-shipped，3-delivered，4-returned，5-completed
     */
    private Integer status;

    private Date createDate;

    private Date paymentDate;


}
