package k21;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class DepthLimitedSearchAlgo {
	Stack<Node> frontier = new Stack<Node>();
	List<Node> explored = new ArrayList<>();
	public Node execute(Node root, String goal, int limitedDepth) {
		if (root.getLabel().equals(goal)) return root;
		explored.add(root);
		if (limitedDepth==0) {
			if (frontier.isEmpty()) return null;
			else {
				Node n = frontier.pop();
				int nodeLevel = findUpParentNode(root, "S"), nextNodeLevel = findUpParentNode(n, "S");
				return execute(n, goal, nodeLevel-nextNodeLevel);
			}
		}
		else {
			List<Node> children = root.getChildrenNodes();
			if (children.isEmpty()) {
				if (frontier.isEmpty()) return null;
			else {
				Node n = frontier.pop();
				int nodeLevel = findUpParentNode(root, "S"), nextNodeLevel = findUpParentNode(n, "S");
				return execute(n, goal, nodeLevel-nextNodeLevel);
			}
			}
			for (int i = children.size()-1; i >= 0; i--) {
				if (!frontier.contains(children.get(i)) && !explored.contains(children.get(i)) && -limitedDepth<2) {
					children.get(i).setParent(root);
					frontier.push(children.get(i));
				}
			}
			if (frontier.isEmpty()) return null;
			Node n = frontier.pop();
			return execute(n, goal, limitedDepth-1);
		}
	}
	
	public int findUpParentNode(Node n1, String n2) {
		if (n1.getParent().getLabel().equals(n2)) return 1;		
		return 1+findUpParentNode(n1.getParent(), n2);
	}

}
