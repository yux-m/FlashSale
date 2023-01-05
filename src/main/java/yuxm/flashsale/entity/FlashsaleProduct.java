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
 * Flash Sale Products
 * </p>
 *
 * @author yuxm
 * @since 2023-01-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_flashsale_product")
public class FlashsaleProduct implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * flash sale product ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private Long productId;

    private BigDecimal flashsalePrice;

    private Integer stockCount;

    /**
     * start time of flash sale
     */
    private Date startDate;

    /**
     * end time of flash sale
     */
    private Date endDate;


}
