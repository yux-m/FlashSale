package yuxm.flashsale.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import yuxm.flashsale.entity.Product;
import yuxm.flashsale.mapper.ProductMapper;
import yuxm.flashsale.service.IProductService;

/**
 * <p>
 * Product Table service implementation
 * </p>
 *
 * @author yuxm
 * @since 2023-01-04
 */
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements IProductService {

}
