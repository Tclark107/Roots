class FindRoot3{

   public static void main(String[] args){

      int r = 4, s = 7;

      double a=1.0, b=s, tolerance=0.00000000001;
      double mid=a, width;

      width = b-a;
      while ( width>tolerance ){
         mid = (a+b)/2;
         if( f(a, r, s)*f(mid, r, s)<=0 ){  // if root is in [a, mid]
            b = mid;                        //    move b left
         } else {                           // else root is in (mid, b] 
            a = mid;                        //    move a right
         }
         width = b-a;
      }
      System.out.println("The "+r+"-th root of "+s+
                         " as computed by bisection:  "+mid );
      System.out.println("The "+r+"-th root of "+s+
                         " as computed by Math.pow(): "+Math.pow(s,1.0/r) );
   }
   
   // f()
   // computes x^n - k
   static double f(double x, int n, int k){
      double p = 1;
      for(int i=0; i<n; i++) p *= x; 
      return p - k; 
   }
   
}