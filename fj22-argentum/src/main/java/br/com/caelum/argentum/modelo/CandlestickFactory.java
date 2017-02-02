package br.com.caelum.argentum.modelo;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class CandlestickFactory {
	// constroi um Candle
	public Candlestick constroiCandleParaData(Calendar data, List<Negociacao> negociacoes) {

		double maximo = 0;
		double minimo = negociacoes.isEmpty() ? 0 : Double.MAX_VALUE;
		double volume = 0;

		for (Negociacao negociacao : negociacoes) {
			volume += negociacao.getVolume();

			if (negociacao.getPreco() > maximo) {
				maximo = negociacao.getPreco();
			}
			if (negociacao.getPreco() < minimo) {
				minimo = negociacao.getPreco();
			}
		}

		double abertura = negociacoes.isEmpty() ? 0 : negociacoes.get(0).getPreco();
		double fechamento = negociacoes.isEmpty() ? 0 : negociacoes.get(negociacoes.size() - 1).getPreco();

		return new Candlestick(abertura, fechamento, minimo, maximo, volume, data);

	}

	public List<Candlestick> constroiCandles(List<Negociacao> todasNegociacoes) {
		Calendar dataAtual = todasNegociacoes.get(0).getData();
		List<Negociacao> negociacoesDoDia = new ArrayList<>();
		List<Candlestick> candles = new ArrayList<>();
		/*Logica em aula
		for (Negociacao negociacoes : todasNegociacoes) {
			
			if (negociacoes.equals(dataAtual)) {
				negociacaoDoDia.add(negociacoes);
			} else {
				Candlestick c = constroiCandleParaData(dataAtual,negociacaoDoDia);
				candles.add(c);
				dataAtual = negociacoes.getData();
				negociacaoDoDia.clear();
				negociacaoDoDia.add(negociacoes);
			}
		}
		return null;*/
			
			// se nao for mesmo dia, fecha candle e reinicia variáveis
			for(Negociacao negociacao: todasNegociacoes){
				if(!negociacao.isMesmoDia(dataAtual)){
					Candlestick candleDoDia = constroiCandleParaData(dataAtual,negociacoesDoDia);
					
					
				candles.add(candleDoDia);
				negociacoesDoDia = new ArrayList<Negociacao>();
				dataAtual = negociacao.getData();
				}
				negociacoesDoDia.add(negociacao);
			}
			//Adiciona ultimo candle
			Candlestick candleDoDia = constroiCandleParaData(dataAtual, negociacoesDoDia);
			candles.add(candleDoDia);
			
			return candles;

	}


}
