package imposto;

import java.util.ArrayList;
import java.util.List;

import conceito_fiscal.NF_Final;

public class Imposto_Info 
{
	// Essa classe deve ser carregada de informações complexas
	//  sobre o cálculo de impostos. Pode inclusive ter
	//  um histórico de NFs para ponderar sua cobrança.
	private static List<NF_Final> nf_anteriores = new ArrayList<NF_Final>();
	public static void addNF(NF_Final nf_final){
		nf_anteriores.add(nf_final);
	}
	
	public static List<NF_Final> getNf_anteriores(){
		return nf_anteriores;	
	}
	
	public static float getTotalAnterior(){
		float total_anterior = 0;
		for (NF_Final nf : nf_anteriores){
			total_anterior += nf.calculaImposto();
		}
		
		return total_anterior;
	}
	
	
	static final int taxa = 10;
}
