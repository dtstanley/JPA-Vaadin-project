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
	public class Activity_LevelEditor extends VerticalLayout {
		private static final long serialVersionUID =1L;

		private Activity_LevelRepo repository;

		/**
		 * The currently edited Activity_Level
		 */
		private Activity_Level activity_level;

//		 Fields to edit properties in User entity 
//		TextField idactivity_level = new TextField("Activity_level ID");
		TextField beginner = new TextField("beginner");
		TextField intermediate = new TextField("intermediate");
		TextField advanced = new TextField("advanced");
		TextField pro = new TextField("pro");
		
//		 Action buttons 
		Button save = new Button("Save", FontAwesome.SAVE);
		Button cancel = new Button("Cancel");
		Button delete = new Button("Delete", FontAwesome.TRASH_O);
		CssLayout actions = new CssLayout(save, cancel, delete);

		Binder<Activity_Level> binder = new Binder<>(Activity_Level.class);

		@Autowired
		public void Activity_LevelEditor(Activity_LevelRepo repository) {
			this.repository = repository;

			addComponents(beginner, intermediate, advanced, pro);
			addComponents(save, cancel, delete);

			// bind using naming convention
			binder.bindInstanceFields(this);

			// Configure and style components
			setSpacing(true);
			actions.setStyleName(ValoTheme.LAYOUT_COMPONENT_GROUP);
			save.setStyleName(ValoTheme.BUTTON_PRIMARY);
			save.setClickShortcut(ShortcutAction.KeyCode.ENTER);

			// wire action buttons to save, delete and reset
//ISSUE?			save.addClickListener(e -> repository.save(activity_level));
//ISSUE?			delete.addClickListener(e -> repository.delete(activity_level));
			cancel.addClickListener(e -> editActivity_Level(activity_level));
			setVisible(false);
		}

		public interface ChangeHandler {

			void onChange();
		}

		public final void editActivity_Level(Activity_Level al) {
			if (al == null) {
				setVisible(false);
				return;
			}
			final boolean persisted = al.getIdactivity_level() != 0;
			if (persisted) {
				// Find fresh entity for editing
				Activity activity_level = repository.findOne(getId());
				//al.getIdactivity_level());
				//activity_level = repository.findAll(activity_level);
			}
			else {
				activity_level = al;
			}
			cancel.setVisible(persisted);

			// Bind customer properties to similarly named fields
			// Could also use annotation or "manual binding" or programmatically
			// moving values from fields to entities before saving
			binder.setBean(activity_level);

			setVisible(true);

			// A hack to ensure the whole form is visible
			save.focus();
			// Select all text in firstName field automatically
			beginner.selectAll();							//<<<REVIEW
		}

		public void setChangeHandler(ChangeHandler h) {
			// ChangeHandler is notified when either save or delete
			// is clicked
			save.addClickListener(e -> h.onChange());
			delete.addClickListener(e -> h.onChange());
		}

/*		public void editActivity_Level(Activity al) {
			// TODO Auto-generated method stub
			System.out.println("Don't know why this method was forced by system. - DT");
		}
*/

	}

