package conceito_fiscal;

public abstract class PS_Abstract {
	protected String nome_;

	public String getNome() {
		return nome_;
	}
	// [Imutable Object] A classe abstrata PS e seus filhos nao possuem
	// nenhum método setter, por isso ela é imutável, ou seja, todos os
	// seus atributos sao setados uma vez soh.
	public boolean isFinal() {
		return true;
	}

	public boolean isFolha() {
		return false;
	}
}
