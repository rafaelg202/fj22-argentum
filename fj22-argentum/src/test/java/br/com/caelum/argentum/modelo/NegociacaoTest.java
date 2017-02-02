package br.com.caelum.argentum.modelo;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.Assert;
import org.junit.Test;

public class NegociacaoTest {

	@Test
	public void dataDaNegociacaoEHImutavel() {
		// Se criar um negocio no dia 15...
		Calendar c = Calendar.getInstance();
		c.set(Calendar.DAY_OF_MONTH, 15);
		Negociacao n = new Negociacao(10, 5, c);

		// ainda que eu tente mudar a data para 20
		n.getData().set(Calendar.DAY_OF_MONTH, 20);

		// ele continua no dia 15
		Assert.assertEquals(15, n.getData().get(Calendar.DAY_OF_MONTH));
	}

	@Test(expected = IllegalArgumentException.class)
	public void naoCriaNegociacaoComNula() {
		new Negociacao(10, 5, null);
	}

	@Test
	public void mesmoMillissegundoEhDoMesmoDia() {
		Calendar agora = Calendar.getInstance();
		Calendar mesmoMomento = (Calendar) agora.clone();

		Negociacao negociacao = new Negociacao(40.0, 100, agora);
		Assert.assertTrue(negociacao.isMesmoDia(mesmoMomento));
	}

	@Test
	public void comHorariosDiferentesEhMesmoDia() {
		// usando GregorianCalendar(ano, mes, dia, hora, minuto)
		Calendar manha = new GregorianCalendar(2011, 10, 20, 8, 30);
		Calendar tarde = new GregorianCalendar(2011, 10, 20, 15, 30);

		Negociacao negociacao = new Negociacao(40.0, 100, manha);
		Assert.assertTrue(negociacao.isMesmoDia(tarde));
	}

	@Test
	public void mesmoDiaEMesMasAnosDiferentesNaoSaoDoMesmoDia(){

		Calendar ano = new GregorianCalendar(2012, 10, 20, 8, 30);
		Calendar ano2 = new GregorianCalendar(2011, 10, 20, 8, 30);

		Negociacao negociacao = new Negociacao(40.0, 100, ano);
		Assert.assertFalse(negociacao.isMesmoDia(ano2));
	}
}
