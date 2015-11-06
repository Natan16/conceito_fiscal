package conceito_fiscal;

import imposto.Imposto_Facade;

import java.util.ArrayList;

public class NF extends NF_Abstract
{
	public NF() {
		IVs_ = new ArrayList<IV>();
	}
	
	// Checagem do Status da NF.
	@Override
	public String getStatus() {
		return "NF em elaboracao";
	}
	@Override
	public boolean isFinal() {
		return false;
	}
	/**********************************/
	/* Manipula��o da Lista de IVs    */
	/**********************************/
	// O IV adicionado � NF � constru�do dentro da mesma.
	public IV addNewIV(PS_Abstract ps, int quant, int price){
		IV item = new IV(this, ps, quant, price);
		IVs_.add(item);
		return item;
	}
	public boolean containsIV(IV item){
		return IVs_.contains(item);
	}
	public IV getIV (int ID){
		return IVs_.get(ID);
	}
	public ArrayList<IV> getIVs(){
		return IVs_;
	}
	// Remove IV da lista IVs, desde que ela n�o fique vazia
	public void removeIV(IV item){
		if (IVs_.size() > 1)
			IVs_.remove(item);
	}
	public void removeID(int id){
		if (IVs_.size() > 1)
			IVs_.remove(IVs_.get(id));
	}
	public int sizeIVs(){
		return IVs_.size();
	}
	
	/**********************************/
	/*       C�lculo de Impostos      */
	/**********************************/
	public int calculaImposto(){
		return Imposto_Facade.tax(IVs_);
	}
}

