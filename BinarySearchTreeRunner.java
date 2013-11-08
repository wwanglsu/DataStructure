//This is to test BinarySearchTree
import java.util.Random;
import java.util.Arrays;
public class BinarySearchTreeRunner
{
	public static void main(String[] args)
	{
		Random generator=new Random();
		BinarySearchTree BST=new BinarySearchTree();
		BinarySearchTree BST2=new BinarySearchTree();
		int[] a=new int[10];
		for(int i=0; i<a.length;i++)
		{
			a[i]=generator.nextInt(10);
			BST2.add(a[i]);
		}
		BST2.print();
		
		BST.add("CA");
		BST.add("BC");
		BST.add("C");
		BST.add("D");
		BST.add("KE");
		BST.add("F");
		BST.add("G");
		BST.add("H");
		BST.add("AB");
		BST.add("I");
		BST.add("J");
		
		BST.print();
	  BinarySearchTree t = new BinarySearchTree();
	  t.add("D");
      t.add("B");
      t.add("A");
      t.add("C");
      t.add("F");
      t.add("E");
      t.add("I");
      t.add("G");
      t.add("H");
      t.add("J");
      t.remove("A"); // Removing leaf
      t.remove("B"); // Removing element with one child
      t.remove("F"); // Removing element with two children
      t.remove("D"); // Removing root
      t.print();
      System.out.println( "4/2= "+((64>>3)+0));
	  
	  
	  
	}
}
