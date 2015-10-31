package conceito_fiscal;

import imposto.Imposto_Facade;
import banco_dados.BDNF_Facade;
import java.util.ArrayList;

public abstract class NF_Abstract 
{
	protected static int ID_;
	protected ArrayList<IV> IVs_;
	protected BDNF_Facade BDNF_Facade_;
	protected Imposto_Facade Imposto_Facade_;
	public abstract int calculaImposto();
}

