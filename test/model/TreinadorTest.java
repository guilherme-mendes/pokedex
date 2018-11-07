/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class TreinadorTest
{
	@Test
	public void TreinadorTest()
	{
		Treinador treinador = new Treinador();
		
                assertEquals(treinador.getId(), 1);
		assertEquals(treinador.getNome(), "James");
		assertEquals(treinador.getRegiao(), "Unova");
		
	}
        @Test
	public void testGetId(){
		int id = 1;
		Treinador treinador = new Treinador(id, "James", "Unova");
		assertEquals(treinador.getId(), id);
	}
        
	@Test
	public void testGetNome(){
		String nome = "James";
		Treinador treinador = new Treinador(1, nome, "Unova");
		assertEquals(treinador.getNome(), nome);
	}

	@Test
	public void testGetRegiao(){
		String regiao = "Unova";
		Treinador treinador = new Treinador(1, "James", regiao);
		assertEquals(treinador.getRegiao(), regiao);
	}
}

