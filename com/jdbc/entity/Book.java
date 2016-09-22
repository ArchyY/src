package com.jdbc.entity;

import java.util.Date;



public class Book {
    
	private String isbn;
     private String bName;
     private String press;
     private double price;
     private Date printDate;
     private int bkCount;
     private String author;
     private String introduce;
	 private byte[] pic;
	 private int buyCount=1;
     private int status=1;
	 public Book(){
    	 
     }
     
     
     
     public Book(String isbn, String bName, String press, double price,
			Date printDate, int bkCount, String author, String introduce) {
		super();
		this.isbn = isbn;
		this.bName = bName;
		this.press = press;
		this.price = price;
		this.printDate = printDate;
		this.bkCount = bkCount;
		this.author = author;
		this.introduce = introduce;
	}



	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public String getbName() {
		return bName;
	}
	public void setbName(String bName) {
		this.bName = bName;
	}
	public String getPress() {
		return press;
	}
	public void setPress(String press) {
		this.press = press;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public Date getPrintDate() {
		return printDate;
	}
	public void setPrintDate(Date printDate) {
		this.printDate = printDate;
	}
	public int getBkCount() {
		return bkCount;
	}
	public void setBkCount(int bkCount) {
		this.bkCount = bkCount;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getIntroduce() {
		return introduce;
	}
	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}
	
	  @Override
		public String toString() {
			return "Book [isbn=" + isbn + ", bName=" + bName + ", press=" + press
					+ ", price=" + price + ", printDate=" + printDate
					+ ", bkCount=" + bkCount + ", author=" + author
					+ ", introduce=" + introduce + "]";
		}



	public byte[] getPic() {
		return pic;
	}



	public void setPic(byte[] pic) {
		this.pic = pic;
	}





	public int getBuyCount() {
		return buyCount;
	}
    public void setBuyCount(int buyCount) {
		this.buyCount = buyCount;
	}
    public void addBuyCount(){
    	this.buyCount++;
    }



	public int getStatus() {
		return status;
	}



	public void setStatus(int status) {
		this.status = status;
	} 
}
