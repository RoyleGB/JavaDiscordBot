package botgames;

import java.util.concurrent.ThreadLocalRandom;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class Meme extends ListenerAdapter {

	public String prefix = "!";

	public String randomize(String url) {
		ThreadLocalRandom random = ThreadLocalRandom.current();
		return url + "&" + random.nextInt() + "=" + random.nextInt();

	}

	public void onMessageReceived(MessageReceivedEvent event) {
		if (event.getMessage().getContentRaw().equalsIgnoreCase(prefix + "cat")) {

			EmbedBuilder result = new EmbedBuilder();
			result.setTitle("MEOW");
			result.setImage(randomize("http://thecatapi.com/api/images/get?format=src&type=png"));
			event.getChannel().sendMessageEmbeds(result.build()).queue();

		}
	}
}
