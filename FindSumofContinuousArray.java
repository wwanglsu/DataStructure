import java.util.Scanner;
 enum Color{
   WHITE(1),BLACK(2),RED(35),BLUE(4);
   //WHITE,BLACK,RED,BLUE;
   private int value;
   private Color(int c){value=c;}
   public int getValue(){return value;}
  }
  
public class FindSumofContinuousArray {
  static int num;
  static {
    System.out.println("Hello, World! Please enter a num: ");
    //System.exit(0);
    Scanner in=new Scanner(System.in);
    num=in.nextInt();
  }
  
  public static void main(String[] args){
     System.out.println("The num: "+num);
     int[] arr={5,6,0,1,8,8,12,30,10,5};
     System.out.println("Result: "+findContinuousArray(arr));
     System.out.println( Color.RED.getValue());
     System.out.println(Color.values()[1].getValue());
     int[] arr2={13,-3,-25,20,-3,-16,-23,18,20,-7,12,-5,-22,15,-4,7};
     int[] arr3={-3,0,-5,7,-6,0};
     System.out.println("Maximum Subarray 1: "+findMaximumSubarray1(arr2, 0, arr2.length-1));
     System.out.println("Maximum Subarray 2: "+findMaximumSubarray2(arr2));
     System.out.println("Maximum Subarray 3: "+findMaximumSubarray3(arr2));
  }
  
  static boolean findContinuousArray(int[] arr){
      int s=arr[0];
      int j=1;
      int i=0;
        while(true){
           if(j==arr.length) return false;
           if(s<num){s+=arr[j]; j++;}
           if(s==num) return true;
           if(s>num){s-=arr[i]; i++; }            
        }
  }
  //DP O(n)
  static int findMaximumSubarray2(int[] arr){
   int max_so_far = arr[0];
   int curr_max = arr[0];
   for(int i=1; i<arr.length;i++){
      curr_max = (int)Math.max(arr[i], curr_max+arr[i]);
      max_so_far = (int)Math.max(max_so_far, curr_max);
   }
   return max_so_far;
  }
  //DP O(n)
  static int findMaximumSubarray3(int[] arr){
   int max=arr[0];
   int curr=arr[0];
   int j=1, i=0;
   while(true){
      if(j==arr.length) return max;
      curr=(int)Math.max(arr[j],curr+arr[j]);
      max=(int)Math.max(max,curr);
      j++;
   }
  }
     
  // O(n lg n)
  static int findMaxCrossSubarray(int[] arr, int low, int high){
   int mid=(low+high)/2;
   int left_sum=Integer.MIN_VALUE;
   int curr_l_sum=0;
   for(int i=mid; i>=0;i--){
      curr_l_sum+=arr[i];
      if(curr_l_sum>left_sum){
         left_sum=curr_l_sum;
      }
   }
   int right_sum=Integer.MIN_VALUE;
   int curr_r_sum=0;
   for(int i=mid+1; i<=high;i++){
      curr_r_sum+=arr[i];
      if(curr_r_sum>right_sum){
         right_sum=curr_r_sum;
      }
   }
   if(left_sum>0 && right_sum>0)return left_sum+right_sum;
   else return (int)Math.max(left_sum, right_sum); 
  }
  static int findMaximumSubarray1(int[] arr, int low, int high){
   int mid=(low+high)/2, left_sum=0, right_sum=0, cross_sum=0;   
   if(low==high) return arr[low];
   else{
      left_sum=findMaximumSubarray1(arr, low, mid);
      right_sum=findMaximumSubarray1(arr, mid+1, high);
      cross_sum=findMaxCrossSubarray(arr, low, high);
   }
   if(left_sum>right_sum && left_sum>cross_sum) return left_sum;
   else if(right_sum>left_sum && right_sum>cross_sum) return right_sum;
   else return cross_sum;
  }  
}