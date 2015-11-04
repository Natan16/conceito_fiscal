package imposto;

import conceito_fiscal.IV;

public class Imposto_Strategy_high extends Imposto_Strategy{
	public float hook_Calc(Imposto tribute,IV item){
		return 1.1f*tribute.calculaImposto(item);
	}
}
