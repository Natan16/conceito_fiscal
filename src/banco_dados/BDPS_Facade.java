package banco_dados;

import conceito_fiscal.PS_Abstract;
import conceito_fiscal.PS_Concrete;
import java.util.ArrayList;

public class BDPS_Facade 
{
	// Padr�o Singleton:
	private static BDPS_Facade INSTANCE;
	public static BDPS_Facade getInstance() {
		if (BDPS_Facade.INSTANCE == null)
			BDPS_Facade.INSTANCE = new BDPS_Facade();
		return BDPS_Facade.INSTANCE;
	}
		
	// M�todos Facade:
	public static BDPS getBDPSinstance(){
            return BDPS.getInstance();
	}
	public static PS_Abstract createNewPS(String nome, String categoria, ArrayList<PS_Abstract> PS){
		return BDPS.getInstance().createNewPS(nome, categoria, PS);
	}
	public static PS_Abstract getPS(int ID){
		return BDPS.getInstance().getPS(ID);
	}
	public static String getTributeCat(PS_Abstract PS){
		return BDPS.getInstance().getTributeCat(PS);
	}
}
