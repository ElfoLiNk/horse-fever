/**
 * 
 */
package it.polimi.ingegneriaDelSoftware2013.horseFever_leonardo.orsello_matteo.gazzetta;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

/**
 * @author matteo
 * 
 */
public class Write {
	private static BufferedWriter bw;
	private static FileOutputStream fos;

	public static void write(String s) {
		fos = new FileOutputStream(java.io.FileDescriptor.out);
		bw = new BufferedWriter(new OutputStreamWriter(fos));
		try {
			bw.write(s);
		} catch (IOException e) {
			System.out.println("Errore di flusso");
		}
	}

	public static void write(int i) {
		fos = new FileOutputStream(java.io.FileDescriptor.out);
		bw = new BufferedWriter(new OutputStreamWriter(fos));
		try {
			bw.write(i);
			bw.close();
			fos.close();
		} catch (IOException e1) {
			Write.write("Errore di flusso");
		}
	}
}