package banco_dados;

import conceito_fiscal.PS_Abstract;
import java.util.ArrayList;
import java.util.Collection;

import conceito_fiscal.PS_Concrete;
import conceito_fiscal.PS_Concrete_Folha;

public class BDPS_MockDados 
{
	private final int nMockObjects_ = 10;
	private ArrayList<PS_Abstract> PSs_;
	private ArrayList<String> TributeCat_;
	
	public int getNMockObjects(){
		return nMockObjects_;
	}
	
	// Cria uma lista aleat�ria de P/S
	public Collection<? extends PS_Abstract> getPSs_() 
	{
		PSs_ = new ArrayList<PS_Abstract>();
		for (int i = 0; i < nMockObjects_; i++)
			PSs_.add(PS_Concrete_Folha.createNewPS(("produto"+i)));
		return PSs_;
	}
	
	// Cria uma lista aleat�ria de Categorias de Impostos
	public ArrayList<String> getTributeCat() {
		String categoria = "";
		TributeCat_ = new ArrayList<String>();
		for (int i = 0; i < nMockObjects_; i++)
		{
			if (i%3 == 0) categoria = "A";
			if (i%3 == 1) categoria = "B";
			if (i%3 == 2) categoria = "C";
			TributeCat_.add(categoria);
		}	
		return TributeCat_;
	}
}
