package Ex1;
import java.util.Vector;

public class Graph {
    Vector<Edge> edges;
    Vector<Node> vertex;

    public Graph(Vector<Edge> edges, Vector<Node> vertex) {
        this.edges = edges;
        this.vertex = vertex;
    }
    public Graph(int sizeV, int sizeE){
        this.vertex = new Vector<>(sizeV);
        this.edges = new Vector<>(sizeE);
    }

    public void addEdge(Edge edge){
        this.edges.add(edge);
        edge.getU().getAdj().add(edge.getV());
        edge.getV().getAdj().add(edge.getU());
    }
    public void addExistEdge(Edge edge){
        this.edges.add(edge);
    }

    public void removeEdge(Edge edge){
        edge.getV().getAdj().remove(edge.getU());
        edge.getU().getAdj().remove(edge.getV());
        this.edges.remove(edge);
    }

    public Edge fineEdge(Node u, Node v){
        for (Edge edge: edges){
            if (edge.getV().equals(v) && edge.getU().equals(u) || edge.getV().equals(u) && edge.getU().equals(v)){
                return edge;
            }
        }
        return null;
    }

    public Vector<Edge> getEdges() {
        return edges;
    }

    public Vector<Node> getVertex() {
        return vertex;
    }

    public void printGraph(){
        for (Node v: this.getVertex()){
            System.out.println("Vertex " + v.getName() + " has edge with: ");
            for (Node u: v.getAdj()){
              System.out.println(u.getName());
            }
        }
    }
}
