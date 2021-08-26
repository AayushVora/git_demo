
import java.text.DecimalFormat;
import java.util.*;

public class ks {
	
	public static int gcd(int c, int m) {
		while (c!=0 && m!=0){
            if(c>m){
                c=c-m;
            }
            else {
                m=m-c;
            }
        }

        return Math.max(c, m);

	}
	
	public static double rand(double[] x, int a, int c, int m) {
		double sum = 0;
		System.out.println("Random Numbers Generated for Variation 1: ");
		System.out.println("Dexter is Here");
		for(int i = 1; i < m; i++){
            x[i] = (((x[i - 1] * a) + c) % m);
		}
		for(int j = 0; j < m; j++){
            x[j]=x[j]/m;
            System.out.print(x[j]+"  ");
            if((j%10==0 && j>5) || (j%(m/5)==0)) {System.out.print("\n");}
        }
		for(int k = 1; k< m; k++) {
			sum = sum + Math.abs(x[k]-x[k-1]);
		}
		sum = sum/m;
		DecimalFormat df = new DecimalFormat("#.####");
		String formatted = df.format(sum);
		System.out.println("\nPeriod for Variation 1: " + m);
		System.out.println("Density for Variation 1: " + formatted);
		Arrays.sort(x);
		
		double[] listArr = new double[m];
		for(int l=1; l<m;l++) {
			listArr[l] = (double) l/m;
		}
		double dplus = dplus(listArr, x);
		double dminus = dminus(listArr, x);
		System.out.println("D+: "+dplus+"\nD-: "+dminus);
		if(dplus>dminus) {
			return dplus;
		}
		else {
			return dminus;
		}
		
	}
	
	public static double dplus(double[] list, double[] x) {
		double[] dplus = new double[x.length];
		for(int a = 0; a<x.length;a++) {
			dplus[a] = list[a] - x[a];
		}
		double max = largest(dplus);
		return max;
	}
	
	
	public static double dminus(double[] list, double[] x) {
		double[] dminus = new double[x.length];
		dminus[0] = x[0];
		for(int a = 1; a<x.length;a++) {
			dminus[a] = x[a]-list[a-1];
		}
		double max = largest(dminus);
		return max;
	}
	
	
	public static double largest(double[] arr) 
    { 
        int i; 
        double max = arr[0];   
        for (i = 1; i < arr.length; i++) 
            if (arr[i] > max) 
                max = arr[i]; 
       
        return max; 
    } 
	
	
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		System.out.println("***Random Number Generator***");
				int p = 1;
		while(true) {
		System.out.print("Enter Power of 2 for 'm' Value (input 0 to end program): ");
		p = sc.nextInt();
		if(p==0) {
			break;
		}
		int m = (int)Math.pow(2, p);
		double[] x = new double[m];
		System.out.print("Enter Seed Value: ");
		x[0] = sc.nextInt();
		System.out.print("Enter Additive Value 'c': ");
		int c = sc.nextInt();
		if(gcd(c,m)!=1) {
			while(gcd(c,m)!=1) {
				System.out.print("'c' Value must be Relatively Prime with 'm' for Maximum Period. Enter new value: ");
				c=sc.nextInt();
			}
		}
		System.out.print("Enter 'a': ");
		int a = sc.nextInt();
		double maximum = rand(x, a, c, m);
		System.out.println("Maximum of D+ and D- is: " + maximum);
		double dalpha = 1.36/Math.sqrt(m);
		if(dalpha>maximum) {
			System.out.println("H0 is Accepted!");
		}
		else {
			System.out.println("H0 is Rejected!");
		}
		}
		sc.close();
	}
}
