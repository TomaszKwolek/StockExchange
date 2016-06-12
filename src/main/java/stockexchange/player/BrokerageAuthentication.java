package stockexchange.player;

public class BrokerageAuthentication {

	private String pesel;
	private String login;
	private String password;
	private int id;
	
	public BrokerageAuthentication() {
		super();
	}

	public BrokerageAuthentication(String pesel, String login, String password, int id) {
		super();
		this.pesel = pesel;
		this.login = login;
		this.password = password;
		this.id = id;
	}

	public String getLogin() {
	 	 return login; 
	}

	public void setLogin(String login) { 
		 this.login = login; 
	}
	
	public String getPassword() {
	 	 return password; 
	}
	
	public void setPassword(String password) { 
		 this.password = password; 
	}

	public String getPesel() {
		return pesel;
	}

	public void setPesel(String pesel) {
		this.pesel = pesel;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	} 
	
}
