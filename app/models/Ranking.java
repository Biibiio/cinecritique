package models;

public class Ranking implements Comparable{

	private User user;
	private long pts;
	
	public Ranking(User user, long pts){
		this.user = user;
		this.pts = pts;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public long getPts() {
		return pts;
	}

	public void setPts(long pts) {
		this.pts = pts;
	}

	@Override
	public String toString() {
		return "Ranking [user=" + user + ", pts=" + pts + "]";
	}

	 public int compareTo(Object other) { 
	      long nombre1 = ((Ranking) other).getPts(); 
	      long nombre2 = this.getPts(); 
	      if (nombre1 > nombre2)  return -1; 
	      else if(nombre1 == nombre2) return 0; 
	      else return 1; 
	   } 
	
}
