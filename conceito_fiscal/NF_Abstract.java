package conceito_fiscal;

import java.util.ArrayList;

public abstract class NF_Abstract 
{
	// Parâmetros básicos da NF
	protected ArrayList<IV> IVs_;
	protected int TotalTribute_;
	
	// Métodos da NF
	public abstract int calculaImposto();
	public abstract boolean isFinal();
	public abstract String getStatus();
	
	public int getTotalTribute(){
		return TotalTribute_;
	}
	/**********************************/
	/*       Impressão da NF          */
	/**********************************/
	public String toPrint(){
		String impressao = "";
		impressao += getStatus();
		for (IV item : IVs_)
		{
			impressao += "\n";
			impressao += "Quant: "+item.getQuant_();
			impressao += " $" + item.getPrice_();
			impressao += " - " + item.getPSname_();
		}
		return impressao;
	}
}

