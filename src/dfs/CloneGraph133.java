package dfs;

import java.util.HashMap;
import java.util.Map;

public class CloneGraph133 {
	public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
		Map<Integer, UndirectedGraphNode> nodeMap = new HashMap<Integer, UndirectedGraphNode>();
		return cloneGraph(node, nodeMap);
	}

	private UndirectedGraphNode cloneGraph(UndirectedGraphNode node, Map<Integer, UndirectedGraphNode> nodeMap) {
		if (node == null)
			return null;
		if (nodeMap.get(node.label) != null)
			return nodeMap.get(node.label);

		UndirectedGraphNode cnode = new UndirectedGraphNode(node.label);
		nodeMap.put(cnode.label, cnode);
		if (node.neighbors != null) {
			for (UndirectedGraphNode neighbor : node.neighbors) {
				cnode.neighbors.add(cloneGraph(neighbor, nodeMap));
			}
		}
		return cnode;
	}
}
