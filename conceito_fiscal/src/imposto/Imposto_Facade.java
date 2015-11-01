package imposto;

import java.util.ArrayList;
import conceito_fiscal.IV;

public class Imposto_Facade 
{
	// Padrão Singleton:
	private static Imposto_Facade INSTANCE;
	public static Imposto_Facade getInstance() {
		if (Imposto_Facade.INSTANCE == null)
			Imposto_Facade.INSTANCE = new Imposto_Facade();
		return Imposto_Facade.INSTANCE;
	}
	
	// Métodos Facade:
	public static int tax(ArrayList<IV> iVs_){
		return Imposto_Calc.getInstance().tax(iVs_);
	}
}
