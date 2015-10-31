package imposto;

import java.util.ArrayList;
import conceito_fiscal.IV;

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
		
	// Seu principal método, que calcula a Taxa de Imposto
	//   sobre uma dada lista de IVs.
	public int tax(ArrayList<IV> IVs_) {
		int totalTax = 0;
		for (IV iv : IVs_)
		{
			// CalculaImposto deve depender da categoria do IV
			//   usando a correta implementação da interface Imposto
			totalTax += tribute_.calculaImposto(iv);
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
