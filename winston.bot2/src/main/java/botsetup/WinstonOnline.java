package botsetup;

import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.EventListener;

public class WinstonOnline implements EventListener {

	public void onEvent(GenericEvent event) {
		if (event instanceof ReadyEvent)
			System.out.println("Winston Online and Reporting! HAHA");
	}
}
