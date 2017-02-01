package br.com.caelum.argentum.modelo;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Candles {
	
	public List<Candlestick> separaCandles (List<Negociacao> todasNegociacoes){
		Calendar dataAtual = todasNegociacoes.get(0).getData();
		List<Negociacao> doDia = new ArrayList<>();
		List<Candlestick> candles = new ArrayList<>();
		
		for(Negociacao negociacoes : todasNegociacoes){
			if(negociacoes.equals(dataAtual)){
				doDia.add(negociacoes);
			}else{
				Candlestick c = constroiCPD(doDia,dataAtual);
				candles.add(c);
				dataAtual = negociacoes.getData();
				doDia.clear();
				doDia.add(negociacoes);
			}
		}
		return null;
		
	}
}
