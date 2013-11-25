/**
 * 
 */
package Main;

/**
 * @author Matthew Glennon (mglennon@virginiafirst.org)
 * https://github.com/VirginiaFIRST/FTC-FieldMgmt
 */
public class FCSMsg {
	// Team Status Bytes
	public static final int TEAM_STATUS_OK						= 0x0000;
	public static final int TEAM_STATUS_WARNING					= 0x0001; // Yellow
	public static final int TEAM_STATUS_ERROR					= 0x0002; // Red
	public static final int TEAM_STATUS_DISABLED				= 0x0003; // Red also
	// Match Types
	public static final int MATCH_TYPE_PRACTICE					= 0x0000;
	public static final int MATCH_TYPE_QUALIFICATION			= 0x0001;
	public static final int MATCH_TYPE_QUARTERFINAL				= 0x0002;  // not currently used in FTC
	public static final int MATCH_TYPE_SEMI_FINAL				= 0x0003;
	public static final int MATCH_TYPE_FINAL					= 0x0004;
	// Match Configs
	public static final int MATCH_CONFIG_AUTONOMOUS_ENABLED		= 0x0001;
	public static final int MATCH_CONFIG_TELEOP_ENABLED			= 0x0002;
	public static final int MATCH_CONFIG_ENDGAME_ENABLED		= 0x0004;
	// Match States
	public static final int MATCH_STATE_AUTONOMOUS_WAITING		= 0x0000;
	public static final int MATCH_STATE_AUTONOMOUS_RUNNING		= 0x0001;
	public static final int MATCH_STATE_AUTONOMOUS_ENDED		= 0x0002;
	public static final int MATCH_STATE_TELEOP_WAITING			= 0x0010;
	public static final int MATCH_STATE_TELEOP_RUNNING			= 0x0011;
	public static final int MATCH_STATE_TELEOP_ENDED			= 0x0012;
	public static final int MATCH_STATE_ENDGAME_RUNNING			= 0x0020;  // Note that this can be set along with MATCH_STATE_TELEOP_RUNNING
	public static final int MATCH_STATE_ENDGAME_ENDED			= 0x0040;
	
	public int iMessageID;
	public int iKeyPart1;
	public int iKeyPart2;
	public int iDivisionID;
	public int iMatchType;
	public int iMatchConfig;
	public int iMatchState;
	public int iTimeRemaining;
	public int iMatchNumber;
	
	public Robot R1;
	public Robot R2;
	public Robot B1;
	public Robot B2;
	
	
	public FCSMsg(byte[] D){
		iMessageID		= SplitBytes(D, 0);
		iKeyPart1		= SplitBytes(D, 4);
		iKeyPart2		= SplitBytes(D, 8);
		iDivisionID		= SplitBytes(D, 12);
		iMatchType		= SplitBytes(D, 16);
		iMatchConfig	= SplitBytes(D, 20);
		iMatchState		= SplitBytes(D, 24);
		iTimeRemaining	= SplitBytes(D, 28);
		iMatchNumber	= SplitBytes(D, 32);
		
		R1	= new Robot(SplitBytes(D, 36), SplitBytes(D, 40));
		R2  = new Robot(SplitBytes(D, 44), SplitBytes(D, 48));
		B1  = new Robot(SplitBytes(D, 52), SplitBytes(D, 56));
		B2  = new Robot(SplitBytes(D, 60), SplitBytes(D, 64));
	}
	public static final int SplitBytes(byte[] b, int i){
		byte[] bb = {b[i],b[i+1],b[i+2],b[i+3]};
		int ret = java.nio.ByteBuffer.wrap(bb).getInt();
		return ret;
	}
	public String MatchType(){
		switch(iMatchType){
			case MATCH_TYPE_PRACTICE:
				return "Practice";
			case MATCH_TYPE_QUALIFICATION:
				return "Qualification";
			case MATCH_TYPE_QUARTERFINAL:
				return "Quarterfinal";
			case MATCH_TYPE_SEMI_FINAL:
				return "Semifinal";
			case MATCH_TYPE_FINAL:
				return "Final";
			default:
				return "Unknown?";
		}
	}
	public String MatchConfig(){
		switch(iMatchConfig){
			case MATCH_CONFIG_AUTONOMOUS_ENABLED:
				return "Autonomous";
			case MATCH_CONFIG_TELEOP_ENABLED:
				return "Teleoperated";
			case MATCH_CONFIG_ENDGAME_ENABLED:
				return "Endgame";
			default:
				return "Unknown?";
		}
	}
	public String MatchState(){
		switch(iMatchState){
			case MATCH_STATE_AUTONOMOUS_WAITING:	// 1
				return "Waiting for Start";
			case MATCH_STATE_AUTONOMOUS_RUNNING:	// 2
				return "Auto Running";
			case MATCH_STATE_AUTONOMOUS_ENDED:		// Not Sent
				return "Auto End";
			case MATCH_STATE_TELEOP_WAITING:		// 3
				return "Tele Waiting";
			case MATCH_STATE_TELEOP_RUNNING:		// 4
				return "Tele Running";
			case MATCH_STATE_ENDGAME_RUNNING:		// 5
				return "Endgame";
			case MATCH_STATE_TELEOP_ENDED:			// Not Sent
				return "Tele End";
			case MATCH_STATE_ENDGAME_ENDED:			// 6
				return "Game End";
			default:
				return "Unknown State = "+String.valueOf(iMatchState);
		}
	}
	public boolean SmoothMatch(int Last){
		int Next = -1;
		switch(Last){
			case MATCH_STATE_AUTONOMOUS_WAITING:
				Next = MATCH_STATE_AUTONOMOUS_RUNNING;
				break;
			case MATCH_STATE_AUTONOMOUS_RUNNING:
				Next = MATCH_STATE_TELEOP_WAITING;
				break;
			case MATCH_STATE_TELEOP_WAITING:
				Next = MATCH_STATE_TELEOP_RUNNING;
				break;
			case MATCH_STATE_TELEOP_RUNNING:
				Next = MATCH_STATE_ENDGAME_RUNNING;
				break;
			case MATCH_STATE_ENDGAME_RUNNING:
				Next = MATCH_STATE_ENDGAME_ENDED;
				break;
			case MATCH_STATE_ENDGAME_ENDED:
				Next = MATCH_STATE_AUTONOMOUS_WAITING;
				break;
			default:
				Next = -1;
				break;
		}
		if(iMatchState == Next) return true;
		else return false;
	}
	public String TimeReaminging(){
		//TODO: Finish
		return "";
	}
}