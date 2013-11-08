
import java.util.ArrayList;

public class MinHeap
{
    private ArrayList<Comparable> elements;
    
    //construct an empty MinHeap
    public MinHeap()
    {
        elements=new ArrayList<Comparable>();
        elements.add(null);
    }
    //add a new element to this heap.
    public void add(Comparable newElement)
    {
        //add a new leaf
        elements.add(null);
        int index=elements.size()-1;
        //demote parents that are larger than the new element
        while(index>1 && getParent(index).compareTo(newElement)>0 )
        {
            elements.set(index, getParent(index));
            index=getParentIndex(index);
        }
        //store the new element into the vacant slot.
        elements.set(index, newElement);
    }
    //get the minimum element stored in the heap
    public Comparable peek()
    {
        return elements.get(1);
    }
    //remove the minimum element from this heap.
    public Comparable remove()
    {
        Comparable minimum=elements.get(1);
        //remove the last element
        int lastIndex=elements.size()-1;
        Comparable last=elements.remove(lastIndex);
        if(lastIndex>1)
        {
            elements.set(1, last);
            fixHeap();
        }
        return minimum;
    }
    //turns the tree back into a heap, provided only the root node violates the heap condition.
    private void fixHeap()
    {
        Comparable root=elements.get(1);
        int lastIndex=elements.size()-1;
        //promote children of removed root while they are smaller than last element.
        int index=1;
        boolean more=true;
        while(more)
        {
            int childIndex=this.getLeftChildIndex(index);
            if(childIndex<=lastIndex)
            {
                //Get smaller child
                //Get left child first
                Comparable child=this.getLeftChild(index);
                //use right child instead if it is smaller
                if(getRightChildIndex(index)<=lastIndex && getRightChild(index).compareTo(child)<0)
                {
                    childIndex=getRightChildIndex(index);
                    child=getRightChild(index);
                }
                //check if larger child is smaller than root
                if(child.compareTo(root)<0)
                {
                    //promote child
                    elements.set(index, child);
                    index=childIndex;
                }
                else
                {
                    //root is smaller than both children
                    more=false;
                }
            }
            else
            {
                //no children
                more=false;
            }            
        }
        //store root element in vacant slot
        elements.set(index, root);
    }
    
    //return the number of elements in this heap
    public int size()
    {
        return elements.size()-1;
    }
    //return the index of the left child
    private static int getLeftChildIndex(int index)
    {
        return 2*index;
    }
    //return the index of the right child
    private static int getRightChildIndex(int index)
    {
        return 2*index+1;
    }
    //return the index of parent.
    private static int getParentIndex(int index)
    {
        return index/2;
    }
    //return the value of the left child.
    private Comparable getLeftChild(int index)
    {
        return elements.get(2*index);
    }
    //return the value of the right child
    private Comparable getRightChild(int index)
    {
        return elements.get(2*index+1);
    }
    //return the value of the parent
    private Comparable getParent(int index)
    {
        return elements.get(index/2);
    }   
}