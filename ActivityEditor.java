package hello;

	import org.springframework.beans.factory.annotation.Autowired;

	import com.vaadin.data.Binder;
	import com.vaadin.event.ShortcutAction;
	import com.vaadin.server.FontAwesome;
	import com.vaadin.spring.annotation.SpringComponent;
	import com.vaadin.spring.annotation.UIScope;
	import com.vaadin.ui.Button;
	import com.vaadin.ui.CssLayout;
	import com.vaadin.ui.TextField;
	import com.vaadin.ui.VerticalLayout;
	import com.vaadin.ui.themes.ValoTheme;

	/**
	 * A simple example to introduce building forms. As your real application is probably much
	 * more complicated than this example, you could re-use this form in multiple places. This
	 * example component is only used in VaadinUI.
	 * <p>
	 * In a real world application you'll most likely using a common super class for all your
	 * forms - less code, better UX. See e.g. AbstractForm in Viritin
	 * (https://vaadin.com/addon/viritin).
	 */
	@SpringComponent
	@UIScope
	public class ActivityEditor extends VerticalLayout {
		private static final long serialVersionUID =1L;

		private ActivityRepo repository;

		/**
		 * The currently edited activity
		 */
		private Activity activity;

		/* Fields to edit properties in User entity */
//		TextField idactivity = new TextField("Activity ID");
		TextField activity_date = new TextField("activity_date");
		TextField activity_location = new TextField("activity_location");
		TextField activity_theme = new TextField("activity_theme");
		TextField goal_history_idgoal = new TextField("goal_history_idgoal");
		TextField step = new TextField("step");
		TextField category_idcategory = new TextField("category_idcategory");
		
		/* Action buttons */
		Button save = new Button("Save", FontAwesome.SAVE);
		Button cancel = new Button("Cancel");
		Button delete = new Button("Delete", FontAwesome.TRASH_O);
		CssLayout actions = new CssLayout(save, cancel, delete);

		Binder<Activity> binder = new Binder<>(Activity.class);

		@Autowired
		public void ActivityEditor(ActivityRepo repository) {
			this.repository = repository;

			addComponents(activity_date, activity_location, activity_theme, goal_history_idgoal, step, category_idcategory);
			addComponents(save, cancel, delete);

			// bind using naming convention
			binder.bindInstanceFields(this);

			// Configure and style components
			setSpacing(true);
			actions.setStyleName(ValoTheme.LAYOUT_COMPONENT_GROUP);
			save.setStyleName(ValoTheme.BUTTON_PRIMARY);
			save.setClickShortcut(ShortcutAction.KeyCode.ENTER);

			// wire action buttons to save, delete and reset
			save.addClickListener(e -> repository.save(activity));
			delete.addClickListener(e -> repository.delete(activity));
			cancel.addClickListener(e -> editActivity(activity));
			setVisible(false);
		}

		public interface ChangeHandler {

			void onChange();
		}

		public final void editActivity(Activity a) {
			if (a == null) {
				setVisible(false);
				return;
			}
			final boolean persisted = a.getIdactivity() != 0;
			if (persisted) {
				// Find fresh entity for editing
				activity = repository.findOne(getId());
				//a.getIdactivity());
				//activity = repository.findAll(activity);
			}
			else {
				activity = a;
			}
			cancel.setVisible(persisted);

			// Bind customer properties to similarly named fields
			// Could also use annotation or "manual binding" or programmatically
			// moving values from fields to entities before saving
			binder.setBean(activity);

			setVisible(true);

			// A hack to ensure the whole form is visible
			save.focus();
			// Select all text in firstName field automatically
			activity_date.selectAll();							//<<<REVIEW
		}

		public void setChangeHandler(ChangeHandler h) {
			// ChangeHandler is notified when either save or delete
			// is clicked
			save.addClickListener(e -> h.onChange());
			delete.addClickListener(e -> h.onChange());
		}

	}
