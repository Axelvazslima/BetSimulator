import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Responsible for a team's information.
 */
public class Team {

    /**
     * Id that represents the team.
     */
    private final String id;

    /**
     * Team's name.
     */
    private final String name;

    /**
     * Team's name.
     */
    private final String mascot;

    /**
     * Set that stores the tournaments the team is registered in.
     */
    private Set<Tournament> tournaments;

    /**
     * Counts how many times the team was betted by the users as the bet of the tournament.
     */
    private int timesInFirst;

    /**
     * Builds a team based on it's name, id and mascot.
     * @param id Textual id.
     * @param name Team's name.
     * @param mascot Team's mascot.
     */
    public Team(String id, String name, String mascot){
            this.id = id;
            this.name = name;
            this.mascot = mascot;
            this.tournaments = new HashSet<>();
            this.timesInFirst = 0;
    }

    /**
     * @return Set containing the tournaments the team is playing.
     */
    public Set<Tournament> getTournaments() {
        return this.tournaments;
    }

    /**
     * Get the team's id.
     * @return Team's id.
     */
    public String getId() {
        return this.id;
    }

    /**
     * Get the team's mascot.
     * @return Team's mascot.
     */
    public String getMascot() {
        return this.mascot;
    }

    /**
     * Signs a team in a tournament.
     * @param tournament Tournament that the team is going to be registered in.
     * @return Text telling the user if the registration was successful.
     */
    public String addTeamInTournament(Tournament tournament) {
        if (this.tournaments.contains(tournament)){
            return "THE REGISTRATION COULD NOT BE COMPLETED. THE TEAM WAS ALREADY IN THE TOURNAMENT!";
        } else {
            this.tournaments.add(tournament);
            return "TEAM SUCCESSFULLY REGISTERED IN THE TOURNAMENT!";
        }
    }

    /**
     * Verify objects' equality.
     * @param o Object that is going to be compared with this one.
     * @return True, if they are equal, false if they are not.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Team team = (Team) o;
        return Objects.equals(id, team.id);
    }

    /**
     * Integer code used by Java to make comparisons.
     * @return Object's integer code using hash.
     */
    @Override
    public int hashCode() {
        return Objects.hash(id.toUpperCase());
    }

    /**
     * Adds one to the variable that counts how many times the team was betted as the first place of a tournament.
     */
    public void upByOneTimesInFirst(){
        this.timesInFirst++;
    }

    /**
     * Get the times the team was betted as the best team in the tournament.
     * @return Integer with the amount of times the team was betted as the winner of it all.
     */
    public int getTimesInFirst() {
        return this.timesInFirst;
    }

    /**
     * @return Team's name.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Team's textual representation.
     * @return Team's textual representation.
     */
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        return sb.append("[").append(this.id).append("] ").append(this.name).append(" / ").append(this.mascot).toString();
    }
}
