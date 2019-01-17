package product.view;

import java.util.List;
import java.util.Scanner;

import product.controller.ProductController;
import product.model.vo.Product;
import product.model.vo.ProductIO;

public class ProductMenu {
	private Scanner sc = new Scanner(System.in);
	private ProductController pController = new ProductController();
	private final int PRODUCT_INPUT = 0;
	private final int PRODUCT_OUTPUT = 1;
	
	public void mainMenu() {
		int choice = 0;
		String menu = "\n***** 상품재고관리프로그램 *****\n"
					+ "1. 전체상품조회\n"
					+ "2. 상품아이디검색\n"
					+ "3. 상품명검색\n"
					+ "4. 상품추가\n"
					+ "5. 상품정보변경\n"
					+ "6. 상품삭제\n"
					+ "7. 상품입/출고메뉴\n"
					+ "9. 프로그램종료\n"
					+ "메뉴선택 : ";
		
		while(true){
			//메뉴출력
			System.out.print(menu);
			//사용자메뉴선택
			choice = sc.nextInt();
			
			switch (choice) {
			case 1:
				pController.selectProductList();
				break;
			case 2:
				pController.selectOne(inputProductId());
				break;
			case 3:
				pController.selectByPName(inputPName());
				break;
			case 4:
				Product p = inputProduct();
				pController.insertProduct(p);
				break;
			case 5:
				p = updateProduct();
				pController.updateProduct(p);
				break;
			case 6:
				pController.deleteProduct(inputProductId());
				break;
			case 7:
				ioMenu();
				break;
			case 9:
				System.out.print("정말로 끝내시겠습니까?(y/n) : ");
				if('y'==sc.next().toLowerCase().charAt(0)) return;
				break;
			default:
				System.out.println("번호를 잘못 입력하셨습니다.");
			}
		}
	}
	
	private Product inputProduct() {
		System.out.println("새 상품정보를 입력하세요 :");
		System.out.print("상품아이디 : ");
		String productId = sc.next();
		sc.nextLine();
		System.out.print("상품명 : ");
		String pName = sc.nextLine();
		System.out.print("가격 : ");
		int price = sc.nextInt();
		sc.nextLine();
		System.out.print("상품설명 : ");
		String description = sc.nextLine();
		
		return new Product(productId, pName, price, description, 0);
	}
	
	private Product updateProduct() {
		String productId = inputProductId();
		sc.nextLine();
		System.out.print("상품명 : ");
		String pName = sc.nextLine();
		System.out.print("가격 : ");
		int price = sc.nextInt();
		sc.nextLine();
		System.out.print("상품설명 : ");
		String description = sc.nextLine();
		System.out.print("재고량 : ");
		int stock = sc.nextInt();
		
		return new Product(productId, pName, price, description, stock);
	}
	
	private String inputPName() {
		System.out.print("조회할 상품명 입력 : ");
		return sc.next();
	}

	private void ioMenu(){
		int choice = 0;
		String menu = "\n***** 상품입출고메뉴*****\n"
				+ "1. 전체입출고내역조회\n"
				+ "2. 상품입고\n"
				+ "3. 상품출고\n"
				+ "9. 메인메뉴로 돌아가기\n"
				+ "메뉴선택 : ";
		while(true){
			//io메뉴출력
			System.out.print(menu);
			//사용자메뉴선택
			choice = sc.nextInt();
			
			switch(choice){
			case 1:
				pController.selectProductIOList();
				break;
			case 2:
				//사용자입력
				String productId = inputProductId();
				int amount = inputAmount(PRODUCT_INPUT);
				//ProductIO객체생성
				ProductIO pio = new ProductIO();
				pio.setProductId(productId);
				pio.setAmount(amount);
				pio.setStatus("I");
				pController.insertProduct_IO(pio);
				break;
			case 3:
				//사용자입력
				productId = inputProductId();
				amount = inputAmount(PRODUCT_OUTPUT);
				//ProductIO객체생성
				pio = new ProductIO();
				pio.setProductId(productId);
				pio.setAmount(amount);
				pio.setStatus("O");
				pController.insertProduct_IO(pio);
				break;
			case 9:return;
			default:
				System.out.println("번호를 잘못 입력하셨습니다.");
			}
		}
	}
	
	/**
	 * 상품아이디입력
	 * @return
	 */
	private String inputProductId() {
		System.out.print("상품아이디입력 : ");
		return sc.next();
	}

	/**
	 * 입출고 수량입력
	 * @param io_flag 
	 * @return
	 */
	private int inputAmount(int io_flag) {
		System.out.print(io_flag==0?"입고 수량 입력 : ":"출고 수량 입력 :");
		return sc.nextInt();
	}

	/**
	 * 처리오류메세지 출력메소드
	 * @param message
	 */
	public void displayError(String message) {
		System.out.println("처리실패 : "+message);
	}

	/**
	 * 처리성공메세지 출력메소드
	 * @param message
	 */
	public void displaySuccess(String message) {
		System.out.println("처리성공 : "+message);
	}

	public void displayNoData() {
		System.out.println("조회된 데이터가 없습니다.");
		
	}

	public void displayProductList(List<Product> list) {
		System.out.println("\n조회된  상품정보는 다음과 같습니다.");
		System.out.println("상품아이디\t상품명\t가격\t제품설명\t재고량");
		System.out.println("----------------------------------------------------------------------");
		for (Product p : list) {
			System.out.println(p);			
		}
	}

	public void displayProductIOList(List<ProductIO> list) {
		System.out.println("\n조회된  입출고내역정보는 다음과 같습니다.");
		System.out.println("번호\t상품아이디\t날짜\t수량\t입/출고");
		System.out.println("----------------------------------------------------------------------");
		for (ProductIO p : list) {
			System.out.println(p);			
		}
	}

	public void displayProduct(Product p) {
		System.out.println("\n조회된  상품정보는 다음과 같습니다.");
		System.out.println("상품아이디\t상품명\t가격\t제품설명\t재고량");
		System.out.println("----------------------------------------------------------------------");
		System.out.println(p);				
	}

}
