package imposto;

import java.util.ArrayList;
import java.util.List;

public class Imposto_Info {
	// Essa classe deve ser carregada de informa��es complexas
	// sobre o c�lculo de impostos. Pode inclusive ter
	// um hist�rico de NFs para ponderar sua cobran�a.
	private static List<Float> nf_anteriores;

	public static void startInfo(){
		nf_anteriores = new ArrayList<Float>();
	}
	public static void addNF(int totalTribute_) {
		if (nf_anteriores == null) return;
		nf_anteriores.add((float) totalTribute_);
	}

	// Fatura total das NFs anteriores
	public static int getFaturasAnteriores(){
		if(nf_anteriores == null) return 0;
		int totalAnterior = 0;
		for (Float fatura : nf_anteriores) 
			totalAnterior += fatura;
		return totalAnterior;
	}
	
	// Fatura m�dia das NFs anteriores
	public static int getMediaAnterior(){
		if (nf_anteriores == null || nf_anteriores.isEmpty()) return 0;
		return getFaturasAnteriores()/nf_anteriores.size();
	}

	public static int getTax() {
		return taxa;
	}

	private static final int taxa = 10;
}
