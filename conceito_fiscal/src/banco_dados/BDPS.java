package banco_dados;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import conceito_fiscal.PS_Concrete;

public class BDPS 
{
	private ArrayList<PS_Concrete> PSs_;
	private ArrayList<String> TributeCat_;
	private Map<String,Float> aliquotas_;
	// Construtor de BDNF usando PS
	public BDPS(){
		BDPS_MockDados Mock = new BDPS_MockDados();
		PSs_ = new ArrayList<>(Mock.getPSs_());
		TributeCat_ = new ArrayList<>(Mock.getTributeCat());
		aliquotas_ = new HashMap<String,Float>(Mock.getAliquotas());
	}
	
	public float getAliquota(String cat){
		return aliquotas_.get(cat);
	}
	
	// Padrão Singleton:
	private static BDPS INSTANCE;
	public static BDPS getInstance() {
		if (BDPS.INSTANCE == null)
			BDPS.INSTANCE = new BDPS();
		return BDPS.INSTANCE;
	}

	/**********************************/
	/*   Manipulação dos ArrayLists   */
	/**********************************/
	// Operações de add/remove no ArrayList:
	public PS_Concrete createNewPS(String nome, String categoria, PS_Concrete PS){
		PS_Concrete newPS = PS_Concrete.createNewPS(nome, PS);
		PSs_.add(newPS);
		TributeCat_.add(categoria);
		return newPS;
	}
	public void removePS(PS_Concrete PS){
		TributeCat_.remove(PSs_.indexOf(PS));
		PSs_.remove(PS);
	}
	// Operações de busca no ArrayList:
	public PS_Concrete getPS(int ID){
		return PSs_.get(ID);
	}
	public String getTributeCat(PS_Concrete PS){
		return TributeCat_.get(PSs_.indexOf(PS));
	}
}
