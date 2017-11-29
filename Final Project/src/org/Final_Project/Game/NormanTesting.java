package org.Final_Project.Game;

import javax.script.ScriptException;

public class NormanTesting {
	public static void main(String[] args) throws ScriptException {
		String hi = "5 *  4 ^ 10  / 10";
		Game24 bye = new Game24();
		Object result = 0;
		result = bye.check24(hi);
		//System.out.println(result);
	}
}
