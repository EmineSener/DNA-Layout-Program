
public class ComplementUretim {
      static char [][] sekanslar;       
	 static char [][] complements ;
	 char karsilik;
	ComplementUretim(char [][]a,int b,int c){
		complements=new char[b][];
		sekanslar=a;
		complement(b,c);
	}
	void complement(int b,int c) {
		for(int i=0;i<b;i++) {
			 complements[i]=new char [c];
			 for(int j=0;j<c;j++) 
				 complements[i][c-1-j]=karsilik(sekanslar[i][j]);
		}
	}
	char karsilik(char d) {
		switch(d) {
		case 'A':
			return 'T';
		case 'C':
			 return 'G';
		case 'G':
			return 'C';
		default:
			return 'A';
			
		}
	}
	static  void printComplements() {
  	  for(int i=0;i<complements.length;i++) 
  		  System.out.println(complements[i]);
    }
}

