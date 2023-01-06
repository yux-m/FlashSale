package yuxm.flashsale.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import yuxm.flashsale.entity.*;
import yuxm.flashsale.mapper.OrderMapper;
import yuxm.flashsale.service.IFlashsaleOrderService;
import yuxm.flashsale.service.IFlashsaleProductService;
import yuxm.flashsale.service.IOrderService;
import yuxm.flashsale.service.IProductService;
import yuxm.flashsale.vo.ProductVO;

import java.util.Date;

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

    @Autowired
    private IFlashsaleProductService flashsaleProductService;

    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private IFlashsaleOrderService flashsaleOrderService;
    @Autowired
    private IProductService productService;

    @Override
    public Order createOrder(User user, ProductVO productVO) {
        //update stock
        FlashsaleProduct flashsaleProduct = flashsaleProductService.getOne(new QueryWrapper<FlashsaleProduct>().eq("product_id", productVO.getId()));
        flashsaleProduct.setStockCount(flashsaleProduct.getStockCount() - 1);
        flashsaleProductService.updateById(flashsaleProduct);
        Product product = productService.findProductVoByProductId(productVO.getId());
        product.setProductStock(product.getProductStock() - 1);
        productService.updateById(product);
        //new order
        Order order = new Order();
        order.setUserId(user.getId());
        order.setProductId(productVO.getId());
        order.setDeliveryAddrId(0L);
        order.setProductName(productVO.getProductName());
        order.setProductCount(1);
        order.setProductPrice(productVO.getFlashsalePrice());
        order.setOrderChannel(1);
        order.setStatus(0);
        order.setCreateDate(new Date());
        orderMapper.insert(order);
        //new sale order
        FlashsaleOrder flashsaleOrder = new FlashsaleOrder();
        flashsaleOrder.setUserId(user.getId());
        flashsaleOrder.setOrderId(order.getId());
        flashsaleOrder.setProductId(productVO.getId());
        flashsaleOrderService.save(flashsaleOrder);

        return order;
    }

    @Override
    public Order findOrderByOrderId(Long orderId) {
        return orderMapper.findOrderByOrderId(orderId);
    }

}
