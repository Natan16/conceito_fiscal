package imposto;

import conceito_fiscal.IV;

public class ImpostoNull implements Imposto{
	
	@Override
	public int calculaImposto(IV iv) {
		return 0;
	}
	
}
