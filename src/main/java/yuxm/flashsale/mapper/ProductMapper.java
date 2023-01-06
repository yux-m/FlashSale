package yuxm.flashsale.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import yuxm.flashsale.entity.Product;
import yuxm.flashsale.vo.ProductVO;

import java.util.List;

/**
 * <p>
 * Product Table Mapper interface
 * </p>
 *
 * @author yuxm
 * @since 2023-01-04
 */
public interface ProductMapper extends BaseMapper<Product> {

    /**
     * Find all products on sale. See ProductMapper.xml for SQL commands.
     *
     * @return List<ProductVO>
     */
    List<ProductVO> findProductVO();

    /**
     * Find a product by id. See ProductMapper.xml for SQL commands.
     *
     * @return the ProductVO target
     */
    ProductVO findProductVoByProductId(Long productId);
}
