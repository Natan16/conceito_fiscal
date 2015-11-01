package conceito_fiscal;

import java.util.ArrayList;

public abstract class NF_Abstract 
{
	// Parâmetros básicos da NF
	protected ArrayList<IV> IVs_;
	protected int TotalTribute_;
	
	// Métodos da NF
	public abstract int calculaImposto();
	public abstract boolean isFinal();
	public abstract String getStatus();
}

