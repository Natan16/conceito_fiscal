package conceito_fiscal;

import imposto.Imposto_Calc;
import java.util.ArrayList;

public class NF extends NF_Abstract
{
	public NF(){
		IVs_ = new ArrayList<IV>();
		calcImposto_ = new Imposto_Calc();
	}
	public void addIV(IV item){
		IVs_.add(item);
	}
	public void removeIV(IV item){
		IVs_.remove(item);
	}
	public int calculaImposto(){
		return calcImposto_.tax(IVs_);
	}
}

