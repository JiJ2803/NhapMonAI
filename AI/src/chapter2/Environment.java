package chapter2;

import java.util.Random;

public class Environment {
	public static final Action MOVE_LEFT = new DynamicAction("LEFT");
	public static final Action MOVE_RIGHT = new DynamicAction("RIGHT");
	public static final Action MOVE_UP = new DynamicAction("UP");
	public static final Action MOVE_DOWN = new DynamicAction("DOWN");
	public static final Action SUCK_DIRT = new DynamicAction("SUCK");
	public static final String LOCATION_A = "A";
	public static final String LOCATION_B = "B";
	public static final String LOCATION_C = "C";
	public static final String LOCATION_D = "D";
	public static int measurePoints = 0;
	public static boolean hasMoved = true;

	public enum LocationState {
		CLEAN, DIRTY, OBSTACLE
	}

	private EnvironmentState envState;
	private boolean isDone = false;// all squares are CLEAN
	private Agent agent = null;

	public Environment(LocationState locAState, LocationState locBState, LocationState locCState,
			LocationState locDState) {
		envState = new EnvironmentState(locAState, locBState, locCState, locDState);
	}

	// add an agent into the enviroment
	public void addAgent(Agent agent, String location) {
		this.agent = agent;
		envState.setAgentLocation(location);
	}

	public EnvironmentState getCurrentState() {
		return this.envState;
	}

	// Update enviroment state when agent do an action
	public EnvironmentState executeAction(Action action) {
		String agentLocation = envState.getAgentLocation();
		if (action == MOVE_LEFT) {
			if (agentLocation == LOCATION_A) {
				envState.setAgentLocation(LOCATION_A);
				hasMoved = false;
			} else if (agentLocation == LOCATION_D) {
				envState.setAgentLocation(LOCATION_D);
				hasMoved = false;
			} else if (agentLocation == LOCATION_B) {
				envState.setAgentLocation(LOCATION_A);
				hasMoved = true;
			} else if (agentLocation == LOCATION_C) {
				envState.setAgentLocation(LOCATION_D);
				hasMoved = true;
			}

		} else if (action == MOVE_RIGHT) {
			if (agentLocation == LOCATION_A) {
				envState.setAgentLocation(LOCATION_B);
				hasMoved = true;
			} else if (agentLocation == LOCATION_B) {
				envState.setAgentLocation(LOCATION_B);
				hasMoved = false;
			} else if (agentLocation == LOCATION_C) {
				envState.setAgentLocation(LOCATION_C);
				hasMoved = false;
			} else if (agentLocation == LOCATION_D) {
				envState.setAgentLocation(LOCATION_C);
				hasMoved = true;
			}

		} else if (action == MOVE_DOWN) {

			if (agentLocation == LOCATION_A) {
				envState.setAgentLocation(LOCATION_D);
				hasMoved = true;
			} else if (agentLocation == LOCATION_D) {
				envState.setAgentLocation(LOCATION_D);
				hasMoved = false;
			} else if (agentLocation == LOCATION_B) {
				envState.setAgentLocation(LOCATION_C);
				hasMoved = true;
			} else if (agentLocation == LOCATION_C) {
				envState.setAgentLocation(LOCATION_C);
				hasMoved = false;
			}

		} else if (action == MOVE_UP) {
			if (agentLocation == LOCATION_A) {
				envState.setAgentLocation(LOCATION_A);
				hasMoved = false;
			} else if (agentLocation == LOCATION_D) {
				envState.setAgentLocation(LOCATION_A);
				hasMoved = true;
			} else if (agentLocation == LOCATION_B) {
				envState.setAgentLocation(LOCATION_B);
				hasMoved = false;
			} else if (agentLocation == LOCATION_C) {
				envState.setAgentLocation(LOCATION_B);
				hasMoved = true;
			}

		} else if (action == SUCK_DIRT) {
			if (agentLocation.equals(LOCATION_A)) {
				envState.setLocationState(LOCATION_A, LocationState.CLEAN);
			} else if (agentLocation.equals(LOCATION_B)) {
				envState.setLocationState(LOCATION_B, LocationState.CLEAN);
			} else if (agentLocation.equals(LOCATION_C)) {
				envState.setLocationState(LOCATION_C, LocationState.CLEAN);
			} else if (agentLocation.equals(LOCATION_D)) {
				envState.setLocationState(LOCATION_D, LocationState.CLEAN);
			}
		}
		return envState;
	}

	// get percept<AgentLocation, LocationState> at the current location where agent
	// is in.
	public Percept getPerceptSeenBy() {
		String agentLocation = envState.getAgentLocation();
		LocationState locationState = envState.getLocationState(agentLocation);
		return new Percept(agentLocation, locationState);
	}

	public void step() {
		envState.display();
		String agentLocation = this.envState.getAgentLocation();
		Action anAction = agent.execute(getPerceptSeenBy());
		EnvironmentState es = executeAction(anAction);

		System.out.println("Agent Loc.: " + agentLocation + "\tAction: " + anAction);

		if ((es.getLocationState(LOCATION_A) == LocationState.CLEAN)
				&& (es.getLocationState(LOCATION_B) == LocationState.CLEAN))
			isDone = true;// if both squares are clean, then agent do not need to do any action
		es.display();
		PerformanceMeasure(anAction);
	}

	public void step(int n) {
		for (int i = 0; i < n; i++) {
			step();
			System.out.println("-------------------------");
		}
	}

	public void stepUntilDone() {
		int i = 0;

		while (!isDone) {
			System.out.println("step: " + i++);
			step();
		}
	}

	public void PerformanceMeasure(Action action) {

		if (action == SUCK_DIRT) {
			measurePoints = measurePoints + 500;
		}
		if (!hasMoved) {
			measurePoints = measurePoints - 100;
		} else
			measurePoints = measurePoints - 10;

		System.out.println("Performance Measure: " + measurePoints);
	}
}
