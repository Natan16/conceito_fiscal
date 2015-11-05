package imposto;

import java.util.ArrayList;
import java.util.List;

import banco_dados.BDPS_Facade;
import conceito_fiscal.IV;
import conceito_fiscal.NF_Final;
import conceito_fiscal.PS_Concrete;

public abstract class Imposto_Strategy {
	public static Imposto_Strategy getStrategy(ArrayList<IV> IVs_){
		int totalBruto = 0;
		float totalAnterior = 0.0f;
		for (IV iv : IVs_)
			totalBruto += iv.getPrice_()*iv.getQuant_();
		//aqui a depedência com as notas fiscais anteriores fica evidente
		List<Float> anteriores = Imposto_Info.getNf_anteriores();
		for(Float fatura:anteriores){
			totalAnterior += fatura;
		}
		
		
			
		if ( totalBruto > 1000 || totalAnterior > 20000)
			  return new Imposto_Strategy_high();
		return new Imposto_Strategy_low();
	}
	public float calc(ArrayList<IV> IVs_){
		Imposto tribute_;
		float totalTax = 0;
		totalTax += Imposto_Info.taxa;
		for (IV item : IVs_)
		{
			PS_Concrete ps = (PS_Concrete) item.getPS_();
			String cat = BDPS_Facade.getTributeCat(ps); 
			tribute_ = Imposto_Factory.getImposto(cat);
			// CalculaImposto deve depender da categoria do IV
			//   usando a correta implementação da interface Imposto
			totalTax += hook_Calc(tribute_, item);  
		}
		return totalTax;
	}
	
	public float hook_Calc(Imposto tribute,IV item){
		return tribute.calculaImposto(item);
	}
	
}
