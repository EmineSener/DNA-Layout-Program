import java.util.Scanner;
import java.io.*;
public class LayoutTest{
    static char   [] ilkSekans;
    static char [][]sekanslar;
    static char [][]complementler;
   static int indel,match,mismatch;
   static int [][] overlap;
   static int  [][] yildizliMatris;
   static int [][] yildizliMatrisBoz;
  
  static int [][] degerler=new int [100][2];
  static int sonsatir_ust=49;
  static int sonsatir_alt=52;
  
    static char [][] layout=new char [480][70];
    static int sonsatir=20;
    static int sonsutun=35;
    static int oncekinesne;
   
	public static void main(String[] args)  throws IOException{
		File file1=new File("overlapMatrisi.txt");
		if(!file1.exists()) {
			file1.createNewFile();
			}
		File file2=new File("layout.txt");
		if(!file2.exists()) {
			file2.createNewFile();
			}
		File file3=new File("sekanslarComplementler.txt");
		if(!file3.exists()) {
			file3.createNewFile();
			}
		PrintWriter output = new  PrintWriter(file1);
		PrintWriter output1 = new  PrintWriter(file2);
		PrintWriter output2= new  PrintWriter(file3);
		Scanner input=new Scanner(System.in);
          SekansUretim sekans=new SekansUretim();
          ilkSekans=SekansUretim.sekans;
          System.out.println(ilkSekans);
          
          Sekanslama sekans1=new Sekanslama(ilkSekans); 
		  
		  sekanslar=Sekanslama.sekanslar;
		   
		  System.out.println("Sekanslar");
		  Sekanslama.printSekanslar();
		  
		  ComplementUretim complement=new ComplementUretim(sekanslar,Sekanslama.sekansSayisi,Sekanslama.sekansUzunlugu);
		  System.out.println("Complementler");
		  ComplementUretim.printComplements();
		  
		  complementler=ComplementUretim.complements;
		  
		    System.out.println("Ýndel puanini bir pozitif sayi olarak giriniz:");
			indel=input.nextInt();
			System.out.println("Match puanini bir pozitif sayi olarak giriniz:");
			match=input.nextInt();
			System.out.println("Mismatch puanini bir pozitif sayi olarak giriniz:");
	        mismatch=input.nextInt();
		  
		  
		  Alignment [] skorlar =new Alignment [Sekanslama.sekansSayisi*Sekanslama.sekansSayisi];
		  int sayac=0;
		 for(int i=0;i<Sekanslama.sekansSayisi;i++) {
			 for(int j=0;j<Sekanslama.sekansSayisi;j++) {
				 if(i<=j)  //matriste i =j leri * a cevirmeyi unutma 
					 skorlar[sayac++]=new Alignment(sekanslar[i],sekanslar[j],indel,mismatch,match);
				 else {
				 	 skorlar [sayac++]=new Alignment(complementler[i],sekanslar[j],indel,mismatch,match);
				 }
			 }
		  
		  		
		  }
		 
		 //maatris 
		 overlap=new int [Sekanslama.sekansSayisi][Sekanslama.sekansSayisi];
		 int a=0;
		 for(int i=0;i<Sekanslama.sekansSayisi;i++) {
			 for(int j=0;j<Sekanslama.sekansSayisi;j++) {
				 overlap[i][j]=skorlar[a++].eniyiskor;
				
			 }
		 }
		 
		 for(int i=0;i<Sekanslama.sekansSayisi;i++) {
			 for(int j=0;j<Sekanslama.sekansSayisi;j++) {
				System.out.print(" "+overlap[i][j]);
			 }
			 System.out.println();
		 }
		 
		 OverlapMatrisi sonMatris=new OverlapMatrisi(Sekanslama.sekansSayisi,overlap);
		 yildizliMatris=new int [Sekanslama.sekansSayisi][Sekanslama.sekansSayisi];
		 
		 
		 
		
		 yildizliMatris=OverlapMatrisi.Matris;
		 yildizliMatrisBoz=yildizliMatris;
		 output.print("     ");
		 for(int i=0;i<Sekanslama.sekansSayisi;i++) {
			 if(i<10) {
			 	output.print(i+"    ");}
			 else 
			    output.print(i+"   ");
			 
		 }
		 output.println();
		 for(int i=0;i<yildizliMatris.length;i++) {
			 if(i<10) {
				 	output.print(i+"    ");}
				 else 
				    output.print(i+"   ");
			 for(int j=0;j<yildizliMatris.length;j++) {
				 if(yildizliMatris[i][j]<0||yildizliMatris[i][j]>=10) {
					 	output.print(yildizliMatris[i][j]+"   ");}
				else 
					    output.print(yildizliMatris[i][j]+"    ");
				
			 }
			 output.println();
		 }
		 output.close();
		 
		 int enbuyuk = 0;
	        int enbuyukj=0;
	        int enbuyuki=0;
	        for (int i = 0; i < yildizliMatrisBoz.length; i++){
	        	for(int j=0;j<yildizliMatrisBoz.length;j++)
	        		if (yildizliMatrisBoz[i][j] > enbuyuk) { 
	        			enbuyuk = yildizliMatrisBoz[i][j];
	        			enbuyukj=j;
	        			enbuyuki=i;
	        			
	        		}
	        }
	     
		degerler[50][0]=enbuyuki;
		degerler[51][0]=enbuyukj;
		yildizliMatrisBoz[enbuyuki][enbuyukj]=-1;
 	if(enbuyuki<enbuyukj) {
 		
 		degerler[50][1]=1;
 		degerler[51][1]=1;
 		
 	}
 	else {
 		degerler[50][1]=-1;//complement
 	    degerler[51][1]=1;
 	}
	
	
	/*else {
	break;}*/
	
		  ustGenislet(degerler[50][0]);	
		  sonsatir_ust++;//bu main bloguna en son hangi satira yazim yatigimiz bildirecek
		 
			altGenislet(degerler[51][0]);
			sonsatir_alt--;
			
			for(int i=0;i<degerler.length;i++) {
				for(int j=0;j<2;j++) {
					System.out.print(degerler[i][j]);
				}
				System.out.println();
			 	
		       }
			
			
		
				if(degerler[sonsatir_ust][1]==-1)//complement yaz 
				{
					for(int i=0;i<Sekanslama.sekansUzunlugu;i++) {
						layout[sonsatir][sonsutun+i]=complementler[degerler[sonsatir_ust][0]][i];
					}
					sonsatir++;
				}
				else {
					for(int i=0;i<Sekanslama.sekansUzunlugu;i++) {
						layout[sonsatir][sonsutun+i]=sekanslar[degerler[sonsatir_ust][0]][i];
					}
					sonsatir++;
				}
				
				
		for(int m=sonsatir_ust+1;m<sonsatir_alt+1;m++) {
			int buyuknesne=0;
			int kucuknesne=0;
			oncekinesne=degerler[m-1][0];
			
			if(oncekinesne>degerler[m][0]) {
				buyuknesne=oncekinesne;
				kucuknesne=degerler[m][0];
			}
			else {
				buyuknesne=degerler[m][0];
				kucuknesne=oncekinesne;
			}
			if(degerler[m][1]==-1) {//complement ise buyuk nesne once gelecek
				if(oncekinesne>degerler[m][0])
						sonsutun=sonsutun-skorlar[buyuknesne*Sekanslama.sekansSayisi+kucuknesne].fark;
				else
					sonsutun=sonsutun+skorlar[buyuknesne*Sekanslama.sekansSayisi+kucuknesne].fark;
		    	for(int h=0;h<Sekanslama.sekansUzunlugu;h++) {
					layout[sonsatir][sonsutun+h]=complementler[degerler[m][0]][h];
						}
					sonsatir++;
				skorlar[buyuknesne*Sekanslama.sekansSayisi+kucuknesne].print();
				
			}
			else {//sekanslardan yaz
				if(oncekinesne>degerler[m][0])
					sonsutun=sonsutun-skorlar[kucuknesne*Sekanslama.sekansSayisi+buyuknesne].fark;
				else
					sonsutun=sonsutun+skorlar[kucuknesne*Sekanslama.sekansSayisi+buyuknesne].fark;
				
				for(int h=0;h<Sekanslama.sekansUzunlugu;h++) {
					layout[sonsatir][sonsutun+h]=sekanslar[degerler[m][0]][h];
				}
				skorlar[kucuknesne*Sekanslama.sekansSayisi+buyuknesne].print();
				sonsatir++;
			}
			
			
				
			}
		
			
		 for(int i=0;i<layout.length;i++) {
			 for(int j=0;j<70;j++) {
				 output1.print(layout[i][j]);
			 }
			 output1.println();
		 }
		 
		 output1.close();
		 output2.println("Sekanslar");
		 for(int i=0;i<sekanslar.length;i++) {
			 for(int j=0;j<Sekanslama.sekansUzunlugu;j++) {
				 output2.print(sekanslar[i][j]);
			 }
			 output2.println();
		 }
		 output2.println("complementler");
		 for(int i=0;i<sekanslar.length;i++) {
			 for(int j=0;j<Sekanslama.sekansUzunlugu;j++) {
				 output2.print(complementler[i][j]);
			 }
			 output2.println();
		 }
		 output2.close();


		}

static int k=0;
static	void ustGenislet(int n) {
	while(true) {
	int enbuyuk=0;
	int enbuyuki=0;	
	int enbuyukj=0;
	int enbuyuk1=0;
	int enbuyuk2=0;
	
		for(int j=0;j<yildizliMatrisBoz.length;j++) {
			 if( yildizliMatrisBoz[n][j]>enbuyuk) {  //satir sabit suutn ara 
				 enbuyuk=yildizliMatrisBoz[n][j];
				 enbuyukj=j;
			 }
		}
		
		enbuyuk1=enbuyuk;
		enbuyuk=0;
		for(int i=0;i<yildizliMatrisBoz.length;i++) {
			 if( yildizliMatrisBoz[i][n]>enbuyuk) {  //sutun sabit satir ara 
				 enbuyuk=yildizliMatrisBoz[i][n];
				 
				 enbuyuki=i;
			 }
		}
		enbuyuk2=enbuyuk;
		
		if(enbuyuk2>0||enbuyuk1>0) {
			if(enbuyuk1>enbuyuk2) {//burda enbuyuk degerin satir aramasi sonucu mu sutun aramasi sonucu oldugu kontrol edilir .enbuyuksutun aramasiile
				                       //sutun aramasinda complement dusunmeye gerek yok.
				//enbuyukj kazandi onu yazdir
				degerler[sonsatir_ust][0]=enbuyukj;
				yildizliMatrisBoz[n][enbuyukj]=-1;
				n=enbuyuki;
				degerler[sonsatir_ust--][1]=1;//sutundan elde edilem sekanslarda complementlik durumu yok 
				
			}
			else {
				//enbuyuk2 kazandi 
				//i sekansi elde ettim ve complementmi kontrol etmek zorundayim.
				degerler[sonsatir_ust][0]=enbuyuki;
				yildizliMatrisBoz[enbuyuki][n]=-1;
				n=enbuyukj;
				if(enbuyuki<n){//complement degildir
					degerler[sonsatir_ust--][1]=1;
				}	
				else {
					degerler[sonsatir_ust--][1]=-1;
				}
			}
		}
		else {
		break;
		}
	}
}
static	void altGenislet(int n) {
	while(true) {
	int enbuyuk=0;
	int enbuyuki=0;	
	int enbuyukj=0;
	int enbuyuk1=0;
	int enbuyuk2=0;
	
		for(int j=0;j<yildizliMatrisBoz.length;j++) {
			 if( yildizliMatrisBoz[n][j]>enbuyuk) {  //satir sabit suutn ara 
				 enbuyuk=yildizliMatrisBoz[n][j];
				 enbuyukj=j;
			 }
		}
		
		enbuyuk1=enbuyuk;
		enbuyuk=0;
		for(int i=0;i<yildizliMatrisBoz.length;i++) {
			 if( yildizliMatrisBoz[i][n]>enbuyuk) {  //sutun sabit satir ara 
				 enbuyuk=yildizliMatrisBoz[i][n];
				 
				 enbuyuki=i;
			 }
		}
		enbuyuk2=enbuyuk;
		
		if(enbuyuk2>0||enbuyuk1>0) {
			if(enbuyuk1>enbuyuk2) {//burda enbuyuk degerin satir aramasi sonucu mu sutun aramasi sonucu oldugu kontrol edilir .enbuyuksutun aramasiile
				                       //sutun aramasinda complement dusunmeye gerek yok.
				//enbuyukj kazandi onu yazdir
				degerler[sonsatir_alt][0]=enbuyukj;
				yildizliMatrisBoz[n][enbuyukj]=-1;
				n=enbuyuki;
				degerler[sonsatir_alt++][1]=1;//sutundan elde edilem sekanslarda complementlik durumu yok 
				
			}
			else {
				//enbuyuk2 kazandi 
				//i sekansi elde ettim ve complementmi kontrol etmek zorundayim.
				degerler[sonsatir_alt][0]=enbuyuki;
				yildizliMatrisBoz[enbuyuki][n]=-1;
				n=enbuyukj;
				if(enbuyuki<n){//complement degildir
					degerler[sonsatir_alt++][1]=1;
				}	
				else {
					degerler[sonsatir_alt++][1]=-1;
				}
			}
		}
		else {
		break;
		}
	}
}
}
	






