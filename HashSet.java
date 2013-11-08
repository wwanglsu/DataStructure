
import java.util.AbstractSet;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class HashSet extends AbstractSet
{
    private int size;
    private Node[] buckets;
    
    public HashSet(int bucketsLength)
    {
        buckets=new Node[bucketsLength];
        size=0;
    }
    
    public boolean contains(Object x)
    {
        int h=x.hashCode();
        if(h<0) h=-h;
        h=h%buckets.length;
        
        Node current=buckets[h];
        while(current != null)
        {
            if(current.data.equals(x)) return true;
            else current=current.next;
        }
        return false;
    }
    
    public boolean add(Object x)
    {
        int h=x.hashCode();
        if(h<0) h=-h;
        h=h%buckets.length;
        
        Node current=buckets[h];
        while(current != null)
        {
            if(current.data.equals(x)) return false;
            current=current.next;
        }
        Node newNode=new Node();
        newNode.data=x;
        newNode.next=buckets[h];  // now put
        buckets[h]=newNode;       // newNode to buckets[h]
        size++;
        return true;
    }
    
    public boolean remove(Object x)
    {
        int h=x.hashCode();
        if(h<0) h=-h;
        h=h%buckets.length;
        
        Node current=buckets[h];
        Node previous=null;
        while(current != null)
        {
            if(current.data.equals(x))
            {
                if(previous==null) buckets[h]=current.next;
                else previous.next=current.next;
                size--;
                return true;
            }
            previous=current;
            current=current.next;
        }
        return false;
    }
    
    public Iterator iterator()
    {
        return new HashSetIterator();
    }
    
    public int size()
    {
        return size;
    }
    
    class Node
    {
        public Object data;
        public Node next;
    }
    
    class HashSetIterator implements Iterator
    {
        private int previousBucket;
        private int bucket;
        private Node previous;
        private Node current;
        
        public HashSetIterator()
        {
            previousBucket=-1;
            bucket=-1;
            current=null;
            previous=null;
        }
        
        public boolean hasNext()
        {
            if(current != null && current.next != null) return true;
            for(int i=bucket+1; i<buckets.length; i++)
                if(buckets[i] != null) return true;
            return false;
        }
        
        public Object next()
        {
            previous=current;
            previousBucket=bucket;
            if(current == null || current.next == null)
            {
                bucket++; // move to next bucket.
                while(bucket<buckets.length && buckets[bucket] == null)
                    bucket++;
                if(bucket<buckets.length) current=buckets[bucket];
                else throw new NoSuchElementException();
            }
            else current=current.next; // move to the next element in bucket.
            return current.data;            
        }
        
        public void remove()
        {
            if(previous != null && previous.next== current) previous.next=current.next; //previous is the null or last position ina buckets[]
            else if(previousBucket<bucket) buckets[bucket]=current.next; //next line
            else throw new IllegalStateException();
            current=previous;
            bucket=previousBucket;
            size--;
        }        
    }
}