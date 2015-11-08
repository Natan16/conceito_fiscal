package conceito_fiscal;

public class IV 
{
	private NF NF_;
	private int quant_, price_;
	private PS_Abstract PS_;
	
	// O construtor de IV já garante que ele se refere a 
	//   exatamente uma NF e a um PS.
	protected IV (NF nf, PS_Abstract ps, int quant, int price){
		NF_ = nf;
		PS_ = ps;
		quant_ = quant;
		price_ = price;
	}

	// Getters das informações do IV
	public NF getNF_() {
		return NF_;
	}
	public int getQuant_() {
		return quant_;
	}
	public int getPrice_() {
		return price_;
	}

	// Gerencia o produto referido pelo IV
	public String getPSname_(){
		return PS_.getNome();
	}
	public PS_Abstract getPS_() {
		return PS_;
	}
	public void setPS_(PS_Abstract ps) {
		PS_ = ps;
	}
}
