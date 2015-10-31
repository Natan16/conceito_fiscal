package banco_dados;

import java.util.ArrayList;
import java.util.Collection;
import conceito_fiscal.PS_Concrete;

public class BDPS_MockDados 
{
	private ArrayList<PS_Concrete> PSs_;
	
	// Cria uma lista aleatória de PSs
	public Collection<? extends PS_Concrete> getPSs_() 
	{
		String categoria = "";
		PSs_ = new ArrayList<PS_Concrete>();
		for (int i = 0; i <10; i++)
		{
			if (i%3 == 0) categoria = "A";
			if (i%3 == 1) categoria = "B";
			if (i%3 == 2) categoria = "C";
			PSs_.add(new PS_Concrete(("produto"+i),categoria,null));
		}	
		return PSs_;
	}
}
