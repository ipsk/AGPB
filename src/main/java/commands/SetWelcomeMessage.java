/*
 * MIT License
 *
 * Copyright (c) 2020 AshDev (Ashley Tonkin)
 *
 * Permission is hereby granted, free of charge, to any person obtaining a
 * copy of this software and associated documentation files (the "Software"),
 * to deal in the Software without restriction, including without limitation
 * the rights to use, copy, modify, merge, publish, distribute, sublicense,
 * and/or sell copies of the Software, and to permit persons to whom
 * the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
 * IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY
 * CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT,
 * TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE
 * SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import settings.BotSettings;
import settings.GuildProperties;

/**
 * The type Set welcome message.
 */
public class SetWelcomeMessage extends Command {

  private static final Logger log = LoggerFactory.getLogger(SetWelcomeMessage.class);

  // The bot settings object initialized in the Main class
  private final BotSettings botSettings;

  /**
   * Instantiates a new Set welcome message.
   */
  public SetWelcomeMessage(final BotSettings botSettings) {
    this.botSettings = botSettings;

    this.name = "setwelcome";
    this.help = "Set the welcome message new users will get";
    this.guildOnly = true;
    this.arguments = "[message]";
  }

  /**
   * Execute.
   *
   * @param event the event
   */
  @Override
  protected void execute(CommandEvent event) {
    // Getting the guild ID
    final Long guildId = event.getGuild().getIdLong();

    String msg = event.getArgs().trim();

    if (!msg.isEmpty()) {
      botSettings.getSettings(guildId).setProperty(GuildProperties.WELCOME_MESSAGE, msg);
      botSettings.getSettings(guildId).save();

      log.info("Welcome message has been set.");
    } else {
      log.error("Args where empty, no message");
    }
  }

}
