package yuxm.flashsale.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import yuxm.flashsale.entity.FlashsaleOrder;
import yuxm.flashsale.mapper.FlashsaleOrderMapper;
import yuxm.flashsale.service.IFlashsaleOrderService;

/**
 * <p>
 * Flash Sale Orders service implementation
 * </p>
 *
 * @author yuxm
 * @since 2023-01-04
 */
@Service
public class FlashsaleOrderServiceImpl extends ServiceImpl<FlashsaleOrderMapper, FlashsaleOrder> implements IFlashsaleOrderService {

}
