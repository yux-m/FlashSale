package yuxm.flashsale.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import yuxm.flashsale.entity.Product;
import yuxm.flashsale.mapper.ProductMapper;
import yuxm.flashsale.service.IProductService;
import yuxm.flashsale.vo.ProductVO;

import java.util.List;

/**
 * <p>
 * Product Table service implementation
 * </p>
 *
 * @author yuxm
 * @since 2023-01-04
 */
@Service
@Primary
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements IProductService {

    @Autowired
    private ProductMapper productMapper;

    /**
     * Find all products on sale.
     *
     * @return List<ProductVO>
     */
    @Override
    public List<ProductVO> findProductVO() {
        return productMapper.findProductVO();
    }

    /**
     * Find a product by id.
     *
     * @return the ProductVO target
     */
    @Override
    public ProductVO findProductVoByProductId(Long productId) {
        return productMapper.findProductVoByProductId(productId);
    }
}
