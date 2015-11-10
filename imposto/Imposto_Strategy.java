package imposto;

import java.util.ArrayList;

import banco_dados.BDPS_Facade;
import conceito_fiscal.IV;

public abstract class Imposto_Strategy 
{
	// Seleciona uma determinada estratégia e realiza o cálculo de imposto
	public static int tax(ArrayList<IV> IVs_){
		int totalBruto_ = getTotalBruto(IVs_);
		int totalAnterior_ = Imposto_Info.getFaturasAnteriores();
		Imposto_Strategy strategy = Imposto_Strategy.getStrategy(totalBruto_, totalAnterior_);
		int mediaAnterior_ = Imposto_Info.getMediaAnterior();
		return strategy.calcTribute(IVs_, mediaAnterior_);
	}
	// Adota uma determinada estratégia conforme o TotalBruto/TotalAnterior
	public static Imposto_Strategy getStrategy(int totalBruto, int totalAnterior) {
		if (totalBruto > 1000 || totalAnterior > 20000)
			return new Imposto_Strategy_high();
		return new Imposto_Strategy_low();
	}

	// Calcula total bruto da NF sem impostos.
	public static int getTotalBruto(ArrayList<IV> IVs_){
		int totalBruto = 0;
		for (IV iv : IVs_)
			totalBruto += iv.getPrice_() * iv.getQuant_();
		return totalBruto;
	}
	
	// Calcula o total considerando impostos e totalAnterior
	public int calcTribute(ArrayList<IV> IVs_, int mediaAnterior) {
		Imposto tribute;
		float totalTax = Imposto_Info.getTax();
		// Taxa 1% da fatura média das NFs anteriores.
		totalTax += mediaAnterior/100;
		for (IV item : IVs_) {
			String cat = BDPS_Facade.getTributeCat(item.getPS_());
			tribute = Imposto_Factory.getImposto(cat);
			// CalculaImposto deve depender da categoria do IV
			// usando a correta implementação da interface Imposto
			totalTax += hook_Calc(tribute, item);
		}
		return (int)totalTax;
	}

	public float hook_Calc(Imposto tribute, IV item) {
		return tribute.calculaImposto(item);
	}
}
