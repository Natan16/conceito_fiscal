package imposto;

import java.util.ArrayList;
import java.util.List;

import conceito_fiscal.NF_Final;

public class Imposto_Info 
{
	
	// Essa classe deve ser carregada de informa��es complexas
	//  sobre o c�lculo de impostos. Pode inclusive ter
	//  um hist�rico de NFs para ponderar sua cobran�a.
	private static List<Float> nf_anteriores = new ArrayList<Float>();
	
	public static void addNF(int  totalTribute_){
		nf_anteriores.add((float) totalTribute_);
	}
	
	public static  List<Float> getNf_anteriores(){
		return nf_anteriores;	
	}
			
	static final int taxa = 10;
}
