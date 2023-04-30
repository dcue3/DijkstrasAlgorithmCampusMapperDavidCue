import java.util.List;

public interface ShortPathGraphInterface<NodeType, EdgeType extends Number> extends GraphADT<NodeType, EdgeType> {
	// public ShortPathGraph();
	public List<NodeType> shortestPathDataRequiredNode(NodeType start, NodeType end, NodeType required);
	public double shortestPathCostRequiredNode(NodeType start, NodeType end, NodeType required);
	public NodeType getNode(NodeType x);
	public double shortestPathCost(NodeType start, NodeType end);
	public List<NodeType> shortestPathData(NodeType start, NodeType end);
	public ShortPathGraphInterface<NodeType, EdgeType> clear();
}

