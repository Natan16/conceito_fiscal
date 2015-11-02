package conceito_fiscal;

public class PS_Concrete extends PS_Abstract
{
	private PS_Abstract PS_;
	
	// O construtor privado proíbe a instanciação de novos P/S.
	// 	Somente o BDPS pode criar novos P/S, pelo método createNewPS
	private PS_Concrete(String nome, PS_Abstract PS) {
		nome_ = nome;
		this.PS_ = PS;
	}
	public static PS_Concrete createNewPS(String nome, PS_Abstract PS) {
		return new PS_Concrete(nome, PS);
	}
	
	// O P/S pode se referir a um outro P/S abstrato.
	public PS_Abstract getPS_() {
		return PS_;
	}
}
