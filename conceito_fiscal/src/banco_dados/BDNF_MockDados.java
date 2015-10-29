package banco_dados;

import java.util.ArrayList;
import java.util.Collection;

import conceito_fiscal.NF;

public class BDNF_MockDados 
{
	private ArrayList<NF> NFs_;
	public Collection<? extends NF> getNFs_() 
	{
		NFs_ = new ArrayList<NF>();
		
		NF nf1 = new NF();
		NFs_.add(nf1);
		
		NF nf2 = new NF();
		NFs_.add(nf2);
		
		return NFs_;
	}
}
