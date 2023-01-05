package yuxm.flashsale.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import yuxm.flashsale.entity.Order;
import yuxm.flashsale.mapper.OrderMapper;
import yuxm.flashsale.service.IOrderService;

/**
 * <p>
 * Order Table service implementation
 * </p>
 *
 * @author yuxm
 * @since 2023-01-04
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderService {

}
