package hello;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;

public class UserLayout extends HorizontalLayout{
	
	private final ComboBox done;
	private final TextField text;
	
	public UserLayout(User vUser){
		
		setSpacing(true);
		setDefaultComponentAlignment(Alignment.MIDDLE_LEFT);
		done = new ComboBox();
		text = new TextField();
		
		addComponents(done, text);
	}

}
