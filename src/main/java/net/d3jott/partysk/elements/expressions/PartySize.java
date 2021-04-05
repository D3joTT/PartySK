package net.d3jott.partysk.elements.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import net.d3jott.partysk.utils.PartyAndFriends;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

public class PartySize extends SimpleExpression<Integer> {

    static {
        Skript.registerExpression(PartySize.class, Integer.class, ExpressionType.SIMPLE,"%player%[s] party size");
    }

    PartyAndFriends paf = new PartyAndFriends();

    private Expression<Player> player;

    @Override
    public Class<? extends Integer> getReturnType() {

        return Integer.class;
    }

    @Override
    public boolean isSingle() {

        return true;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] expressions, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parser) {
        this.player = (Expression<Player>) expressions[0];
        return true;
    }

    @Override
    public String toString(@Nullable Event event, boolean debug) {
        return player.toString(event, debug) + " party size";
    }

    @Override
    protected Integer[] get(Event event) {

        Player pl = player.getSingle(event);

        return new Integer[]{paf.partySize(pl)};
    }
}
