package botgames;

import java.awt.Color;
import java.util.Random;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class CoinFlip extends ListenerAdapter {

    public String prefix = "!";

    public void onMessageReceived(MessageReceivedEvent event) {
        Message message = event.getMessage();
        if (message.getContentRaw().equalsIgnoreCase(prefix + "flip")) {
        	
            Random rand = new Random();
            int flip = rand.nextInt(2);
            if (flip == 0) {
            	EmbedBuilder heads = new EmbedBuilder()
                        .setColor(Color.RED)
                        .setTitle("Heads!");
                event.getChannel().sendMessageEmbeds(heads.build()).queue();
            } else {
            	EmbedBuilder tails = new EmbedBuilder()
                        .setColor(Color.BLUE)
                        .setTitle("Tails!");
                event.getChannel().sendMessageEmbeds(tails.build()).queue();
            }
        }
    }
}
