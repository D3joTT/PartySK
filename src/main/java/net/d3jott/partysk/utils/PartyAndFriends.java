package net.d3jott.partysk.utils;

import de.simonsator.partyandfriends.spigot.api.pafplayers.PAFPlayer;
import de.simonsator.partyandfriends.spigot.api.pafplayers.PAFPlayerManager;
import de.simonsator.partyandfriends.spigot.api.party.PartyManager;
import de.simonsator.partyandfriends.spigot.api.party.PlayerParty;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class PartyAndFriends {

    private final PartyManager pafAPI;
    private final PAFPlayerManager pafPlayerAPI;

    public PartyAndFriends(PartyManager pafAPI, PAFPlayerManager pafPlayerAPI) {

        this.pafAPI = pafAPI;
        this.pafPlayerAPI = pafPlayerAPI;
    }

    public PartyAndFriends() {
        this(PartyManager.getInstance(), PAFPlayerManager.getInstance());
    }

    private PAFPlayer getPAFPlayer(Player player) {

        return pafPlayerAPI.getPlayer(player.getUniqueId());
    }

    private PlayerParty getPlayerParty(Player player) {

        PAFPlayer pafPlayer = getPAFPlayer(player);

        return pafPlayer == null ? null : pafAPI.getParty(pafPlayer);
    }

    public int partySize(Player player) {

        PlayerParty party = getPlayerParty(player);

        if (party == null)
            return 0;

        return party.getAllPlayers().size();
    }

    public List<String> getMembers(Player player) {

        List<String> members = new ArrayList<>();
        PlayerParty party = getPlayerParty(player);

        if (party == null)
            return members;

        for (PAFPlayer pafPlayer : party.getPlayers()) {

            Player p = Bukkit.getPlayer(pafPlayer.getUniqueId());

            if (p != null)
                members.add(p.getName());
        }

        return members;
    }

    public boolean hasParty(Player player) {

        return getPlayerParty(player) != null;
    }

    public boolean isLeader(Player pl) {

        PlayerParty party = getPlayerParty(pl);

        if (party == null)
            return false;

        PAFPlayer partyOwner = party.getLeader();

        return pl.getUniqueId().equals(partyOwner.getUniqueId());
    }
}
