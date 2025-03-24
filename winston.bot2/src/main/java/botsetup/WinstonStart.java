package botsetup;

import botcommands.BotClear;
import botcommands.BotMenu;
import botgames.CoinFlip;
import botgames.DiceRoll;
import botgames.GuessNumber;
import botgames.Meme;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;

public class WinstonStart {

	public static void main(String[] args) {
		
		final String BOT_TOKEN = "YOUR_BOT_TOKEN";
		

		try {
			 JDABuilder.createDefault(BOT_TOKEN, GatewayIntent.GUILD_MESSAGES, GatewayIntent.MESSAGE_CONTENT) // Replace with your BOT token
			 .addEventListeners(new WinstonOnline())
			 .addEventListeners(new BotMenu())
			 .addEventListeners(new GuessNumber())
			 .addEventListeners(new CoinFlip())
			 .addEventListeners(new DiceRoll())
			 .addEventListeners(new BotClear())
			 .addEventListeners(new Meme())
			 
             .build();

		} catch (IllegalArgumentException e) {
			System.err.println("Illegal Argument: " + e);
		}
	}
}
