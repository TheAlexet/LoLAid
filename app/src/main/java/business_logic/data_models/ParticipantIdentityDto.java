package business_logic.data_models;

public class ParticipantIdentityDto
{
    private int participantId;
    private PlayerDto player;

    public ParticipantIdentityDto(){}

    public ParticipantIdentityDto(int participantId, PlayerDto player) {
        this.participantId = participantId;
        this.player = player;
    }

    public int getParticipantId() {
        return participantId;
    }

    public void setParticipantId(int participantId) {
        this.participantId = participantId;
    }

    public PlayerDto getPlayer() {
        return player;
    }

    public void setPlayer(PlayerDto player) {
        this.player = player;
    }
}
