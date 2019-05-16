package cafe.jjdev.mall.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cafe.jjdev.mall.mapper.ProductCommonMapper;
import cafe.jjdev.mall.vo.ProductCommon;

@Service
public class ProductCommonService {
	@Autowired
	private ProductCommonMapper productCommonMapper;
	
	// 상품 색상별 사이즈 검색
	public List<Integer> getProductSizeByColor(Map<String, Object> map){
		List<Integer> productSize = null;
		if(map.get("productColor") != null){
			productSize = productCommonMapper.selectProductSizeByColor(map);
		}
		return productSize;
	}
	// 상품 상세보기 
	public ProductCommon getProductByProductCommon(int productCommonNo){
		return productCommonMapper.selectProductByProductCommon(productCommonNo);
	}
	// 카테고리별 상품 리스트
	public Map<String, Object> getProductCommonListByCategoryNo(int categoryNo, int currentPage, String searchWord){
		final int ROW_PER_PAGE = 10;
		int beginRow = (currentPage - 1) * ROW_PER_PAGE;
		Map<String, Object> countMap = new HashMap<String, Object>();
		countMap.put("categoryNo", categoryNo);
		countMap.put("searchWord", searchWord);
		int count = productCommonMapper.selectProductCommonCountByCategoryNo(countMap);
		int lastPage = count / ROW_PER_PAGE;
		if(count % ROW_PER_PAGE != 0) {
			lastPage++;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("categoryNo", categoryNo);
		map.put("rowPerPage", ROW_PER_PAGE);
		map.put("beginRow", beginRow);
		map.put("searchWord", searchWord);
		List<ProductCommon> productCommonList = productCommonMapper.selectProductCommonListByCategoryNo(map);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("lastPage", lastPage);
		resultMap.put("productCommonList", productCommonList);
		resultMap.put("count", count);
		return resultMap;
	}
}
