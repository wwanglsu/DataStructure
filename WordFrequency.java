import java.util.Map;
import java.util.Set;
import java.util.Iterator;
import java.util.TreeMap;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
public class WordFrequency
{
	public static void main(String[] args) throws  NoSuchElementException, FileNotFoundException // ,  For Collection and List null element
	{
		Scanner in=new Scanner(new File("alice30.txt"));
		Map<String, Integer> frequencies=new TreeMap<String, Integer>();
		//Use any characters other than a-z or A-Z or 0-9 as delimiters
		in.useDelimiter("[^a-zA-Z0-9]+");
		
		while(in.hasNext())
		{
			String word=in.next().toLowerCase();
			System.out.println(word);
			Integer count=frequencies.get(word);
			if(count==null) count=1;
			else count++;
			frequencies.put(word, count);			// add and set functions.
			
		}
		for(String key: frequencies.keySet())
		{
			System.out.println(key+"  ,  "+frequencies.get(key));
		}
		
		/*   is not better than above code snippet.
		Set<String> wordSet=frequencies.keySet();
		Iterator iter=wordSet.iterator();
		if(iter.next() ==null ) throw new NoSuchElementException();
		while(iter.hasNext())  
		{
			
			System.out.println("The key: "+iter.next()+"  ,  "+frequencies.get( iter.next()  )  );
			
		}
		*/
		
		
	}
	
}