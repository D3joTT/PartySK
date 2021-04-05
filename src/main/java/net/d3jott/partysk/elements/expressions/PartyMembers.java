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

public class PartyMembers extends SimpleExpression<String> {

    static {
        Skript.registerExpression(PartyMembers.class, String.class, ExpressionType.SIMPLE,"%player%[s] party members");
    }

    PartyAndFriends paf = new PartyAndFriends();

    private Expression<Player> player;

    @Override
    public Class<? extends String> getReturnType() {

        return String.class;
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
        return player.toString(event, debug) + " party members";
    }

    @Override
    protected String[] get(Event event) {

        Player pl = player.getSingle(event);

        return new String[] {String.valueOf(paf.getMembers(pl)).replace("[","").replace("]","")};
    }
}
