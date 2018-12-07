
package org.fogbeam.example.opennlp;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;

import opennlp.tools.tokenize.Tokenizer;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;


public class TokenizerMain
{
	public static void main( String[] args ) throws Exception
	{
		
		// the provided model  
		// Con este modelo cuando encuentra "three , por pantalla saca:
		// "
		// three
		// InputStream modelIn = new FileInputStream( "models/en-token.bin" );

		
		// the model we trained
		InputStream modelIn = new FileInputStream( "models/en-token.model" );
		
		File fichero = null;
		FileReader fr = null;
		BufferedReader br = null;
		
		FileWriter fichero_salida = null;
		PrintWriter pw = null;
		
		
		try
		{
			TokenizerModel model = new TokenizerModel( modelIn );
		
			Tokenizer tokenizer = new TokenizerME(model);
			
				/* note what happens with the "three depending on which model you use */
			/*String[] tokens = tokenizer.tokenize
					(  "A ranger journeying with Oglethorpe, founder of the Georgia Colony, " 
							+ " mentions \"three Mounts raised by the Indians over three of their Great Kings" 
							+ " who were killed in the Wars.\"" );
							
			for( String token : tokens )
				{
					System.out.println( token );
				}
			*/
			
			String cadena;
			/*
			FileInputStream fichero = new FileInputStream("eval-data/en-sent.eval");
			BufferedInputStream b = new BufferedInputStream(fichero);
			cadena = b.read();
			String[] tokens = tokenizer.tokenize ( cadena  );
			*/
			
			// apartura del fichero
			fichero = new File("ficheros_pruebas/prueba1.txt");
			fr = new FileReader(fichero);
			br = new BufferedReader(fr);
			
			// lectura del fichero
			//cadena=br.readLine();
			
			fichero_salida = new FileWriter("ficheros_pruebas/salida.txt"); // fichero donde almacenaremos la salida
			pw = new PrintWriter(fichero_salida);
			
			while((cadena=br.readLine())!=null) {	// este bucle es para recorrer todas las lineas del fichero
				String[] tokens = tokenizer.tokenize ( cadena  );
				
				for( String token : tokens )
				{
					//System.out.println( token );
					pw.println(token);	// aqui escribiremos en el fichero de salida
					//pw.println("linea");
				}
			}
			
		}
		catch( IOException e )
		{
			e.printStackTrace();
		}
		finally
		{
			if( modelIn != null )
			{
				try
				{
					modelIn.close();
				}
				catch( IOException e )
				{
				}
			}
			if (null != fichero_salida) {	// cerramos el fichero, si no hacemos esto no se guardara el contenido
				fichero_salida.close();
			}
		}
		System.out.println( "\n-----\ndone" );
	}
}
