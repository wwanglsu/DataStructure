/**
The singleton pattern ensures a class has only one instance and ensures access to the instance by the method.
*/
public class SingletonPattern{
   public static void main(String[] args){
      Singleton s=Singleton.getInstance();
      s.showMessage();
   }
}

class Singleton{
   private static Singleton _instance=null;
   public static Singleton getInstance(){
      if(_instance==null){
         _instance=new Singleton();
      }
      return _instance;
   }
   public void showMessage(){
      System.out.println("Hello, this is singleton!");
   }
}