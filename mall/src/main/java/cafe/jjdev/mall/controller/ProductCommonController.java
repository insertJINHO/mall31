package cafe.jjdev.mall.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cafe.jjdev.mall.service.ProductCommonService;
import cafe.jjdev.mall.vo.ProductCommon;

@Controller
public class ProductCommonController {
	@Autowired
	private ProductCommonService productCommonService;
	
	// 상품 상세보기
	@GetMapping("/product/getProductByProductCommon")
	public String getProductByProductCommon(int productCommonNo, Model model,
			@RequestParam(value = "productColor", required = false) String productColor){
		System.out.println("ProductCommonController.getProductListByCategory GET productColor : "+productColor);
		// 1. 해당 productCommonNo의 상품을 검색하고 리턴받아 뷰로 가져간다
		ProductCommon productCommon = productCommonService.getProductByProductCommon(productCommonNo);
		System.out.println("ProductCommonController.getProductByProductCommon GET productCommon : "+productCommon);
		// 상품 색상별 사이즈를 검색하는 productCommonService.getProductSizeByColor 메소드의 매개변수
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("productCommonNo", productCommonNo);
		map.put("productColor", productColor);
		// 2. 상품 색상별 사이즈를 검색하고 리턴받는다
		List<Integer> productSize = productCommonService.getProductSizeByColor(map);
		System.out.println("ProductCommonController.getProductByProductCommon GET productSize : "+productSize);
		model.addAttribute("productCommon", productCommon); // 상품의 기본 정보
		model.addAttribute("productColor", productColor);	// 컬러 옵션 select box에서 중복 값을 제거하기 위한 값
		model.addAttribute("productSize", productSize);
		return "/product/getProductByProductCommon";
	}
	// 카테고리별 상품 리스트, 검색
	@GetMapping("/product/getProductListByCategory")
	public String getProductListByCategory(@RequestParam(value = "currentPage", required = false, defaultValue = "1") int currentPage, 
			int categoryNo, Model model, @RequestParam(value = "searchWord", required = false) String searchWord,
			@RequestParam(value = "productColor", required = false) String productColor) {
		System.out.println("ProductCommonController.getProductListByCategory GET searchWord : "+searchWord);
		Map<String, Object> resultMap = productCommonService.getProductCommonListByCategoryNo(categoryNo, currentPage, searchWord);
		model.addAttribute("productCommonList", resultMap.get("productCommonList"));
		model.addAttribute("lastPage", resultMap.get("lastPage"));
		model.addAttribute("count", resultMap.get("count"));
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("categoryNo", categoryNo);
		model.addAttribute("searchWord", searchWord);
		return "product/getProductListByCategory";
	}
}
