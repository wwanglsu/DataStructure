/** Naive String matching  O((n-m+1)*m)
    Rabin-Karp  O(m)+O((n-m+1)*m) : matches the hash value of the pattern with the hash value of current substring of text, and if the hash values match then only it starts matching individual characters.
    Finite automaton: O(M|sigama|)+O(n)
    KMP: O(m)+O(n)
*/
import java.util.Arrays;
public class StringMatch{
   public static final int NO_OF_CHARS=256; // size of alphabet (total number of possible characters in pattern and text)
	public static void main(String[] args){
      String T="abacbacbakjt"; //"000010001010001";//
      String P="cba";  // "0001";//
      NaiveMatch(T, P);
      //RabinKarpMatch("314152359023141526739921", "31415", 10, 13);
      RabinKarpMatch("AABAACAADAABAAABAA", "AABA", 10, 13);
      FiniteAutomataMatch("000010001010001", "0001");
      KMP_Match("AABAACAADAABAAABAA", "AABA");      
      //System.out.println("\n"+Arrays.toString(computePrefixFunction("AAA")));
	}	
	static void NaiveMatch(String T, String P){
		int n=T.length();
		int m=P.length();
		for(int s=0; s<=n-m; s++){
			for(int j=0; j<m; j++){
				if(T.charAt(s+j)!=P.charAt(j)) break;
            if(j==m-1) System.out.println("Naive Pattern occurs with shift at: "+s);            
			}         
		}             
	}
   // work on a big numeric string using hash value.
   static void RabinKarpMatch(String T, String P, int radix, int modulus){
      int n=T.length();
      int m=P.length();
      int h=(int)Math.pow(radix, m-1) % modulus;      
      int t=0; //hash value for text T
      int p=0;  // hash value for pattern
      // preprocessing, calculate the hash value for pattern and first window of text.
      for(int i=0; i<m; i++){  
         p=(radix*p+P.charAt(i)) % modulus; //hash function.
         t=(radix*t+T.charAt(i)) % modulus; //hash function.
      }
      //slide the pattern over text one by one matching
      for(int s=0; s<=n-m; s++){      
         if(p==t){ //hash value equals
         /*   if(P.equals(T.substring(s, s+m)))  //works well too.
               System.out.println("Rabin Karp Pattern occurs with shift at: "+s);  */
            for(int j=0; j<m; j++){
				    if(T.charAt(s+j)!=P.charAt(j)) break;
               if(j==m-1) System.out.printf("\nRabin Karp Pattern with shift at: %d", s);                          
			   }
         }
         //calculate hash value for next window of text, removing leading digit, add trailing digit.
         if(s<n-m){
            t=(radix*(t-T.charAt(s)*h)+T.charAt(s+m)) % modulus;
            if(t<0) t=t+modulus;//If negative, converting it to positive
         }
      }      
   }   
   // build Finite Automata (FA) into 2D array. At every step, we consider next character of text, look for the next state in the built FA and move to new state. If we reach final state, then pattern is found in text.
   static void FiniteAutomataMatch(String T, String P){
      int n=T.length();
      int m=P.length();
      int[][] TF=new int[m+1][NO_OF_CHARS];
      computeTransitionFunction(P, m, TF);
      //process text over Finite Automata
      int j=0;
      for(int i=0; i<n; i++){
         j=TF[j][T.charAt(i)];
         if(j==m) System.out.printf("\nFinite Automata: pattern found at index %d", i-m+1);     
      }
   }
   //builds the TF table which represents Finite Automata for a given pattern
   static void computeTransitionFunction(String P, int m, int[][] TF){
      int lps=0; //longest prefix suffix, in fact it is the above two row cross-character-value.
      // Fill entries in first row
      for (int x =0; x < NO_OF_CHARS; x++)
         TF[0][x] = 0;
      TF[0][P.charAt(0)] = 1;
      // Fill entries in other rows
      for(int i=1; i<=m; i++){
         for(int x=0; x<NO_OF_CHARS; x++)
            TF[i][x]=TF[lps][x]; // Copy values from row at index lps
         if(i<m){ 
            TF[i][P.charAt(i)]=i+1; // Update the entry corresponding to this character         
            if(i<m) lps=TF[lps][P.charAt(i)]; // Update lps for next row to be filled
         }
         //if(i==m) TF[i][m]=i+1;
      }
   }
   //Knuth-Morris-Pratt algorithm
   static void KMP_Match(String T, String P){
      int n=T.length();
      int m=P.length();
      int[] aa=computePrefixFunction(P);      
      int q=0; //match Num
      for(int i=0; i<n; i++){
         while(q>0 && P.charAt(q)!=T.charAt(i)) 
            q=aa[q-1]; //next character doesn't match
         if(P.charAt(q)==T.charAt(i)) q+=1; //next character matches
         if(q==m){
            System.out.printf("\nKMP: pattern found at index %d", i-m+1);
            q=aa[q-1];//look for the next match
         }
      }
   }
   static int[] computePrefixFunction(String P){
      int m=P.length();
      int len=0; // length of the previous longest prefix suffix
      int[] array=new int[m];
      array[0]=0;
      for(int i=1; i<m; i++){
         while(len>0 && P.charAt(len)!=P.charAt(i))
            len=array[len-1]; //最大左前缀
         if(P.charAt(len)==P.charAt(i)) len+=1;
         array[i]=len;
      }
      return array;
   }
}