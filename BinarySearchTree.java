/*
This class implements a binary search tree whose nodes hold objects that implement the computer interface.
*/

public class BinarySearchTree
{
	private Node root;
	//Construct an empty tree
	public BinarySearchTree()
	{
		root=null;
	}
	
	//insert a new node into the tree
	public void add(Comparable obj)
	{
		Node newNode=new Node();
		newNode.data=obj;
		newNode.left=null;
		newNode.right=null;
		if(root==null) root=newNode;
		else root.addNode(newNode);
	}
	
	// Tries to find an object in the tree
	public boolean find(Comparable obj)
	{
		Node current=root;
		while(current != null)
		{
			int d=current.data.compareTo(obj);
			if(d==0) return true;
			else if(d>0) current=current.left;
			else current=current.right;
		}
		return false;		
	}
	
	public void remove(Comparable obj)
	{
		//Find node to be remove
		Node toBeRemoved=root;
		Node parent=null;
		boolean found=false;
		while( !found && toBeRemoved !=null) //search the BST for obj first
		{
			int d=toBeRemoved.data.compareTo(obj);
			if(d==0) found=true;
			else
			{
				parent=toBeRemoved;
				if(d>0) toBeRemoved=toBeRemoved.left;
				else toBeRemoved=toBeRemoved.right;
			}
		}
		if(! found) return;
		//Now toBeRemoved contains obj
			// if one of the children is empty, use the other.
		if(toBeRemoved.left==null || toBeRemoved.right==null)
		{
			Node newChild;
			if(toBeRemoved.left==null)
				newChild=toBeRemoved.right;
			else 
				newChild=toBeRemoved.left;
			
			if(parent==null) // Found in root.
				root=newChild;
			else if(parent.left==toBeRemoved)
				parent.left=newChild;
			else 
				parent.right=newChild;
			return;			
		}
			//Neither subtree is empty
			//Find smallest element of the right subtree
			Node smallestParent=toBeRemoved;
			Node smallest=toBeRemoved.right;
			while(smallest.left != null)  //find successor
			{
				smallestParent=smallest;
				smallest=smallest.left;
			}
			//smallest contains smallest child in right subtree.
			//move contents, unlink child.
			toBeRemoved.data=smallest.data;
			if(smallestParent==toBeRemoved)
				smallestParent.right=smallest.right;
			else 
				smallestParent.left=smallest.right;
	}
	
	//print the contents of the tree in sorted order.
	public void print()
	{
		if(root != null)
			root.printNodes();
		System.out.println();
	}
	
	//A node of a tree stores a data item and references of left and right child nodes.
	class Node
	{
		private Comparable data;
		public Node left;
		public Node right;
		
		//insert a new node as a descendant of this node.
		public void addNode(Node newNode)
		{
			int comp=newNode.data.compareTo(this.data);
			if(comp<0)
			{
				if(this.left==null) this.left=newNode;
				else this.left.addNode(newNode);
			}
			else if(comp > 0)
			{
				if(this.right == null) this.right=newNode;
				else this.right.addNode(newNode);
			}			
		}
		//print this node and all of its descendants in sorted order.
		public void printNodes()
		{
			if(this.left !=null) this.left.printNodes();
			System.out.println(data+"  ");
			if(right !=null) this.right.printNodes();
		}		
	}	
}