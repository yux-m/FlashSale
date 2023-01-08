package yuxm.flashsale.service;

import com.baomidou.mybatisplus.extension.service.IService;
import yuxm.flashsale.entity.Order;
import yuxm.flashsale.entity.User;
import yuxm.flashsale.vo.ProductVO;

/**
 * <p>
 * Order Table Service
 * </p>
 *
 * @author yuxm
 * @since 2023-01-04
 */
public interface IOrderService extends IService<Order> {


    Order createOrder(User user, ProductVO productVO);

    Order findOrderByOrderId(Long orderId);

}