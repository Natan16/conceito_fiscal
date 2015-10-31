package conceito_fiscal;

public class NF_Builder 
{
	private NF nf_;
	private PS_Concrete ps_;

	// Builder básico de NF
	public NF constructNF(){
		nf_ = new NF();
		ps_ = new PS_Concrete("produto","A", null);
		nf_.addNewIV(ps_, 1, 10);
		return nf_;
	}
	
	// Builder de NF final
	public NF_Final constructFinalNF(NF nf){
		return new NF_Final(nf);
	}
}
