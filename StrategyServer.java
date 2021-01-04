package strategySE;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

@WebServlet(urlPatterns = {"/strategy-se"})
public class StrategyServer extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Lock updateLock = new ReentrantLock(); // safe from concurrency problems
	
	static ValueHolder updater = new ValueHolder();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
	        // send json
			response.setContentType("application/json;charset=UTF-8");
	        response.getOutputStream().print(updater.generateJson());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		updateLock.lock();
		try {
			BufferedReader br = request.getReader();
			String str = br.readLine();
			JSONObject jsonObj = (JSONObject) new JSONParser().parse(str);
			       
            updater.setValues(jsonObj.get("map"), jsonObj.get("first"), jsonObj.get("second"), jsonObj.get("turn"));
            
	    } catch (Exception e) {
	    	e.printStackTrace();
	    } finally {
			updateLock.unlock();			
		}
	}

}
