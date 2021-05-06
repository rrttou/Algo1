//Raz Yaniv 302826342
//Itay Levi 311402754

package Ex1;
import java.util.Vector;

public class Main {
    public static void main(String[] args) {
        WeightFunc w = (Node a, Node b) -> a.getId() + b.getId(); // weight func which will be send to mstPrim
        Vector <Node> nodes = new Vector<>();
        Vector <Edge> ALL_edges = new Vector<>();

        //each node has unique id and name
        Node a = new Node(0, "a");
        Node b = new Node(1, "b");
        Node c = new Node(2, "c");
        Node d = new Node(3, "d");
        Node e = new Node(4, "e");
        Node f = new Node(5, "f");
        Node g = new Node(6, "g");
        Node h = new Node(7, "h");
        Node i = new Node(8, "i");
        Node j = new Node(9, "j");
        Node k = new Node(10, "k");
        Node l = new Node(11, "l");
        Node m = new Node(12, "m");
        Node n = new Node(13, "n");
        Node o = new Node(14, "o");
        Node p = new Node(15, "p");
        Node q = new Node(16, "q");
        Node r = new Node(17, "r");
        Node s = new Node(18, "s");
        Node t = new Node(19, "t");
        Node x = new Node(20, "x");

        nodes.add(a);
        nodes.add(b);
        nodes.add(c);
        nodes.add(d);
        nodes.add(e);
        nodes.add(f);
        nodes.add(g);
        nodes.add(h);
        nodes.add(i);
        nodes.add(j);
        nodes.add(k);
        nodes.add(l);
        nodes.add(m);
        nodes.add(n);
        nodes.add(o);
        nodes.add(p);
        nodes.add(q);
        nodes.add(r);
        nodes.add(s);
        nodes.add(t);
        nodes.add(x);

        //(a,b) and (b,a) is the same
        ALL_edges.add(new Edge(a, b, w));
        ALL_edges.add(new Edge(a, c, w));
        ALL_edges.add(new Edge(a, d, w));
        ALL_edges.add(new Edge(d, c, w));
        ALL_edges.add(new Edge(d, f, w));
        ALL_edges.add(new Edge(d, g, w));
        ALL_edges.add(new Edge(b, f, w));
        ALL_edges.add(new Edge(f, l, w));
        ALL_edges.add(new Edge(g, h, w));
        ALL_edges.add(new Edge(g, j, w));

        ALL_edges.add(new Edge(e, j, w));
        ALL_edges.add(new Edge(e, i, w));
        ALL_edges.add(new Edge(i, k, w));
        ALL_edges.add(new Edge(k, l, w));
        ALL_edges.add(new Edge(c, e, w));
        ALL_edges.add(new Edge(a, s, w));
        ALL_edges.add(new Edge(s, m, w));
        ALL_edges.add(new Edge(s, c, w));
        ALL_edges.add(new Edge(s, e, w));
        ALL_edges.add(new Edge(m, n, w));

        ALL_edges.add(new Edge(m, i, w));
        ALL_edges.add(new Edge(m, e, w));
        ALL_edges.add(new Edge(e, d, w));
        ALL_edges.add(new Edge(d, b, w));
        ALL_edges.add(new Edge(f, g, w));
        ALL_edges.add(new Edge(e, g, w));
        ALL_edges.add(new Edge(j, h, w));
        ALL_edges.add(new Edge(i, j, w));
        ALL_edges.add(new Edge(i, n, w));
        ALL_edges.add(new Edge(n, k, w));

        ALL_edges.add(new Edge(n, o, w));
        ALL_edges.add(new Edge(k, o, w));
        ALL_edges.add(new Edge(k, j, w));
        ALL_edges.add(new Edge(j, l, w));
        ALL_edges.add(new Edge(o, l, w));
        ALL_edges.add(new Edge(x, l, w));
        ALL_edges.add(new Edge(p, l, w));
        ALL_edges.add(new Edge(t, l, w));
        ALL_edges.add(new Edge(q, l, w));
        ALL_edges.add(new Edge(t, p, w));

        ALL_edges.add(new Edge(a, r, w));
        ALL_edges.add(new Edge(r, b, w));
        ALL_edges.add(new Edge(r, f, w));
        ALL_edges.add(new Edge(f, q, w));
        ALL_edges.add(new Edge(r, q, w));
        ALL_edges.add(new Edge(r, t, w));
        ALL_edges.add(new Edge(q, t, w));
        ALL_edges.add(new Edge(o, x, w));
        ALL_edges.add(new Edge(p, x, w));
        ALL_edges.add(new Edge(h, l, w));



        Graph graph = new Graph(ALL_edges, nodes);
        graph.printGraph();
        System.out.println("-----------------------------------");
        MstPrim mst = new MstPrim(graph);
        mst.RunMst(graph, graph.getVertex().elementAt(0), w);//MST(G, s, w)
        mst.printMST();
        System.out.println("-----------------------------------");
        Edge p_o = new Edge(p, o, w); //doesn't change the tree
        graph.getEdges().add(p_o);
        System.out.println("The added edge which doesn't change the tree: ");
        p_o.printEdge();
        mst.fixMST(p_o, w, graph);
        System.out.println("-----------------------------------");
        mst.printMST();
        System.out.println("-----------------------------------");
        System.out.println("The added edge which does change the tree: ");
        Edge a_o = new Edge(a, o, w);//change the tree
        a_o.printEdge();
        graph.getEdges().add(a_o);
        mst.fixMST(a_o, w, graph);//o_k edge removed from the tree and o_a edge added to the tree from the graph
        mst.printMST();

    }
}

