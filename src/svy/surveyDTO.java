package svy;

public class surveyDTO {
	private int no;
	private String name;
	private String company;
	private String email;
	private String satisfaction;
	private String part;
	private String howto;
	private int agree;
	
	public surveyDTO() {
		
	}
	public surveyDTO(int no, String name, String company, String email, String satisfaction,String part, String howto, int agree) {
		this.no = no;
		this.name = name;
		this.company = company;
		this.email = email;
		this.satisfaction = satisfaction;
		this.part = part;
		this.howto = howto;
		this.agree = agree;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSatisfaction() {
		return satisfaction;
	}
	public void setSatisfaction(String satisfaction) {
		this.satisfaction = satisfaction;
	}
	public String getPart() {
		return part;
	}
	public void setPart(String part) {
		this.part = part;
	}
	public String getHowto() {
		return howto;
	}
	public void setHowto(String howto) {
		this.howto = howto;
	}
	public int getAgree() {
		return agree;
	}
	public void setAgree(int agree) {
		this.agree = agree;
	}
	
	
}
