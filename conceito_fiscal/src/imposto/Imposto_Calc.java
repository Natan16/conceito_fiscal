package imposto;

import java.util.ArrayList;

import banco_dados.BDPS_Facade;
import conceito_fiscal.IV;
import conceito_fiscal.PS_Concrete;

public class Imposto_Calc 
{
	// Classes usadas por Imposto_Calc
	private Imposto_Info info_;
	private Imposto tribute_;
	
	// Padrão Singleton:
	private static Imposto_Calc INSTANCE;
	public static Imposto_Calc getInstance() {
		if (Imposto_Calc.INSTANCE == null)
			Imposto_Calc.INSTANCE = new Imposto_Calc();
		return Imposto_Calc.INSTANCE;
	}
	
	public Imposto_Calc(){
		info_ = new Imposto_Info();
	}
		
	// Seu principal método, que calcula a Taxa de Imposto
	//   sobre uma dada lista de IVs.
	public int tax(ArrayList<IV> IVs_) {
		Imposto_Strategy strategy = null;
		//calcula o valor total dos IVs e a partir daí determina o estado
		strategy = Imposto_Strategy.getStrategy(IVs_);
		int totalTax = (int) strategy.calc(IVs_);
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
