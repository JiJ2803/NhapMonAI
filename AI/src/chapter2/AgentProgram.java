package chapter2;

import java.util.Random;

import chapter2.Environment.LocationState;

public class AgentProgram {

	public Action execute(Percept p) {// location, status
		String location = p.getAgentLocation();
		Action[] moving = { Environment.MOVE_LEFT, Environment.MOVE_RIGHT, 
				Environment.MOVE_UP, Environment.MOVE_DOWN };

		if (p.getLocationState() == LocationState.DIRTY) {
			return Environment.SUCK_DIRT;
			
		} else if (location.equals(Environment.LOCATION_A)) {
			Random random = new Random();
			int randomIndex = random.nextInt(moving.length);
			Action randomAction = moving[randomIndex];
			return randomAction;

		} else if (location.equals(Environment.LOCATION_B)) {
			Random random = new Random();
			int randomIndex = random.nextInt(moving.length);
			Action randomAction = moving[randomIndex];
			return randomAction;
			
		} else if (location.equals(Environment.LOCATION_C)) {
			Random random = new Random();
			int randomIndex = random.nextInt(moving.length);
			Action randomAction = moving[randomIndex];
			return randomAction;
			
		} else if (location.equals(Environment.LOCATION_D)) {
			Random random = new Random();
			int randomIndex = random.nextInt(moving.length);
			Action randomAction = moving[randomIndex];
			return randomAction;
		}
		return NoOpAction.NO_OP;

	}
}