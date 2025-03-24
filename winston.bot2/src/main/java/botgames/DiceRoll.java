package botgames;

import java.awt.Color;
import java.util.Random;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class DiceRoll extends ListenerAdapter {

	public String prefix = "!";
	private final Random rand = new Random(); // One Random instance for efficiency

	@Override
	public void onMessageReceived(MessageReceivedEvent event) {
		Message message = event.getMessage();
		String content = message.getContentRaw();

		if (content.equalsIgnoreCase(prefix + "roll")) {
			int roll = rand.nextInt(6) + 1; // Ensure range is 1-6
			EmbedBuilder dice = new EmbedBuilder()
                    .setColor(Color.BLUE)
                    .setTitle("🎲" + roll);
            event.getChannel().sendMessageEmbeds(dice.build()).queue();
		}

		if (content.equalsIgnoreCase(prefix + "dblroll")) {
			int dice1 = rand.nextInt(6) + 1;
			int dice2 = rand.nextInt(6) + 1;
			
			EmbedBuilder die = new EmbedBuilder()
                    .setColor(Color.RED)
                    .setTitle("🎲" + dice1 + " " + " 🎲" + dice2);
            event.getChannel().sendMessageEmbeds(die.build()).queue();
		}
	}
}
