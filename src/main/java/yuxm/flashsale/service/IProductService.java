package yuxm.flashsale.service;

import com.baomidou.mybatisplus.extension.service.IService;
import yuxm.flashsale.entity.Product;
import yuxm.flashsale.vo.ProductVO;

import java.util.List;

/**
 * <p>
 * Product Table Service
 * </p>
 *
 * @author yuxm
 * @since 2023-01-04
 */
public interface IProductService extends IService<Product> {

    /**
     * Find all products on sale.
     *
     * @return List<ProductVO>
     */
    List<ProductVO> findProductVO();

    /**
     * Find a product by id.
     *
     * @return the ProductVO target
     */
    ProductVO findProductVoByProductId(Long productId);
}
