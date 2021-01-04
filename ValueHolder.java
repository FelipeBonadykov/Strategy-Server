package strategySE;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateful;

import org.json.simple.JSONObject;

@Stateful
@EJB(name = "ValueHolder", beanInterface = ValueHolder.class)
@LocalBean
public class ValueHolder implements Serializable {
	private static final long serialVersionUID = 1L;
	public ValueHolder() {
		
	}
	
	private String map = "none";
	private String firstPlayer = "none";
	private String secondPlayer = "none";
	private String turn = "none";
	
	@SuppressWarnings("unchecked")
	public String generateJson() {
		JSONObject jObj = new JSONObject();
        jObj.put("map", map);
        jObj.put("first", firstPlayer);
        jObj.put("second", secondPlayer);
        jObj.put("turn", turn);
		return jObj.toString();		
	}
	
	public void setValues(Object map, Object firstPlayer, Object secondPlayer, Object turn) {
		if (map != null) 
			this.map = (String) map;			
        if (firstPlayer != null) 
        	this.firstPlayer = (String) firstPlayer;            
        if (secondPlayer != null) 
        	this.secondPlayer = (String) secondPlayer;            
        if (turn != null) 
        	this.turn = (String) turn;
		
	}

}
