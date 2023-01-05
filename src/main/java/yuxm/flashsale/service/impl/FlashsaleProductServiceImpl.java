package yuxm.flashsale.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import yuxm.flashsale.entity.FlashsaleProduct;
import yuxm.flashsale.mapper.FlashsaleProductMapper;
import yuxm.flashsale.service.IFlashsaleProductService;

/**
 * <p>
 * Flash Sale Products service implementation
 * </p>
 *
 * @author yuxm
 * @since 2023-01-04
 */
@Service
public class FlashsaleProductServiceImpl extends ServiceImpl<FlashsaleProductMapper, FlashsaleProduct> implements IFlashsaleProductService {

}
