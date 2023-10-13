package k21;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class UniformCostSearchAlgo implements ISearchAlgo{

	@Override
	public Node execute(Node root, String goal) {
		// TODO Auto-generated method stub
		PriorityQueue<Node> frontier = new PriorityQueue<Node>(new Comparator<Node>() {

			@Override
			public int compare(Node o1, Node o2) {
				// TODO Auto-generated method stub
				return (int) (o1.getPathCost()-o2.getPathCost());
			}
			
		});
		
		if (root.getLabel().equals(goal)) return root;
		List<Node> explored = new ArrayList<>();
		frontier.add(root);
		while (!frontier.isEmpty()) {
			Node currentNode = frontier.poll();
			if(currentNode.getLabel().equals(goal)) return currentNode;
			explored.add(currentNode);
			List<Node> children = currentNode.getChildrenNodes();
			for (Node child : children) {
				if (!frontier.contains(child) && !explored.contains(child)) {
					for (Edge edge : currentNode.getChildren()) {
						if (edge.getEnd().equals(child)) {
							child.setPathCost(edge.getWeight()+currentNode.getPathCost());
							break;
						}
					}
					child.setParent(currentNode);
					frontier.add(child);
				}else {
					double newChildPathCost = 0;
					for (Edge edge : currentNode.getChildren()) {
						if (edge.getEnd().equals(child)) {
							newChildPathCost = edge.getWeight() + currentNode.getPathCost();
						}
					}
					for (Node frnode : frontier) {
						if (frnode.equals(child)) {
							if (frnode.getPathCost() > newChildPathCost) {
								frontier.remove(frnode);
								child.setPathCost(newChildPathCost);
								child.setParent(currentNode);
								frontier.add(child);
								break;
							}
						}
					}
				}
			}
		}
		return null;
	}

	
	@Override
	public Node execute(Node root, String start, String goal) {
		// TODO Auto-generated method stub
		if (root.getLabel().equals(goal)) return new Node(goal);
		if (start.equals(goal)) return new Node(goal);
		Node strartNode = execute(root, start);
		strartNode.setParent(null);
		strartNode.setPathCost(0);
		return execute(strartNode, goal);
	}
	
	public Node execute(Node root, String goal, int limitedDepth) {
		
		
		return null;
	}

}
