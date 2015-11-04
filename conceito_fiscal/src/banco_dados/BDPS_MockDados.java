package banco_dados;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import conceito_fiscal.PS_Concrete;

public class BDPS_MockDados 
{
	private final int nMockObjects_ = 10;
	private ArrayList<PS_Concrete> PSs_;
	private ArrayList<String> TributeCat_;
	private Map<String,Float> aliquotas_; 
	
	public int getNMockObjects(){
		return nMockObjects_;
	}
	//criando HashMap que associa cada categoria a uma possível aliquota adicional
	public HashMap<String,Float> getAliquotas(){
		aliquotas_ = new HashMap<String, Float>();
		aliquotas_.put("A", 0.0f);
		aliquotas_.put("B", 0.0f);
		aliquotas_.put("A+", 0.1f);
		return (HashMap<String, Float>) aliquotas_;
		
	}
	// Cria uma lista aleatória de P/S
	public Collection<? extends PS_Concrete> getPSs_() 
	{
		PSs_ = new ArrayList<PS_Concrete>();
		for (int i = 0; i < nMockObjects_; i++)
			PSs_.add(PS_Concrete.createNewPS(("produto"+i), null));
		return PSs_;
	}
	
	// Cria uma lista aleatória de Categorias de Impostos
	public ArrayList<String> getTributeCat() {
		String categoria = "";
		TributeCat_ = new ArrayList<String>();
		for (int i = 0; i < nMockObjects_; i++)
		{
			if (i%3 == 0) categoria = "A";
			if (i%3 == 1) categoria = "B";
			if (i%3 == 2) categoria = "A+";
			TributeCat_.add(categoria);
		}	
		return TributeCat_;
	}
}
