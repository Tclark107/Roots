class FindRoot1{

   public static void main(String[] args) {
      double a = 0.0, b = 10.0, eps = 0.00001;
      double root = 0.0 , residual;
      while ( b - a > eps ) {
         root = (a + b) / 2.0;
         System.out.println("root: " + root);
         residual = f(root);
         System.out.println("square minus 2: " + residual);
         if (residual > 0)
            b = root; // replace right endpoint
         else
            a = root; // replace left endpoint
      }
      System.out.println("root = " + root);
   }
   
   static double f(double x){ 
        return (x * x - 2.0); 
   }
   
}