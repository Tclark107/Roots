class FindRoot2{

   public static void main(String[] args){

      double a=0.0, b=10.0, tolerance=0.00001;
      double mid=a, width;

      width = b-a;
      while ( width>tolerance ){
         mid = (a+b)/2;
         if( f(a)*f(mid)<=0 ){  // if root is in [a, mid]
            b = mid;            //    move b left
         } else {               // else root is in (mid, b] 
            a = mid;            //    move a right
         }
         width = b-a;
      }
      System.out.println("root = " + mid);
   }
   
   static double f(double x){ 
      return (x * x - 2.0); 
   }
   
}