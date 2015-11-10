package conceito_fiscal;

import banco_dados.BDPS_Facade;

public class NF_Builder 
{
	// Builder b�sico de NF
	public static NF constructNF(int idProduto, int quant, int price){
		NF nf_ = new NF();
		PS_Abstract ps_ = BDPS_Facade.getPS(idProduto);
		nf_.addNewIV(ps_, quant, price);
		return nf_;
	}
	// Builder basico de NF_Final
	public static NF_Final constructNF_Final(NF nf, int id){
		NF_Final nf_ = new NF_Final(nf, id);
		return nf_;
	}
}
