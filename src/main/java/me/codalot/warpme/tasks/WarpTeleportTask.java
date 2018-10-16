package me.codalot.warpme.tasks;

import me.codalot.warpme.Loader;
import me.codalot.warpme.setup.Message;
import me.codalot.warpme.warps.Warp;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.HashSet;
import java.util.Set;

public class WarpTeleportTask extends BukkitRunnable {

    public static Set<WarpTeleportTask> instances = new HashSet<>();
    private static Loader loader = Loader.getInstance();

    private Player player;
    private Warp warp;

    private Location location;
    private int time;

    private boolean teleported;
    private TeleportStage stage;

    private int preTimer;
    private int postTimer;

    public WarpTeleportTask(Player player, Warp warp) {
        this.player = player;
        this.warp = warp;

        location = player.getLocation();
        time = 0;

        teleported = false;
        stage = TeleportStage.PRE_TELEPORT;

        preTimer = 0;
        postTimer = 0;

        player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 100000, 254, true, false));
        player.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 100000, 1, true, false));
        player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 100000, 1, true, false));

        player.sendTitle("§eTeleporting...", "");

        instances.add(this);

        runTaskTimer(loader, 1, 1);
    }

    @Override
    public void run() {

        if (player == null || player.isDead() || !player.isOnline())
            cancel();

        switch (stage) {
            case PRE_TELEPORT:
                beforeTeleport();
                break;
            case TELEPORT:
                teleport();
                break;
            case POST_TELEPORT:
                afterTeleport();
                break;
        }

        time++;
    }

    private void beforeTeleport() {
        double x = Math.sin(preTimer * 0.25);
        double y = preTimer * 0.025;
        double z = Math.cos(preTimer * 0.25);

        Location location = this.location.clone().add(x, y, z);

        location.getWorld().spawnParticle(Particle.VILLAGER_HAPPY, location, 5);

        player.setVelocity(new Vector(0, 0.01, 0));
        player.setGravity(false);

        if (y > 2)
            stage = TeleportStage.TELEPORT;

        preTimer++;
    }

    private void afterTeleport() {
        double y = postTimer * 0.25;

        if (postTimer == 0)
            warp.getLocation().getWorld().spawnParticle(Particle.CLOUD, warp.getLocation().getX(), warp.getLocation().getY(), warp.getLocation().getZ(), 0, 0, 0, 150   , 0);


        for (double i = 0; i < Math.PI * 2; i += Math.PI / 12) {
            Location location = warp.getLocation().clone().add(Math.sin(i), y, Math.cos(i));
            location.getWorld().spawnParticle(Particle.FLAME, location.getX(), location.getY(), location.getZ(), 0, 0, 0, 0, 1);
        }

        if (y > 2)
            cancel();

        postTimer++;
    }

    private void teleport() {
        player.teleport(warp.getLocation());
        player.sendMessage(Message.WARP_TELEPORTED.getMessage().replace("%warp", warp.getName()));

        player.removePotionEffect(PotionEffectType.SLOW);
        player.removePotionEffect(PotionEffectType.CONFUSION);
        player.removePotionEffect(PotionEffectType.BLINDNESS);

        player.setVelocity(new Vector(0, 0, 0));
        player.setFallDistance(0);
        player.setGravity(true);

        teleported = true;
        stage = TeleportStage.POST_TELEPORT;

        player.sendTitle("§eTeleported to", warp.getDisplayName());
    }

    @Override
    public void cancel() {
        super.cancel();

        instances.remove(this);

        try {
            Loader.getInstance().getManager().getPlayerManager().getPlayer(player).removeTeleportTask();
        } catch (Exception ignored) {}
    }

    public enum TeleportStage {
        PRE_TELEPORT,
        TELEPORT,
        POST_TELEPORT;
    }
}
