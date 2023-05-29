package com.examenes.dao.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

	private static SessionFactory factoria;
	
	public static SessionFactory getFactoria() {
		if (HibernateUtil.factoria == null) {
			Configuration configuracion = new Configuration();
			//Carga el fichero hibernate.cfg.xml
			configuracion.configure();
			//Construyo la factoria de sesiones a partir de la configuracion
			HibernateUtil.factoria = configuracion.buildSessionFactory();
		}
		return HibernateUtil.factoria;
	}
	
}
