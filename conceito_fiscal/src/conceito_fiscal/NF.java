package conceito_fiscal;

import imposto.Imposto_Facade;
import java.util.ArrayList;
import banco_dados.BDNF_Facade;

public class NF extends NF_Abstract
{
	public NF(){
		BDNF_Facade_ = BDNF_Facade.getInstance();
		Imposto_Facade_ = Imposto_Facade.getInstance();
		IVs_ = new ArrayList<IV>();
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
	public IV getIV(int ID){
		return IVs_.get(ID);
	}
	// Remove IV da lista IVs, desde que ela n�o fique vazia
	public void removeIV(int ID){
		if (IVs_.size() > 1)
			IVs_.remove(IVs_.get(ID));
	}
	public int sizeIVs(){
		return IVs_.size();
	}
	
	/**********************************/
	/*       C�lculo de Impostos      */
	/**********************************/
	public int calculaImposto(){
		return Imposto_Facade_.tax(IVs_);
	}
}

