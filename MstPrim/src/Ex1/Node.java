package Ex1;

import java.util.Vector;

public class Node {
    private Integer data;
    private Vector<Node> adj;
    private Node pai;
    private  int id;
    private int distance_from_tree_root;
    String name;

    public Node(int id, String name) {
        this.id = id;
        adj = new Vector<Node>();
        this.name = new String(name);
        this.distance_from_tree_root = 0;
    }

    public int getDistance_from_tree_root() {
        return distance_from_tree_root;
    }
    public void setDistance_from_tree_root(int distance_from_tree_root) {
        this.distance_from_tree_root = distance_from_tree_root;
    }


    public void addAdj(Node u){
        adj.add(u);
    }

    public Integer getData() {
        return data;
    }

    public void setData(Integer data) {
        this.data = data;
    }

    public void setPai(Node pai) {
        this.pai = pai;
    }

    public Vector<Node> getAdj() {
        return adj;
    }

    public Node getPai() {
        return pai;
    }


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
    public boolean isPaiExistInAdj(){
        for (Node v: adj){
            if (this.pai != null && this.pai.equals(v)){
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return id == node.id && name.equals(node.name);
    }

}

