package conceito_fiscal;

import imposto.CalcImposto;
import java.util.ArrayList;

public class NF 
{
	private ArrayList<IV> IVs_;
	private CalcImposto calcImposto_;
	
	public NF(){
		IVs_ = new ArrayList<IV>();
		calcImposto_ = new CalcImposto();
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

