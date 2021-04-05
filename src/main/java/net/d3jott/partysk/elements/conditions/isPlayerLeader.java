package net.d3jott.partysk.elements.conditions;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Condition;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import net.d3jott.partysk.utils.PartyAndFriends;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

public class isPlayerLeader extends Condition {

    static {
        Skript.registerCondition(isPlayerLeader.class, "%player% (1¦is|2¦is(n't| not)) [the] partyleader");
    }

    PartyAndFriends paf = new PartyAndFriends();

    Expression<Player> player;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] expressions, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parser) {

        this.player = (Expression<Player>) expressions[0];
        return true;
    }

    @Override
    public String toString(@Nullable Event event, boolean debug) {
        return "Player is leader " + player.toString(event, debug);
    }

    @Override
    public boolean check(Event event) {

        Player pl = player.getSingle(event);

        return paf.isLeader(pl);
    }
}
