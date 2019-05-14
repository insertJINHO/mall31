package cafe.jjdev.mall.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cafe.jjdev.mall.service.ProductCommonService;

@Controller
public class ProductCommonController {
	@Autowired
	private ProductCommonService productCommonService;
	
	// 카테고리별 상품 리스트, 검색
	@GetMapping("/product/getProductListByCategory")
	public String getProductListByCategory(@RequestParam(value = "currentPage", required = false, defaultValue = "1") int currentPage, int categoryNo, 
			Model model, @RequestParam(value = "searchWord", required = false) String searchWord) {
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
