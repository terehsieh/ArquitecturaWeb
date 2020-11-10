package tp3.gr1.entidades;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import tp3.gr1.dao.CarreraDAO;

public class ReporteCarrerasPorAnio {
	
	private List<ReporteGraduadosCarrerasPorAnio> graduados;
	
	private List<ReporteInscriptosCarrerasPorAnio> inscriptos;
	
	public ReporteCarrerasPorAnio(List<ReporteGraduadosCarrerasPorAnio> graduados,
			List<ReporteInscriptosCarrerasPorAnio> inscriptos) {
		super();
		CarreraDAO c1 = new CarreraDAO();
		this.graduados = c1.getGraduadosPorCarrera();
		this.inscriptos = c1.getInscriptosPorCarrera();
	}

	public List<InfoCarrera>  getReporte(){ 
		List<InfoCarrera> info = new ArrayList<InfoCarrera>();
		Iterator<ReporteGraduadosCarrerasPorAnio> itG = this.graduados.iterator();
		Iterator<ReporteInscriptosCarrerasPorAnio> itI = this.inscriptos.iterator();
		ReporteGraduadosCarrerasPorAnio g = itG.next();
		ReporteInscriptosCarrerasPorAnio i = itI.next();
		
		while (itG.hasNext() && itI.hasNext()) {
			if (i.getNombre_carrera().compareTo(g.getNombre_carrera())==0) {
				if (i.getFechaInscripcion()<g.getFechaGraduacion()) {
					info.add(new InfoCarrera(i.getNombre_carrera(),i.getFechaInscripcion(),i.getCantInscriptos(),new BigDecimal(0)));
					i = itI.next();
				}
				else if (i.getFechaInscripcion()>g.getFechaGraduacion()){
					info.add(new InfoCarrera(g.getNombre_carrera(),g.getFechaGraduacion(),BigInteger.valueOf(0),g.getCantGraduados()));
					g = itG.next();	
				}
				else {
					info.add(new InfoCarrera(i.getNombre_carrera(),i.getFechaInscripcion(),i.getCantInscriptos(),g.getCantGraduados()));
					i = itI.next();
					g = itG.next();					
				}
			}
			else if (i.getNombre_carrera().compareTo(g.getNombre_carrera())<0) {
				info.add(new InfoCarrera(i.getNombre_carrera(),i.getFechaInscripcion(),i.getCantInscriptos(),new BigDecimal(0)));				
				i = itI.next();
			}
			else if (i.getNombre_carrera().compareTo(g.getNombre_carrera())>0) {
				info.add(new InfoCarrera(g.getNombre_carrera(),g.getFechaGraduacion(),BigInteger.valueOf(0),g.getCantGraduados()));				
				g = itG.next();
			}
	
		}
		if(itG.hasNext()) {
			while (itG.hasNext()) {
				info.add(new InfoCarrera(g.getNombre_carrera(),g.getFechaGraduacion(),BigInteger.valueOf(0),g.getCantGraduados()));
				g = itG.next();	
			}
		}else if(itI.hasNext()) {
			while (itI.hasNext()) {
				info.add(new InfoCarrera(i.getNombre_carrera(),i.getFechaInscripcion(),i.getCantInscriptos(),new BigDecimal(0)));
				i = itI.next();
			}
		}
		
		return info;
	}

	@Override
	public String toString() {
		return "ReporteCarrerasPorAnio [graduados=" + graduados + ", inscriptos=" + inscriptos + "]";
	}


}

