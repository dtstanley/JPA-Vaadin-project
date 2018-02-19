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
	public class userEditor extends VerticalLayout {
		private static final long serialVersionUID =1L;

		private UserRepo repository;

		/**
		 * The currently edited user
		 */
		private User user;

		/* Fields to edit properties in User entity */
		TextField user_first_name = new TextField("First name");
		TextField user_last_name = new TextField("Last name");
		TextField user_email = new TextField("email");

		/* Action buttons */
		Button save = new Button("Save", FontAwesome.SAVE);
		Button cancel = new Button("Cancel");
		Button delete = new Button("Delete", FontAwesome.TRASH_O);
		CssLayout actions = new CssLayout(save, cancel, delete);

		Binder<User> binder = new Binder<>(User.class);

		@Autowired
		public void UserEditor(UserRepo repository) {
			this.repository = repository;

			addComponents(user_first_name, user_last_name, user_email);

			// bind using naming convention
			binder.bindInstanceFields(this);

			// Configure and style components
			setSpacing(true);
			actions.setStyleName(ValoTheme.LAYOUT_COMPONENT_GROUP);
			save.setStyleName(ValoTheme.BUTTON_PRIMARY);
			save.setClickShortcut(ShortcutAction.KeyCode.ENTER);

			// wire action buttons to save, delete and reset
			save.addClickListener(e -> repository.save(user));
			delete.addClickListener(e -> repository.delete(user));
			cancel.addClickListener(e -> editCustomer(user));
			setVisible(false);
		}

		public interface ChangeHandler {

			void onChange();
		}

		public final void editCustomer(User u) {
			if (u == null) {
				setVisible(false);
				return;
			}
			final boolean persisted = u.getIduser() != 0;
			if (persisted) {
				// Find fresh entity for editing
				user = repository.findOne(getId());
				//((long)u.getIduser());
				//user = repository.findAll(iduser);
			}
			else {
				user = u;
			}
			cancel.setVisible(persisted);

			// Bind customer properties to similarly named fields
			// Could also use annotation or "manual binding" or programmatically
			// moving values from fields to entities before saving
			binder.setBean(user);

			setVisible(true);

			// A hack to ensure the whole form is visible
			save.focus();
			// Select all text in firstName field automatically
			user_first_name.selectAll();
		}

		public void setChangeHandler(ChangeHandler h) {
			// ChangeHandler is notified when either save or delete
			// is clicked
			save.addClickListener(e -> h.onChange());
			delete.addClickListener(e -> h.onChange());
		}

	}
