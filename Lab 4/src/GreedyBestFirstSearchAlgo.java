import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class GreedyBestFirstSearchAlgo implements IInformedSearchAlgo {

	@Override
	public Node execute(Node root, String goal) {
		// TODO Auto-generated method stub
		List<Node> explored = new ArrayList<>();
		PriorityQueue<Node> frontier = new PriorityQueue<Node>(new Comparator<Node>() {

			@Override
			public int compare(Node o1, Node o2) {
				// TODO Auto-generated method stub
				int re = Double.compare(o1.getH(), o2.getH());
				if (re == 0) {
					return o1.getLabel().compareTo(o2.getLabel());
				} else
					return re;
			}

		});
		frontier.add(root);

		while (!frontier.isEmpty()) {
			Node currentNode = frontier.poll();
			if (currentNode.getLabel().equals(goal))
				return currentNode;
			explored.add(currentNode);

			List<Edge> children = currentNode.getChildren();
			for (Edge n : children) {
				Node child = n.getEnd();
				if (!frontier.contains(child) && !explored.contains(child)) {
					frontier.add(child);
					child.setParent(currentNode);
					child.setG(currentNode.getG() + n.getWeight());
				}

				if (frontier.contains(child) && child.getG() > currentNode.getG() + n.getWeight()) {
					frontier.remove(child);
					frontier.add(child);
					child.setG(currentNode.getG() + n.getWeight());
					child.setParent(currentNode);

				}
			}
		}

		return null;
	}

	@Override
	public Node execute(Node root, String start, String goal) {
		// TODO Auto-generated method stub
		if (root.getLabel().equals(goal))
			return new Node(goal);
		if (start.equals(goal))
			return new Node(goal);
		Node strartNode = execute(root, start);
		strartNode.setParent(null);
		strartNode.setH(0);
		return execute(strartNode, goal);
	}

	public Node execute(Node root, String goal, int limitedDepth) {

		return null;
	}

}
