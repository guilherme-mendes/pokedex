package controler;

import java.net.URL;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.util.ArrayList;
import model.Pokemon;

public class PokemonAPI {

	ArrayList<Pokemon> listaPokemons = new ArrayList<Pokemon>();
	
	public PokemonAPI(){
		

		String url = "https://pokeapi.co/api/v2/pokemon/";
	     URL obj = null;
		try {
			obj = new URL(url);
		} catch (MalformedURLException e5) {
			
			e5.printStackTrace();
		}
	    		 
	     HttpURLConnection con = null;
		try {
			con = (HttpURLConnection) obj.openConnection();
		} catch (IOException e4) {
			
			e4.printStackTrace();
		}
	   
	     try {
			con.setRequestMethod("GET");
		} catch (ProtocolException e3) {
			
			e3.printStackTrace();
		}
	 
	     con.setRequestProperty("User-Agent", "Mozilla/5.0");
	     int responseCode = 0;
		try {
			responseCode = con.getResponseCode();
		} catch (IOException e2) {
			
			e2.printStackTrace();
		}
	     System.out.println("\nSending 'GET' request to URL : " + url);
	     System.out.println("Response Code : " + responseCode);
	     BufferedReader in = null;
		try {
			in = new BufferedReader(
			         new InputStreamReader(con.getInputStream()));
		} catch (IOException e1) {
			
			e1.printStackTrace();
		}
		  	
		  	 
		     String inputLine;
		     boolean valorNomes = false;
		     ArrayList<Nome> nome = new ArrayList<Nome>();
		     
		     StringBuffer response = new StringBuffer();
		     try {
				while ((inputLine = in.readLine()) != null) {
				 	response.append(inputLine);
				 	
    
				 	nome.add(new Nome(inputLine));	
				
				 }
			} catch (IOException e) {
				
				e.printStackTrace();
			}	
		     
		     String primeiroNome = nome.get(6).getNome();
                     
		     int i = 6;
		     int j = 7;
		  
		     while(i < 3799) {
		    	 
		    	 String[] splitNome = nome.get(i).getNome().split("\"");
		    	 String[] splitURL = nome.get(j).getNome().split("\"");
		    	 Pokemon pokemonLista = new Pokemon();
		    	 pokemonLista.setName(splitNome[3]);
		    	 pokemonLista.setUrl(splitURL[3]);
		    	 
		    	 this.listaPokemons.add(pokemonLista);

			     i = i + 4;
			     j = j + 4;
		     }
		     
	}
	
	public ArrayList<Pokemon> listaInteira(){
		
		return listaPokemons;
	}
	
	public String[] nomeCadaPokemon() {
		String[] a = new String[listaPokemons.size()];
		String aux;
		
	    for(int i = 0; i< 949; i++) {
	    	aux = listaPokemons.get(i).getName();
	    	a[i] = aux;
	    	System.out.println(a[i]);
	    }
	    return a;
	}

	static class Nome{
		String nome;
		
		Nome(String nome){
			this.nome = nome;
		}
		
		String getNome() {
			return nome;
		}
	}
        
	}
	
	
	
