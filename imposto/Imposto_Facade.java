package imposto;

import java.util.ArrayList;

import conceito_fiscal.IV;

public class Imposto_Facade 
{
	// Padr�o Singleton:
	private static Imposto_Facade INSTANCE;
	public static Imposto_Facade getInstance() {
		if (Imposto_Facade.INSTANCE == null)
			Imposto_Facade.INSTANCE = new Imposto_Facade();
		return Imposto_Facade.INSTANCE;
	}
	
	// M�todos Facade:
	public static int tax(ArrayList<IV> IVs_){
		return Imposto_Strategy.tax(IVs_);
	}
	public static void startInfo(){
		Imposto_Info.startInfo();
	}
}
