package com.rr.server.entity.mob.player.skill;

import com.rr.server.entity.mob.player.Player;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents a in-game skill.
 *
 * @author Christian Tucker
 */
public class Skill {

    /**
     * The player that owns this skill instance.
     */
    private final Player player;

    /**
     * The id of this skill.
     */
    private final int skillId;

    /**
     * The current level of this skill.
     */
    private int level;

    /**
     * The current experience the player has in this skill.
     */
    private double experience;

    /**
     * Creates a new skill instance with the provided id for the provided player.
     *
     * @param player    The player.
     * @param skillId   The id.
     */
    public Skill(Player player, int skillId) {
        this.player = player;
        this.skillId = skillId;
    }

    /**
     * Returns the current level of this skill.
     *
     * @return  The current level.
     */
    public int getLevel() {
        return level;
    }

    /**
     * Sets the current level of this skill.
     *
     * @param level The new level of this skill.
     * @return  The skill modified.
     */
    public Skill setLevel(int level) {
        this.level = level;
        return this;
    }

    /**
     * Returns the current experience of this skill.
     *
     * @return  The current experience.
     */
    public double getExperience() {
        return experience;
    }

    /**
     * Sets the current experience of this skill.
     *
     * @param experience    The new experience.
     * @return  The skill modified.
     */
    public Skill setExperience(double experience) {
        this.experience = experience;
        return this;
    }

    /**
     * Adds or removes(if the parameter is negative) experience to this skill.
     * Checks against the level table to see if you've gone up or down a level.
     *
     * @param experience    The experience to add/rmeove.
     * @return  The skill modified.
     */
    public Skill addExperience(double experience) {
        this.experience += experience;
        if(experience >= levelTable.get(level + 1)) {
            System.out.println("Congratulations, you have increased an " + getName(skillId) + " level.");
            level++;
        } else {
            if(experience <= levelTable.get(level)){
                for(int i = levelTable.values().size() - 1; i >= 0; i--) {
                    if(experience >= levelTable.get(i)) {
                        System.out.println("Due to experience loss your " + getName(skillId) + " level was set to " + i);
                        break;
                    }
                }
            }
        }
        return this;
    }

    /**
     * The leveling table, containing each level and the experience required to reach it.
     */
    private static Map<Integer, Integer> levelTable = new HashMap<>();

    /**
     * The skill table, containing each skill id, and the name of the skill for it.
     */
    private static Map<Integer, String> skillTable = new HashMap<>();

    /**
     * Returns the name of the skill based on the id provided from the {@code skillTable}.
     *
     * @param skillId   The skill id.
     * @return  The name of the skill.
     */
    public static String getName(int skillId) {
        if(skillId < 0 || skillId > 24) throw new IllegalArgumentException("Skill id must be between 0 and 24");
        return skillTable.get(skillId);
    }

    /**
     * Initializes the tables.
     */
    static {
        levelTable.put(1, 0);
        levelTable.put(2, 83);
        levelTable.put(3, 174);
        levelTable.put(4, 276);
        levelTable.put(5, 388);
        levelTable.put(6, 512);
        levelTable.put(7, 650);
        levelTable.put(8, 801);
        levelTable.put(9, 969);
        levelTable.put(10, 1154);
        levelTable.put(11, 1358);
        levelTable.put(12, 1584);
        levelTable.put(13, 1833);
        levelTable.put(14, 2107);
        levelTable.put(15, 2411);
        levelTable.put(16, 2746);
        levelTable.put(17, 3115);
        levelTable.put(18, 3523);
        levelTable.put(19, 3973);
        levelTable.put(20, 4470);
        levelTable.put(21, 5018);
        levelTable.put(22, 5624);
        levelTable.put(23, 6291);
        levelTable.put(24, 7028);
        levelTable.put(25, 7842);
        levelTable.put(26, 8740);
        levelTable.put(27, 9730);
        levelTable.put(28, 10824);
        levelTable.put(29, 12031);
        levelTable.put(30, 13363);
        levelTable.put(31, 14833);
        levelTable.put(32, 16456);
        levelTable.put(33, 18247);
        levelTable.put(34, 20224);
        levelTable.put(35, 22406);
        levelTable.put(36, 24815);
        levelTable.put(37, 27473);
        levelTable.put(38, 30408);
        levelTable.put(39, 33648);
        levelTable.put(40, 37224);
        levelTable.put(41, 41171);
        levelTable.put(42, 45529);
        levelTable.put(43, 50339);
        levelTable.put(44, 55649);
        levelTable.put(45, 61512);
        levelTable.put(46, 67983);
        levelTable.put(47, 75127);
        levelTable.put(48, 83014);
        levelTable.put(49, 91721);
        levelTable.put(50, 101333);
        levelTable.put(51, 111945);
        levelTable.put(52, 123660);
        levelTable.put(53, 136594);
        levelTable.put(54, 150872);
        levelTable.put(55, 166636);
        levelTable.put(56, 184040);
        levelTable.put(57, 203254);
        levelTable.put(58, 224466);
        levelTable.put(59, 247886);
        levelTable.put(60, 273742);
        levelTable.put(61, 302288);
        levelTable.put(62, 333804);
        levelTable.put(63, 368599);
        levelTable.put(64, 407015);
        levelTable.put(65, 449428);
        levelTable.put(66, 496254);
        levelTable.put(67, 547953);
        levelTable.put(68, 605032);
        levelTable.put(69, 668051);
        levelTable.put(70, 737627);
        levelTable.put(71, 814445);
        levelTable.put(72, 899257);
        levelTable.put(73, 992895);
        levelTable.put(74, 1096278);
        levelTable.put(75, 1210421);
        levelTable.put(76, 1336443);
        levelTable.put(77, 1475581);
        levelTable.put(78, 1629200);
        levelTable.put(79, 1798808);
        levelTable.put(80, 1986068);
        levelTable.put(81, 2192818);
        levelTable.put(82, 2421087);
        levelTable.put(83, 2673114);
        levelTable.put(84, 2951373);
        levelTable.put(85, 3258594);
        levelTable.put(86, 3597792);
        levelTable.put(87, 3972294);
        levelTable.put(88, 4385776);
        levelTable.put(89, 4842295);
        levelTable.put(90, 5346332);
        levelTable.put(91, 5902831);
        levelTable.put(92, 6517253);
        levelTable.put(93, 7195629);
        levelTable.put(94, 7944614);
        levelTable.put(95, 8771558);
        levelTable.put(96, 9684577);
        levelTable.put(97, 10692629);
        levelTable.put(98, 11805606);
        levelTable.put(99, 13034431);
        levelTable.put(100, 14391160);
        levelTable.put(101, 15889109);
        levelTable.put(102, 17542976);
        levelTable.put(103, 19368992);
        levelTable.put(104, 21385073);
        levelTable.put(105, 23611006);
        levelTable.put(106, 26068632);
        levelTable.put(107, 28782069);
        levelTable.put(108, 31777943);
        levelTable.put(109, 35085654);
        levelTable.put(110, 38737661);
        levelTable.put(111, 42769801);
        levelTable.put(112, 47221641);
        levelTable.put(113, 52136869);
        levelTable.put(114, 57563718);
        levelTable.put(115, 63555443);
        levelTable.put(116, 70170840);
        levelTable.put(117, 77474828);
        levelTable.put(118, 85539082);
        levelTable.put(119, 94442737);
        levelTable.put(120, 104273167);
    }
}
