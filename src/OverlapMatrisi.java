import java.util.Scanner;
import java.lang.Integer;
public class OverlapMatrisi {
	static int [][]geciciMatris;
     static int [][]Matris;
     static char  [][] yildizliMatris;
     int t_degeri;
     int uzunluk;
     Scanner input=new Scanner (System.in);
     OverlapMatrisi(int n,int [][] sonMatris){
    	 Matris=new int [n][n];
    	 yildizliMatris=new char [n][n];
    	 uzunluk=n;
    	 geciciMatris=new int [n][n];
    	 this.geciciMatris=sonMatris;
    	 System.out.println("Matrisiniz icin kabul edilebilecek en kucuk degeri giriniz");
    	 t_degeri=input.nextInt();
    	 esikDegeri(t_degeri);
    	 yildizla();
    	 
     }
     
     void esikDegeri(int t_degeri) {
    	 for(int i=0;i<uzunluk;i++) {
    		 for(int j=0;j<uzunluk;j++) {
    			  if(i==j)
    				 Matris[i][j]=-1;
    		    else if(geciciMatris[i][j]>=t_degeri) {
    				 Matris[i][j]=geciciMatris[i][j];
    			 }
    			 
    			 else {
    				 Matris[i][j]=0; 
    			 }
    		 }
    	 }
     }
     
     void yildizla() {
    	 for(int i=0;i<uzunluk ;i++) {
    		 for(int j=0;j<uzunluk;j++) {
    			 if(i==j)
    				 System.out.print(" *");
    			 else 
    				 System.out.print(" " +Matris[i][j]); 
    		 }
    		 System.out.println();
    	 }
     }
     
     
     
    
	
 }
