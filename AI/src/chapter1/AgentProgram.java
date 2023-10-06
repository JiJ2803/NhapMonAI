package chapter1;

import chapter1.Environment.LocationState;

public class AgentProgram {

	public Action execute(Percept p) {// location, status
		 String location = p.getAgentLocation();

	        if (p.getLocationState() == LocationState.DIRTY) {
	            return Environment.SUCK_DIRT;
	        } else if (location.equals(Environment.LOCATION_A)) {
	            return Environment.MOVE_RIGHT;
	        } else if (location.equals(Environment.LOCATION_B)) {
	            return Environment.MOVE_LEFT;
	        }
		return NoOpAction.NO_OP;
		
	}
}