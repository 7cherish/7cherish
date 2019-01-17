package product.model.vo;

import java.sql.Date;

public class ProductIO {
	private int ioNo;
	private String productId;
	private Date pDate;
	private int amount;
	private String status;
	
	public ProductIO(){}
	public ProductIO(int ioNo, String productId, Date pDate, int amount, String status) {
		super();
		this.ioNo = ioNo;
		this.productId = productId;
		this.pDate = pDate;
		this.amount = amount;
		this.status = status;
	}
	public int getIoNo() {
		return ioNo;
	}
	public void setIoNo(int ioNo) {
		this.ioNo = ioNo;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public Date getpDate() {
		return pDate;
	}
	public void setpDate(Date pDate) {
		this.pDate = pDate;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	@Override
	public String toString() {
		return ioNo + "\t" + productId + "\t" + pDate + "\t" + amount + "\t" + status;
	}
	
	
}
