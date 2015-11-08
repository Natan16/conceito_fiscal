package conceito_fiscal;

import java.util.ArrayList;

public class PS_Concrete extends PS_Abstract {
	private ArrayList<PS_Abstract> PS_;

	// O construtor privado pro�be a instancia��o de novos P/S.
	// Somente o BDPS pode criar novos P/S, pelo m�todo createNewPS
	private PS_Concrete(String nome, ArrayList<PS_Abstract> PS) {
		PS_ = new ArrayList<PS_Abstract>(PS);
		nome_ = nome;
	}

	public static PS_Concrete createNewPS(String nome, ArrayList<PS_Abstract> PS) {
		return new PS_Concrete(nome, PS);
	}

	// O P/S pode se referir a um outro P/S abstrato.
	public PS_Abstract getPSs_(int id) {
		return PS_.get(id);
	}
}
