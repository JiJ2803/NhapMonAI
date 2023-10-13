package k21;

public class TestDrive {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Node nodeS = new Node("S");
		Node nodeA = new Node("A"); 
		Node nodeB = new Node("B");
		Node nodeC = new Node("C"); 
		Node nodeD = new Node("D");
		Node nodeE = new Node("E"); 
		Node nodeF = new Node("F");
		Node nodeG = new Node("G"); 
		Node nodeH = new Node("H");
		nodeS.addEdge(nodeA, 5); 
		nodeS.addEdge(nodeB, 2);
		nodeS.addEdge(nodeC, 4); 
		nodeA.addEdge(nodeD, 9);
		nodeA.addEdge(nodeE, 4); 
		nodeB.addEdge(nodeG, 6);
		nodeC.addEdge(nodeF, 2); 
		nodeD.addEdge(nodeH, 7);
		nodeE.addEdge(nodeG, 6); 
		nodeF.addEdge(nodeG, 1);

		//Breadth First Search Test
		System.out.println("*Breadth First Search Test:");
		System.out.println("Breadth First Search 1 Test:");
		ISearchAlgo algoBFS = new BreadthFirstSearchAlgo();
		Node resultBFS1 = algoBFS.execute(nodeS, "G");
		System.out.println(NodeUtils.printPath(resultBFS1));
		
		System.out.println("Breadth First Search 2 Test:");
		Node resultBFS2 = algoBFS.execute(nodeS, "S", "G");
		System.out.println(NodeUtils.printPath(resultBFS2));
		
		//Depth First Search Test
		System.out.println("\n*Depth First Search Test:");
		System.out.println("Depth First Search 1 Test:");
		ISearchAlgo algoDFS = new DepthFirstSearchAlgo();
		Node resultDFS1 = algoDFS.execute(nodeS, "G");
		System.out.println(NodeUtils.printPath(resultDFS1));
		
		System.out.println("Depth First Search 2 Test:");
		Node resultDFS2 = algoDFS.execute(nodeS, "A", "G");
		System.out.println(NodeUtils.printPath(resultDFS2));
		
		//Uniform Cost Search Test
		System.out.println("\n*Uniform Cost Search Test:");
		System.out.println("Uniform Cost Search 1 Test:");
		ISearchAlgo algoUCS = new UniformCostSearchAlgo();
		Node resultUCS1 = algoUCS.execute(nodeS, "G");
		System.out.println(resultUCS1);
		System.out.println(NodeUtils.printPath(resultUCS1));
		
		System.out.println("Uniform Cost Search 2 Test:");
		Node resultUCS2 = algoUCS.execute(nodeS, "C", "G");
		System.out.println(resultUCS2);
		System.out.println(NodeUtils.printPath(resultUCS2));
		
		//Depth Limited Search
		int limitedDepth1 = 2;
		System.out.println("\nDepth Limited Search Test with limited depth = " + limitedDepth1 + ":");
		DepthLimitedSearchAlgo algoDLS = new DepthLimitedSearchAlgo();
		Node resultDLS1 = algoDLS.execute(nodeS, "G", limitedDepth1);
		System.out.println(NodeUtils.printPath(resultDLS1));
		
		int limitedDepth2 = 4;
		System.out.println("Depth Limited Search Test with limited depth = " + limitedDepth2 + ":");
		DepthLimitedSearchAlgo algoDLS2 = new DepthLimitedSearchAlgo();
		Node resultDLS2 = algoDLS2.execute(nodeS, "G", limitedDepth2);
		System.out.println(NodeUtils.printPath(resultDLS2));
	}

}
