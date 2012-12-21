package gson;

public class SaleArticle {
	private String title;
	private String author;
	private String publisher;
	private String price;
	private String status;
	
	public SaleArticle(){}
	public SaleArticle(String t, String a, String ps, String p, String s){
		this.title = t;
		this.author = a;
		this.publisher = ps;
		this.price = p;
		this.status = s;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	String method;
	
}

