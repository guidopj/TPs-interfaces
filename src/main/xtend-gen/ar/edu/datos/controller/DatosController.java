package ar.edu.datos.controller;

import ar.edu.datos.appModels.DatosDueloAppModel;
import ar.edu.datos.appModels.EstadisticasMinificadas;
import ar.edu.datos.xtrest.JSONPropertyUtils;
import domain.Caracteristicas;
import domain.Jugador;
import domain.NoHayOponenteException;
import domain.Personaje;
import domain.Ubicacion;
import homes.HomeJuego;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.eclipse.jetty.server.Request;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.Extension;
import org.uqbar.xtrest.api.Result;
import org.uqbar.xtrest.api.XTRest;
import org.uqbar.xtrest.api.annotation.Controller;
import org.uqbar.xtrest.api.annotation.Get;
import org.uqbar.xtrest.http.ContentType;
import org.uqbar.xtrest.json.JSONUtils;
import org.uqbar.xtrest.result.ResultFactory;

@Controller
@SuppressWarnings("all")
public class DatosController extends ResultFactory {
  @Extension
  private JSONUtils _jSONUtils = new JSONUtils();
  
  @Extension
  private JSONPropertyUtils _jSONPropertyUtils = new JSONPropertyUtils();
  
  public static void main(final String[] args) {
    XTRest.start(DatosController.class, 9000);
  }
  
  private DatosDueloAppModel appModel;
  
  public DatosController() {
    HomeJuego _homeJuego = new HomeJuego();
    DatosDueloAppModel _datosDueloAppModel = new DatosDueloAppModel(_homeJuego);
    this.appModel = _datosDueloAppModel;
  }
  
  @Get("/posiciones")
  public Result datos(final String target, final Request baseRequest, final HttpServletRequest request, final HttpServletResponse response) {
    Result _xblockexpression = null;
    {
      final List<Ubicacion> ret = this.appModel.posiciones();
      response.setContentType(ContentType.APPLICATION_JSON);
      String _json = this._jSONUtils.toJson(ret);
      _xblockexpression = ResultFactory.ok(_json);
    }
    return _xblockexpression;
  }
  
  @Get("/personajes")
  public Result personajes(final String target, final Request baseRequest, final HttpServletRequest request, final HttpServletResponse response) {
    Result _xblockexpression = null;
    {
      HomeJuego _homeJuego = this.appModel.getHomeJuego();
      final List<Personaje> ret = _homeJuego.getPersonajes();
      response.setContentType(ContentType.APPLICATION_JSON);
      String _json = this._jSONUtils.toJson(ret);
      _xblockexpression = ResultFactory.ok(_json);
    }
    return _xblockexpression;
  }
  
  @Get("/descripcion_personaje/:idJugador/:idPersonaje")
  public Result descripcion(final String idJugador, final String idPersonaje, final String target, final Request baseRequest, final HttpServletRequest request, final HttpServletResponse response) {
    Result _xblockexpression = null;
    {
      Integer _valueOf = Integer.valueOf(idJugador);
      Integer _valueOf_1 = Integer.valueOf(idPersonaje);
      Personaje _obtenerPersonaje = this.appModel.obtenerPersonaje(_valueOf, _valueOf_1);
      final Caracteristicas ret = this.appModel.caracteristicasDelPersonaje(_obtenerPersonaje);
      response.setContentType(ContentType.APPLICATION_JSON);
      String _json = this._jSONUtils.toJson(ret);
      _xblockexpression = ResultFactory.ok(_json);
    }
    return _xblockexpression;
  }
  
  @Get("/nombre_personajes")
  public Result nombresPersonajes(final String target, final Request baseRequest, final HttpServletRequest request, final HttpServletResponse response) {
    Result _xblockexpression = null;
    {
      HomeJuego _homeJuego = this.appModel.getHomeJuego();
      Jugador _jugador = _homeJuego.jugador();
      final List<String> ret = _jugador.nombrePersonajes();
      response.setContentType(ContentType.APPLICATION_JSON);
      String _json = this._jSONUtils.toJson(ret);
      _xblockexpression = ResultFactory.ok(_json);
    }
    return _xblockexpression;
  }
  
  @Get("/estadisticas/:idJugador/:idPersonaje")
  public Result estadisticas(final String idJugador, final String idPersonaje, final String target, final Request baseRequest, final HttpServletRequest request, final HttpServletResponse response) {
    Result _xblockexpression = null;
    {
      Integer _valueOf = Integer.valueOf(idJugador);
      Integer _valueOf_1 = Integer.valueOf(idPersonaje);
      final EstadisticasMinificadas ret = this.appModel.datosDeEstadisticas2(_valueOf, _valueOf_1);
      response.setContentType(ContentType.APPLICATION_JSON);
      String _json = this._jSONUtils.toJson(ret);
      _xblockexpression = ResultFactory.ok(_json);
    }
    return _xblockexpression;
  }
  
  @Get("/iniciarDuelo/:idJugador/:idPersonaje/:pos")
  public Result iniciarDuelo(final String idJugador, final String idPersonaje, final String pos, final String target, final Request baseRequest, final HttpServletRequest request, final HttpServletResponse response) {
    Result _xtrycatchfinallyexpression = null;
    try {
      Result _xblockexpression = null;
      {
        Integer _valueOf = Integer.valueOf(idJugador);
        Integer _valueOf_1 = Integer.valueOf(idPersonaje);
        final List<? extends Serializable> duelo = this.appModel.iniciarDuelo(_valueOf, _valueOf_1, pos);
        response.setContentType(ContentType.APPLICATION_JSON);
        String _json = this._jSONUtils.toJson(duelo);
        _xblockexpression = ResultFactory.ok(_json);
      }
      _xtrycatchfinallyexpression = _xblockexpression;
    } catch (final Throwable _t) {
      if (_t instanceof NoHayOponenteException) {
        final NoHayOponenteException e = (NoHayOponenteException)_t;
        _xtrycatchfinallyexpression = ResultFactory.badRequest("No hay rival para vos");
      } else {
        throw Exceptions.sneakyThrow(_t);
      }
    }
    return _xtrycatchfinallyexpression;
  }
  
  @Get("/buscarAMrX/:idJugador/:idPersonaje/:pos")
  public Result mrX(final String idJugador, final String idPersonaje, final String pos, final String target, final Request baseRequest, final HttpServletRequest request, final HttpServletResponse response) {
    Result _xblockexpression = null;
    {
      Integer _valueOf = Integer.valueOf(idJugador);
      Integer _valueOf_1 = Integer.valueOf(idPersonaje);
      final List<? extends Serializable> duelo = this.appModel.iniciarDueloConMrX(_valueOf, _valueOf_1, pos);
      response.setContentType(ContentType.APPLICATION_JSON);
      String _json = this._jSONUtils.toJson(duelo);
      _xblockexpression = ResultFactory.ok(_json);
    }
    return _xblockexpression;
  }
  
  public void handle(final String target, final Request baseRequest, final HttpServletRequest request, final HttpServletResponse response) throws IOException, ServletException {
    {
    	Matcher matcher = 
    		Pattern.compile("/posiciones").matcher(target);
    	if (request.getMethod().equalsIgnoreCase("Get") && matcher.matches()) {
    		// take parameters from request
    		
    		// take variables from url
    		
    		
    	    Result result = datos(target, baseRequest, request, response);
    	    result.process(response);
    	    
    		response.addHeader("Access-Control-Allow-Origin", "*");
    	    baseRequest.setHandled(true);
    	    return;
    	}
    }
    {
    	Matcher matcher = 
    		Pattern.compile("/personajes").matcher(target);
    	if (request.getMethod().equalsIgnoreCase("Get") && matcher.matches()) {
    		// take parameters from request
    		
    		// take variables from url
    		
    		
    	    Result result = personajes(target, baseRequest, request, response);
    	    result.process(response);
    	    
    		response.addHeader("Access-Control-Allow-Origin", "*");
    	    baseRequest.setHandled(true);
    	    return;
    	}
    }
    {
    	Matcher matcher = 
    		Pattern.compile("/descripcion_personaje/(\\w+)/(\\w+)").matcher(target);
    	if (request.getMethod().equalsIgnoreCase("Get") && matcher.matches()) {
    		// take parameters from request
    		
    		// take variables from url
    		String idJugador = matcher.group(1);
    		String idPersonaje = matcher.group(2);
    		
    		
    	    Result result = descripcion(idJugador, idPersonaje, target, baseRequest, request, response);
    	    result.process(response);
    	    
    		response.addHeader("Access-Control-Allow-Origin", "*");
    	    baseRequest.setHandled(true);
    	    return;
    	}
    }
    {
    	Matcher matcher = 
    		Pattern.compile("/nombre_personajes").matcher(target);
    	if (request.getMethod().equalsIgnoreCase("Get") && matcher.matches()) {
    		// take parameters from request
    		
    		// take variables from url
    		
    		
    	    Result result = nombresPersonajes(target, baseRequest, request, response);
    	    result.process(response);
    	    
    		response.addHeader("Access-Control-Allow-Origin", "*");
    	    baseRequest.setHandled(true);
    	    return;
    	}
    }
    {
    	Matcher matcher = 
    		Pattern.compile("/estadisticas/(\\w+)/(\\w+)").matcher(target);
    	if (request.getMethod().equalsIgnoreCase("Get") && matcher.matches()) {
    		// take parameters from request
    		
    		// take variables from url
    		String idJugador = matcher.group(1);
    		String idPersonaje = matcher.group(2);
    		
    		
    	    Result result = estadisticas(idJugador, idPersonaje, target, baseRequest, request, response);
    	    result.process(response);
    	    
    		response.addHeader("Access-Control-Allow-Origin", "*");
    	    baseRequest.setHandled(true);
    	    return;
    	}
    }
    {
    	Matcher matcher = 
    		Pattern.compile("/iniciarDuelo/(\\w+)/(\\w+)/(\\w+)").matcher(target);
    	if (request.getMethod().equalsIgnoreCase("Get") && matcher.matches()) {
    		// take parameters from request
    		
    		// take variables from url
    		String idJugador = matcher.group(1);
    		String idPersonaje = matcher.group(2);
    		String pos = matcher.group(3);
    		
    		
    	    Result result = iniciarDuelo(idJugador, idPersonaje, pos, target, baseRequest, request, response);
    	    result.process(response);
    	    
    		response.addHeader("Access-Control-Allow-Origin", "*");
    	    baseRequest.setHandled(true);
    	    return;
    	}
    }
    {
    	Matcher matcher = 
    		Pattern.compile("/buscarAMrX/(\\w+)/(\\w+)/(\\w+)").matcher(target);
    	if (request.getMethod().equalsIgnoreCase("Get") && matcher.matches()) {
    		// take parameters from request
    		
    		// take variables from url
    		String idJugador = matcher.group(1);
    		String idPersonaje = matcher.group(2);
    		String pos = matcher.group(3);
    		
    		
    	    Result result = mrX(idJugador, idPersonaje, pos, target, baseRequest, request, response);
    	    result.process(response);
    	    
    		response.addHeader("Access-Control-Allow-Origin", "*");
    	    baseRequest.setHandled(true);
    	    return;
    	}
    }
    this.pageNotFound(baseRequest, request, response);
  }
  
  public void pageNotFound(final Request baseRequest, final HttpServletRequest request, final HttpServletResponse response) throws IOException, ServletException {
    response.getWriter().write(
    	"<html><head><title>XtRest - Page Not Found!</title></head>" 
    	+ "<body>"
    	+ "	<h1>Page Not Found !</h1>"
    	+ "	Supported resources:"
    	+ "	<table>"
    	+ "		<thead><tr><th>Verb</th><th>URL</th><th>Parameters</th></tr></thead>"
    	+ "		<tbody>"
    	+ "			<tr>"
    	+ "				<td>GET</td>"
    	+ "				<td>/posiciones</td>"
    	+ "				<td></td>"
    	+ "			</tr>"
    	+ "			<tr>"
    	+ "				<td>GET</td>"
    	+ "				<td>/personajes</td>"
    	+ "				<td></td>"
    	+ "			</tr>"
    	+ "			<tr>"
    	+ "				<td>GET</td>"
    	+ "				<td>/descripcion_personaje/:idJugador/:idPersonaje</td>"
    	+ "				<td>idJugador, idPersonaje</td>"
    	+ "			</tr>"
    	+ "			<tr>"
    	+ "				<td>GET</td>"
    	+ "				<td>/nombre_personajes</td>"
    	+ "				<td></td>"
    	+ "			</tr>"
    	+ "			<tr>"
    	+ "				<td>GET</td>"
    	+ "				<td>/estadisticas/:idJugador/:idPersonaje</td>"
    	+ "				<td>idJugador, idPersonaje</td>"
    	+ "			</tr>"
    	+ "			<tr>"
    	+ "				<td>GET</td>"
    	+ "				<td>/iniciarDuelo/:idJugador/:idPersonaje/:pos</td>"
    	+ "				<td>idJugador, idPersonaje, pos</td>"
    	+ "			</tr>"
    	+ "			<tr>"
    	+ "				<td>GET</td>"
    	+ "				<td>/buscarAMrX/:idJugador/:idPersonaje/:pos</td>"
    	+ "				<td>idJugador, idPersonaje, pos</td>"
    	+ "			</tr>"
    	+ "		</tbody>"
    	+ "	</table>"
    	+ "</body>"
    );
    response.setStatus(404);
    baseRequest.setHandled(true);
  }
}
