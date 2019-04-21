package schiffespiel.application.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

public interface GameObject {
	
	public void init();
	
	public void delete();
	
	public void update();
	
}
