package conceito_fiscal;

public class NF_Final extends NF_Abstract
{	
	public NF_Final(NF nf){
		// Essa NF deve ser igual a nf, porém deve ser final.
	}
	public int calculaImposto(){
		return calcImposto_.tax(IVs_);
	}
}

