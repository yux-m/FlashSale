package yuxm.flashsale.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * <p>
 * Flash Sale Orders
 * </p>
 *
 * @author yuxm
 * @since 2023-01-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_flashsale_order")
public class FlashsaleOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * flash sale order ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String userId;

    private Long orderId;

    private Long productId;


}
