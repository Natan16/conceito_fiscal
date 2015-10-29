package conceito_fiscal;

import imposto.Imposto_Calc;
import java.util.ArrayList;

public abstract class NF_Abstract 
{
	protected int ID_;
	protected ArrayList<IV> IVs_;
	protected Imposto_Calc calcImposto_;
	public abstract int calculaImposto();
}

