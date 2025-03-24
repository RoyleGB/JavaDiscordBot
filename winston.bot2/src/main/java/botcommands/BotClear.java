package botcommands;

import java.awt.Color;
import java.util.List;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class BotClear extends ListenerAdapter {
    private final String prefix = "!";
    private String test = "Hello World";

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        String[] args = event.getMessage().getContentRaw().split("\\s+"); // Split message into words

        if (args[0].equalsIgnoreCase(prefix + "clear")) {
            if (args.length < 2) { // No number provided
                EmbedBuilder usage = new EmbedBuilder()
                        .setColor(Color.RED)
                        .setTitle("Specify Amount to Delete!")
                        .setDescription("Usage: `" + prefix + "clear [# Of Messages]`");
                event.getChannel().sendMessageEmbeds(usage.build()).queue();
                return;
            }

            try {
                int amount = Integer.parseInt(args[1]); // Parse number from command

                if (amount < 1 || amount > 100) { // Discord API allows only 1-100 messages
                    EmbedBuilder error = new EmbedBuilder()
                            .setColor(Color.RED)
                            .setTitle("Invalid Number of Messages!")
                            .setDescription("You can only delete between **1 and 100** messages.");
                    event.getChannel().sendMessageEmbeds(error.build()).queue();
                    return;
                }

                // Retrieve and delete messages
                List<Message> messages = event.getChannel().getHistory().retrievePast(amount).complete();
                event.getChannel().purgeMessages(messages);

                // Success Embed
                EmbedBuilder success = new EmbedBuilder()
                        .setColor(Color.GREEN)
                        .setTitle("🧹 Cleared " + amount + " Messages!");
                event.getChannel().sendMessageEmbeds(success.build()).queue();

            } catch (NumberFormatException e) {
                // If user enters non-numeric value
                EmbedBuilder error = new EmbedBuilder()
                        .setColor(Color.RED)
                        .setTitle("Invalid Number!")
                        .setDescription("Please enter a valid **number** between **1 and 100**.");
                event.getChannel().sendMessageEmbeds(error.build()).queue();
            }
        }
    }
}
