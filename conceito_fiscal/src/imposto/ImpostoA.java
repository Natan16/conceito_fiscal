package imposto;

import conceito_fiscal.IV;

public class ImpostoA implements Imposto
{
	float aliquota_ = 0.1f;
	
	public ImpostoA(float aliquota){
		aliquota_ += aliquota; 
	}
	// A categoria de imposto A tem taxa de 10% de acréscimo
	//   sobre o valor total do IV.
	@Override
	public int calculaImposto(IV item) {
		int itemTax = 0;
		itemTax += item.getQuant_()*item.getPrice_();
		itemTax *= 1 + aliquota_;
		return itemTax;
	}

}
