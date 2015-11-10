package imposto;

import banco_dados.BDPS_Facade;

public class Imposto_Factory {

	public static Imposto getImposto(String cat) {
		float aliquota = BDPS_Facade.getAliquota(cat);
		if (cat == "A") {
			return new ImpostoA(aliquota);
		} else if (cat == "B") {
			return new ImpostoB(aliquota);
		} else if (cat == "A+") {
			return new ImpostoA(aliquota);
		}
		return new ImpostoNull();
	}
}
