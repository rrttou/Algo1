package Ex1;

public class Edge {
    private Node u, v;
    private Integer weight;
    public Edge(Node v, Node u, WeightFunc w) {
        this.u = u;
        this.v = v;
        this.u.getAdj().add(v);
        this.v.getAdj().add(u);
        this.weight =w.weight(v, u);
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Edge edge = (Edge) o;
        return u.equals(edge.u) && v.equals(edge.v);
    }

    public Node getU() {
        return u;
    }

    public Node getV() {
        return v;
    }

    public int getWeight() {
        return weight;
    }

    public void printEdge(){
        System.out.println(this.v.getName() + " <-> " + this.u.getName());
    }




}
