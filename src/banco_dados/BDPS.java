package banco_dados;

import conceito_fiscal.PS_Abstract;
import java.util.ArrayList;

import conceito_fiscal.PS_Concrete;
import conceito_fiscal.PS_Concrete_Folha;

public class BDPS 
{
	private ArrayList<PS_Abstract> PSs_, lista_PS;
	private ArrayList<String> TributeCat_;
	// Construtor de BDNF usando PS
	public BDPS(){
		BDPS_MockDados Mock = new BDPS_MockDados();
		PSs_ = new ArrayList<>(Mock.getPSs_());
		TributeCat_ = new ArrayList<>(Mock.getTributeCat());
                lista_PS = new ArrayList<PS_Abstract>();
	}
	
	// Padr�o Singleton:
	private static BDPS INSTANCE;
	public static BDPS getInstance() {
		if (BDPS.INSTANCE == null)
			BDPS.INSTANCE = new BDPS();
		return BDPS.INSTANCE;
	}

	/**********************************/
	/*   Manipula��o dos ArrayLists   */
	/**********************************/
	// Opera��es de add/remove no ArrayList:
	public PS_Abstract createNewPS(String nome, String categoria, ArrayList<PS_Abstract> lista_PS){
            PS_Abstract newPS;
            if (lista_PS == null)
                newPS = PS_Concrete_Folha.createNewPS(nome);
            else newPS = PS_Concrete.createNewPS(nome, lista_PS);
            PSs_.add(newPS);
            TributeCat_.add(categoria);
            return newPS;
	}
	public void removePS(PS_Abstract PS){
		TributeCat_.remove(PSs_.indexOf(PS));
		PSs_.remove(PS);
	}
	// Opera��es de busca no ArrayList:
	public PS_Abstract getPS(int ID){
		return PSs_.get(ID);
	}
	public String getTributeCat(PS_Abstract PS){
		return TributeCat_.get(PSs_.indexOf(PS));
	}
}
