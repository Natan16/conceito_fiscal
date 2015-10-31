package conceito_fiscal;

public class PS_Concrete extends PS_Abstract
{
	private PS_Abstract PS_;
	
	public PS_Concrete(String nome, String categoria, PS_Abstract PS) {
		nome_ = nome;
		catImposto_ = categoria;
		this.PS_ = PS;
	}

	public PS_Abstract getPS_() {
		return PS_;
	}
}
