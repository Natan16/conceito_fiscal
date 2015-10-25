package conceito_fiscal;

public class IV 
{
	private NF NF_;
	private int quant_, desc_;
	private AbstractPS PS_;
	
	public IV (NF nf, AbstractPS ps, int quant, int desc){
		NF_ = nf;
		PS_ = ps;
		quant_ = quant;
		desc_ = desc;
	}

	public NF getNF_() {
		return NF_;
	}

	public void setNF_(NF nF_) {
		NF_ = nF_;
	}

	public int getQuant_() {
		return quant_;
	}

	public int getDesc_() {
		return desc_;
	}

	public AbstractPS getPS_() {
		return PS_;
	}
}
