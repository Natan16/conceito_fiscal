package conceito_fiscal;

public class PS_Concrete_Folha extends PS_Abstract {
	// O PS do tipo folha nao tem PS.
	private PS_Concrete_Folha(String nome) {
		nome_ = nome;
	}

	public static PS_Concrete_Folha createNewPS(String nome) {
		return new PS_Concrete_Folha(nome);
	}

	@Override
	public boolean isFolha() {
		return true;
	}
}
