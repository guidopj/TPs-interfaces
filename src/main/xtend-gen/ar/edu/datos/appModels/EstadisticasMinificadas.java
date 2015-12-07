package ar.edu.datos.appModels;

import domain.Ubicacion;
import org.eclipse.xtend.lib.annotations.Accessors;
import org.eclipse.xtext.xbase.lib.Pure;

@Accessors
@SuppressWarnings("all")
public class EstadisticasMinificadas {
  private int jugadas;
  
  private int ganadas;
  
  private int kills;
  
  private int deads;
  
  private int assists;
  
  private String mejorUbicacion;
  
  private String puntaje;
  
  public EstadisticasMinificadas(final int jugadas, final int ganadas, final int kills, final int deads, final int assists, final Ubicacion mejorUbicacion, final String puntaje) {
    this.jugadas = jugadas;
    this.ganadas = ganadas;
    this.kills = kills;
    this.deads = deads;
    this.assists = assists;
    String _valueOf = String.valueOf(mejorUbicacion);
    this.mejorUbicacion = _valueOf;
    this.puntaje = puntaje;
  }
  
  @Pure
  public int getJugadas() {
    return this.jugadas;
  }
  
  public void setJugadas(final int jugadas) {
    this.jugadas = jugadas;
  }
  
  @Pure
  public int getGanadas() {
    return this.ganadas;
  }
  
  public void setGanadas(final int ganadas) {
    this.ganadas = ganadas;
  }
  
  @Pure
  public int getKills() {
    return this.kills;
  }
  
  public void setKills(final int kills) {
    this.kills = kills;
  }
  
  @Pure
  public int getDeads() {
    return this.deads;
  }
  
  public void setDeads(final int deads) {
    this.deads = deads;
  }
  
  @Pure
  public int getAssists() {
    return this.assists;
  }
  
  public void setAssists(final int assists) {
    this.assists = assists;
  }
  
  @Pure
  public String getMejorUbicacion() {
    return this.mejorUbicacion;
  }
  
  public void setMejorUbicacion(final String mejorUbicacion) {
    this.mejorUbicacion = mejorUbicacion;
  }
  
  @Pure
  public String getPuntaje() {
    return this.puntaje;
  }
  
  public void setPuntaje(final String puntaje) {
    this.puntaje = puntaje;
  }
}
