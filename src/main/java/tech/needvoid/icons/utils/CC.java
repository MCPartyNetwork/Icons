package tech.needvoid.icons.utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class CC {

    /**
     * @param text the text to colorize
     * @return A coloured string
     */
    public static String colour(String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }

    private static final Pattern HEX_PATTERN = Pattern.compile("#(?:[A-Fa-f0-9]{6}|[A-Fa-f0-9]{3})");

    /**
     * @param list The array to translate
     * @return A translated array
     */
    public static List<String> colour (List<String> list) {
        List<String> translated = new ArrayList<>();
        for (String string : list) {
            translated.add(colour(string));
        }
        return translated;
    }

    /**
     * @param text The text to output
     */
    public static void log(String text) {
        Bukkit.getConsoleSender().sendMessage(colour("[Icons] " + text));
    }


}
