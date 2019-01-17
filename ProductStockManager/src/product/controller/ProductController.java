package product.controller;

import java.util.List;
import product.model.exception.ProductException;
import product.model.service.ProductService;
import product.model.vo.Product;
import product.model.vo.ProductIO;
import product.view.ProductMenu;

public class ProductController {

	public void insertProduct_IO(ProductIO pio) {
		String status = "I".equals(pio.getStatus())?"상품입고":"상품출고";
		
		try {
			int result = new ProductService().insertProduct_IO(pio);
			if(result>0)
				new ProductMenu().displaySuccess(status+"완료");
			else
				new ProductMenu().displayError(status+"오류! 출고량보다 재고량이 적습니다. 다시 작성해주세요.");
		} catch (ProductException e) {
			new ProductMenu().displayError(status+"오류! 관리자에게 문의하세요.");
			System.err.println(e.getMessage());
		}
	}

	public void selectProductList() {
		try {
			List<Product> list = new ProductService().selectProductList();
			if(!list.isEmpty()){
				new ProductMenu().displayProductList(list);
			}
			else{
				new ProductMenu().displayNoData();
			}

		} catch (ProductException e) {
			new ProductMenu().displayError("상품 전체 조회 실패! 관리자에게 문의하세요.");
			//컨트롤러단에서 찍힐 컨트롤러 메세지
			System.err.println(e.getMessage());
		}
	}
	
	public void selectProductIOList() {
		try {
			List<ProductIO> list = new ProductService().selectProductIOList();
			if(!list.isEmpty()){
				new ProductMenu().displayProductIOList(list);
			}
			else{
				new ProductMenu().displayNoData();
			}

		} catch (ProductException e) {
			new ProductMenu().displayError("입출고내역 전체 조회 실패! 관리자에게 문의하세요.");
			//컨트롤러단에서 찍힐 컨트롤러 메세지
			System.err.println(e.getMessage());
		}
	}

	public void selectOne(String productId) {
		try {
			
			Product p = new ProductService().selectOne(productId);
			
			//리턴될 결과에 따라 성공/실패에 대한 뷰가 선택되어 구동됨.
			if(p !=null){
				new ProductMenu().displayProduct(p);
			}
			else{
				new ProductMenu().displayNoData();
			}
		} catch (ProductException e) {
			new ProductMenu().displayError("상품 조회 실패! 관리자에게 문의하세요.");
			//컨트롤러단에서 찍힐 컨트롤러 메세지
			System.err.println(e.getMessage());
		}
	}

	public void selectByPName(String pName) {
		try {
			List<Product> list = new ProductService().selectByPName(pName);
			if(!list.isEmpty()){
				new ProductMenu().displayProductList(list);
			}
			else{
				new ProductMenu().displayNoData();
			}

		} catch (ProductException e) {
			new ProductMenu().displayError("상품명 조회 실패! 관리자에게 문의하세요.");
			//컨트롤러단에서 찍힐 컨트롤러 메세지
			System.err.println(e.getMessage());
		}
	}

	public void insertProduct(Product p) {
		try {
			if(new ProductService().insertProduct(p) > 0)
				new ProductMenu().displaySuccess("상품등록 성공!");
			
		} catch (ProductException e) {
			new ProductMenu().displayError("상품등록 실패! 관리자에게 문의하세요.");
			//컨트롤러단에서 찍힐 컨트롤러 메세지
			System.err.println(e.getMessage());
		}
		
	}

	public void updateProduct(Product p) {
		try {
			if(new ProductService().updateProduct(p) > 0)
				new ProductMenu().displaySuccess("상품 정보 수정 성공!");
			else 
				new ProductMenu().displayNoData();
		} catch (ProductException e) {
			new ProductMenu().displayError("상품 정보 수정 실패! 관리자에게 문의하세요.");
			//컨트롤러단에서 찍힐 컨트롤러 메세지
			System.err.println(e.getMessage());
		}
		
	}

	public void deleteProduct(String productId) {
		try {
			if(new ProductService().deleteProduct(productId) > 0)
				new ProductMenu().displaySuccess("상품 정보 삭제 성공!");
			else 
				new ProductMenu().displayNoData();
		} catch (ProductException e) {
			new ProductMenu().displayError("상품 정보 삭제 실패! 관리자에게 문의하세요.");
			//컨트롤러단에서 찍힐 컨트롤러 메세지
			System.err.println(e.getMessage());
		}
	}

}
