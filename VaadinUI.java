package hello;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.vaadin.server.FontAwesome;
import com.vaadin.server.VaadinRequest;
import com.vaadin.shared.ui.ValueChangeMode;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

//@SuppressWarnings("deprecation")
@SpringUI
public class VaadinUI extends UI {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final UserRepo repo;
	private final ActivityRepo repoA;
	private final Activity_LevelRepo repoAl;

	private final userEditor editor;
	private final ActivityEditor editorA;
	private final Activity_LevelEditor editorAl;

	final Grid<User> grid;
	final Grid<Activity> gridA;
	final Grid<Activity> gridAl;

	final TextField filter;
	final TextField filterA;
	final TextField filterAl;
	
	private final Button addNewBtn;
	private final Button addNewBtnA;
	private final Button addNewBtnAl;

	@Autowired
	public VaadinUI(UserRepo repo, userEditor editor, ActivityRepo repoA, ActivityEditor editorA, Activity_LevelRepo repoAl, Activity_LevelEditor editorAl) {
		this.repo = repo;
		this.repoA = repoA;
		this.repoAl = repoAl;
		
		this.editor = editor;
		this.editorA = editorA;
		this.editorAl = editorAl;
		
		this.grid = new Grid<>(User.class);
		this.gridA = new Grid<>(Activity.class);
		this.gridAl = new Grid<>(Activity.class);
		
		this.filter = new TextField();
		this.filterA = new TextField();
		this.filterAl = new TextField();
		
		this.addNewBtn = new Button("New User", FontAwesome.PLUS);
		this.addNewBtnA = new Button("New Activity", FontAwesome.PLUS);
		this.addNewBtnAl = new Button("New Activity Level");
	}

	@Override
	protected void init(VaadinRequest request) {
		// build layout
		HorizontalLayout actions = new HorizontalLayout(filter, addNewBtn, filterA, addNewBtnA, filterAl, addNewBtnAl);
		VerticalLayout mainLayout = new VerticalLayout(actions, grid, editor, gridA, editorA, gridAl, editorAl);
		setContent(mainLayout);

		grid.setHeight(300, Unit.PIXELS);
		grid.setWidth(800, Unit.PIXELS);
		grid.setColumns("iduser", "user_first_name",  "user_last_name", "user_email");
		filter.setPlaceholder("Filter by user id");
		
		gridA.setHeight(300, Unit.PIXELS);
		gridA.setWidth(1000, Unit.PIXELS);
		gridA.setColumns("idactivity", "activity_date", "activity_location", "activity_theme", "goal_history_idgoal", "step", "category_idcategory");
		filterA.setPlaceholder("Filter by activity id");
		
		gridAl.setHeight(300, Unit.PIXELS);
		gridAl.setWidth(800, Unit.PIXELS);
//ISSUE?		gridAl.setColumns("idactivity_level", "beginner", "intermediate", "advanced", "pro");
		filterAl.setPlaceholder("Filter by activity_level id");
		
		// Hook logic to components

		// Replace listing with filtered content when user changes filter
		filter.setValueChangeMode(ValueChangeMode.LAZY);
		filter.addValueChangeListener(e -> listUser(e.getValue()));
		filterA.setValueChangeMode(ValueChangeMode.LAZY);
		filterA.addValueChangeListener(e -> listActivity(e.getValue()));
		filterAl.setValueChangeMode(ValueChangeMode.LAZY);
		filterAl.addValueChangeListener(e -> listActivity_level(e.getValue()));

		// Connect selected Customer to editor or hide if none is selected
		grid.asSingleSelect().addValueChangeListener(e -> {
			editor.editUser(e.getValue());
		});
		gridA.asSingleSelect().addValueChangeListener(e -> {
			editorA.editActivity(e.getValue());
		});
/*		gridAl.asSingleSelect().addValueChangeListener(e -> {
			editorAl.editActivity_Level(e.getValue());
		});
*/
		// Instantiate and edit new Customer the new button is clicked
		// addNewBtn.addClickListener(e -> editor.editUser(new User()));
		addNewBtn.addClickListener(e -> editor.editUser(new User("", "","", "")));
		addNewBtnA.addClickListener(e -> editorA.editActivity(new Activity("", "","", "", "", "", "")));
		addNewBtnAl.addClickListener(e -> editorAl.editActivity_Level(new Activity_Level("", "","", "", "")));
		
		// Listen changes made by the editor, refresh data from backend
		editor.setChangeHandler(() -> {
			editor.setVisible(false);
			listUser(filter.getValue());
		});
		editor.setChangeHandler(() -> {
			editorA.setVisible(false);
			listActivity(filterA.getValue());
		});
		
		editor.setChangeHandler(() -> {
			editorAl.setVisible(false);
			listActivity_level(filterAl.getValue());
		});

		// Initialize listing
		listUser(null);
		listActivity(null);
		listActivity_level(null);
	}

	// tag::listUser[]
	void listUser(String filterText) {
		if (StringUtils.isEmpty(filterText)) {
/*			grid.setItems(repo.findAll());
		}
		else {
			grid.setItems(repo.findByuser_last_name(filterText));
			//grid.setItems(repo.findByLastNameStartsWithIgnoreCase(filterText));
*/		

			ArrayList item = new ArrayList();
			for (User u : repo.findAll()){
				item.add(u);
			}
				grid.setItems(item);
		}
	}// end::listUser[]
	
	// tag::listActivity[]
	void listActivity(String filterText) {
		if (StringUtils.isEmpty(filterText)) {
/*			grid.setItems(repo.findAll());
		}
		else {
			grid.setItems(repo.findByuser_last_name(filterText));
			//grid.setItems(repo.findByLastNameStartsWithIgnoreCase(filterText));
*/		

/*			ArrayList itemA = new ArrayList();
			for (Activity a : repoA.findAll()){
				itemA.add(a);
			}
				gridA.setItems(itemA);*/
		}
	}
	// end::listActivity[]
	
	// tag::listActivity_level[]
	void listActivity_level(String filterText) {
		if (StringUtils.isEmpty(filterText)) {
/*			grid.setItems(repo.findAll());
		}
		else {
			grid.setItems(repo.findByuser_last_name(filterText));
			//grid.setItems(repo.findByLastNameStartsWithIgnoreCase(filterText));
*/		

/*			ArrayList itemAl = new ArrayList();
			for (listActivity_level al : repoA.findAll()){
				itemA.add(al);
			}
				gridA.setItems(itemAl);*/
		}
	}
	// end::listActivity_level[]

}
