package banco_dados;

import java.util.ArrayList;
import java.util.Collection;

import conceito_fiscal.NF;
import conceito_fiscal.NF_Builder;

public class BDNF_MockDados 
{
	private ArrayList<NF> NFs_;
	
	// Cria uma lista aleatória de NFs
	public Collection<? extends NF> getNFs_() 
	{
		NF_Builder nfBuilder = new NF_Builder();
		nfBuilder.constructNF();
		
		NFs_ = new ArrayList<NF>();
		for (int i = 0; i <10; i++)
			NFs_.add(nfBuilder.constructNF());
		return NFs_;
	}
}
