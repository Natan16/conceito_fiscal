package banco_dados;

import java.util.ArrayList;
import java.util.Collection;

import conceito_fiscal.NF;
import conceito_fiscal.NF_Builder;

public class BDNF_MockDados 
{
	private final int nMockObjects_ = 10;
	private ArrayList<NF> NFs_;
	
	public int getNMockObjects(){
		return nMockObjects_;
	}
	
	// Cria uma lista aleatória de NFs
	public Collection<? extends NF> getNFs_() 
	{
		NF_Builder.constructNF();
		
		NFs_ = new ArrayList<NF>();
		for (int i = 0; i < nMockObjects_; i++)
			NFs_.add(NF_Builder.constructNF());
		return NFs_;
	}
}
