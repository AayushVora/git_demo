
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class Run {
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
	
	public static void rand(double[] x, int a, int c, int m) {
		double sum = 0;
		System.out.println("Random Numbers Generated: ");
		for(int i = 1; i < m; i++){
            x[i] = (((x[i - 1] * a) + c) % m);
		}
		for(int j = 0; j < m; j++){
            x[j]=x[j]/m;
            System.out.print(x[j]+"  ");
            if((j%10==0 && j>5)) {System.out.print("\n");}
        }
		for(int k = 1; k< m; k++) {
			sum = sum + Math.abs(x[k]-x[k-1]);
		}
		sum = sum/m;
		DecimalFormat df = new DecimalFormat("#.####");
		String formatted = df.format(sum);
		System.out.println("\nPeriod: " + m);
		System.out.println("Density: " + formatted);
		
		ArrayList<Character> run = new ArrayList<Character>();
		for(int p=0; p<m-1; p++) {
			if(x[p]>x[p+1]) {
				run.add('-');
			}
			else {
				run.add('+');
			}
		}
		char first = run.get(0);
		int count = 0;
		for(int q=0; q<run.size();q++) {
			if(run.get(q)==first) {
				continue;
			}
			else {
				count++;
				first = run.get(q);
			}
		}
		System.out.println("Number of Runs: " + count);
		int size = run.size();
		double mean = (2*size-1)/3;
		double temp = 16*size-29;
		double stdDev = Math.sqrt(temp);
		double z0 = (count - mean)/stdDev;
		System.out.println("Mean Value: " + mean + "\nStandard Deviation: " + stdDev + "\nz0: " + z0);
		if(z0>=(-1.96) && z0<=1.96) {
			System.out.println("H0 is Accepted");
		}else {
			System.out.println("H0 is Rejected");
		}
	}
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		System.out.println("***RUNS TEST***");
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
		rand(x, a, c, m);
		System.out.println("***************************");
		}
		sc.close();
	}
}
