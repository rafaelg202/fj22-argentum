package br.com.caelum.argentum.modelo;

import static org.junit.Assert.*;

import java.util.Calendar;

import org.junit.Test;

public class CandlestickTest {

	@Test (expected=IllegalArgumentException.class)
	public void precoMaximoNaoPodeSerMenorQueMinimo() {
		
		Calendar hoje = Calendar.getInstance();
		Candlestick c = new Candlestick(10,10,50,40,100,hoje);
	}

}
