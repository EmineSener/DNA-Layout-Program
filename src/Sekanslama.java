import java.util.Scanner;
import java.util.Random;
public class Sekanslama {
          static char [][] sekanslar ;
          char [] ilkSekans;
          static int sekansSayisi;
          static int sekansUzunlugu;
          int ilkPoz;
          Scanner input=new Scanner(System.in);
          
         
          Sekanslama(char [] a)
          {   ilkSekans=new char [ a.length];
        	  ilkSekans=a;
        	  System.out.println("Kaç adet sekans istiyorsunuz?");
        	  sekansSayisi=input.nextInt();
        	  System.out.println("Sekans uzunluğunu giriniz");
        	  sekansUzunlugu=input.nextInt();
        	  sekanslar=new char [sekansSayisi][];
        	  sekansla(sekansUzunlugu,sekansSayisi);
        	  }
          void sekansla (int sekansUzulugu,int sekansSayisi){
        	  
              for(int i=0;i<sekansSayisi;i++) {
            	  sekanslar[i]=new char [sekansUzunlugu];
            	  for(int j=0;j<sekansUzunlugu;j++) {
            		  if(j==0) {
                          ilkPoz=new Random().nextInt(ilkSekans.length-sekansUzunlugu+1);
                          sekanslar[i][j]=ilkSekans[ilkPoz++];
            		  }
            		  else
            			  sekanslar [i][j]=ilkSekans[ilkPoz++];
            	  }
            			  
            	  }
              }
        static  void printSekanslar() {
        	  for(int i=0;i<sekanslar.length;i++) 
        		  System.out.println(sekanslar[i]);
          }
        
          }


