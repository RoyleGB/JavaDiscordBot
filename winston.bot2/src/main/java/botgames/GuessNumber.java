package botgames;

import java.util.Random;

import org.jetbrains.annotations.NotNull;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class GuessNumber extends ListenerAdapter {

	public String prefix = "!";
	private static int randomNumber;
	private boolean gameStarted;
	public int numOfTries = 0;

	@Override
	public void onMessageReceived(@NotNull MessageReceivedEvent event) {
		// Ignore BOT messages to prevent loops
		if (event.getAuthor().isBot())
			return;

		if (event.getMessage().getContentRaw().equalsIgnoreCase(prefix + "gn")) {
			gameStarted = true;
			randomNumber = new Random().nextInt(100);
			event.getChannel().sendMessage(event.getAuthor().getAsMention()
					+ " started a new game! Guess the number between 1 and 100" + " (!quit - end game)").queue();
		}
		if (gameStarted) {
			try {
				if (event.getMessage().getContentRaw().equalsIgnoreCase(prefix + "quit")) {
					gameStarted = false;
					event.getChannel().sendMessage("The game has ended").queue();
					return;
				}
				int guess = Integer.parseInt(event.getMessage().getContentRaw());
				if (guess == randomNumber) {
					event.getChannel().sendMessage("You Are Epic, the number was: " + randomNumber).queue();
					event.getChannel().sendMessage("You got the number in: " + numOfTries + " tries!").queue();
					gameStarted = false;
					numOfTries = 0;
				} else if (guess < randomNumber) {
					event.getChannel().sendMessage("🔻 Too low! Try Again").queue();
					numOfTries++;
				} else if (guess > randomNumber) {
					event.getChannel().sendMessage("🔺 Too high! Try Again").queue();
					numOfTries++;
				}

			} catch (NumberFormatException e) {
				event.getChannel().sendMessage("Please enter a **Valid Number**!").queue();
			}
		}
	}

}
