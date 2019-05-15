package cafe.jjdev.mall.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import cafe.jjdev.mall.vo.ProductCommon;

@Mapper
public interface ProductCommonMapper {
	// 카테고리별 상품 갯수 	
	public int selectProductCommonCountByCategoryNo(Map<String, Object> map);
	// 2. 상품 상세보기 
	public ProductCommon selectProductByProductCommon(int productCommonNo);
	// 1. 카테고리별 상품 리스트
	public List<ProductCommon> selectProductCommonListByCategoryNo(Map<String, Object> map);
}
