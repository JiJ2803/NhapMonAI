package k21;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BreadthFirstSearchAlgo implements ISearchAlgo{
	@Override
	public Node execute(Node root, String goal) {
		// TODO Auto-generated method stub
		if (root.getLabel().equals(goal)) return root;
		Queue<Node> frontier = new LinkedList<Node>();
		List<Node> explored = new ArrayList<>();
		frontier.add(root);
		while (!frontier.isEmpty()) {
			Node currentNode = frontier.poll();
			if(currentNode.getLabel().equals(goal)) return currentNode;
			explored.add(currentNode);
			List<Node> children = currentNode.getChildrenNodes();
			for (Node child : children) {
				if (!frontier.contains(child) && !explored.contains(child)) {
					child.setParent(currentNode);
					frontier.add(child);
				}
			}
		}
		return null;
	}

	@Override
	public Node execute(Node root, String start, String goal) {
		// TODO Auto-generated method stub
		if (root.getLabel().equals(goal)) return root;
		if (start.equals(goal)) return new Node(goal);
		Node strartNode = execute(root, start);
		strartNode.setParent(null);
		return execute(strartNode, goal);
	}

}
