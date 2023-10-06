package chapter1; 

public class Percept {
	private String agentLocation; //vi tri cua agent
	private Environment.LocationState state; //trang thai cua moi truong

	public Percept(String agentLocation, Environment.LocationState state) {
		this.agentLocation = agentLocation;
		this.state = state;
	}

	//getter
	//lay trang thai moi truong
	public Environment.LocationState getLocationState() {
		return this.state;
	}

	//lay vi tri agent
	public String getAgentLocation() {
		return this.agentLocation;
	}
}