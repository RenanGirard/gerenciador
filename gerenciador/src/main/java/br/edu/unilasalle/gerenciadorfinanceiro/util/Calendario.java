package br.edu.unilasalle.gerenciadorfinanceiro.util;

import java.util.Calendar;
import java.util.Date;

public class Calendario {

	public Date adicionaMes(Date dataDeLancamento, int i) {
		// TODO Auto-generated method stub
		Calendar cal = Calendar.getInstance();
		cal.setTime(dataDeLancamento);
		cal.add(Calendar.MONTH, i);
		Date data = cal.getTime();
		return data;
	}

}
