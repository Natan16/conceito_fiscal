package conceito_fiscal;

import banco_dados.BDPS_Facade;

public class NF_Builder 
{
	private NF nf_;
	private PS_Concrete ps_;

	// Builder básico de NF
	public NF constructNF(){
		nf_ = new NF();
		ps_ = BDPS_Facade.createNewPS("produto","A", null);
		nf_.addNewIV(ps_, 1, 10);
		return nf_;
	}
}
