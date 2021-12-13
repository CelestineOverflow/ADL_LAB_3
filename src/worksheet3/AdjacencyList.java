package worksheet3;

import org.w3c.dom.Node;

import java.util.Iterator;
import java.util.LinkedList;

public class AdjacencyList implements Iterable<Integer>{//this is some illegal shit right there
    private LinkedList<Integer> neighbours;
    private int id;
    public AdjacencyList(int id){
        this.neighbours = new LinkedList<>();
        this.id = id;
    }

    public int getId() {
        return id;
    }
    public void add(int neighbour){
        neighbours.add(neighbour);
    }

    public boolean contains(int v){
        Iterator<Integer> iterator = this.iterator();
        while(iterator.hasNext()){
             if (iterator.next() == v){
                 return true;
             }
        }
        return false;
    }

    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<Integer>() {
            int tmp = 0;
            public boolean hasNext() {
                return tmp<neighbours.size();
            }

            @Override
            public Integer next() {
                Integer current_element = neighbours.get(tmp);
                tmp++;
                return current_element;
            }
        };
    }
}
