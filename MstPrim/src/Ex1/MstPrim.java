package Ex1;



public class MstPrim {
    final Integer INFINITY = Integer.MAX_VALUE;
    MinHeap priority_Q;
    Graph mst;
    public MstPrim(Graph graph) {
        this.priority_Q = new MinHeap(graph.getVertex().size());
        mst = new Graph(graph.getVertex().size(), graph.getEdges().size());
    }

    public void  RunMst(Graph g, Node s, WeightFunc w){ //g contains edges with weight
        priority_Q = new MinHeap(g.getVertex().size()); //O(n)
        for (Node node : g.getVertex()) {
            priority_Q.add(node);
        }
        for (int i = 0; i<priority_Q.getSize(); i++){ //O(n)
            priority_Q.getHeap()[i].setData(INFINITY);
        }
        s.setData(0);
        s.setPai(null);
        priority_Q.BuildHeap(); //O(n)
        while (!priority_Q.isEmpty()){
            Node v = priority_Q.Extract_Min();
               for(Node v1: v.getAdj()){
                if (priority_Q.isExist(v1) && w.weight(v, v1) < v1.getData()){
                    v1.setPai(v);
                    v1.setData(w.weight(v, v1));
                    v1.setDistance_from_tree_root(v.getDistance_from_tree_root() + 1); //use a little bit of BFS logic for tracking a cycle after adding an edge to mst
                    priority_Q.Heapify_up(priority_Q.Whereis(v1)); //logn, where is returns the index of v1 in heap
                }
            }
               mst.getVertex().add(v);
               for (Node v1: v.getAdj()){
                   if (v1.getPai() != null && v1.getPai().equals(v)){
                       mst.addExistEdge(g.fineEdge(v1, v1.getPai()));
                   }
               }

        }
    }

    void fixMST(Edge edge, WeightFunc w, Graph graph){
        Node u = edge.getU(); // uses u, v, edge references to move in mst cycle without changing anything
        Node v = edge.getV();
        Edge ToBeRemoveEdge = edge; //In the end it will be the max value edge which will be removed from the tree
        Edge max1 = edge; //ToBeRemoved = MAX_WEIGHT(max1, max2)
        Edge max2 = edge;

            while (u.getDistance_from_tree_root() > v.getDistance_from_tree_root()){
                if (max1.getWeight() < w.weight(u, u.getPai())){
                    max1 = graph.fineEdge(u, u.getPai());
                }
                u = u.getPai();
            }
            while (v.getDistance_from_tree_root() > u.getDistance_from_tree_root()){
                if (max2.getWeight() < w.weight(v, v.getPai())){
                    max2 = graph.fineEdge(v, v.getPai());
                }
                v = v.getPai();
            }
            if (max1.getWeight() > ToBeRemoveEdge.getWeight()){
                ToBeRemoveEdge = max1;
            }
            if (max2.getWeight() > ToBeRemoveEdge.getWeight()){
                ToBeRemoveEdge = max2;
            }
             while (!u.equals(v)) {//As long as v and u are not the same vertex
                 if (ToBeRemoveEdge.getWeight() < w.weight(u, u.getPai())){ //move backward in the cycle until v and u in the same distance from mst root
                     ToBeRemoveEdge = graph.fineEdge(u, u.getPai());
                 }
                 if (ToBeRemoveEdge.getWeight() < w.weight(v, v.getPai())){
                     ToBeRemoveEdge = graph.fineEdge(v, v.getPai());
                 }
                 v = v.getPai();
                 u = u.getPai();
             }
             if (ToBeRemoveEdge.equals(edge))//If edge which sent to this function is the largest weight then it wil be removed
                graph.removeEdge(ToBeRemoveEdge);
             else {
                        for (Node v1: ToBeRemoveEdge.getV().getAdj()){
                            if (w.weight(v1, ToBeRemoveEdge.getV()) < ToBeRemoveEdge.getV().getData()){
                                ToBeRemoveEdge.getV().setPai(v1);
                                ToBeRemoveEdge.getV().setData(w.weight(v1, ToBeRemoveEdge.getV()));
                                ToBeRemoveEdge.getV().setDistance_from_tree_root(v1.getDistance_from_tree_root() + 1);
                            }
                        }
                        for (Node v1: ToBeRemoveEdge.getU().getAdj()){
                             if (w.weight(v1, ToBeRemoveEdge.getU()) < ToBeRemoveEdge.getU().getData()){
                                 ToBeRemoveEdge.getU().setPai(v1);
                                 ToBeRemoveEdge.getU().setData(w.weight(v1, ToBeRemoveEdge.getU()));
                                 ToBeRemoveEdge.getU().setDistance_from_tree_root(v1.getDistance_from_tree_root() + 1);
                             }
                        }
                        mst.removeEdge(ToBeRemoveEdge);
                        mst.addEdge(edge);
             }
    }



    void printMST(){
        System.out.println("The vertexes and edges in MST: ");
        for (int i = mst.getVertex().size()-1; i>0;i--){
            System.out.println(mst.getVertex().elementAt(i).getName() + " <-> " + mst.getVertex().elementAt(i).getPai().getName());
        }
    }
}

//if (ToBeRemoveEdge.getV().isPaiExistInAdj()){ //Block to fix the data of the new edge vertexes in the mst tree
//        for (Node v2: ToBeRemoveEdge.getV().getAdj()){
//        if (ToBeRemoveEdge.getV().equals(v2.getPai())){
//        if (!edge.getV().equals(v2)){
//
//        }
//        ToBeRemoveEdge.getV().setData(w.weight(ToBeRemoveEdge.getV(), v2));
//        ToBeRemoveEdge.getV().setDistance_from_tree_root(v2.getDistance_from_tree_root() + 1);
//        }
//        if (edge.getV().getPai().equals(ToBeRemoveEdge.getV())){
//        edge.getV().setPai(edge.getU());
//        edge.getV().setData(w.weight(edge.getV(), edge.getU()));
//        edge.getV().setDistance_from_tree_root(edge.getU().getDistance_from_tree_root() + 1);
//        }
//        }