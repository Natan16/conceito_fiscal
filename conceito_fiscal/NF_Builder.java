package conceito_fiscal;

import banco_dados.BDPS_Facade;

public class NF_Builder 
{
	// Builder basico de NF
	public static NF constructNF(){
		NF nf_ = new NF();
		PS_Abstract ps_ = BDPS_Facade.createNewPS("produto","A", null);
		nf_.addNewIV(ps_, 1, 10);
		return nf_;
	}
	// Builder basico de NF_Final
	public static NF_Final constructNF_Final(NF nf, int id){
		NF_Final nf_ = new NF_Final(nf, id);
		return nf_;
	}
}
