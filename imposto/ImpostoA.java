package imposto;

import conceito_fiscal.IV;

public class ImpostoA implements Imposto
{
	// A categoria de imposto A tem taxa de 10% de acréscimo
	//   sobre o valor total do IV.
	@Override
	public int calculaImposto(IV item) {
		int itemTax = 0;
		itemTax += item.getQuant_()*item.getPrice_();
		itemTax *= 1.1;
		return itemTax;
	}

}
