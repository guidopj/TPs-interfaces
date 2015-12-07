package ar.edu.datos.appModels;

import ar.edu.datos.appModels.EstadisticasMinificadas;
import domain.Calificacion;
import domain.Caracteristicas;
import domain.EstadisticasPersonajes;
import domain.Juego;
import domain.Jugador;
import domain.Personaje;
import domain.Ubicacion;
import duelos.Duelo;
import homes.HomeJuego;
import java.io.Serializable;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import org.eclipse.xtend.lib.annotations.Accessors;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.ListExtensions;
import org.eclipse.xtext.xbase.lib.Pure;
import org.uqbar.commons.utils.Observable;
import resultado.Resultado;
import retador.Bot;
import retador.Iniciador;
import retador.Retador;

@Accessors
@Observable
@SuppressWarnings("all")
public class DatosDueloAppModel {
  private HomeJuego homeJuego;
  
  public DatosDueloAppModel(final HomeJuego homeJuego) {
    this.homeJuego = homeJuego;
  }
  
  public List<Ubicacion> posiciones() {
    return Collections.<Ubicacion>unmodifiableList(CollectionLiterals.<Ubicacion>newArrayList(Ubicacion.TOP, Ubicacion.BOTTOM, Ubicacion.MIDDLE, Ubicacion.JUNGLE));
  }
  
  public Juego juego() {
    return this.homeJuego.getJuego();
  }
  
  public Jugador obtenerJugador(final Integer idJugador) {
    List<Jugador> _jugadores = this.jugadores();
    final Function1<Jugador, Boolean> _function = new Function1<Jugador, Boolean>() {
      public Boolean apply(final Jugador it) {
        Integer _id = it.getId();
        return Boolean.valueOf(_id.equals(idJugador));
      }
    };
    return IterableExtensions.<Jugador>findFirst(_jugadores, _function);
  }
  
  public List<Jugador> jugadores() {
    Juego _juego = this.juego();
    return _juego.getJugadores();
  }
  
  public LinkedHashMap<String, Object> datosDeEstadisticas(final Integer idJugador, final Integer idPersonaje) {
    EstadisticasPersonajes _estadisticasDePersonajeSeleccionado = this.homeJuego.estadisticasDePersonajeSeleccionado(idJugador, idPersonaje);
    return this.dameSusPropiedades(_estadisticasDePersonajeSeleccionado);
  }
  
  public EstadisticasMinificadas datosDeEstadisticas2(final Integer idJugador, final Integer idPersonaje) {
    EstadisticasPersonajes _estadisticasDePersonajeSeleccionado = this.homeJuego.estadisticasDePersonajeSeleccionado(idJugador, idPersonaje);
    return this.minificadas(_estadisticasDePersonajeSeleccionado);
  }
  
  public EstadisticasMinificadas minificadas(final EstadisticasPersonajes it) {
    int _vecesUsadoAntesDelDuelo = it.getVecesUsadoAntesDelDuelo();
    int _vecesQueGanoDuelo = it.getVecesQueGanoDuelo();
    int _vecesKills = it.getVecesKills();
    int _vecesDeads = it.getVecesDeads();
    int _vecesAssist = it.getVecesAssist();
    Ubicacion _mejorUbicacion = it.getMejorUbicacion();
    Calificacion _calificacion = it.getCalificacion();
    String _categoria = _calificacion.getCategoria();
    return new EstadisticasMinificadas(_vecesUsadoAntesDelDuelo, _vecesQueGanoDuelo, _vecesKills, _vecesDeads, _vecesAssist, _mejorUbicacion, _categoria);
  }
  
  public LinkedHashMap<String, Object> dameSusPropiedades(final EstadisticasPersonajes it) {
    LinkedHashMap<String, Object> _xblockexpression = null;
    {
      final LinkedHashMap<String, Object> propiedades = new LinkedHashMap<String, Object>();
      int _vecesUsadoAntesDelDuelo = it.getVecesUsadoAntesDelDuelo();
      propiedades.put("Jugadas", Integer.valueOf(_vecesUsadoAntesDelDuelo));
      int _vecesQueGanoDuelo = it.getVecesQueGanoDuelo();
      propiedades.put("Ganadas", Integer.valueOf(_vecesQueGanoDuelo));
      int _vecesKills = it.getVecesKills();
      propiedades.put("Kills", Integer.valueOf(_vecesKills));
      int _vecesDeads = it.getVecesDeads();
      propiedades.put("Deads", Integer.valueOf(_vecesDeads));
      int _vecesAssist = it.getVecesAssist();
      propiedades.put("Assists", Integer.valueOf(_vecesAssist));
      Ubicacion _mejorUbicacion = it.getMejorUbicacion();
      propiedades.put("Mejor ubicacion", _mejorUbicacion);
      Calificacion _calificacion = it.getCalificacion();
      String _categoria = _calificacion.getCategoria();
      propiedades.put("Puntaje", _categoria);
      _xblockexpression = propiedades;
    }
    return _xblockexpression;
  }
  
  public List<? extends Serializable> iniciarDuelo(final Integer idJugador, final Integer idPersonaje, final String pos) {
    List<? extends Serializable> _xblockexpression = null;
    {
      Juego _juego = this.juego();
      Jugador _obtenerJugador = this.obtenerJugador(idJugador);
      Personaje _obtenerPersonaje = this.obtenerPersonaje(idJugador, idPersonaje);
      Ubicacion _obtenerUbicacionDe = this.obtenerUbicacionDe(pos);
      final Duelo duelo = _juego.iniciarReto(_obtenerJugador, _obtenerPersonaje, _obtenerUbicacionDe);
      _xblockexpression = this.datos(duelo);
    }
    return _xblockexpression;
  }
  
  public List<? extends Serializable> iniciarDueloConMrX(final Integer idJugador, final Integer idPersonaje, final String pos) {
    List<? extends Serializable> _xblockexpression = null;
    {
      Jugador _obtenerJugador = this.obtenerJugador(idJugador);
      Personaje _obtenerPersonaje = this.obtenerPersonaje(idJugador, idPersonaje);
      Ubicacion _obtenerUbicacionDe = this.obtenerUbicacionDe(pos);
      final Iniciador retador = new Iniciador(_obtenerJugador, _obtenerPersonaje, _obtenerUbicacionDe);
      Juego _juego = this.juego();
      final Bot mrX = _juego.generarMRX(retador, 1);
      final Duelo duelo = new Duelo(retador, mrX);
      duelo.realizarse();
      _xblockexpression = this.datos(duelo);
    }
    return _xblockexpression;
  }
  
  public List<? extends Serializable> datos(final Duelo duelo) {
    Retador _retador = duelo.getRetador();
    LinkedHashMap<String, Object> _propiedadesParaLasEstadisticas = this.propiedadesParaLasEstadisticas(_retador);
    Retador _retado = duelo.getRetado();
    LinkedHashMap<String, Object> _propiedadesParaLasEstadisticas_1 = this.propiedadesParaLasEstadisticas(_retado);
    Retador _retador_1 = duelo.getRetador();
    Personaje _personaje = _retador_1.getPersonaje();
    String _nombre = _personaje.getNombre();
    Retador _retado_1 = duelo.getRetado();
    Personaje _personaje_1 = _retado_1.getPersonaje();
    String _nombre_1 = _personaje_1.getNombre();
    Resultado _resultado = duelo.getResultado();
    String _msj = _resultado.msj();
    Resultado _resultado_1 = duelo.getResultado();
    String _veredicto = _resultado_1.veredicto();
    Retador _retador_2 = duelo.getRetador();
    int _puntaje = this.puntaje(_retador_2);
    Retador _retado_2 = duelo.getRetado();
    int _puntaje_1 = this.puntaje(_retado_2);
    Retador _retador_3 = duelo.getRetador();
    Personaje _personaje_2 = _retador_3.getPersonaje();
    String _source = _personaje_2.getSource();
    Retador _retado_3 = duelo.getRetado();
    Personaje _personaje_3 = _retado_3.getPersonaje();
    String _source_1 = _personaje_3.getSource();
    return Collections.<Serializable>unmodifiableList(CollectionLiterals.<Serializable>newArrayList(_propiedadesParaLasEstadisticas, _propiedadesParaLasEstadisticas_1, _nombre, _nombre_1, _msj, _veredicto, Integer.valueOf(_puntaje), Integer.valueOf(_puntaje_1), _source, _source_1));
  }
  
  public int puntaje(final Retador ret) {
    Jugador _jugador = ret.getJugador();
    Personaje _personaje = ret.getPersonaje();
    EstadisticasPersonajes _estadisticas = _jugador.estadisticas(_personaje);
    return _estadisticas.poderDeAtaque();
  }
  
  public LinkedHashMap<String, Object> propiedadesParaLasEstadisticas(final Retador it) {
    Jugador _jugador = it.getJugador();
    Personaje _personaje = it.getPersonaje();
    EstadisticasPersonajes _estadisticas = _jugador.estadisticas(_personaje);
    return this.dameSusPropiedades(_estadisticas);
  }
  
  public Ubicacion obtenerUbicacionDe(final String posicion) {
    return Ubicacion.valueOf(posicion);
  }
  
  public Personaje obtenerPersonaje(final Integer idJugador, final Integer idPersonaje) {
    Jugador _obtenerJugador = this.obtenerJugador(idJugador);
    return this.personajePor(_obtenerJugador, idPersonaje);
  }
  
  public Personaje personajePor(final Jugador jugador, final Integer idPersonaje) {
    List<EstadisticasPersonajes> _estadisticasPersonajes = jugador.getEstadisticasPersonajes();
    final Function1<EstadisticasPersonajes, Personaje> _function = new Function1<EstadisticasPersonajes, Personaje>() {
      public Personaje apply(final EstadisticasPersonajes it) {
        return it.getPersonaje();
      }
    };
    List<Personaje> _map = ListExtensions.<EstadisticasPersonajes, Personaje>map(_estadisticasPersonajes, _function);
    final Function1<Personaje, Boolean> _function_1 = new Function1<Personaje, Boolean>() {
      public Boolean apply(final Personaje it) {
        Integer _id = it.getId();
        return Boolean.valueOf(_id.equals(idPersonaje));
      }
    };
    return IterableExtensions.<Personaje>findFirst(_map, _function_1);
  }
  
  public LinkedHashMap<String, Object> caracteristicas(final Personaje personaje) {
    LinkedHashMap<String, Object> _xblockexpression = null;
    {
      final LinkedHashMap<String, Object> propiedades = new LinkedHashMap<String, Object>();
      List<String> _especialidades = personaje.getEspecialidades();
      propiedades.put("Especialidades", _especialidades);
      List<String> _debilidades = personaje.getDebilidades();
      propiedades.put("Debilidades", _debilidades);
      Ubicacion _ubicacionIdeal = personaje.getUbicacionIdeal();
      propiedades.put("Mejor Posicion", _ubicacionIdeal);
      _xblockexpression = propiedades;
    }
    return _xblockexpression;
  }
  
  public Caracteristicas caracteristicasDelPersonaje(final Personaje personaje) {
    return personaje.caracteristicas();
  }
  
  @Pure
  public HomeJuego getHomeJuego() {
    return this.homeJuego;
  }
  
  public void setHomeJuego(final HomeJuego homeJuego) {
    this.homeJuego = homeJuego;
  }
}
