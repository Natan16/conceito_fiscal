package conceito_fiscal;

import imposto.Imposto_Facade;

import java.util.ArrayList;

public class NF_Final extends NF_Abstract
{	
	protected final int ID_;
	protected final int TotalTribute_;
	protected final ArrayList<IV> IVs_;
	
	// Construtor: finalNF deve ser igual a nf, porém final.
	public NF_Final(NF nf, int id){
		IVs_ = new ArrayList<IV>(nf.getIVs());
		TotalTribute_ = this.calculaImposto();
		ID_ = id;
	}
	
	// Checagem do Status da NF.
	@Override
	public String getStatus() {
		return "NF finalizada";
	}
	@Override
	public boolean isFinal() {
		return true;
	}
	/**********************************/
	/*       Cálculo de Impostos      */
	/**********************************/
	public int calculaImposto(){
		return Imposto_Facade.tax(IVs_);
	}
}

