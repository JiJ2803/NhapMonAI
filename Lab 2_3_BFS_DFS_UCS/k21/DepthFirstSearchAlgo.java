package k21;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class DepthFirstSearchAlgo implements ISearchAlgo{

	@Override
	public Node execute(Node root, String goal) {
		// TODO Auto-generated method stub
		if (root.getLabel().equals(goal)) return root;
		Stack<Node> frontier = new Stack<Node>();
		List<Node> explored = new ArrayList<>();
		frontier.add(root);
		while (!frontier.isEmpty()) {
			Node currentNode = frontier.pop();
			if(currentNode.getLabel().equals(goal)) return currentNode;
			explored.add(currentNode);
			List<Node> children = currentNode.getChildrenNodes();
			for (int i = children.size()-1; i >= 0; i--) {
				if (!frontier.contains(children.get(i)) && !explored.contains(children.get(i))) {
					children.get(i).setParent(currentNode);
					frontier.push(children.get(i));
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
