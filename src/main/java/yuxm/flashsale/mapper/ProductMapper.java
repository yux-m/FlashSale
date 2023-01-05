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

    List<ProductVO> findProductVO();
}
