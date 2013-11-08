import java.util.Iterator;
import java.util.Set;

public class HashSetDemo
{
    public static void main(String[] args)
    {
        Set names=new HashSet(31);
          names.add("Harry");
          names.add("Sue");
          names.add("Nina");
          names.add("Susannah");
          names.add("Larry");
          names.add("Eve");
          names.add("Sarah");
          names.add("Adam");
          names.add("Tony");
          names.add("Katherine");
          names.add("Juliet");
          names.add("Romeo");
          names.remove("Romeo");
          names.remove("George");
          Iterator ite=names.iterator();
          System.out.println("Original size: "+names.size());
          System.out.println("contains: "+names.contains("Eve"));
          while (ite.hasNext())
         {
         System.out.println(ite.next());
         ite.remove();
         
         }
         
         System.out.println("contains: "+names.contains("Eve"));
         System.out.println("Now size: "+names.size());
    }
}