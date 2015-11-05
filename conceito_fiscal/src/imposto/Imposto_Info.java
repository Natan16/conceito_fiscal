package imposto;

import java.util.ArrayList;
import java.util.List;

import conceito_fiscal.NF_Final;

public class Imposto_Info 
{
	
	// Essa classe deve ser carregada de informações complexas
	//  sobre o cálculo de impostos. Pode inclusive ter
	//  um histórico de NFs para ponderar sua cobrança.
	private static List<Float> nf_anteriores = new ArrayList<Float>();
	
	public static void addNF(int  totalTribute_){
		nf_anteriores.add((float) totalTribute_);
	}
	
	public static  List<Float> getNf_anteriores(){
		return nf_anteriores;	
	}
			
	static final int taxa = 10;
}
