/**
In Strategy pattern, a class behavior or its algorithm can be changed at run time. This type of design pattern comes under behavior pattern.
In Strategy pattern, we create objects which represent various strategies and a context object whose behavior varies as per its strategy object. The strategy object changes the executing algorithm of the context object.
*/

public class StrategyPattern{
   public static void main(String[] args){
      Context c1=new Context(new AddStrategy());
      System.out.println("57+3= "+c1.execution(57,3));
      c1=new Context(new MultiplyStrategy());
      System.out.println("57+3= "+c1.execution(57,3));
      c1=new Context(new SubstractStrategy());
      System.out.println("57+3= "+c1.execution(57,3));
      char[] data={'h','e','l','o','s'};
      System.out.println(String.valueOf(data,2,3));
      try{
         int ddd=Integer.valueOf("12  58 7  ");
      }catch(NumberFormatException e){
         System.out.println("ppppppp");
      }
      System.out.println("int_is_decimal_palindrome: "+int_is_decimal_palindrome(12320));
      
      int iii=3;
      if(iii==3){
         while(iii>0){
            iii--;
            if(iii==1) break;
            System.out.println("iii: "+iii);
         }
         System.out.println("break donesn't impact");
      }
      System.out.println("hhereeee");
   }
   
   static boolean int_is_decimal_palindrome(int num){
		int a= num,b=0;
		while(a>0){
			b = b*10+a%10;
			a = a/10;
		}
		return num==b;
	}
}
   
interface Strategy{
   int doOperation(int num1, int num2);
}

class AddStrategy implements Strategy{
   public int doOperation(int num1, int num2){
      return num1+num2;
   }
}
class MultiplyStrategy implements Strategy{
   public int doOperation(int num1, int num2){
      return num1*num2;
   }
}
class SubstractStrategy implements Strategy{
   public int doOperation(int num1, int num2){
      return num1-num2;
   }
}

class Context{
   Strategy strategy;
   public Context(Strategy cls){this.strategy=cls;}
   public int execution(int num1, int num2){
      return strategy.doOperation(num1,num2);
   }
}