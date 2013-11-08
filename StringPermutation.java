/*
String permutation
*/
import java.util.ArrayList;
public class StringPermutation{
   public static void main(String[] args){
      ArrayList<String>  permutations=getPermu("abc");
      System.out.println("permutations: "+permutations+"\n");
		int i=1;
		for(String s: permutations)
		{
			System.out.println(i+" : "+s);
			i++;
		}
      
      permutation("123");
   }
   
   static ArrayList<String> getPermu(String str){
      ArrayList<String> results=new ArrayList<String>();
      if(str.length()==0){
         results.add(str);
         return results;
      }
      for(int i=0; i<str.length();i++){
         String shorter=str.substring(0,i)+str.substring(i+1);
         ArrayList<String> result=getPermu(shorter);
         for(String e:result){
            results.add(str.charAt(i)+e);
         }
      }
      return results;
   }
   
   
   public static void permutation(String str) { 
    permutation("", str); 
}

private static void permutation(String prefix, String str) {
    int n = str.length();
    if (n == 0) System.out.println(prefix);
    else {
        for (int i = 0; i < n; i++)
            permutation(prefix + str.charAt(i), str.substring(0, i) + str.substring(i+1, n));
    }
}
}