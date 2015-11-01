package conceito_fiscal;

import java.util.ArrayList;

public abstract class NF_Abstract 
{
	// Par�metros b�sicos da NF
	protected ArrayList<IV> IVs_;
	protected int TotalTribute_;
	
	// M�todos da NF
	public abstract int calculaImposto();
	public abstract boolean isFinal();
	public abstract String getStatus();
}

