package yuxm.flashsale.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import yuxm.flashsale.entity.Order;

/**
 * <p>
 * Order Table Mapper interface
 * </p>
 *
 * @author yuxm
 * @since 2023-01-04
 */
public interface OrderMapper extends BaseMapper<Order> {

    Order findOrderByOrderId(Long orderId);
}
