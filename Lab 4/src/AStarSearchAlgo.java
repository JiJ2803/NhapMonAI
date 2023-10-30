import java.util.*;

public class AStarSearchAlgo implements IInformedSearchAlgo {

	@Override
	public Node execute(Node root, String goal) {
		// TODO Auto-generated method stub
		PriorityQueue<Node> frontier = new PriorityQueue<Node>(new Comparator<Node>() {
			@Override
			public int compare(Node o1, Node o2) {
				// TODO Auto-generated method stub
				int pc = Double.compare(o1.getG() + o1.getH(), o2.getG() + o2.getH());
				if (pc != 0) {
					return pc;
				} else {
					return o1.compareTo(o2);
				}
			}
		});

		List<Node> explored = new ArrayList<>();
		frontier.add(root);
		while (!frontier.isEmpty()) {
			Node currentNode = frontier.poll();
			if (currentNode.getLabel().equals(goal)) {
				root.setParent(null);
				return currentNode;
			}
			explored.add(currentNode);
			List<Edge> children = currentNode.getChildren();
			for (Edge child : children) {
				Node n = child.getEnd();
				double newGCost = currentNode.getG() + child.getWeight();
				if (!frontier.contains(n) && !explored.contains(n)) {
					n.setParent(currentNode);
					n.setG(newGCost);
					frontier.add(n);
				} else if (explored.contains(n)) {
					continue;
				} else if ((n.getG() + n.getH()) > newGCost) {
					frontier.remove(n);
					n.setParent(currentNode);
					n.setG(newGCost);
					frontier.add(n);
				}
			}
		}
		return null;
	}

	@Override
	public Node execute(Node root, String start, String goal) {
		// TODO Auto-generated method stub
		if (root.getLabel().equals(goal))
			return root;
		if (start.equals(goal))
			return new Node(goal);
		Node strartNode = execute(root, start);
		if (strartNode == null)
			return null;
		strartNode.setParent(null);
		strartNode.setG(0);
		return execute(strartNode, goal);
	}

	public boolean isAdmissibleH(Node root, String goal) {
		Queue<Node> allNodes = new LinkedList<Node>();
		Queue<Node> subNodes = new LinkedList<Node>();
		allNodes.add(root);
		subNodes.add(root);
		Node n = new Node(null);
		while (!subNodes.isEmpty()) {
			Node currentNode = subNodes.poll();
			List<Node> children = currentNode.getChildrenNodes();
			for (Node child : children) {
				if (!allNodes.contains(child) && !subNodes.contains(child)) {
					allNodes.add(child);
					subNodes.add(child);
					if (child.getLabel().equals(goal)) {
						n = child;
					}
				}
			}
		}
		while (!allNodes.isEmpty()) {
			Node currentNode = allNodes.poll();
			currentNode.setG(0);
			execute(currentNode, goal);
			if (currentNode.getH() > n.getG()) {
				return false;
			}
		}
		return true;
	}
}