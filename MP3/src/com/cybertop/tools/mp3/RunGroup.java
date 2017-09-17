package com.cybertop.tools.mp3;

public class RunGroup {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Groupping[] groupping = {Groupping.AUTOR,
								 Groupping.ALBUM
								};
		
		new GroupBy("/media/windows/Dati/Musica", "/media/windows/Dati/M", groupping, true).execute();

	}

}
