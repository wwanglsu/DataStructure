public class MinHeapRunner
{
   public static void main(String[] args)
   {
        MinHeap q=new MinHeap();
      q.add(new WorkOrder(3, "Shampoo carpets"));
      q.add(new WorkOrder(7, "Empty trash"));
      q.add(new WorkOrder(8, "Water plants"));
      q.add(new WorkOrder(7, "Remove pencil sharpener shavings"));
      q.add(new WorkOrder(6, "Replace light bulb"));
      q.add(new WorkOrder(1, "Fix broken sink"));
      q.add(new WorkOrder(9, "Clean coffee maker"));
      q.add(new WorkOrder(2, "Order cleaning supplies!!!!!!!!"));
      
      while(q.size()>0)
        System.out.println(q.remove());
        
   }
   
   static class WorkOrder implements Comparable
   {
        private int priority;
        private String note;
        public WorkOrder(int aPriority, String aNote)
        {
            this.priority=aPriority;
            this.note=aNote;
        }
        public String toString()
        {
            return "Priority= "+ priority+", description= "+ note;
        }
        public int compareTo(Object otherObject)
        {
            WorkOrder other=(WorkOrder)otherObject;
            if(priority<other.priority) return -1;
            if(priority>other.priority) return 1;
            return 0;
        }
   }
}