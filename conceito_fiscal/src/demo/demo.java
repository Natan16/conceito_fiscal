package demo;

import conceito_fiscal.NF;
import conceito_fiscal.NF_Builder;

public class demo 
{
	static NF_Builder builderNF;
	static NF myNF;
	public static void main(String[] args)
	{
		builderNF = new NF_Builder();
		myNF = builderNF.getNF();
	}

}
