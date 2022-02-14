import java.util.*;
class LeafNode{
    int num;
    LeafNode(int num){
        this.num = num;
    }
}
class Node{
    String name;
    int min = Integer.MAX_VALUE;
    int max = Integer.MIN_VALUE;
    Node(String name){
        this.name = name;
    }
    ArrayList<Node> neighbour = new ArrayList<>();
    ArrayList<LeafNode> leaf = new ArrayList<>();
}

class AlphaBetaPrunning{
    ArrayList<Node> nodes = new ArrayList<>();
    AlphaBetaPrunning(ArrayList<Node> nodes){
        this.nodes = nodes;
    }

    public void undirectedChildLinks(Node n1,Node n2){
        n1.neighbour.add(n2);
    }

    public void undirectedLeafLinks(Node n1, LeafNode n2){
        n1.leaf.add(n2);
    }
    public ArrayList<Node> getChildNeighbours(Node n1){
        return n1.neighbour;
    }

    public ArrayList<LeafNode> getLeafNeighbour(Node n1){
        return n1.leaf;
    }

    public void minimax(Node root){
            System.out.println(root.name);
            for(Node n : getChildNeighbours(root)){
                System.out.print(n.name + "-->");
                if(getLeafNeighbour(n) != null){
                    for(LeafNode l : getLeafNeighbour(n)){
                        System.out.print(l.num + "  ");
                        if(l.num < n.min){
                            n.min = l.num;
                        }
                    }
                    System.out.println();
                }
                
            }
            System.out.print("Max value is :");
            for(Node n : getChildNeighbours(root)){
                if(n.min > root.max){
                    root.max = n.min;
                }
            }
            System.out.print(root.max);
            System.out.println();
        }
    
        public void maxmin(Node root){
            System.out.println(root.name);
            for(Node n : getChildNeighbours(root)){
                System.out.print(n.name + "-->");
                if(getLeafNeighbour(n) != null){
                    for(LeafNode l : getLeafNeighbour(n)){
                        System.out.print(l.num + "  ");
                        if(l.num > n.max){
                            n.max = l.num;
                        }
                    }
                    System.out.println();
                }
                
            }
            System.out.print("Min value is :");
            for(Node n : getChildNeighbours(root)){
                if(n.max < root.min){
                    root.min = n.max;
                }
            }
            System.out.print(root.min);
            System.out.println();
        }
        //prunning minimax
        public void pminimax(Node root){
            System.out.println(root.name);
            for(Node n : getChildNeighbours(root)){
                System.out.print(n.name + " :");
                    for(LeafNode l : getLeafNeighbour(n)){
                        System.out.print(l.num + "  ");
                        if(l.num < n.min){
                            n.min = l.num;
                        }
                    }
                root.max = n.min;  
                break;   
            }
            System.out.println();
            for(int i = 2; i < nodes.size();i++){
                Node curr = nodes.get(i);
                System.out.print(curr.name + " :");
                for(LeafNode l : getLeafNeighbour(curr)){
                    System.out.print(l.num + "  ");
                    if(l.num < root.max){
                        curr.min = l.num;
                        break;
                    }
                    if(l.num < curr.min){
                        curr.min = l.num;
                    }
                }
                System.out.println();
            }
            for(Node n : getChildNeighbours(root)){
                if(n.min < root.max){
                    root.max = n.min;
                }
            }
            System.out.println("Maximized value is :" +root.max);     
        }
        public void pmaxmin(Node root){
            System.out.println(root.name);
            for(Node n : getChildNeighbours(root)){
                System.out.print(n.name + " :");
                    for(LeafNode l : getLeafNeighbour(n)){
                        System.out.print(l.num + "  ");
                        if(l.num > n.max){
                            n.max = l.num;
                        }
                    }
                root.min = n.max;  
                break;   
            }
            System.out.println();
            for(int i = 2; i < nodes.size();i++){
                Node curr = nodes.get(i);
                System.out.print(curr.name + " :");
                for(LeafNode l : getLeafNeighbour(curr)){
                    System.out.print(l.num + "  ");
                    if(l.num > root.min){
                        curr.max = l.num;
                        break;
                    }
                    if(l.num > curr.max){
                        curr.max = l.num;
                    }
                }
                System.out.println();
            }
            for(Node n : getChildNeighbours(root)){
                if(n.max < root.min){
                    root.min = n.max;
                }
            }
            System.out.println("Minized value is :" +root.min);  
        }
    public static void main(String[] args) {
        ArrayList<Node> nodes = new ArrayList<>();

        Node root = new Node("A");
        Node n2 = new Node("B");
        Node n3 = new Node("C");
        Node n4 = new Node("D");

        nodes.add(root);
        nodes.add(n2);
        nodes.add(n3);
        nodes.add(n4);
 

        LeafNode l1 = new LeafNode(3);
        LeafNode l2 = new LeafNode(12);
        LeafNode l3 = new LeafNode(8);

        LeafNode l4 = new LeafNode(2);
        LeafNode l5 = new LeafNode(6);
        LeafNode l6 = new LeafNode(5);

        LeafNode l7 = new LeafNode(14);
        LeafNode l8 = new LeafNode(5);
        LeafNode l9 = new LeafNode(2);

        AlphaBetaPrunning abp = new AlphaBetaPrunning(nodes);
        abp.undirectedChildLinks(root, n2);
        abp.undirectedChildLinks(root, n3);
        abp.undirectedChildLinks(root, n4);

        abp.undirectedLeafLinks(n2, l1);
        abp.undirectedLeafLinks(n2, l2);
        abp.undirectedLeafLinks(n2, l3);

        abp.undirectedLeafLinks(n3, l4);
        abp.undirectedLeafLinks(n3, l5);
        abp.undirectedLeafLinks(n3, l6);

        abp.undirectedLeafLinks(n4, l7);
        abp.undirectedLeafLinks(n4, l8);
        abp.undirectedLeafLinks(n4, l9);

        System.out.println("---------------Without Prunning-------------------------");
        abp.minimax(root);
        abp.maxmin(root);

        System.out.println("------------------With Pruning------------------------------");
        abp.pminimax(root);
        abp.pmaxmin(root);
    }
}