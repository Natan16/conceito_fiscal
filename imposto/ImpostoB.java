package imposto;

import conceito_fiscal.IV;

public class ImpostoB implements Imposto
{
	
	float aliquota_ = 0.2f;
	
	public ImpostoB(float aliquota){
		aliquota_ += aliquota; 
	}
	// A categoria de imposto B tem taxa de N*10% de acréscimo
	//   sobre o valor total do IV, em que N é a quantidade do IV.
	@Override
	public int calculaImposto(IV item) {
		int itemTax = 0;
		itemTax += item.getQuant_()*item.getPrice_();
		itemTax *= 1 + aliquota_;
		return itemTax;
	}

}
