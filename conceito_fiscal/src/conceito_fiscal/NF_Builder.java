package conceito_fiscal;

public class NF_Builder 
{
	private NF nf_;
	private IV iv__;
	private PS_Concrete ps_;
	
	public NF_Builder()
	{
		nf_ = new NF();
		String categoria = "A";
		ps_ = new PS_Concrete(categoria, null);
		int quant = 1;
		int desc = 10;
		iv__ = new IV(nf_, ps_, quant, desc);
		nf_.addIV(iv__);
	}
	public NF getNF(){
		return nf_;
	}
}
