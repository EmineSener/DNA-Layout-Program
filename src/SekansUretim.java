import java.util.Scanner;
import java.util.Random;
public class SekansUretim{
	 static char [] sekans;
	int cevap;
	int uzunluk;
	Scanner input=new Scanner(System.in);
	SekansUretim(){
		System.out.println("Sekansý kendiniz girmek istiyorsanýz 1;bilgisayarýn rastgele olrak olusturmasini istiyorsaniz 2 giriniz");
		cevap=input.nextInt();
		if(cevap==1) 
			 sekans=kullaniciSekansi();
		else {
			System.out.println("Kaç uzunluklu sekans istiyorsunuz");
			uzunluk=input.nextInt();
			sekans=bilgisayarSekansi(uzunluk);
			
		}
	}
	     char [] kullaniciSekansi() {
	    	 System.out.println("Sekansýnýzý giriniz: ");
	    	 String x=input.next();
	    	 return x.toCharArray();
	     }
	     
	     char [] bilgisayarSekansi(int uzunluk){
          char [] chars= new char[uzunluk];
    		 
    		 for (int i = 0; i < chars.length; i++) {
    			 switch (new Random().nextInt(4)) {
    			 case 0:
    				 chars[i]='A';
    			     break;
    			 case 1:
    				 chars[i]='C';
    				 break;
    			 case 2:
    				 chars[i]='G';
    				 break;
    			default :
                       chars[i]='T';
    			 }
    		 }
                return chars;

	     }
	    
}
