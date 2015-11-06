package imposto;

import java.util.ArrayList;

import banco_dados.BDPS_Facade;
import conceito_fiscal.IV;
import conceito_fiscal.PS_Abstract;
import conceito_fiscal.PS_Concrete;

public class Imposto_Calc 
{
	// Classes usadas por Imposto_Calc
	private Imposto_Info info_;
	private Imposto tribute_;
	
	// Padr�o Singleton:
	private static Imposto_Calc INSTANCE;
	public static Imposto_Calc getInstance() {
		if (Imposto_Calc.INSTANCE == null)
			Imposto_Calc.INSTANCE = new Imposto_Calc();
		return Imposto_Calc.INSTANCE;
	}
	
	public Imposto_Calc(){
		info_ = new Imposto_Info();
	}
		
	// Seu principal m�todo, que calcula a Taxa de Imposto
	//   sobre uma dada lista de IVs.
	public int tax(ArrayList<IV> IVs_) {
		int totalTax = 0;
		for (IV item : IVs_)
		{
			PS_Abstract ps =  item.getPS_();
			String cat = BDPS_Facade.getTributeCat(ps); 
			tribute_ = Imposto_Factory.getImposto(cat);
			// CalculaImposto deve depender da categoria do IV
			//   usando a correta implementa��o da interface Imposto
			totalTax += tribute_.calculaImposto(item);
		}
		totalTax += info_.taxa;
		return totalTax;
	}
	
	
	// Getters e Setters
	public Imposto_Info getInfo_() {
		return info_;
	}

	public void setInfo_(Imposto_Info info_) {
		this.info_ = info_;
	}

	public Imposto getCalc() {
		return tribute_;
	}

	public void setCalc(Imposto calc) {
		this.tribute_ = calc;
	}
	
}
