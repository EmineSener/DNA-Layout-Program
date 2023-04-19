import java.util.Scanner;
public class Alignment {
	char [] sekans1;//timelenan sekans 
	char [] sekans2;//timelanan 2.sekans 
	char [] A;   //main  gonderecek aligment matriisnde sutuna yerlestirilen matris
	char []B;  //satiri yerlestirilen matris
	 int [][] alignment;
	static int indel,match,mismatch;//static cunku her yeni nesnede tekrar sormamali
	 int eniyiskor;    
	 int [] enbuyukpoz=new int [2];
	int [][] best=new int [100][2];
	int overlapbaz=0;
	boolean tip;
	int fark;//fark degiskeni sayesinde en yuksek pozisyonun farkýni alinca eslestirme sirasinda eslesilen sekansýn ilk pozisyonunda fark kadar ileri veya geri 
	//gidis oluyor.ileri veya geri gitmeyi ise farkin - veya + cikmasi belirler..
	char[][] matris=new char [2][140];
	int filecursor;//matrisim hangi sutunda basliyor tutar.
	
	Alignment(char [] A,char [] B,int indel,int mismatch,int match){
		this.indel=indel;
		this.mismatch=mismatch;      //kullanicidan aldigim degerleri atadim 
		this.match=match;
		this.A=A;   //kullanicinin gonderdigi sekanslar atadim.
		this.B=B;
		sekans1=new char [A.length+1];  //sekans1 A sekansinin time li hali 
		sekans2=new char [B.length+1];  //b sekansinin time li hali 
		timeEkle(A,sekans1);  //A sekansina time ekleyip sekans1 e atatyacak
		timeEkle(B,sekans2);
		alignment=new int [A.length+1][B.length+1];    //alignment isleminin yapildigi matriism 
		hizala(sekans1,sekans2);       //time li sekanslari hizalamaya basla 
		eniyiskor=enSkor(alignment);
		
		System.out.println(enbuyukpoz[0]+","+enbuyukpoz[1]);
		fark=enbuyukpoz[0]-enbuyukpoz[1];
		
		print();
		
	}
	

	void timeEkle(char [] eski,char [] yeni) {//dogru calisiyor kontrol edildi.
		for(int i=1;i<yeni.length;i++) {
			yeni[i]=eski[i-1];
		}
		yeni[0]='-';}
	
	void hizala(char [] sekans1,char [] sekans2) {     //2 sekans alir 
		for(int i=0;i<sekans1.length;i++) {//zaten 2'side ayni uzunlukta karesel matris olusacak
			for(int j=0;j<sekans2.length;j++) {
				if(i==0||j==0) {//ilk satira ve sutuna full 0
					alignment[i][j]=0;
				}
				else {      //ilk satir veya sutun degilse i j degerlerini puanlaya gonder 
					alignment[i][j]=puanla(i,j,sekans1,sekans2);//3 case den hangisi en buyukse bu doner 
				}
			}
		}
	}
	
	int puanla(int i,int j,char []a,char []b) {
		int[] araskorlar=new int[3];     //3 case'imden hangisi en buyuk bulmak icin skorlari bunda tutuyorum 
   	 	araskorlar[0]=ustGel(i,j);//indel atma durumlarinda sekansa bakilamdigindan sekans bilgilerini gondermiyorum
   	 	araskorlar[1]=yanGel(i,j);
   	 	araskorlar[2]=diagonelGel(i,j,a,b);//diagonel gelme duurmlarinda match mistmatch 2 duurm .Sekansa bakilacak.sekans bilgilerini de gonder 
   	 		return maxBul_dizi(araskorlar);	 //genel bir metod verilen dizideki max sayiyi bulur
    }

	 int ustGel(int i,int j) {
    	 return alignment[i-1][j]-indel;
     }
     int yanGel(int i,int j) {
    	 return alignment[i][j-1]-indel;
     }
     int diagonelGel(int i,int j,char []a,char []b) {
    	if(a[i]==b[j])
    		return alignment[i-1][j-1]+match;
    	else 
    		return alignment[i-1][j-1]-mismatch;
     }
     
    public static int maxBul_dizi(int [] sayilar) { //cok genel metod.Dogru calisiyor denedim
    	 
    	 int enbuyuk = sayilar[0];
         
         for (int i = 0; i < sayilar.length; i++){
             if (sayilar[i] > enbuyuk) 
                 enbuyuk = sayilar[i];
         }
         return enbuyuk;
         
     }
    
    
    int enSkor(int [][] alignment) {
    	int enbuyuk=0;
    	enbuyukpoz[0]=-1;
    	enbuyukpoz[1]=-1;
    	for(int i=0;i<alignment.length;i++) {
    		if(alignment[i][alignment.length-1]>enbuyuk) {//son sutuna bakar .
    			enbuyuk=alignment[i][alignment.length-1];
    			enbuyukpoz[0]=i;
    			enbuyukpoz[1]=alignment.length-1;
    		}
    	}
    	for(int j=0;j<alignment.length;j++) {
    		if(alignment[alignment.length-1][j]>enbuyuk) {
    			enbuyuk=alignment[alignment.length-1][j];
    			enbuyukpoz[1]=j;
    			enbuyukpoz[0]=alignment.length-1;
    		}
    	}
    	return enbuyuk;
    }
    
    int matris_tip() {
    	if(best[0][0]>best[0][1]) {
    		tip=true;
    		return best[0][0]-best[0][1];
    	}
    	else {
    		tip=false;
    		return best[0][0]-best[0][1];
    	}
    }
    void print() {
    	 { //!!!!!!!!!!!!unutma 
	    	int sonsutun=70;
	    	for(int i=0;i<A.length;i++) {
	    	matris[0][sonsutun+i]=A[i];
	    			}
	    	 sonsutun=sonsutun+fark;
	    	 for(int i=0;i<B.length;i++) { 
	    	 matris[1][sonsutun+i]=B[i];
	    	 }
	    	
	    	if(sonsutun>=70) {
	    		filecursor=70;
	    	}
	    	else 
	    		filecursor=sonsutun;
	    	 for(int i=0;i<2;i++) {
	    		 for(int j=filecursor;j<140;j++) {
	    			 System.out.print(matris[i][j]);
	    		 }
	    		 System.out.println("");
	    	 }
    
    
    
    
}
    }
}