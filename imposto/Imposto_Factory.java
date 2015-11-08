package imposto;

public class Imposto_Factory {
	
	public static Imposto getImposto(String cat){
		if ( cat == "A"){
			return new ImpostoA();
		}
		else if( cat == "B"){
			return new ImpostoB();
		}
		return new ImpostoNull();
	}
}
