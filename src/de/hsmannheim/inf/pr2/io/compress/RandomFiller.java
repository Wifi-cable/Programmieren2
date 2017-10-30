package de.hsmannheim.inf.pr2.io.compress;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
/* diese klasse ist dafür da einen haufen zufälliger zahlen in ein array zu schreiben. 
 * im constructor den namen angeben der angeleigt werden soll */


public class RandomFiller extends OutputStream {
	String name;
	public RandomFiller(String name) {
		this.name=name;
		// TODO Auto-generated constructor stub
	}
	
	/*@ pram
	 * die menge an zahlen die in den file geschrieben werden sollen.  
	 * vorsicht, der file wird jedesmal wieder mit neuen random zahlen gefüllt*/
	 protected File writeRandomFile( int amount)throws IOException{
	 File random = new File(name) ;
	 String absolut= random.getAbsolutePath();
	// System.out.println("der file ist bei "+absolut);
	 byte []writeout=new byte[amount];
	boolean hasFile = random.createNewFile();
//if(hasFile){
	
	OutputStream oput= new FileOutputStream(absolut, false);
	 for (int i=0;i<amount;i++){
		 int tmp= (int)(Math.random()*10)-2;
		 writeout[i]=(byte)tmp;
	 }
	
	 oput.write(writeout);
	 oput.flush();
	 oput.close();
	//}
	
	 return random;
}
	@Override
	public void write(int b) throws IOException {
	// methode der oberklasse.

	}

}
