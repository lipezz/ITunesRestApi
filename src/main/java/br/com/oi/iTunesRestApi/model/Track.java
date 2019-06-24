package br.com.oi.iTunesRestApi.model;

public class Track {
	
	private String name; 
	private String number;
			
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
		
	@Override
    public String toString() {
        return String.format(
        		"Track[name=%s, number=%s]", 
        		name, number);
    }		
}