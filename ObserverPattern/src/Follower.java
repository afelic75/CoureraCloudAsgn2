
public class Follower implements Observer {
private String followerName;
	
	public Follower(String follerName) {
		this.followerName = follerName;
		
	}
	
	public String getFollowerName() {
		return this.followerName;
	}
	
	public void setFollowername(String followerName) {
		 this.followerName = followerName;
	}
	
	

	public void update(String status) {
		System.out.printf("%s received update\"%s\"\n", followerName, status);
		
	}
	
	public void play() {
		System.out.println("Playing..... Video");
	}
	
}
